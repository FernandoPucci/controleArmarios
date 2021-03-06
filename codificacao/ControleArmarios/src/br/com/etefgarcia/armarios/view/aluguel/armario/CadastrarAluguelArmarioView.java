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
package br.com.etefgarcia.armarios.view.aluguel.armario;

import br.com.etefgarcia.armarios.action.aluguel.armario.CadastrarAluguelArmarioViewAction;
import br.com.etefgarcia.armarios.controller.AluguelArmarioController;
import br.com.etefgarcia.armarios.model.AluguelArmario;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.model.Armario;
import br.com.etefgarcia.armarios.model.Usuario;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.ServiceUtils;
import br.com.etefgarcia.armarios.util.TelaRenderUtil;
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
public class CadastrarAluguelArmarioView extends javax.swing.JFrame {

    private AluguelArmarioController aluguelArmarioController;
    private CadastrarAluguelArmarioViewAction cadastrarAluguelArmarioViewAction;

    private List<Armario> listaArmarios = null;
    private List<AluguelArmario> listaArmariosDevolucao = null;

    private Boolean isDevolucao = Boolean.FALSE;

    private final Runnable threadChecaCampoChave = new Runnable() {

        @Override
        public void run() {
            validaCampoChave();
        }
    };

    private final Runnable threadChecaCampoCodigo = new Runnable() {

        @Override
        public void run() {
            validaCampocodigo();
        }
    };

    public CadastrarAluguelArmarioView(Aluno aluno, Usuario usuario) {

        initComponents();

        this.aluno = aluno;
        this.usuario = usuario;

        checaAluno();

        inicializar();
        configurarBotoes(false);
        configurarCampos();
        configurarItens();

        TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

        mostrarTabela(false);

    }

    public CadastrarAluguelArmarioView(Boolean isDevolucao) {

        initComponents();

        if (this.isDevolucao) {
            this.aluno = null;
            this.usuario = null;

        }
        this.isDevolucao = isDevolucao;

        inicializar();
        configurarBotoes(false);
        configurarCampos();
        configurarItens();

        TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

        jLabelIdAluno.setVisible(!isDevolucao);
        jTextFieldIdAluno.setVisible(!isDevolucao);
        jLabelNome.setVisible(!isDevolucao);
        jTextFieldNome.setVisible(!isDevolucao);

        mostrarTabela(false);

    }

    private void checaAluno() {

        if (aluno == null) {

            Mensagens.mostraMensagemErro(jPanelEsquerdo, "Aluno Selecionado Inválido ou Nulo!");
            this.dispose();

        }

    }

    private void inicializar() {

        jButtonBuscar.setName(!isDevolucao ? ConstantesTelas.BTN_BUSCAR_TELA_BUSCA : ConstantesTelas.BTN_BUSCAR_ARMARIOS_CHAVE);
        jButtonCancelar.setName(ConstantesTelas.BTN_CANCELAR);
        jButtonSalvar.setName(!isDevolucao ? ConstantesTelas.BTN_SALVAR : ConstantesTelas.BTN_DEVOLVER);

        jButtonBuscar.setToolTipText(ConstantesTelas.TT_BTN_BUSCAR);
        jButtonSalvar.setToolTipText(!isDevolucao ? ConstantesTelas.TT_BTN_SALVAR : ConstantesTelas.TT_BTN_DEVOLVER
        );
        jButtonLimpar.setToolTipText(ConstantesTelas.TT_BTN_LIMPAR);
        jButtonCancelar.setToolTipText(ConstantesTelas.TT_BTN_CANCELAR);

        jTextFieldChave.requestFocus();

        if (isDevolucao) {

            jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/accept3.png")));

        }

        this.aluguelArmarioController = new AluguelArmarioController(this);
        this.cadastrarAluguelArmarioViewAction = new CadastrarAluguelArmarioViewAction(aluguelArmarioController);

        removeListeners();
        adicionaListeners();

    }

    private void configurarItens() {

        TelaRenderUtil.habilitarCampos(jTableTabela, false);
        TelaRenderUtil.habilitarCampos(jScrollPane1, false);

    }

    private void configurarCampos() {

        TelaRenderUtil.habilitarCampos(jTextFieldChave, true);

        TelaRenderUtil.habilitarCampos(jTextFieldIdArmario, false);

        jTextFieldChave.requestFocus();

        TelaRenderUtil.habilitarCampos(jTextFieldIdAluno, false);
        TelaRenderUtil.habilitarCampos(jTextFieldNome, false);

        jTextFieldIdAluno.setText(aluno.getIdAluno() + "");
        jTextFieldNome.setText(aluno.getNome());

//          jTextFieldIdAluno.setText(!isDevolucao ? aluno.getIdAluno() + "" : "");
//        jTextFieldNome.setText(!isDevolucao ? aluno.getNome() : "");
    }

