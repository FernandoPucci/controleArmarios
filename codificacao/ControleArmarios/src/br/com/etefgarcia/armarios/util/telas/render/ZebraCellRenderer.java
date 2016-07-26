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
package br.com.etefgarcia.armarios.util.telas.render;

import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author fernando-pucci
 */
public class ZebraCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, java.lang.Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (!isSelected) {

            Color c = table.getBackground();

            if ((row % 2) != 0) { // && c.getRed() > 10 && c.getGreen() > 10 && c.getBlue() > 10) {
                setBackground(new Color(c.getRed() - 30, c.getGreen() - 20, c.getBlue() - 10));
            } else {
                setBackground(c);
            }
        }

        Color cor = table.getForeground();
        Font fonte = table.getFont();

        if (table.getName().equals(ConstantesTelas.ITM_TABELA)) {
            try {

                if (table.getModel().getValueAt(row, 5).toString().equalsIgnoreCase("true")) {

                    setForeground(Color.GRAY);

                } else {

                    setFont(fonte);
                    setForeground(Color.BLUE);
                }

            } catch (Exception ex) {
                setFont(fonte);
                setForeground(Color.BLUE);
            }
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
