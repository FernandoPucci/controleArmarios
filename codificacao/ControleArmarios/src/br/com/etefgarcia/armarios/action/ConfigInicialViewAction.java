/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etefgarcia.armarios.action;

import br.com.etefgarcia.armarios.controller.ConfigController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;

/**
 *
 * @author fernando-pucci
 */
public class ConfigInicialViewAction implements MouseListener {

    private final ConfigController configController;

    public ConfigInicialViewAction(ConfigController configController) {

        this.configController = configController;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Object source = e.getSource();
            if (source instanceof AbstractButton) {

                configController.acaoClickController((AbstractButton) source);

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
