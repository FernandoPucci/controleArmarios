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
package br.com.etefgarcia.armarios.view.armario;

import br.com.etefgarcia.armarios.action.armario.ConsultarArmarioViewAction;
import br.com.etefgarcia.armarios.controller.ArmarioController;
import br.com.etefgarcia.armarios.model.Armario;
import br.com.etefgarcia.armarios.util.TelaRenderUtil;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import br.com.etefgarcia.armarios.util.telas.render.ZebraCellRenderer;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando-pucci
 *
 */
public class ConsultarArmarioView extends javax.swing.JFrame {

    private ArmarioController armarioController;
    private ConsultarArmarioViewAction consultarArmarioViewAction;
    private List<Armario> listaArmarios = null;

    private Boolean consultaOcupados = Boolean.FALSE;

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

    public ConsultarArmarioView() {

        initComponents();

        inicializar();
        configurarBotoes(false);
        configurarBotoesFiltro(true);
        inicializarRadioButtons();
        configurarCampos();
        configurarItens();

        TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

        mostrarTabela(false);

    }

    //construtor para Chaves em Uso (armarios Ocupados)
    public ConsultarArmarioView(Boolean ocupados) {

        initComponents();

        this.consultaOcupados = ocupados;

        inicializar();

        configurarBotoes(false);

        configurarBotoesFiltro(false);
        inicializarRadioButtons();

        configurarCampos();
        configurarItens();

        jRadioButtonOcupados.setSelected(true);
        jRadioButtonSomenteAtivos.setSelected(true);

        jButtonEditar.setVisible(false);
        jButtonLimpar.setVisible(false);

        TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

        jTextFieldChave.requestFocus();

        //  mostrarTabela(false);
        armarioController.getThreadConsultarArmarioGeral().start();

    }

    private void inicializar() {

        jButtonBuscar.setName(ConstantesTelas.BTN_BUSCAR_TELA_BUSCA);
        jButtonCancelar.setName(ConstantesTelas.BTN_CANCELAR);
        jButtonEditar.setName(ConstantesTelas.BTN_EDITAR);
        jButtonCancelar.setName(ConstantesTelas.BTN_CANCELAR_CONSULTA_ARMARIOS);

        jRadioButtonSomenteAtivos.setName(ConstantesTelas.BTN_FILTRO_ATIVOS);
        jRadioButtonSomenteInativos.setName(ConstantesTelas.BTN_FILTRO_INATIVOS);
        jRadioButtonTodos.setName(ConstantesTelas.BTN_FILTRO_TODOS);

        jRadioButtonTodosLivresOcupados.setName(ConstantesTelas.BTN_FILTRO_ARMARIOS_TODOS);
        jRadioButtonLivres.setName(ConstantesTelas.BTN_FILTRO_ARMARIOS_LIVRES);
        jRadioButtonOcupados.setName(ConstantesTelas.BTN_FILTRO_ARMARIOS_OCUPADOS);

        jButtonBuscar.setToolTipText(ConstantesTelas.TT_BTN_BUSCAR);
        jButtonEditar.setToolTipText(ConstantesTelas.TT_BTN_EDITAR);
        jButtonLimpar.setToolTipText(ConstantesTelas.TT_BTN_LIMPAR_PESQUISA);
        jButtonCancelar.setToolTipText(ConstantesTelas.TT_BTN_CANCELAR);

        jTextFieldChave.requestFocus();

        this.armarioController = new ArmarioController(this);
        this.consultarArmarioViewAction = new ConsultarArmarioViewAction(armarioController);

        removeListeners();
        adicionaListeners();

    }

    private void configurarItens() {

        TelaRenderUtil.habilitarCampos(jTableTabela, false);
        TelaRenderUtil.habilitarCampos(jScrollPane1, false);

    }

    private void configurarCampos() {

        jTextFieldIdArmario.setEnabled(true);
        jTextFieldIdArmario.setEditable(true);

    }

