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
package br.com.etefgarcia.armarios.view.aluno;

import br.com.etefgarcia.armarios.action.aluno.CadastrarAlunoViewAction;
import br.com.etefgarcia.armarios.controller.AlunoController;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.ServiceUtils;
import br.com.etefgarcia.armarios.util.TelaRenderUtil;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author fernando-pucci
 *
 */
public class CarregarPlanilhaAlunoView extends javax.swing.JFrame {

    private AlunoController alunoController;
    private CadastrarAlunoViewAction cadastrarAlunoViewAction;
    private Boolean isArquivoValido = Boolean.FALSE;
    private String log = "";
    private JFileChooser fc;

    public CarregarPlanilhaAlunoView() {
        initComponents();

        inicializar();
        configurarBotoes(false);

    }

    private void inicializar() {

        jButtonBuscar.setName(ConstantesTelas.BTN_CARREGAR_ARQUIVO);
        jButtonCancelar.setName(ConstantesTelas.BTN_CANCELAR_ENVIO);
        jButtonSalvar.setName(ConstantesTelas.BTN_SALVAR_PLANILHA);

        jButtonBuscar.setToolTipText(ConstantesTelas.TT_BTN_CARREGAR_ARQUIVO);
        jButtonSalvar.setToolTipText(ConstantesTelas.TT_BTN_SALVAR_UPLOAD);
        jButtonLimpar.setToolTipText(ConstantesTelas.TT_BTN_LIMPAR);
        jButtonCancelar.setToolTipText(ConstantesTelas.TT_BTN_CANCELAR);

        jTextPaneLogger.setContentType("text/html");

        jLabelInstrucoes.setText(ConstantesTelas.TEXTO_INICIAL_TELA_CARREGAR_ARQUIVO);

        this.alunoController = new AlunoController(this);
        this.cadastrarAlunoViewAction = new CadastrarAlunoViewAction(alunoController);

        removeListeners();
        adicionaListeners();

        TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

    }

    private void configurarBotoes(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jButtonSalvar, habilitar);
        TelaRenderUtil.habilitarBotao(jButtonLimpar, habilitar);

    }

    public void habilitarSalvar(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jButtonSalvar, isArquivoValido ? habilitar : false);
        TelaRenderUtil.habilitarBotao(jButtonLimpar, habilitar);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFundo = new javax.swing.JPanel();
        jPanelEsquerdo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelInstrucoes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneLogger = new javax.swing.JTextPane();
        jPanelBotoes = new javax.swing.JPanel();
        jButtonBuscar = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(ConstantesTelas.TITULO_JANELA_PRINCIPAL + " - " + ConstantesTelas.TITULO_CADASTRAR_ALUNO);
        setMinimumSize(new java.awt.Dimension(640, 480));
        setResizable(false);

        jPanelFundo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelFundo.setMaximumSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setMinimumSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setPreferredSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setRequestFocusEnabled(false);
        jPanelFundo.setLayout(new javax.swing.BoxLayout(jPanelFundo, javax.swing.BoxLayout.LINE_AXIS));

        jPanelEsquerdo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, ConstantesTelas.TITULO_CARREGAR_ARQUIVO
            , javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14)), javax.swing.BorderFactory.createEtchedBorder())); // NOI18N
jPanelEsquerdo.setMaximumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setMinimumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setPreferredSize(new java.awt.Dimension(500, 459));

jPanel1.setMaximumSize(new java.awt.Dimension(430, 400));
jPanel1.setMinimumSize(new java.awt.Dimension(430, 400));
jPanel1.setLayout(new java.awt.GridLayout(2, 1));

jLabelInstrucoes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
jLabelInstrucoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
jLabelInstrucoes.setText("....");
jPanel1.add(jLabelInstrucoes);

jScrollPane2.setPreferredSize(new java.awt.Dimension(430, 200));

jTextPaneLogger.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
jTextPaneLogger.setPreferredSize(new java.awt.Dimension(420, 200));
jScrollPane2.setViewportView(jTextPaneLogger);

jPanel1.add(jScrollPane2);

jPanelEsquerdo.add(jPanel1);

jPanelFundo.add(jPanelEsquerdo);

jPanelBotoes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
jPanelBotoes.setMaximumSize(new java.awt.Dimension(120, 459));
jPanelBotoes.setMinimumSize(new java.awt.Dimension(120, 459));
jPanelBotoes.setName(""); // NOI18N
jPanelBotoes.setPreferredSize(new java.awt.Dimension(120, 459));
jPanelBotoes.setRequestFocusEnabled(false);
jPanelBotoes.setLayout(new java.awt.GridLayout(5, 1));