    private void configurarBotoes(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jButtonBuscar, habilitar);
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

        armario = new br.com.etefgarcia.armarios.model.Armario();
        aluno = new br.com.etefgarcia.armarios.model.Aluno();
        usuario = new br.com.etefgarcia.armarios.model.Usuario();
        jPanelFundo = new javax.swing.JPanel();
        jPanelEsquerdo = new javax.swing.JPanel();
        jLabelIdAlrmario = new javax.swing.JLabel();
        jTextFieldIdArmario = new javax.swing.JTextField();
        jLabelChave = new javax.swing.JLabel();
        jTextFieldChave = new javax.swing.JTextField();
        jPanelInternoFiltrosBusca = new javax.swing.JPanel();
        jLabelIdAluno = new javax.swing.JLabel();
        jTextFieldIdAluno = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
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
        setTitle(!isDevolucao?(ConstantesTelas.TITULO_JANELA_PRINCIPAL + " - " + ConstantesTelas.TITULO_RETIRAR_CHAVE):(ConstantesTelas.TITULO_JANELA_PRINCIPAL + " - " + ConstantesTelas.TITULO_DEVOLUCAO));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setResizable(false);

        jPanelFundo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelFundo.setMaximumSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setMinimumSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setPreferredSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setRequestFocusEnabled(false);
        jPanelFundo.setLayout(new javax.swing.BoxLayout(jPanelFundo, javax.swing.BoxLayout.LINE_AXIS));

        jPanelEsquerdo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, !isDevolucao?ConstantesTelas.TITULO_RETIRAR_CHAVE:ConstantesTelas.TITULO_DEVOLUCAO
            , javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14)), javax.swing.BorderFactory.createEtchedBorder())); // NOI18N
jPanelEsquerdo.setMaximumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setMinimumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setPreferredSize(new java.awt.Dimension(500, 459));

jLabelIdAlrmario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
jLabelIdAlrmario.setText("Cód.:");
jPanelEsquerdo.add(jLabelIdAlrmario);

