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
package br.com.etefgarcia.armarios.action;

import br.com.etefgarcia.armarios.controller.TelaPrincipalController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author fernando-pucci
 */
public class TelaPrincipalViewAction implements MouseListener {

    private final TelaPrincipalController telaPrincipalController;

    public TelaPrincipalViewAction(TelaPrincipalController telaPrincipalController) {

        this.telaPrincipalController = telaPrincipalController;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Object source = e.getSource();
            if (source instanceof AbstractButton) {

                telaPrincipalController.acaoClickController((AbstractButton) source);

            } else if (source instanceof JMenuItem) {

                telaPrincipalController.acaoClickController((AbstractButton) source);

            } else if (source instanceof JMenu) {

                telaPrincipalController.acaoClickController((AbstractButton) source);

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
