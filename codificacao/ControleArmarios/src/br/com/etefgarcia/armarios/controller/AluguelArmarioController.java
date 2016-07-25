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
import br.com.etefgarcia.armarios.model.AluguelArmario;
import br.com.etefgarcia.armarios.model.Armario;
import br.com.etefgarcia.armarios.service.ArmarioService;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import br.com.etefgarcia.armarios.view.aluguel.armario.CadastrarAluguelArmarioView;



import java.util.ArrayList;
import java.util.List;
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

    public Thread getThreadConfirmarCancelarBusca() {

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

    public Thread getThreadConsultarArmario() {

        return new Thread() {

            @Override
            public void run() {

                try {

                    consultarArmariosPorChave();

                } catch (SistemaException ex) {

                    Mensagens.mostraMensagemErro(cadastrarAluguelArmarioView.getPainel(), ex.getMessage());

                }

            }

        };
    }

    public Thread getThreadConsultarArmarioGeral() {

        return new Thread() {

            @Override
            public void run() {

                try {

                    Boolean filtroGeral = null;

                    if (cadastrarAluguelArmarioView.getFiltroAtivosSelecionado() != null) {
                        switch (cadastrarAluguelArmarioView.getFiltroAtivosSelecionado()) {

                            case ConstantesTelas.BTN_FILTRO_TODOS:
                                filtroGeral = null;
                                break;
                            case ConstantesTelas.BTN_FILTRO_ATIVOS:
                                filtroGeral = Boolean.TRUE;
                                break;
                            case ConstantesTelas.BTN_FILTRO_INATIVOS:
                                filtroGeral = Boolean.FALSE;
                                break;

                        }
                    }

                    if (cadastrarAluguelArmarioView.getFiltroArmariosLivresSelecionado() != null) {
                        switch (cadastrarAluguelArmarioView.getFiltroArmariosLivresSelecionado()) {

                            case ConstantesTelas.BTN_FILTRO_ARMARIOS_TODOS:
                                consultarArmariosGeral(null, filtroGeral);
                                break;
                            case ConstantesTelas.BTN_FILTRO_ARMARIOS_OCUPADOS:
                                consultarArmariosGeral(Boolean.TRUE, filtroGeral);
                                break;
                            case ConstantesTelas.BTN_FILTRO_ARMARIOS_LIVRES:
                                consultarArmariosGeral(Boolean.FALSE, filtroGeral);
                                break;

                        }
                    } else {
                        consultarArmariosGeral(filtroGeral, null);

                    }

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

    public Thread getThreadGetArmarioSelecionadoConsultar() {

        return new Thread() {

            @Override
            public void run() {

//                AluguelArmario a = cadastrarAluguelArmarioView.getArmarioSelecionado();
//
//                System.out.println(a);

            }

        };
    }

    public Thread getThreadCadastrarArmario() {

        return new Thread() {

            @Override
            public void run() {
//                try {
//
//                    AluguelArmario a = cadastrarAluguelArmarioView.getArmario();
//
//                    ArmarioService.cadastrarAtualizarArmarioService(a.getIdArmario(), a.getChave() + "", a.getDescricao(), a.getFlgOcupado(), a.getFlgAtivo());
//
//                    Mensagens.mostraMensagemSucesso(cadastrarAluguelArmarioView.getPainel(), a.getIdArmario() != null ? "AluguelArmario Atualizado com sucesso." : "AluguelArmario Cadastrado com sucesso.");
//
//                    cadastrarAluguelArmarioView.limparCampos(true);
//
//                } catch (NegocioException | SistemaException ex) {
//
//                    Mensagens.mostraMensagemErro(cadastrarAluguelArmarioView.getPainel(), ex.getMessage());
//
//                }
//
            }

        };

    }

    public Thread getThreadShowTelaCadastrarAtualizar() {

        return new Thread() {

            @Override
            public void run() {
//
//                AluguelArmario a = cadastrarAluguelArmarioView.getArmarioSelecionado();
//
//                new CadastrarAluguelArmarioView(a, true).setVisible(true);

            }

        };

    }

    //METODOS PRIVADOS
    private void consultarArmariosPorChave() throws SistemaException {

//        List<AluguelArmario> listaArmarios = ArmarioService.consultarArmariosByChaveService(new Long(cadastrarAluguelArmarioView.getChave()), true);
//
//        if (listaArmarios == null || listaArmarios.isEmpty() && !cadastrarAluguelArmarioView.isTesteChaveExistente()) {
//            Mensagens.mostraMensagemAlerta(cadastrarAluguelArmarioView.getPainel(), "Não há resultados para esta busca.");
//            cadastrarAluguelArmarioView.limparCampos(false);
//
//        } else {
//
//            if (cadastrarAluguelArmarioView.isTesteChaveExistente()) {
//
//                Mensagens.mostraMensagemErro(cadastrarAluguelArmarioView.getPainel(), "Esta chave já esta cadastrada.");
//
//            }
//
//            cadastrarAluguelArmarioView.setListaArmarios(listaArmarios);
//            cadastrarAluguelArmarioView.mostrarTabelas(true);
//        }
    }

    //consulta aluguelArmario generica
    private void consultarArmariosGeral(Boolean ocupados, Boolean ativos) throws SistemaException, NegocioException {

        List<AluguelArmario> listaArmarios = null;

//        if (cadastrarAluguelArmarioView.getCodigo() != null && !cadastrarAluguelArmarioView.getCodigo().isEmpty()) {
//
//            AluguelArmario a = ArmarioService.consultarArmariosByCodigoService(cadastrarAluguelArmarioView.getCodigo());
//
//            if (a != null) {
//                listaArmarios = new ArrayList<>();
//                listaArmarios.add(a);
//            }
//
//        } else if (cadastrarAluguelArmarioView.getChave() != null && !cadastrarAluguelArmarioView.getChave().isEmpty()) {
//
//            listaArmarios = ArmarioService.consultarArmariosByChaveService(new Long(cadastrarAluguelArmarioView.getChave()), ativos);
//
//        } else if (ativos == null && (cadastrarAluguelArmarioView.getChave() == null || cadastrarAluguelArmarioView.getChave().isEmpty())) {
//
//            listaArmarios = ArmarioService.consultarTodosArmariosService();
//
//        } else {
//
//            listaArmarios = ArmarioService.consultarArmariosService(ocupados, ativos);
//
//        }

        if (listaArmarios == null || listaArmarios.isEmpty()) {
            Mensagens.mostraMensagemAlerta(cadastrarAluguelArmarioView.getPainel(), "Não há resultados para esta busca.");
            cadastrarAluguelArmarioView.limparCampos();

        } else {

//            cadastrarAluguelArmarioView.setListaArmarios(listaArmarios);
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

    public void acaoClickController(javax.swing.JRadioButton botao) {

        if (botao.isEnabled()) {
            acaoController(botao.getName());
        }
    }

    private void acaoController(String botao) {

        switch (botao) {

            case ConstantesTelas.BTN_BUSCAR:
                getThreadConsultarArmario().start();
                break;

            case ConstantesTelas.BTN_BUSCAR_TELA_BUSCA:
                getThreadConsultarArmarioGeral().start();
                break;

            case ConstantesTelas.BTN_FILTRO_TODOS:
                getThreadConsultarArmarioGeral().start();
                break;

            case ConstantesTelas.BTN_FILTRO_ATIVOS:
                getThreadConsultarArmarioGeral().start();
                break;

            case ConstantesTelas.BTN_FILTRO_INATIVOS:
                getThreadConsultarArmarioGeral().start();
                break;

            case ConstantesTelas.BTN_FILTRO_ARMARIOS_TODOS:
                getThreadConsultarArmarioGeral().start();
                break;
            case ConstantesTelas.BTN_FILTRO_ARMARIOS_LIVRES:
                getThreadConsultarArmarioGeral().start();
                break;
            case ConstantesTelas.BTN_FILTRO_ARMARIOS_OCUPADOS:
                getThreadConsultarArmarioGeral().start();
                break;

            case ConstantesTelas.BTN_SALVAR:
                getThreadCadastrarArmario().start();
                break;

            case ConstantesTelas.BTN_CANCELAR:
                getThreadConfirmarCancelar().start();
                break;
            case ConstantesTelas.BTN_CANCELAR_CONSULTA_ARMARIOS:
                getThreadConfirmarCancelarBusca().start();
                break;

            case ConstantesTelas.BTN_EDITAR:
                getThreadShowTelaCadastrarAtualizar().start();
                break;

            case ConstantesTelas.BTN_LIMPAR:
                if (cadastrarAluguelArmarioView != null) {
//                    cadastrarAluguelArmarioView.limparCampos(true);
                }

                if (cadastrarAluguelArmarioView != null) {
                    cadastrarAluguelArmarioView.limparCampos();
                }

                break;

            case ConstantesTelas.ITM_TABELA:
                getThreadGetArmarioSelecionado().start();
                break;

            case ConstantesTelas.ITM_TABELA_CONSULTAR:
                getThreadGetArmarioSelecionadoConsultar().start();
                break;

        }

        //TEST:
        System.out.println(botao);

    }
}
