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
package br.com.etefgarcia.armarios.view;

/**
 *
 * @author fernando-pucci
 */
public class BarraProgresso extends javax.swing.JFrame {

    private Double total;

    /**
     * Creates new form BarraProgresso
     */
    public BarraProgresso() {
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

        jLabel1 = new javax.swing.JLabel();
        jProgressBarBarraProgresso = new javax.swing.JProgressBar();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabelTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        getContentPane().setLayout(new java.awt.GridLayout(4, 1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/imgLoading_30x30.gif"))); // NOI18N
        jLabel1.setText("Aguarde...");
        getContentPane().add(jLabel1);

        jProgressBarBarraProgresso.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jProgressBarBarraProgresso.setForeground(new java.awt.Color(191, 19, 19));
        jProgressBarBarraProgresso.setToolTipText("");
        jProgressBarBarraProgresso.setMaximumSize(new java.awt.Dimension(150, 24));
        jProgressBarBarraProgresso.setMinimumSize(new java.awt.Dimension(150, 24));
        jProgressBarBarraProgresso.setName(""); // NOI18N
        jProgressBarBarraProgresso.setRequestFocusEnabled(false);
        jProgressBarBarraProgresso.setStringPainted(true);
        getContentPane().add(jProgressBarBarraProgresso);
        getContentPane().add(filler1);

        jLabelTotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelTotal.setForeground(new java.awt.Color(7, 9, 190));
        jLabelTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTotal.setText("jLabel2");
        getContentPane().add(jLabelTotal);

        setSize(new java.awt.Dimension(400, 150));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(BarraProgresso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BarraProgresso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BarraProgresso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BarraProgresso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BarraProgresso().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JProgressBar jProgressBarBarraProgresso;
    // End of variables declaration//GEN-END:variables

    private String formataTexto(Integer valorAtual) {

        return "[ " + valorAtual + " / " + (this.total == null ? 0 : this.total.intValue()) + " ]";

    }

    public void setValorTotal(Double total) {

        this.total = total;

    }

    public double getValorTotal() {

        return total;

    }

    public void setValor(Double valorPercentual, Integer valorQtd) {
        if (valorPercentual <= 100) {
            this.jProgressBarBarraProgresso.setValue(valorPercentual.intValue());
            this.jLabelTotal.setText(formataTexto(valorQtd));

            this.jProgressBarBarraProgresso.repaint();
            this.jLabelTotal.repaint();
        }
    }

}
