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

import br.com.etefgarcia.armarios.dao.AlunoDAO;
import br.com.etefgarcia.armarios.dao.impl.AlunoDAOImpl;
import br.com.etefgarcia.armarios.exceptions.NegocioException;
import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.service.AlunoService;
import br.com.etefgarcia.armarios.util.ExcelUtil;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.TelaUtils;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import br.com.etefgarcia.armarios.view.aluno.CadastrarAlunoView;
import br.com.etefgarcia.armarios.view.aluno.CarregarPlanilhaAlunoView;
import br.com.etefgarcia.armarios.view.aluno.ConsultarAlunoView;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractButton;

/**
 *
 * @author fernando-pucci
 */
public class AlunoController {
    
    private CadastrarAlunoView cadastrarAlunoView = null;
    private ConsultarAlunoView consultarAlunoView = null;
    private CarregarPlanilhaAlunoView carregarPlanilhaAlunoView = null;
    
    public AlunoController(ConsultarAlunoView consultarAlunoView) {
        
        this.consultarAlunoView = consultarAlunoView;
        
    }
    
    public AlunoController(CadastrarAlunoView cadastrarAlunoView) {
        
        this.cadastrarAlunoView = cadastrarAlunoView;
        
    }
    
    public AlunoController(CarregarPlanilhaAlunoView carregarPlanilhaAlunoView) {
        
        this.carregarPlanilhaAlunoView = carregarPlanilhaAlunoView;
        
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
    
    private Thread getThreadConfirmarCancelarEnvio() {
        return new Thread() {
            
            @Override
            public void run() {
                
                int opt = Mensagens.mostraMensagemPergunta("Tem certeza que fechar esta Janela?");
                if (opt == 0) {
                    
                    carregarPlanilhaAlunoView.dispose();
                    
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
                    
                    consultarAlunoView.dispose();
                    
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
    
    public Thread getThreadConsultarAlunoGeral() {
        
        return new Thread() {
            
            @Override
            public void run() {
                
                try {
                    
                    switch (consultarAlunoView.getFiltroSelecionado()) {
                        
                        case ConstantesTelas.BTN_FILTRO_TODOS:
                            consultarAlunosGeral(null);
                            break;
                        case ConstantesTelas.BTN_FILTRO_ATIVOS:
                            consultarAlunosGeral(Boolean.TRUE);
                            break;
                        case ConstantesTelas.BTN_FILTRO_INATIVOS:
                            consultarAlunosGeral(Boolean.FALSE);
                            break;
                        
                    }
                    
                } catch (SistemaException | NegocioException ex) {
                    
                    if (ex instanceof NegocioException) {
                        
                        Mensagens.mostraMensagemAlerta(consultarAlunoView.getPainel(), ex.getMessage());
                        
                    } else {
                        
                        Mensagens.mostraMensagemErro(consultarAlunoView.getPainel(), ex.getMessage());
                        
                    }
                    
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
    
    public Thread getThreadGetAlunoSelecionadoConsultar() {
        
        return new Thread() {
            
            @Override
            public void run() {
                
                Aluno a = consultarAlunoView.getAlunoSelecionado();
                
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
    
    public Thread getThreadGetCarregarArquivoPlanilha() {
        return new Thread() {
            
            @Override
            public void run() {
                
                int opt = carregarPlanilhaAlunoView.mostrarFileChooser();
                
                if (opt == 0) {
                    
                    File f = carregarPlanilhaAlunoView.getFileChooser().getSelectedFile();
                    
                    carregarPlanilhaAlunoView.setLogger("<font color=\"blue\">Arquivo Selecionado [<b>" + f.getName() + "</b>].</font>");
                    
                    if ((!f.getName().toLowerCase().endsWith(".xlsx")) || (f.getName().toLowerCase().endsWith(".xls"))) {

//                        carregarPlanilhaAlunoView.setFileChooser(null);
//                        carregarPlanilhaAlunoView.setArquivoIsValido(Boolean.FALSE);
//                        carregarPlanilhaAlunoView.setLogger("<font color=\"red\"><b>ERRO!</b> Somente são permitidos arquivos com extensão .XLS ou .XLSX.</font>");
//                        
//                        Mensagens.mostraMensagemErro(carregarPlanilhaAlunoView.getPainel(), "Somente são permitidos arquivos com extensão .XLS ou .XLSX");
                        carregarPlanilhaAlunoView.processarErro("Somente são permitidos arquivos com extensão .XLS ou .XLSX.");
                    } else {

//                        carregarPlanilhaAlunoView.setLogger("<font color=\"green\"><b>Nome de Arquivo e extensões validados.</b></font>");
                        carregarPlanilhaAlunoView.processarSucesso("Nome de Arquivo e extensões validados.");
                        carregarPlanilhaAlunoView.setInstrucao("Arquivo válido.\n Clique no botão do disquete para salvar a planilha.");

//                        carregarPlanilhaAlunoView.setArquivoIsValido(Boolean.TRUE);
//                        carregarPlanilhaAlunoView.habilitarSalvar(true);
//
//                        Mensagens.mostraMensagemSucesso(carregarPlanilhaAlunoView.getPainel(), "Nome de Arquivo e extensões validados.");
                    }
                    
                }
                
            }
        };
        
    }
    
    private Thread getThreadGetProcessarPlanilha() {
        
        return new Thread() {
            
            @Override
            public void run() {
                
                try {
                    
                    List<Aluno> listaAlunos = null;
                    
                    ExcelUtil eu = new ExcelUtil();
                    
                    listaAlunos = eu.carregaListaAlunos(carregarPlanilhaAlunoView.getFileChooser().getSelectedFile(), 4);
                    
                    Double totalLinhas = Double.parseDouble(listaAlunos.size() + "");
                    Double linhasAtualizadas = 0.0;
                    
                    AlunoDAO dao = new AlunoDAOImpl();
                    
                    Long initTime = System.nanoTime();
                    System.out.println("AGUARDE...");
                    
                    for (Aluno a : listaAlunos) {
                        
                        System.out.println(a);
                        if (a.getTelefone() == null && a.getEmail() == null) {
                            
                            throw new NegocioException("Aluno " + a.getIdAluno() + ", possui dados de contato inválidos");
                            
                        }
                        
                        if (a.getEmail() != null) {
                            if (!TelaUtils.validaEmail(a.getEmail())) {
                                
                                throw new NegocioException("Aluno " + a.getIdAluno() + ", possui dados de e-mail inválidos");
                                
                            }
                        }
                        
                        if (a.getTelefone() != null) {
                            if (!TelaUtils.validaTelefone(a.getTelefone())) {
                                
                                throw new NegocioException("Aluno " + a.getIdAluno() + ", possui dados de Telefone inválidos");
                                
                            }
                            
                        }
                        
                        try {
                            
                            AlunoService service = new AlunoService(dao);
                            
                            service.cadastrarAluno(a);
                            linhasAtualizadas++;
                            
                        } catch (Exception e) {
                            
                            throw new SistemaException(e.getMessage());
                            
                        }
                    }
                    
                    Long finalTime = System.nanoTime() - initTime;
                    
                    double tempoSegundos = (double) finalTime / 1000000000.0;
                    
                    System.out.println(listaAlunos.size() + " registros em " + tempoSegundos / 60 + " minutos");
                    
                    carregarPlanilhaAlunoView.processarSucesso(listaAlunos.size() + " registros em " + (new BigDecimal(tempoSegundos / 60).ROUND_UP) + " minutos");
                    
                } catch (SistemaException | NegocioException | IOException ex) {
                    
                    carregarPlanilhaAlunoView.processarErro(ex.getMessage());
                    
                }
                
            }
        };
        
    }
    
    public Thread getThreadShowTelaCadastrarAtualizar() {
        
        return new Thread() {
            
            @Override
            public void run() {
                
                Aluno a = consultarAlunoView.getAlunoSelecionado();
                
                new CadastrarAlunoView(a, true).setVisible(true);
                
            }
            
        };
        
    }

    //METODOS PRIVADOS
    //consulta alunos da tela de cadastro
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

    //consulta aluno generica
    private void consultarAlunosGeral(Boolean ativos) throws SistemaException, NegocioException {
        
        List<Aluno> listaAlunos = null;

        //pesquisa por todos alunos
        if (ativos == null && (consultarAlunoView.getNome() == null || consultarAlunoView.getNome().trim().isEmpty())) {
            
            listaAlunos = AlunoService.consultarTodosAlunosService();

            //pesquisa por codiGo
        } else if (consultarAlunoView.getCodigo() != null && !consultarAlunoView.getCodigo().trim().isEmpty()) {
            
            Aluno a = AlunoService.consultarAlunosByCodigoService(consultarAlunoView.getCodigo());
            
            if (a != null) {
                listaAlunos = new ArrayList<>();
                listaAlunos.add(a);
            }

            //pesquisa por nome
        } else if (consultarAlunoView.getNome() != null && !consultarAlunoView.getNome().trim().isEmpty()) {
            
            listaAlunos = AlunoService.consultarAlunosByNomeService(consultarAlunoView.getNome().trim(), ativos);
            
        } else {
            
            listaAlunos = AlunoService.consultarAlunosService(ativos);
            
        }
        
        if (listaAlunos == null || listaAlunos.isEmpty()) {
            Mensagens.mostraMensagemAlerta(consultarAlunoView.getPainel(), "Não há resultados para esta busca.");
            consultarAlunoView.limparCampos();
            
        } else {
            
            consultarAlunoView.setListaAlunos(listaAlunos);
            consultarAlunoView.mostrarTabelas(true);
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
                getThreadConsultarAluno().start();
                break;
            
            case ConstantesTelas.BTN_BUSCAR_TELA_BUSCA:
                getThreadConsultarAlunoGeral().start();
                break;
            
            case ConstantesTelas.BTN_FILTRO_TODOS:
                getThreadConsultarAlunoGeral().start();
                break;
            
            case ConstantesTelas.BTN_FILTRO_ATIVOS:
                getThreadConsultarAlunoGeral().start();
                break;
            
            case ConstantesTelas.BTN_FILTRO_INATIVOS:
                getThreadConsultarAlunoGeral().start();
                break;
            
            case ConstantesTelas.BTN_SALVAR:
                getThreadCadastrarAluno().start();
                break;
            
            case ConstantesTelas.BTN_CANCELAR:
                getThreadConfirmarCancelar().start();
                break;
            case ConstantesTelas.BTN_CANCELAR_CONSULTA_ALUNOS:
                getThreadConfirmarCancelarBusca().start();
                break;
            
            case ConstantesTelas.BTN_CANCELAR_ENVIO:
                getThreadConfirmarCancelarEnvio().start();
                break;
            
            case ConstantesTelas.BTN_EDITAR:
                getThreadShowTelaCadastrarAtualizar().start();
                break;
            
            case ConstantesTelas.BTN_LIMPAR:
                if (cadastrarAlunoView != null) {
                    cadastrarAlunoView.limparCampos(true);
                }
                
                if (consultarAlunoView != null) {
                    consultarAlunoView.limparCampos();
                }
                
                if (carregarPlanilhaAlunoView != null) {
                    carregarPlanilhaAlunoView.limparCampos();
                }
                
                break;
            
            case ConstantesTelas.ITM_TABELA:
                getThreadGetAlunoSelecionado().start();
                break;
            
            case ConstantesTelas.ITM_TABELA_CONSULTAR:
                getThreadGetAlunoSelecionadoConsultar().start();
                break;
            
            case ConstantesTelas.BTN_CARREGAR_ARQUIVO:
                getThreadGetCarregarArquivoPlanilha().start();
                break;
            
        }

        //TEST:
        System.out.println(botao);
        
    }
    
}
