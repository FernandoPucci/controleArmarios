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
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.TelaRenderUtil;
import br.com.etefgarcia.armarios.util.TelaUtils;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import br.com.etefgarcia.armarios.util.telas.render.ZebraCellRenderer;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando-pucci
 *
 */
public class CadastrarAlunoView extends javax.swing.JFrame {

    private AlunoController alunoController;
    private CadastrarAlunoViewAction cadastrarAlunoViewAction;
    private Boolean isAtualizar = Boolean.FALSE;
    private List<Aluno> listaAlunos = null;

    private final Runnable threadChecaCampoNome = new Runnable() {

        @Override
        public void run() {
            validaCampoNome();
        }
    };

    public CadastrarAlunoView(Boolean isAtualizar) {
        initComponents();

        this.isAtualizar = isAtualizar == null ? Boolean.FALSE : isAtualizar;

        inicializar();
        configurarBotoes(false);
        configurarCampos();
        configurarItens();

        mostrarTabela(false);

    }

    public CadastrarAlunoView(Aluno aluno, Boolean isAtualizar) {

        initComponents();

        this.aluno = aluno;

        inicializar();
        configurarBotoes(aluno != null);
        configurarCampos();
        configurarItens();

        mostrarTabela(false);

        if (aluno != null) {
            jCheckBoxFlgAtivo.setEnabled(true);
            vincularAluno();
        }

    }

    private void inicializar() {

        jButtonBuscar.setName(ConstantesTelas.BTN_BUSCAR);
        jButtonCancelar.setName(ConstantesTelas.BTN_CANCELAR);
        jButtonSalvar.setName(ConstantesTelas.BTN_SALVAR);
        jButtonCancelar.setName(ConstantesTelas.BTN_CANCELAR);

        jButtonBuscar.setToolTipText(ConstantesTelas.TT_BTN_BUSCAR);
        jButtonSalvar.setToolTipText(ConstantesTelas.TT_BTN_SALVAR);
        jButtonLimpar.setToolTipText(ConstantesTelas.TT_BTN_LIMPAR);
        jButtonCancelar.setToolTipText(ConstantesTelas.TT_BTN_CANCELAR);

        this.alunoController = new AlunoController(this);
        this.cadastrarAlunoViewAction = new CadastrarAlunoViewAction(alunoController);

        removeListeners();
        adicionaListeners();

    }

    private void configurarItens() {

        TelaRenderUtil.habilitarCampos(jTableTabela, false);
        TelaRenderUtil.habilitarCampos(jScrollPane1, false);

    }

    private void configurarCampos() {

        jTextFieldIdAluno.setEnabled(false);
        jTextFieldIdAluno.setEditable(false);

        if (!isAtualizar) {

            jCheckBoxFlgAtivo.setSelected(true);
            TelaRenderUtil.habilitarCampos(jCheckBoxFlgAtivo, false);

        } else {

            jCheckBoxFlgAtivo.setSelected(this.aluno.getFlgAtivo());
            TelaRenderUtil.habilitarCampos(jCheckBoxFlgAtivo, true);

        }

        preencherComboSexo();

    }

    private void configurarBotoes(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jButtonBuscar, habilitar);
        TelaRenderUtil.habilitarBotao(jButtonSalvar, habilitar);
        TelaRenderUtil.habilitarBotao(jButtonLimpar, habilitar);

    }

    private void habilitarSalvar(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jButtonSalvar, habilitar);
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

        aluno = new br.com.etefgarcia.armarios.model.Aluno();
        jPanelFundo = new javax.swing.JPanel();
        jPanelEsquerdo = new javax.swing.JPanel();
        jLabelIdAluno = new javax.swing.JLabel();
        jTextFieldIdAluno = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jPanelInternoDadosAluno = new javax.swing.JPanel();
        jLabelSexo = new javax.swing.JLabel();
        jComboBoxSexo = new javax.swing.JComboBox();
        jLabelTelefone = new javax.swing.JLabel();
        jTextFieldTelefone = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jCheckBoxFlgAtivo = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTabela = new javax.swing.JTable();
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

        jPanelEsquerdo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, ConstantesTelas.TITULO_CADASTRAR_ALUNO
            , javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14)), javax.swing.BorderFactory.createEtchedBorder())); // NOI18N
