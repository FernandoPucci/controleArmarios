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
package br.com.etefgarcia.armarios.action.aluguel.armario;

import br.com.etefgarcia.armarios.controller.AluguelArmarioController;
import br.com.etefgarcia.armarios.controller.ArmarioController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;

/**
 *
 * @author fernando-pucci
 */
public class CadastrarAluguelArmarioViewAction implements MouseListener {

    private final AluguelArmarioController aluguelArmarioController
;
    public CadastrarAluguelArmarioViewAction(AluguelArmarioController aluguelArmarioController) {

        this.aluguelArmarioController = aluguelArmarioController;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Object source = e.getSource();
            if (source instanceof AbstractButton) {

                aluguelArmarioController.acaoClickController((AbstractButton) source);

            } else if (source instanceof javax.swing.JTable) {

                aluguelArmarioController.acaoClickController((javax.swing.JTable) source);

            } else if (source instanceof javax.swing.JRadioButton) {

                aluguelArmarioController.acaoClickController((javax.swing.JTable) source);

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