jButtonBuscar.setBackground(new java.awt.Color(242, 241, 240));
jButtonBuscar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/search3.png"))); // NOI18N
jButtonBuscar.setToolTipText("TESTE SOENTE");
jPanelBotoes.add(jButtonBuscar);
jPanelBotoes.add(filler1);

jButtonSalvar.setBackground(new java.awt.Color(242, 241, 240));
jButtonSalvar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/Save3.png"))); // NOI18N
jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonSalvarActionPerformed(evt);
    }
    });
    jPanelBotoes.add(jButtonSalvar);

    jButtonLimpar.setBackground(new java.awt.Color(242, 241, 240));
    jButtonLimpar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
    jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/limpar3.png"))); // NOI18N
    jButtonLimpar.setName(ConstantesTelas.BTN_LIMPAR);
    jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonLimparActionPerformed(evt);
        }
    });
    jPanelBotoes.add(jButtonLimpar);

    jButtonCancelar.setBackground(new java.awt.Color(242, 241, 240));
    jButtonCancelar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
    jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/Cancel3.png"))); // NOI18N
    jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonCancelarActionPerformed(evt);
        }
    });
    jPanelBotoes.add(jButtonCancelar);

    jPanelFundo.add(jPanelBotoes);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jPanelFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jPanelFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

    }//GEN-LAST:event_jButtonSalvarActionPerformed

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
//            java.util.logging.Logger.getLogger(CadastrarAlunoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CadastrarAlunoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CadastrarAlunoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CadastrarAlunoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CadastrarAlunoView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabelInstrucoes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelEsquerdo;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPaneLogger;
    // End of variables declaration//GEN-END:variables

    private void removeListeners() {
        jButtonBuscar.removeMouseListener(cadastrarAlunoViewAction);
        jButtonCancelar.removeMouseListener(cadastrarAlunoViewAction);
        jButtonLimpar.removeMouseListener(cadastrarAlunoViewAction);
        jButtonSalvar.removeMouseListener(cadastrarAlunoViewAction);

    }

    private void adicionaListeners() {

        jButtonBuscar.addMouseListener(cadastrarAlunoViewAction);
        jButtonCancelar.addMouseListener(cadastrarAlunoViewAction);
        jButtonLimpar.addMouseListener(cadastrarAlunoViewAction);
        jButtonSalvar.addMouseListener(cadastrarAlunoViewAction);

    }

    public void limparCampos() {

        configurarBotoes(false);
        jTextPaneLogger.setText("");
        log = "";
        fc = null;

    }

    public void setInstrucao(String instrucao) {

        jLabelInstrucoes.setText(instrucao);
        jLabelInstrucoes.repaint();

    }

    public void setLogger(String log) {

        jTextPaneLogger.setText("");

        StringBuilder sb = new StringBuilder();

        sb.append(this.log);

        sb.append("<p>");
        sb.append("<i>");
        sb.append("<b>");
        sb.append(ServiceUtils.formataDataHora(new Date()));
        sb.append("</b>");
        sb.append("</i>");
        sb.append("</p>");

        sb.append(log);
        jTextPaneLogger.setText(sb.toString());

        this.log += sb.toString();

        jTextPaneLogger.setCaretPosition(jTextPaneLogger.getDocument().getLength());

    }

    public JFileChooser getFileChooser() {

        return fc;

    }

    public JFileChooser setFileChooser(JFileChooser fileChooser) {

        return this.fc = fileChooser;
    }

    public JPanel getPainel() {

        return jPanelEsquerdo;

    }

    public void setArquivoIsValido(Boolean isArquivoValido) {
        this.isArquivoValido = isArquivoValido;
    }

    public int mostrarFileChooser() {
        fc = new JFileChooser();
        return fc.showOpenDialog(this);

    }

    public void processarErro(String mensagem) {

        setFileChooser(null);
        setArquivoIsValido(Boolean.FALSE);
        setLogger("<font color=\"red\"><b>ERRO!</b>" + mensagem + "</font>");

        Mensagens.mostraMensagemErro(jPanelEsquerdo, mensagem);

    }

    public void processarSucesso(String mensagem) {

        setLogger("<font color=\"green\"><b>Sucesso. </b>" + mensagem + "</font>");

        setArquivoIsValido(Boolean.TRUE);
        habilitarSalvar(true);

        Mensagens.mostraMensagemSucesso(jPanelEsquerdo, mensagem);

    }

}