jPanelEsquerdo.setMaximumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setMinimumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setPreferredSize(new java.awt.Dimension(500, 459));

jLabelIdAluno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
jLabelIdAluno.setText("Cód.:");
jPanelEsquerdo.add(jLabelIdAluno);

jTextFieldIdAluno.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
jTextFieldIdAluno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
jTextFieldIdAluno.setDisabledTextColor(new java.awt.Color(210, 20, 8));
jTextFieldIdAluno.setPreferredSize(new java.awt.Dimension(90, 28));
jPanelEsquerdo.add(jTextFieldIdAluno);

jLabelNome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
jLabelNome.setText("Nome:");
jPanelEsquerdo.add(jLabelNome);

jTextFieldNome.setMaximumSize(new java.awt.Dimension(286, 28));
jTextFieldNome.setMinimumSize(new java.awt.Dimension(286, 28));
jTextFieldNome.setPreferredSize(new java.awt.Dimension(286, 28));
jTextFieldNome.addFocusListener(new java.awt.event.FocusAdapter() {
    public void focusLost(java.awt.event.FocusEvent evt) {
        jTextFieldNomeFocusLost(evt);
    }
    });
    jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldNomeKeyTyped(evt);
        }
    });
    jPanelEsquerdo.add(jTextFieldNome);

    jPanelInternoDadosAluno.setMaximumSize(new java.awt.Dimension(483, 80));
    jPanelInternoDadosAluno.setMinimumSize(new java.awt.Dimension(483, 80));
    jPanelInternoDadosAluno.setPreferredSize(new java.awt.Dimension(483, 80));

    jLabelSexo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabelSexo.setText("Sexo:");
    jPanelInternoDadosAluno.add(jLabelSexo);

    jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));
    jComboBoxSexo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBoxSexoActionPerformed(evt);
        }
    });
    jPanelInternoDadosAluno.add(jComboBoxSexo);

    jLabelTelefone.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabelTelefone.setText("Telefone:");
    jLabelTelefone.setMaximumSize(new java.awt.Dimension(80, 17));
    jLabelTelefone.setMinimumSize(new java.awt.Dimension(80, 17));
    jLabelTelefone.setPreferredSize(new java.awt.Dimension(80, 17));
    jLabelTelefone.setRequestFocusEnabled(false);
    jPanelInternoDadosAluno.add(jLabelTelefone);

    jTextFieldTelefone.setMaximumSize(new java.awt.Dimension(220, 28));
    jTextFieldTelefone.setMinimumSize(new java.awt.Dimension(220, 28));
    jTextFieldTelefone.setName(""); // NOI18N
    jTextFieldTelefone.setPreferredSize(new java.awt.Dimension(220, 28));
    jTextFieldTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jTextFieldTelefoneFocusLost(evt);
        }
    });
    jPanelInternoDadosAluno.add(jTextFieldTelefone);

    jLabelEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabelEmail.setText("E-Mail:");
    jPanelInternoDadosAluno.add(jLabelEmail);

    jTextFieldEmail.setMaximumSize(new java.awt.Dimension(250, 28));
    jTextFieldEmail.setMinimumSize(new java.awt.Dimension(250, 28));
    jTextFieldEmail.setPreferredSize(new java.awt.Dimension(250, 28));
    jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextFieldEmailActionPerformed(evt);
        }
    });
    jTextFieldEmail.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jTextFieldEmailFocusLost(evt);
        }
    });
    jPanelInternoDadosAluno.add(jTextFieldEmail);

    jCheckBoxFlgAtivo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jCheckBoxFlgAtivo.setText("Ativo ?");
    jCheckBoxFlgAtivo.setMargin(new java.awt.Insets(0, 50, 0, 0));
    jCheckBoxFlgAtivo.setMaximumSize(new java.awt.Dimension(169, 24));
    jCheckBoxFlgAtivo.setMinimumSize(new java.awt.Dimension(169, 24));
    jCheckBoxFlgAtivo.setPreferredSize(new java.awt.Dimension(169, 24));
    jCheckBoxFlgAtivo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCheckBoxFlgAtivoActionPerformed(evt);
        }
    });
    jPanelInternoDadosAluno.add(jCheckBoxFlgAtivo);

    jPanelEsquerdo.add(jPanelInternoDadosAluno);

    jSeparator1.setMaximumSize(new java.awt.Dimension(450, 15));
    jSeparator1.setMinimumSize(new java.awt.Dimension(450, 15));
    jSeparator1.setName(""); // NOI18N
    jSeparator1.setPreferredSize(new java.awt.Dimension(450, 5));
    jSeparator1.setRequestFocusEnabled(false);
    jPanelEsquerdo.add(jSeparator1);

    jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane1.setMaximumSize(new java.awt.Dimension(452, 270));
    jScrollPane1.setMinimumSize(new java.awt.Dimension(452, 270));
    jScrollPane1.setName(""); // NOI18N
    jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 270));
    jScrollPane1.setRequestFocusEnabled(false);

    jTableTabela.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null}
        },
        new String [] {
            "Título 1"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTableTabela.setEnabled(false);
    jTableTabela.setMaximumSize(new java.awt.Dimension(400, 270));
    jTableTabela.setMinimumSize(new java.awt.Dimension(400, 270));
    jTableTabela.setName(ConstantesTelas.ITM_TABELA);
    jTableTabela.setPreferredSize(new java.awt.Dimension(400, 270));
    jTableTabela.setSelectionBackground(new java.awt.Color(254, 130, 140));
    jTableTabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane1.setViewportView(jTableTabela);

    jPanelEsquerdo.add(jScrollPane1);

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

        if (jComboBoxSexo.getSelectedIndex() == 0) {
            Mensagens.mostraMensagemAlerta("Selecione o Sexo.");

        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextFieldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyTyped

        SwingUtilities.invokeLater(threadChecaCampoNome);
    }//GEN-LAST:event_jTextFieldNomeKeyTyped

    private void jComboBoxSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSexoActionPerformed

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed

    private void jCheckBoxFlgAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxFlgAtivoActionPerformed

        if (!jCheckBoxFlgAtivo.isSelected()) {

            int opt = Mensagens.mostraMensagemPergunta("Se você desativar um aluno, ele deixará de figurar nas pesquisas de Ativos. \n Tem certeza?");
            System.out.println(opt);
            if (0 == opt) {
                jCheckBoxFlgAtivo.setSelected(false);
            } else {
                jCheckBoxFlgAtivo.setSelected(true);
            };
        }

    }//GEN-LAST:event_jCheckBoxFlgAtivoActionPerformed

    private void jTextFieldEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusLost

        if (!jTextFieldEmail.getText().isEmpty() && !TelaUtils.validaEmail(jTextFieldEmail.getText())) {

            Mensagens.mostraMensagemErro("Digite um e-mail válido.");
            jTextFieldEmail.setText("");
            jTextFieldEmail.requestFocus();

        };

    }//GEN-LAST:event_jTextFieldEmailFocusLost

    private void jTextFieldNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNomeFocusLost

        if (!jTextFieldNome.getText().trim().isEmpty()) {
            configurarBotoes(true);
        }
    }//GEN-LAST:event_jTextFieldNomeFocusLost

    private void jTextFieldTelefoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTelefoneFocusLost

        if (!jTextFieldTelefone.getText().trim().isEmpty() && !TelaUtils.validaTelefone(jTextFieldTelefone.getText())) {

            Mensagens.mostraMensagemErro("Digite um telefone válido.");
            jTextFieldTelefone.setText("");
            jTextFieldTelefone.requestFocus();

        }
    }//GEN-LAST:event_jTextFieldTelefoneFocusLost

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
    private br.com.etefgarcia.armarios.model.Aluno aluno;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JCheckBox jCheckBoxFlgAtivo;
    private javax.swing.JComboBox jComboBoxSexo;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelIdAluno;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelEsquerdo;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelInternoDadosAluno;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableTabela;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldIdAluno;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables

    private void removeListeners() {
        jButtonBuscar.removeMouseListener(cadastrarAlunoViewAction);
        jButtonCancelar.removeMouseListener(cadastrarAlunoViewAction);
        jButtonLimpar.removeMouseListener(cadastrarAlunoViewAction);
        jButtonSalvar.removeMouseListener(cadastrarAlunoViewAction);

        jTableTabela.removeMouseListener(cadastrarAlunoViewAction);

    }

    private void adicionaListeners() {

        jButtonBuscar.addMouseListener(cadastrarAlunoViewAction);
        jButtonCancelar.addMouseListener(cadastrarAlunoViewAction);
        jButtonLimpar.addMouseListener(cadastrarAlunoViewAction);
        jButtonSalvar.addMouseListener(cadastrarAlunoViewAction);

        jTableTabela.addMouseListener(cadastrarAlunoViewAction);

    }

    private void formatarCampoTelefone() {
        String telefone = jTextFieldTelefone.getText();

        if (telefone.substring(telefone.indexOf("-"), telefone.length()).trim().length() == 4) {

            String telefoneSaida = "";

            for (int i = 0; i < telefone.length(); i++) {
                if (telefone.charAt(i) == ')') {
                    telefoneSaida += ") ";

                } else {
                    telefoneSaida += telefone.charAt(i);
                }

            }

            jTextFieldTelefone.setText(telefoneSaida);

        }
    }

    private void validaCampoNome() {

        if ((jTextFieldNome.getText().length() > 3) && (!isAtualizar)) {

            TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

            if (this.isAtualizar) {

                alunoController.getThreadConsultarAlunoByNome().start();

            }

        } else if (this.isAtualizar) {

            alunoController.getThreadConsultarAlunoByNome().start();

        } else {

            TelaRenderUtil.habilitarBotao(jButtonBuscar, false);
            TelaRenderUtil.habilitarCampos(jTableTabela, false);
            TelaRenderUtil.habilitarCampos(jScrollPane1, false);

        }

    }

    private void preencherComboSexo() {

        jComboBoxSexo.addItem("Masculino");
        jComboBoxSexo.addItem("Feminino");

    }

    public void limparCampos(boolean limparNome) {

        jTextFieldEmail.setText("");

        if (limparNome) {
            jTextFieldNome.setText("");
        }

        jTextFieldIdAluno.setText("");

        jTextFieldTelefone.setText("");

        jComboBoxSexo.setSelectedIndex(0);

        configurarBotoes(false);

        jScrollPane1.setVisible(false);
        jTableTabela.setVisible(false);

        this.isAtualizar = Boolean.FALSE;
    }

    private void vincularAluno() {

        jTextFieldIdAluno.setText(aluno.getIdAluno() + "");
        jTextFieldNome.setText(aluno.getNome());
        jTextFieldEmail.setText(aluno.getEmail());

        jTextFieldTelefone.setText(aluno.getTelefone());

        jCheckBoxFlgAtivo.setSelected(aluno.getFlgAtivo());

        jComboBoxSexo.setSelectedIndex(aluno.getSexo() == 'M' ? 1 : 2);

    }

    private void mostrarTabela(boolean mostrar) {

        if (listaAlunos != null && mostrar) {
            carregaTabela();

            jTableTabela.setVisible(mostrar);
            jTableTabela.setEnabled(mostrar);

            jScrollPane1.setEnabled(mostrar);
            jScrollPane1.setVisible(mostrar);

            habilitarSalvar(false);

        }

    }

    private void carregaTabela() {

        DefaultTableModel modelTabela = new DefaultTableModel();

        //monta os cabeçalhos das colunas da tabela
        modelTabela.addColumn("Cod");
        modelTabela.addColumn("Nome");
        modelTabela.addColumn("Sexo");
        modelTabela.addColumn("Telefone");
        modelTabela.addColumn("Email");
        modelTabela.addColumn("Ativo");

        for (Aluno c : listaAlunos) {

            //cria uma linha 'generica' com a quantidade de colunas do  modelTabela
            Object[] vetorLinhas = new Object[modelTabela.getColumnCount()];

            //preenche as colunas de cada linha do vetorLinhas para preenchar com os dados do Cliente
            vetorLinhas[0] = c.getIdAluno();
            vetorLinhas[1] = c.getNome();
            vetorLinhas[2] = c.getSexo();
            vetorLinhas[3] = c.getTelefone();
            vetorLinhas[4] = c.getEmail();
            vetorLinhas[5] = c.getFlgAtivoStr();

            //adiciona esta linha ao model da tabela
            modelTabela.addRow(vetorLinhas);

        }

        //coloca checkbox no flgativo
        //ajusta as barras de rolagem
        jTableTabela.setPreferredScrollableViewportSize(jTableTabela.getPreferredSize());

        //desabilita selecao de colunas na tabela
        jTableTabela.setColumnSelectionAllowed(false);

        //desabilita selecao de celulas na tabela
        jTableTabela.setCellSelectionEnabled(false);

        //habilita selecao de unica linha
        jTableTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //habilita selecao de linhas
        jTableTabela.setRowSelectionAllowed(true);

        //habilita tabela zebrada
        jTableTabela.setDefaultRenderer(Object.class, new ZebraCellRenderer());

        //finalmente adiciono o modelo ja pronto à minha tabela
        jTableTabela.setModel(modelTabela);

    }

    public javax.swing.JComponent getPainel() {

        return this.jPanelEsquerdo;

    }

    public Aluno getAluno() {

        this.aluno.setTelefone(jTextFieldTelefone.getText());
        this.aluno.setNome(jTextFieldNome.getText());
        this.aluno.setEmail(jTextFieldEmail.getText());

        try {

            this.aluno.setIdAluno(jTextFieldIdAluno.getText().isEmpty() ? null : Long.parseLong(jTextFieldIdAluno.getText()));

        } catch (Exception ex) {
            this.aluno.setIdAluno(null);
        }

        this.aluno.setFlgAtivo(jCheckBoxFlgAtivo.isSelected());

        this.aluno.setSexo((String) jComboBoxSexo.getSelectedItem());

        return this.aluno;

    }

    public void setAluno(Aluno aluno) {

        if (aluno != null) {

            this.jTextFieldIdAluno.setText(aluno.getIdAluno() + "");
            this.jTextFieldNome.setText(aluno.getNome());
            this.jTextFieldTelefone.setText(aluno.getTelefone());
            this.jTextFieldEmail.setText(aluno.getEmail());

            this.jComboBoxSexo.setSelectedItem(aluno.getSexo() == 'M' ? (String) "Masculino" : (String) "Feminino");

            this.jCheckBoxFlgAtivo.setSelected(aluno.getFlgAtivo());
            this.jCheckBoxFlgAtivo.setEnabled(true);

            habilitarSalvar(aluno.getIdAluno() != null);

            this.isAtualizar = Boolean.TRUE;

        }

    }

    public final void mostrarTabelas(boolean mostrar) {

        mostrarTabela(mostrar);
        this.isAtualizar = mostrar;

    }

    public void setListaAlunos(List<Aluno> listaAlunos) {

        this.listaAlunos = listaAlunos;

    }

    public Aluno getAlunoSelecionado() {

        int linhaSelecionada = jTableTabela.getSelectedRow();

        return listaAlunos.get(linhaSelecionada);

    }

    public String getNome() {

        return this.jTextFieldNome.getText();

    }

}
