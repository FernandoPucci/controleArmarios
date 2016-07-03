/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etefgarcia.armarios.main;

import br.com.etefgarcia.armarios.controller.ConfigController;

import br.com.etefgarcia.armarios.util.ConfigUtils;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.view.ConfigInicialView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando-pucci
 *
 * Classe principal de inicialização do programa
 *
 *
 */
public class Principal {
    
    public static void main(String[] args) {
        
        ConfigController configController;
        
        try {
            
            configController = new ConfigController(new ConfigInicialView());
            
            if (configController.getConfig().getIsPrimeiroAcesso()) {
                
                configController.getThreadShowConfigInicialView().start();
                
            } else {
                configController.testarConfiguracaoDbGeral();
                System.out.println("INICIAR'");
                
            }
        } catch (Exception ex) {
            
            Mensagens.mostraMensagemErro(ex.getMessage());
            System.exit(0);
            
        } 
        
    }
    
}