    private void configurarBotoes(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jButtonBuscar, habilitar);
        TelaRenderUtil.habilitarBotao(jButtonEditar, habilitar);
        TelaRenderUtil.habilitarBotao(jButtonLimpar, habilitar);

        configurarBotoesFiltro(habilitar);

    }

    private void configurarBotoesFiltro(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jRadioButtonSomenteAtivos, habilitar);
        TelaRenderUtil.habilitarBotao(jRadioButtonSomenteInativos, habilitar);
        TelaRenderUtil.habilitarBotao(jRadioButtonTodos, habilitar);

        TelaRenderUtil.habilitarBotao(jRadioButtonTodosLivresOcupados, habilitar);
        TelaRenderUtil.habilitarBotao(jRadioButtonLivres, habilitar);
        TelaRenderUtil.habilitarBotao(jRadioButtonOcupados, habilitar);

        jRadioButtonTodosLivresOcupados.setSelected(true);
        jRadioButtonSomenteAtivos.setSelected(true);
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
        jPanelFundo = new javax.swing.JPanel();
        jPanelEsquerdo = new javax.swing.JPanel();
        jLabelIdAluno = new javax.swing.JLabel();
        jTextFieldIdArmario = new javax.swing.JTextField();
        jLabelChave = new javax.swing.JLabel();
        jTextFieldChave = new javax.swing.JTextField();
        jPanelInternoFiltrosBusca = new javax.swing.JPanel();
        jRadioButtonTodosLivresOcupados = new javax.swing.JRadioButton();
        jRadioButtonLivres = new javax.swing.JRadioButton();
        jRadioButtonOcupados = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jRadioButtonTodos = new javax.swing.JRadioButton();
        jRadioButtonSomenteAtivos = new javax.swing.JRadioButton();
        jRadioButtonSomenteInativos = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTabela = new javax.swing.JTable();
        jPanelBotoes = new javax.swing.JPanel();
        jButtonBuscar = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        jButtonEditar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(ConstantesTelas.TITULO_JANELA_PRINCIPAL + " - " + ConstantesTelas.TITULO_CONSULTAR_ARMARIO);
        setMinimumSize(new java.awt.Dimension(640, 480));
        setResizable(false);

        jPanelFundo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelFundo.setMaximumSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setMinimumSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setPreferredSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setRequestFocusEnabled(false);
        jPanelFundo.setLayout(new javax.swing.BoxLayout(jPanelFundo, javax.swing.BoxLayout.LINE_AXIS));

        jPanelEsquerdo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, ConstantesTelas.TITULO_CONSULTAR_ARMARIO
            , javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14)), javax.swing.BorderFactory.createEtchedBorder())); // NOI18N
jPanelEsquerdo.setMaximumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setMinimumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setPreferredSize(new java.awt.Dimension(500, 459));

