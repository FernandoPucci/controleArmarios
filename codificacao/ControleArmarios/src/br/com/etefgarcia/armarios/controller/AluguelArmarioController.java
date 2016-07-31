/*
 * Copyright (C) 2016 fernando-pucci
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.etefgarcia.armarios.controller;

import br.com.etefgarcia.armarios.exceptions.NegocioException;
import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.model.Armario;
import br.com.etefgarcia.armarios.service.AluguelArmarioService;
import br.com.etefgarcia.armarios.service.ArmarioService;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import br.com.etefgarcia.armarios.view.aluguel.armario.CadastrarAluguelArmarioView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;

/**
 *
 * @author fernando-pucci
 */
public class AluguelArmarioController {

    private CadastrarAluguelArmarioView cadastrarAluguelArmarioView = null;

    public AluguelArmarioController(CadastrarAluguelArmarioView cadastrarAluguelArmarioView) {

        this.cadastrarAluguelArmarioView = cadastrarAluguelArmarioView;

    }

    public Thread getThreadConfirmarCancelar() {

        return new Thread() {

            @Override
            public void run() {

                int opt = Mensagens.mostraMensagemPergunta("Tem certeza que fechar esta Janela?");
                if (opt == 0) {

                    cadastrarAluguelArmarioView.dispose();

                }
            }

        };
    }

    private Thread getThreadConfirmarRetiradaChave() {
        return new Thread() {

            @Override
            public void run() {

                Aluno aluno = cadastrarAluguelArmarioView.getAluno();
                Armario armario = cadastrarAluguelArmarioView.getArmario();

                int opt = Mensagens.mostraMensagemPergunta("Confirma retirada de Chave [ " + armario.getChave() + " ], pelo(a) Aluno(a) [ " + aluno.getNome() + " ] ?");

                if (opt == 0) {
                    try {

                        AluguelArmarioService.cadastrarAtualizarArmarioService(aluno, armario, cadastrarAluguelArmarioView.getUsuario());
                        Mensagens.mostraMensagemSucesso(cadastrarAluguelArmarioView.getPainel(), "Chave Retirada com Sucesso!");
                        cadastrarAluguelArmarioView.dispose();

                    } catch (NegocioException | SistemaException ex) {

                        Mensagens.mostraMensagemErro(cadastrarAluguelArmarioView.getPainel(), ex.getMessage());

                    }
                }
            }

        };
    }

    public Thread getThreadConsultarArmarioGeral() {

        return new Thread() {

            @Override
            public void run() {

                try {

                    //consulta armarios ativos
                    consultarArmariosAtivosPorChave();

                } catch (SistemaException | NegocioException ex) {

                    if (ex instanceof NegocioException) {

                        Mensagens.mostraMensagemAlerta(cadastrarAluguelArmarioView.getPainel(), ex.getMessage());

                    } else {

                        Mensagens.mostraMensagemErro(cadastrarAluguelArmarioView.getPainel(), ex.getMessage());

                    }

                }

            }

        };
    }

    public Thread getThreadGetArmarioSelecionado() {

        return new Thread() {

            @Override
            public void run() {

                Armario a = cadastrarAluguelArmarioView.getArmarioSelecionado();

                cadastrarAluguelArmarioView.setArmario(a);

                System.out.println(a);

            }

        };
    }

    //METODOS PRIVADOS
    //consulta aluguelArmario
    private void consultarArmariosAtivosPorChave() throws SistemaException, NegocioException {

        List<Armario> listaArmarios = null;

        if (cadastrarAluguelArmarioView.getChave() != null && !cadastrarAluguelArmarioView.getChave().isEmpty()) {

            listaArmarios = ArmarioService.consultarArmariosByChaveService(new Long(cadastrarAluguelArmarioView.getChave()), true);

        }

        if (listaArmarios == null || listaArmarios.isEmpty()) {
            Mensagens.mostraMensagemAlerta(cadastrarAluguelArmarioView.getPainel(), "Não há resultados para esta busca.");
            cadastrarAluguelArmarioView.limparCampos();

        } else if (listaArmarios.size() > 1) {

            Mensagens.mostraMensagemErro(cadastrarAluguelArmarioView.getPainel(), "Há mais de um registro para esta chave. Corrija este problema na rela de Cadastro de Armarios.");
            cadastrarAluguelArmarioView.limparCampos();

        } else if (listaArmarios.get(0).getFlgOcupado()) {

            Mensagens.mostraMensagemErro(cadastrarAluguelArmarioView.getPainel(), "Esta chave está ocupada. Verifique utilizando a tela de Empréstimos.");
            cadastrarAluguelArmarioView.limparCampos();

        } else {
            cadastrarAluguelArmarioView.setListaArmarios(listaArmarios);
            cadastrarAluguelArmarioView.setArmario(listaArmarios.get(0));
            cadastrarAluguelArmarioView.mostrarTabelas(true);

        }

    }

    //ACOES
    //trata ações dos botoes clicados
    public void acaoClickController(AbstractButton botao) {

        if (botao.isEnabled()) {
            acaoController(botao.getName());
        }
    }

    public void acaoClickController(javax.swing.JTable botao) {

        if (botao.isEnabled()) {
            acaoController(botao.getName());
        }
    }

    private void acaoController(String botao) {

        switch (botao) {

            case ConstantesTelas.BTN_BUSCAR_TELA_BUSCA:
                getThreadConsultarArmarioGeral().start();
                break;

            case ConstantesTelas.BTN_SALVAR:
                getThreadConfirmarRetiradaChave().start();
                break;

            case ConstantesTelas.BTN_CANCELAR:
                getThreadConfirmarCancelar().start();
                break;

            case ConstantesTelas.BTN_LIMPAR:

                if (cadastrarAluguelArmarioView != null) {
                    cadastrarAluguelArmarioView.limparCampos();
                }

                break;

            case ConstantesTelas.ITM_TABELA:
                getThreadGetArmarioSelecionado().start();
                break;

        }

        //TEST:
        System.out.println("@@@ " + botao);

    }

}
