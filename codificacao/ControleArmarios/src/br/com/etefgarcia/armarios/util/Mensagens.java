/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etefgarcia.armarios.util;

import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author fernando-pucci
 */
public class Mensagens {

    public static void mostraMensagemErro(JComponent componente, String mensagem) {

        piscaComponente(componente, ConstantesTelas.PISCA_ERRO);

        JOptionPane.showMessageDialog(componente, mensagem, ConstantesTelas.TITULO_ERRO, JOptionPane.ERROR_MESSAGE);

    }

    public static void mostraMensagemSucesso(JComponent componente, String mensagem) {

        piscaComponente(componente, ConstantesTelas.PISCA_SUCESSO);

        JOptionPane.showMessageDialog(componente, mensagem, ConstantesTelas.TITULO_SUCESSO, JOptionPane.WARNING_MESSAGE);

    }

    public static int mostraMensagemPergunta(JComponent componente, String mensagem) {
    
        //piscaComponente(componente, ConstantesTelas.PISCA_INFO);
    
        int retorno = JOptionPane.showConfirmDialog(componente, mensagem, ConstantesTelas.TITULO_PERGUNTA, JOptionPane.OK_CANCEL_OPTION);
        
        return retorno;
    }

      public static void mostraMensagemErro(String mensagem) {

             JOptionPane.showMessageDialog(null, mensagem, ConstantesTelas.TITULO_ERRO, JOptionPane.ERROR_MESSAGE);

    }

    public static void mostraMensagemSucesso( String mensagem) {

        JOptionPane.showMessageDialog(null, mensagem, ConstantesTelas.TITULO_SUCESSO, JOptionPane.WARNING_MESSAGE);

    }

    public static int mostraMensagemPergunta(String mensagem) {
    
        //piscaComponente(componente, ConstantesTelas.PISCA_INFO);
    
        int retorno = JOptionPane.showConfirmDialog(null, mensagem, ConstantesTelas.TITULO_PERGUNTA, JOptionPane.OK_CANCEL_OPTION);
        
        return retorno;
    }
    
    
    public static void piscaComponente(final JComponent componente, final int tipoPisca) {
        new Thread() {
            @Override
            public void run() {
                piscaComponenteSyncronized(componente, tipoPisca);
            }
        }.start();
    }

    private static synchronized void piscaComponenteSyncronized(JComponent componente, int tipoPisca) {
        int red = componente.getBackground().getRed();
        int green = componente.getBackground().getGreen();
        int blue = componente.getBackground().getBlue();

        if (tipoPisca == ConstantesTelas.PISCA_ERRO) {
            for (int i = 100; i < 200; i++) {
                componente.setBackground(new Color(red, i, i));
                componente.repaint();
                try {
                    Thread.sleep(2L);
                } catch (InterruptedException ex) {
                }
            }
        } else if (tipoPisca == ConstantesTelas.PISCA_SUCESSO) {
            for (int i = 100; i < 200; i++) {
                componente.setBackground(new Color(i, green, i));
                componente.repaint();
                try {
                    Thread.sleep(2L);
                } catch (InterruptedException ex) {
                }
            }
        } else {
            for (int i = 100; i < 200; i++) {
                componente.setBackground(new Color(i, i, 255));
                componente.repaint();
                try {
                    Thread.sleep(2L);
                } catch (InterruptedException ex) {
                }
            }
        }
        componente.setBackground(new Color(red, green, blue));
    }

}
