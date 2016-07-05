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
package br.com.etefgarcia.armarios.util;

/**
 *
 * @author fernando-pucci
 */
public class TelaRenderUtil {

    
    public static void habilitarBotao(javax.swing.JButton botao, boolean habilitar) {

        botao.setEnabled(habilitar);

    }
    
    public static void habilitarCampos(Object obj, boolean habilitar) {

        if (obj instanceof javax.swing.JFormattedTextField) {
            javax.swing.JFormattedTextField obj2 = (javax.swing.JFormattedTextField) obj;

            obj2.setEnabled(habilitar);
            obj2.setEditable(habilitar);

        } else if (obj instanceof javax.swing.JTextField) {

            javax.swing.JTextField obj2 = (javax.swing.JTextField) obj;

            obj2.setEnabled(habilitar);
            obj2.setEditable(habilitar);

        } else if (obj instanceof javax.swing.JCheckBox) {

            javax.swing.JCheckBox obj2 = (javax.swing.JCheckBox) obj;
            obj2.setEnabled(habilitar);

        } else if (obj instanceof javax.swing.JLabel) {

            javax.swing.JLabel obj2 = (javax.swing.JLabel) obj;

            obj2.setEnabled(habilitar);

        } else if (obj instanceof javax.swing.JTable) {
            
            javax.swing.JTable obj2 = (javax.swing.JTable) obj;

            obj2.setEnabled(habilitar);

        }
    }


}
