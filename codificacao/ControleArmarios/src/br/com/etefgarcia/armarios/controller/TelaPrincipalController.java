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

import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import br.com.etefgarcia.armarios.view.CadastrarAlunoView;
import br.com.etefgarcia.armarios.view.ConfigInicialView;
import br.com.etefgarcia.armarios.view.TelaPrincipalView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;

/**
 *
 * @author fernando-pucci
 */
public class TelaPrincipalController {
    
    public Thread getThreadShowTelaPrincipalView() {
        
        return new Thread() {
            
            @Override
            public void run() {
                new TelaPrincipalView().setVisible(true);
                
            }
            
        };
        
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
