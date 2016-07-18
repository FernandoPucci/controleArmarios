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
import br.com.etefgarcia.armarios.model.Usuario;
import br.com.etefgarcia.armarios.service.UsuarioService;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import br.com.etefgarcia.armarios.view.aluno.CadastrarAlunoView;
import br.com.etefgarcia.armarios.view.ConfigInicialView;
import br.com.etefgarcia.armarios.view.TelaPrincipalView;
import br.com.etefgarcia.armarios.view.aluno.ConsultarAlunoView;
import br.com.etefgarcia.armarios.view.armario.CadastrarArmarioView;
import br.com.etefgarcia.armarios.view.armario.ConsultarArmarioView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;

/**
 *
 * @author fernando-pucci
 */
public class TelaPrincipalController {

    private TelaPrincipalView telaPrincipalView = null;

    public TelaPrincipalController() {

     //   this.telaPrincipalView = new TelaPrincipalView();

    }

    public Thread getThreadShowTelaPrincipalView() {

        return new Thread() {

            @Override
            public void run() {

                
             telaPrincipalView =    new TelaPrincipalView();
                     
                carregaUsuario();

                if (telaPrincipalView.getUsuario() != null) {

                    telaPrincipalView.setVisible(true);

                } else {

                    Mensagens.mostraMensagemAlerta("Problemas ao iniciar;");
                    System.exit(0);
                }
            }
  
        };

    }

    private void carregaUsuario() {

        try {

            // TODO :  Mock usuario id 1L, criar regra de login
            Usuario u = UsuarioService.getUsuarioById(1L);

            if (u == null) {

                throw new NegocioException("Usuário não localizado.");

            } else {

                telaPrincipalView.setUsuario(u);
                telaPrincipalView.mostrarDadosUsuarioLogado();

            }

        } catch (NegocioException | SistemaException sex) {

            Mensagens.mostraMensagemErro(telaPrincipalView.getPainelInferior(), sex.getMessage());

        }

    }

    public Thread getThreadConfirmarSaida() {

        return new Thread() {

            @Override
            public void run() {

                int opt = Mensagens.mostraMensagemPergunta("Tem certeza que deseja Sair do Programa?");
                if (opt == 0) {
                    System.exit(0);
                }
            }

        };
    }

    public Thread getThreadShowConfigurarBancoView() {

        return new Thread() {

            @Override
            public void run() {
                try {
                    ConfigController configController = new ConfigController(new ConfigInicialView());

                    configController.getThreadShowAtualizaConfigInicialView().start();

                } catch (IOException ex) {
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        };
    }

    public Thread getThreadShowCadastrarAlunoView() {

        return new Thread() {

            @Override
            public void run() {

                new CadastrarAlunoView(false).setVisible(true);

            }

        };
    }

    public Thread getThreadShowConsultarAlunoView() {

        return new Thread() {

            @Override
            public void run() {

                new ConsultarAlunoView().setVisible(true);

            }

        };
    }

    public Thread getThreadShowCadastrarArmarioView() {

        return new Thread() {

            @Override
            public void run() {

                new CadastrarArmarioView(false).setVisible(true);

            }

        };
    }

    public Thread getThreadShowConsultarArmarioView() {

        return new Thread() {

            @Override
            public void run() {

                new ConsultarArmarioView().setVisible(true);

            }

        };
    }

    //ACOES
    //trata ações dos botoes clicados
    public void acaoClickController(AbstractButton botao) {

        if (botao.isEnabled()) {
            acaoController(botao.getName());
        }
    }

    private void acaoController(String botao) {

        switch (botao) {

            case ConstantesTelas.BTN_SAIR:
                getThreadConfirmarSaida().start();
                break;

        }

        //TEST:
        System.out.println(botao);

    }

}
