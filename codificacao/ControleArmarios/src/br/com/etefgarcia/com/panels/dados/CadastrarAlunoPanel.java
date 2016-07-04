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
package br.com.etefgarcia.com.panels.dados;

/**
 *
 * @author fernando-pucci
 */
public class CadastrarAlunoPanel extends javax.swing.JPanel {

    /**
     * Creates new form CadastrarAlunoPanel
     */
    public CadastrarAlunoPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        alunoCadastrar = new br.com.etefgarcia.armarios.model.Aluno();
        jPanelEsquerdo = new javax.swing.JPanel();
        jLabelIdAluno = new javax.swing.JLabel();
        jTextFieldIdAluno = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(500, 459));
        setMinimumSize(new java.awt.Dimension(500, 459));

        jPanelEsquerdo.setMaximumSize(new java.awt.Dimension(500, 459));
        jPanelEsquerdo.setMinimumSize(new java.awt.Dimension(500, 459));
        jPanelEsquerdo.setPreferredSize(new java.awt.Dimension(500, 459));

        jLabelIdAluno.setText("Cód.:");
        jPanelEsquerdo.add(jLabelIdAluno);

        jTextFieldIdAluno.setPreferredSize(new java.awt.Dimension(90, 28));
        jPanelEsquerdo.add(jTextFieldIdAluno);

        jLabelNome.setText("Nome:");
        jPanelEsquerdo.add(jLabelNome);

        jTextFieldNome.setMaximumSize(new java.awt.Dimension(286, 28));
        jTextFieldNome.setMinimumSize(new java.awt.Dimension(286, 28));
        jTextFieldNome.setPreferredSize(new java.awt.Dimension(286, 28));
        jPanelEsquerdo.add(jTextFieldNome);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.etefgarcia.armarios.model.Aluno alunoCadastrar;
    private javax.swing.JLabel jLabelIdAluno;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JPanel jPanelEsquerdo;
    private javax.swing.JTextField jTextFieldIdAluno;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}