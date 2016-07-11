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
import br.com.etefgarcia.armarios.service.AlunoService;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import br.com.etefgarcia.armarios.view.CadastrarAlunoView;
import java.util.List;
import javax.swing.AbstractButton;

/**
 *
 * @author fernando-pucci
 */
public class AlunoController {

    private final CadastrarAlunoView cadastrarAlunoView;

    public AlunoController(CadastrarAlunoView cadastrarAlunoView) {

        this.cadastrarAlunoView = cadastrarAlunoView;

    }

    public Thread getThreadConfirmarCancelar() {

        return new Thread() {

            @Override
            public void run() {

                int opt = Mensagens.mostraMensagemPergunta("Tem certeza que fechar esta Janela?");
                if (opt == 0) {
                    cadastrarAlunoView.dispose();
                }
            }

        };
    }

    public Thread getThreadConsultarAluno() {

        return new Thread() {

            @Override
            public void run() {

                try {

                    consultarAlunos();

                } catch (SistemaException ex) {

                    Mensagens.mostraMensagemErro(cadastrarAlunoView.getPainel(), ex.getMessage());

                }

            }

        };
    }

    public Thread getThreadConsultarAlunoByNome() {

        return new Thread() {

            @Override
            public void run() {

                try {

                    consultarAlunos();

                } catch (SistemaException ex) {

                    Mensagens.mostraMensagemErro(cadastrarAlunoView.getPainel(), ex.getMessage());

                }

            }

        };
    }

    public Thread getThreadGetAlunoSelecionado() {

        return new Thread() {

            @Override
            public void run() {

                Aluno a = cadastrarAlunoView.getAlunoSelecionado();

                cadastrarAlunoView.setAluno(a);

                System.out.println(a);

            }

        };
    }

    public Thread getThreadCadastrarAluno() {

        return new Thread() {

            @Override
            public void run() {
                try {

                    Aluno a = cadastrarAlunoView.getAluno();

                    AlunoService.cadastrarAtualizarAlunoService(a.getIdAluno(), a.getNome(), a.getSexo() + "", a.getTelefone(), a.getEmail(), a.getFlgAtivo());

                    Mensagens.mostraMensagemSucesso(cadastrarAlunoView.getPainel(), a.getIdAluno() != null ? "Aluno Atualizado com sucesso." : "Aluno Cadastrado com sucesso.");

                    cadastrarAlunoView.limparCampos(true);

                } catch (NegocioException | SistemaException ex) {

                    Mensagens.mostraMensagemErro(cadastrarAlunoView.getPainel(), ex.getMessage());

                }

            }

        };

    }

    //METODOS PRIVADOS
    private void consultarAlunos() throws SistemaException {

        List<Aluno> listaAlunos = AlunoService.consultarAlunosByNomeService(cadastrarAlunoView.getNome(), true);

        if (listaAlunos == null || listaAlunos.isEmpty()) {
            Mensagens.mostraMensagemAlerta(cadastrarAlunoView.getPainel(), "Não há resultados para esta busca.");
            cadastrarAlunoView.limparCampos(false);

        } else {

            cadastrarAlunoView.setListaAlunos(listaAlunos);
            cadastrarAlunoView.mostrarTabelas(true);
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

            case ConstantesTelas.BTN_BUSCAR:
                getThreadConsultarAluno().start();
                break;

            case ConstantesTelas.BTN_SALVAR:
                getThreadCadastrarAluno().start();
                break;

            case ConstantesTelas.BTN_CANCELAR:
                getThreadConfirmarCancelar().start();
                break;

            case ConstantesTelas.BTN_LIMPAR:
                cadastrarAlunoView.limparCampos(true);
                break;

            case ConstantesTelas.ITM_TABELA:
                getThreadGetAlunoSelecionado().start();
                break;

        }

        //TEST:
        System.out.println(botao);

    }
}