jLabelIdAluno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
jLabelIdAluno.setText("Cód.:");
jPanelEsquerdo.add(jLabelIdAluno);

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

    jRadioButtonTodosLivresOcupados.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jRadioButtonTodosLivresOcupados.setText("Todos");
    jPanelInternoFiltrosBusca.add(jRadioButtonTodosLivresOcupados);

    jRadioButtonLivres.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jRadioButtonLivres.setText("Somente Livres");
    jPanelInternoFiltrosBusca.add(jRadioButtonLivres);

    jRadioButtonOcupados.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jRadioButtonOcupados.setText("Somente Ocupados");
    jPanelInternoFiltrosBusca.add(jRadioButtonOcupados);

    jSeparator2.setMaximumSize(new java.awt.Dimension(410, 6));
    jSeparator2.setMinimumSize(new java.awt.Dimension(410, 6));
    jSeparator2.setName(""); // NOI18N
    jSeparator2.setPreferredSize(new java.awt.Dimension(410, 6));
    jSeparator2.setRequestFocusEnabled(false);
    jPanelInternoFiltrosBusca.add(jSeparator2);

    jRadioButtonTodos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jRadioButtonTodos.setText("Todos");
    jPanelInternoFiltrosBusca.add(jRadioButtonTodos);

    jRadioButtonSomenteAtivos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jRadioButtonSomenteAtivos.setText("Somente Ativos");
    jPanelInternoFiltrosBusca.add(jRadioButtonSomenteAtivos);

    jRadioButtonSomenteInativos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jRadioButtonSomenteInativos.setText("Somente Inativos");
    jPanelInternoFiltrosBusca.add(jRadioButtonSomenteInativos);

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

    jButtonEditar.setBackground(new java.awt.Color(242, 241, 240));
    jButtonEditar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
    jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/edit3.png"))); // NOI18N
    jButtonEditar.setName(""); // NOI18N
    jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonEditarActionPerformed(evt);
        }
    });
    jPanelBotoes.add(jButtonEditar);

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

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed

    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jTextFieldChaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldChaveKeyTyped

        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

        SwingUtilities.invokeLater(threadChecaCampoChave);
    }//GEN-LAST:event_jTextFieldChaveKeyTyped

    private void jTextFieldChaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldChaveFocusLost

        if (!jTextFieldChave.getText().trim().isEmpty() && !consultaOcupados) {
            configurarBotoes(true);
        }
    }//GEN-LAST:event_jTextFieldChaveFocusLost

    private void jTextFieldIdArmarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIdArmarioKeyTyped
        SwingUtilities.invokeLater(threadChecaCampoCodigo);
    }//GEN-LAST:event_jTextFieldIdArmarioKeyTyped

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
    private br.com.etefgarcia.armarios.model.Armario armario;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JLabel jLabelChave;
    private javax.swing.JLabel jLabelIdAluno;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelEsquerdo;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelInternoFiltrosBusca;
    private javax.swing.JRadioButton jRadioButtonLivres;
    private javax.swing.JRadioButton jRadioButtonOcupados;
    private javax.swing.JRadioButton jRadioButtonSomenteAtivos;
    private javax.swing.JRadioButton jRadioButtonSomenteInativos;
    private javax.swing.JRadioButton jRadioButtonTodos;
    private javax.swing.JRadioButton jRadioButtonTodosLivresOcupados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableTabela;
    private javax.swing.JTextField jTextFieldChave;
    private javax.swing.JTextField jTextFieldIdArmario;
    // End of variables declaration//GEN-END:variables

    private void removeListeners() {

        jButtonBuscar.removeMouseListener(consultarArmarioViewAction);
        jButtonCancelar.removeMouseListener(consultarArmarioViewAction);
        jButtonLimpar.removeMouseListener(consultarArmarioViewAction);
        jButtonEditar.removeMouseListener(consultarArmarioViewAction);

        jTableTabela.removeMouseListener(consultarArmarioViewAction);

        jRadioButtonSomenteAtivos.removeMouseListener(consultarArmarioViewAction);
        jRadioButtonSomenteInativos.removeMouseListener(consultarArmarioViewAction);
        jRadioButtonTodos.removeMouseListener(consultarArmarioViewAction);

        jRadioButtonTodosLivresOcupados.removeMouseListener(consultarArmarioViewAction);
        jRadioButtonOcupados.removeMouseListener(consultarArmarioViewAction);
        jRadioButtonLivres.removeMouseListener(consultarArmarioViewAction);

    }

    private void adicionaListeners() {

        jButtonBuscar.addMouseListener(consultarArmarioViewAction);
        jButtonCancelar.addMouseListener(consultarArmarioViewAction);
        jButtonLimpar.addMouseListener(consultarArmarioViewAction);
        jButtonEditar.addMouseListener(consultarArmarioViewAction);

        jTableTabela.addMouseListener(consultarArmarioViewAction);

        jRadioButtonSomenteAtivos.addMouseListener(consultarArmarioViewAction);
        jRadioButtonSomenteInativos.addMouseListener(consultarArmarioViewAction);
        jRadioButtonTodos.addMouseListener(consultarArmarioViewAction);

        jRadioButtonTodosLivresOcupados.addMouseListener(consultarArmarioViewAction);
        jRadioButtonOcupados.addMouseListener(consultarArmarioViewAction);
        jRadioButtonLivres.addMouseListener(consultarArmarioViewAction);

    }

    private void validaCampoChave() {

        if ((jTextFieldChave.getText().length() > 0) && (listaArmarios != null) && (listaArmarios.size() > 0)) {

            TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

            configurarBotoes(true);

            if (!consultaOcupados) {
                configurarBotoesFiltro(true);
            }

            armarioController.getThreadConsultarArmarioGeral().start();

        } else {

            mostrarTabelas(false);
            configurarBotoes(false);

            TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

            if (!consultaOcupados) {
                configurarBotoesFiltro(true);
            }
            
            TelaRenderUtil.habilitarBotao(jButtonBuscar, jTextFieldChave.getText().length() > 0);

        }
    }

    private void validaCampocodigo() {

        if (!consultaOcupados) {

            if (jTextFieldIdArmario.getText().trim().length() > 0) {

                configurarBotoes(true);
                configurarBotoesFiltro(false);

            } else {

                configurarBotoes(false);

            }
        }

    }

    public void limparCampos() {

        jTextFieldChave.setText("");
        jTextFieldIdArmario.setText("");

        configurarBotoes(false);
        configurarBotoesFiltro(true);

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

        }

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

        TelaRenderUtil.habilitarBotao(jButtonEditar, habilitar);
        TelaRenderUtil.habilitarBotao(jButtonLimpar, habilitar);

    }

    private void inicializarRadioButtons() {

        ButtonGroup bgFiltrosAtivos = new ButtonGroup();

        bgFiltrosAtivos.add(jRadioButtonTodos);
        bgFiltrosAtivos.add(jRadioButtonSomenteAtivos);
        bgFiltrosAtivos.add(jRadioButtonSomenteInativos);

        ButtonGroup bgFiltrosOcupados = new ButtonGroup();

        bgFiltrosOcupados.add(jRadioButtonTodosLivresOcupados);
        bgFiltrosOcupados.add(jRadioButtonOcupados);
        bgFiltrosOcupados.add(jRadioButtonLivres);

    }

    public javax.swing.JComponent getPainel() {

        return this.jPanelEsquerdo;

    }

    public Armario getArmario() {

        return this.armario;

    }

    public final void mostrarTabelas(boolean mostrar) {

        mostrarTabela(mostrar);
        habilitarEditar(mostrar);

    }

    public void setListaArmarios(List<Armario> listaArmarios) {

        this.listaArmarios = listaArmarios;

    }

    public String getChave() {

        return jTextFieldChave.getText().trim();

    }

    public String getCodigo() {

        return jTextFieldIdArmario.getText();

    }

    public Armario getArmarioSelecionado() {

        int linhaSelecionada = jTableTabela.getSelectedRow();

        return listaArmarios.get(linhaSelecionada);
    }

    public String getFiltroAtivosSelecionado() {

        if (jRadioButtonSomenteAtivos.isSelected()) {

            return jRadioButtonSomenteAtivos.getName();

        } else if (jRadioButtonSomenteInativos.isSelected()) {

            return jRadioButtonSomenteInativos.getName();

        } else if (jRadioButtonTodos.isSelected()) {

            return jRadioButtonTodos.getName();

        }

        return jRadioButtonTodos.getName();

    }

    public String getFiltroArmariosLivresSelecionado() {

        if (jRadioButtonLivres.isSelected()) {

            return jRadioButtonLivres.getName();

        } else if (jRadioButtonOcupados.isSelected()) {

            return jRadioButtonOcupados.getName();

        } else if (jRadioButtonTodosLivresOcupados.isSelected()) {

            return jRadioButtonTodosLivresOcupados.getName();

        }

        return jRadioButtonTodosLivresOcupados.getName();

    }

}