jTextFieldIdArmario.setEditable(false);
jTextFieldIdArmario.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
jTextFieldIdArmario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
jTextFieldIdArmario.setDisabledTextColor(new java.awt.Color(210, 20, 8));
jTextFieldIdArmario.setPreferredSize(new java.awt.Dimension(90, 28));
jTextFieldIdArmario.addKeyListener(new java.awt.event.KeyAdapter() {
    public void keyTyped(java.awt.event.KeyEvent evt) {
        jTextFieldIdArmarioKeyTyped(evt);
    }
    });
    jPanelEsquerdo.add(jTextFieldIdArmario);

    jLabelChave.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabelChave.setText("Chave:");
    jPanelEsquerdo.add(jLabelChave);

    jTextFieldChave.setMaximumSize(new java.awt.Dimension(286, 28));
    jTextFieldChave.setMinimumSize(new java.awt.Dimension(286, 28));
    jTextFieldChave.setPreferredSize(new java.awt.Dimension(286, 28));
    jTextFieldChave.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jTextFieldChaveFocusLost(evt);
        }
    });
    jTextFieldChave.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldChaveKeyTyped(evt);
        }
    });
    jPanelEsquerdo.add(jTextFieldChave);

    jPanelInternoFiltrosBusca.setEnabled(false);
    jPanelInternoFiltrosBusca.setOpaque(false);
    jPanelInternoFiltrosBusca.setPreferredSize(new java.awt.Dimension(483, 80));

    jLabelIdAluno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabelIdAluno.setText("RM.:");
    jPanelInternoFiltrosBusca.add(jLabelIdAluno);

    jTextFieldIdAluno.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
    jTextFieldIdAluno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    jTextFieldIdAluno.setDisabledTextColor(new java.awt.Color(210, 20, 8));
    jTextFieldIdAluno.setPreferredSize(new java.awt.Dimension(90, 28));
    jTextFieldIdAluno.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldIdAlunoKeyTyped(evt);
        }
    });
    jPanelInternoFiltrosBusca.add(jTextFieldIdAluno);

    jLabelNome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabelNome.setText("Nome:");
    jPanelInternoFiltrosBusca.add(jLabelNome);

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
    jPanelInternoFiltrosBusca.add(jTextFieldNome);

    jPanelEsquerdo.add(jPanelInternoFiltrosBusca);

    jSeparator1.setMaximumSize(new java.awt.Dimension(450, 15));
    jSeparator1.setMinimumSize(new java.awt.Dimension(450, 15));
    jSeparator1.setName(""); // NOI18N
    jSeparator1.setPreferredSize(new java.awt.Dimension(450, 5));
    jSeparator1.setRequestFocusEnabled(false);
    jPanelEsquerdo.add(jSeparator1);

    jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 290));

    jTableTabela.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    ));
    jTableTabela.setName(ConstantesTelas.ITM_TABELA_CONSULTAR);
    jTableTabela.setSelectionBackground(new java.awt.Color(254, 130, 140));
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
    jButtonSalvar.setName(""); // NOI18N
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

    private void jTextFieldChaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldChaveKeyTyped

        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        SwingUtilities.invokeLater(threadChecaCampoChave);
    }//GEN-LAST:event_jTextFieldChaveKeyTyped

    private void jTextFieldChaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldChaveFocusLost

        if (!jTextFieldChave.getText().trim().isEmpty()) {

            configurarBotoes(true);

            if (isDevolucao) {

                aluguelArmarioController.getThreadCarregarListaAluguelArmarioPorChave().start();

            } else {

                aluguelArmarioController.getThreadConsultarArmarioGeral().start();

            }
        }
    }//GEN-LAST:event_jTextFieldChaveFocusLost

    private void jTextFieldIdArmarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIdArmarioKeyTyped
        SwingUtilities.invokeLater(threadChecaCampoCodigo);
    }//GEN-LAST:event_jTextFieldIdArmarioKeyTyped

    private void jTextFieldIdAlunoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIdAlunoKeyTyped
        SwingUtilities.invokeLater(threadChecaCampoCodigo);
    }//GEN-LAST:event_jTextFieldIdAlunoKeyTyped

    private void jTextFieldNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNomeFocusLost

        if (!jTextFieldNome.getText().trim().isEmpty()) {
            configurarBotoes(true);
        }
    }//GEN-LAST:event_jTextFieldNomeFocusLost

    private void jTextFieldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyTyped

    }//GEN-LAST:event_jTextFieldNomeKeyTyped

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
//            java.util.logging.Logger.getLogger(CadastrarArmarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CadastrarArmarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CadastrarArmarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CadastrarArmarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CadastrarArmarioView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.etefgarcia.armarios.model.Aluno aluno;
    private br.com.etefgarcia.armarios.model.Armario armario;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabelChave;
    private javax.swing.JLabel jLabelIdAlrmario;
    private javax.swing.JLabel jLabelIdAluno;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelEsquerdo;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelInternoFiltrosBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableTabela;
    private javax.swing.JTextField jTextFieldChave;
    private javax.swing.JTextField jTextFieldIdAluno;
    private javax.swing.JTextField jTextFieldIdArmario;
    private javax.swing.JTextField jTextFieldNome;
    private br.com.etefgarcia.armarios.model.Usuario usuario;
    // End of variables declaration//GEN-END:variables

    private void removeListeners() {

        jButtonBuscar.removeMouseListener(cadastrarAluguelArmarioViewAction);
        jButtonCancelar.removeMouseListener(cadastrarAluguelArmarioViewAction);
        jButtonLimpar.removeMouseListener(cadastrarAluguelArmarioViewAction);
        jButtonSalvar.removeMouseListener(cadastrarAluguelArmarioViewAction);

        jTableTabela.removeMouseListener(cadastrarAluguelArmarioViewAction);
    }

    private void adicionaListeners() {

        jButtonBuscar.addMouseListener(cadastrarAluguelArmarioViewAction);
        jButtonCancelar.addMouseListener(cadastrarAluguelArmarioViewAction);
        jButtonLimpar.addMouseListener(cadastrarAluguelArmarioViewAction);
        jButtonSalvar.addMouseListener(cadastrarAluguelArmarioViewAction);

        jTableTabela.addMouseListener(cadastrarAluguelArmarioViewAction);

    }

    private void validaCampoChave() {

        if ((jTextFieldChave.getText().length() > 0) && (listaArmarios != null) && (listaArmarios.size() > 0)) {

            TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

            configurarBotoes(true);

        } else {

            mostrarTabelas(false);
            configurarBotoes(false);

            TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

            TelaRenderUtil.habilitarBotao(jButtonBuscar, jTextFieldChave.getText().length() > 0);

        }
    }

    private void validaCampocodigo() {

        if (jTextFieldIdArmario.getText().trim().length() > 0) {

            configurarBotoes(true);

        } else {

            configurarBotoes(false);

        }

    }

    public void limparCampos() {

        jTextFieldChave.setText("");
        jTextFieldIdArmario.setText("");

        configurarBotoes(false);

        jScrollPane1.setVisible(false);
        jTableTabela.setVisible(false);
    }

    private void mostrarTabela(boolean mostrar) {

        if (listaArmarios != null && mostrar) {

            carregaTabela();

            jTableTabela.setVisible(mostrar);
            jTableTabela.setEnabled(mostrar);

            jScrollPane1.setEnabled(mostrar);
            jScrollPane1.setVisible(mostrar);

            habilitarEditar(false);

        } else if (listaArmariosDevolucao != null && mostrar) {

            carregaTabelaDevolucao();

            jTableTabela.setVisible(mostrar);
            jTableTabela.setEnabled(mostrar);

            jScrollPane1.setEnabled(mostrar);
            jScrollPane1.setVisible(mostrar);

            habilitarEditar(false);
        }

    }

    private void carregaTabelaDevolucao() {

        DefaultTableModel modelTabela = new DefaultTableModel();

        //monta os cabeçalhos das colunas da tabela
        modelTabela.addColumn("Nome");
        modelTabela.addColumn("Data da Retirada");

        for (AluguelArmario c : listaArmariosDevolucao) {

            //cria uma linha 'generica' com a quantidade de colunas do  modelTabela
            Object[] vetorLinhas = new Object[modelTabela.getColumnCount()];

            //preenche as colunas de cada linha do vetorLinhas para preenchar com os dados do Cliente
            vetorLinhas[0] = c.getAluno().getNome();
            vetorLinhas[1] = ServiceUtils.formataData(c.getDataAluguel());

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

    private void carregaTabela() {

        DefaultTableModel modelTabela = new DefaultTableModel();

        //monta os cabeçalhos das colunas da tabela
        modelTabela.addColumn("Cod");
        modelTabela.addColumn("Chave");
        modelTabela.addColumn("Descrição");
        modelTabela.addColumn("Status");
        modelTabela.addColumn("Ativo");

        for (Armario c : listaArmarios) {

            //cria uma linha 'generica' com a quantidade de colunas do  modelTabela
            Object[] vetorLinhas = new Object[modelTabela.getColumnCount()];

            //preenche as colunas de cada linha do vetorLinhas para preenchar com os dados do Cliente
            vetorLinhas[0] = c.getIdArmario();
            vetorLinhas[1] = c.getChave();
            vetorLinhas[2] = c.getDescricao();
            vetorLinhas[3] = c.getFlgOcupadoStr();
            vetorLinhas[4] = c.getFlgAtivoStr();

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

    private void habilitarEditar(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jButtonSalvar, habilitar);
        TelaRenderUtil.habilitarBotao(jButtonLimpar, habilitar);

    }

    public javax.swing.JComponent getPainel() {

        return this.jPanelEsquerdo;

    }

    public Armario getArmario() {

        return this.armario;

    }

    public void setArmario(Armario armario) {

        this.armario = armario;

        if (armario != null) {

            jTextFieldIdArmario.setText(armario.getIdArmario() + "");
            jTextFieldChave.setText(armario.getChave() + "");

        }

    }

    public Aluno getAluno() {

        return this.aluno;

    }

    public final void mostrarTabelas(boolean mostrar) {

        mostrarTabela(mostrar);
        habilitarEditar(mostrar);

    }

    public void setListaArmarios(List<Armario> listaArmarios) {

        this.listaArmarios = listaArmarios;

    }

    public void setListaAluguelArmarios(List<AluguelArmario> listaArmariosDevolucao) {

        this.listaArmariosDevolucao = listaArmariosDevolucao;

    }

    public String getChave() {

        return jTextFieldChave.getText().trim();

    }

    public String getCodigo() {

        return jTextFieldIdArmario.getText();

    }

    public Usuario getUsuario() {

        return this.usuario;

    }

    public Armario getArmarioSelecionado() {

        int linhaSelecionada = jTableTabela.getSelectedRow();

        return listaArmarios.get(linhaSelecionada);
    }

    public AluguelArmario getArmarioDevolucaoSelecionado() {

        if (isDevolucao) {
            int linhaSelecionada = jTableTabela.getSelectedRow();

            if (linhaSelecionada < 0) {
                Mensagens.mostraMensagemAlerta(jPanelEsquerdo, "Primeiramente, selecione um Registro na tabela.");

                return null;
            }
            
            return listaArmariosDevolucao.get(linhaSelecionada);
        }

        return null;
    }

}
