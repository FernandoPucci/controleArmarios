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

import br.com.etefgarcia.armarios.action.aluno.ConsultarAlunoViewAction;
import br.com.etefgarcia.armarios.controller.AlunoController;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.model.Usuario;
import br.com.etefgarcia.armarios.util.Mensagens;
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
public class ConsultarAlunoView extends javax.swing.JFrame {

    private AlunoController alunoController;
    private ConsultarAlunoViewAction consultarAlunoViewAction;
    private List<Aluno> listaAlunos = null;

    private Usuario usuario = null;

    private Boolean isRetiradaChave = Boolean.FALSE;

    private final Runnable threadChecaCampoNome = new Runnable() {

        @Override
        public void run() {
            validaCampoNome();
        }
    };

    private final Runnable threadChecaCampoCodigo = new Runnable() {

        @Override
        public void run() {
            validaCampocodigo();
        }
    };

    public ConsultarAlunoView() {

        initComponents();

        inicializar();
        configurarBotoes(false, true);
        configurarBotoesFiltro(true);
        inicializarRadioButtons();
        configurarCampos();
        configurarItens();

        TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

        mostrarTabela(false);

    }

    public ConsultarAlunoView(Boolean isRetiradaChave, Usuario usuario) {

        initComponents();

        this.isRetiradaChave = isRetiradaChave;

        if (usuario != null) {
            this.usuario = usuario;

            inicializar();
            configurarBotoes(false, true);
            configurarBotoesFiltro(!isRetiradaChave);
            inicializarRadioButtons();
            configurarCampos();
            configurarItens();

            TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

            mostrarTabela(false);
        } else {

            Mensagens.mostraMensagemErro(jPanelEsquerdo, "Usuario nulo, impossivel carregar.");

        }

    }

    private void inicializar() {

        jButtonBuscar.setName(!isRetiradaChave ? ConstantesTelas.BTN_BUSCAR_TELA_BUSCA : ConstantesTelas.BTN_BUSCAR_TELA_BUSCA_ALUNO_RETIRADA);
        jButtonCancelar.setName(ConstantesTelas.BTN_CANCELAR);
        jButtonEditar.setName(!isRetiradaChave ? ConstantesTelas.BTN_EDITAR : ConstantesTelas.BTN_CARREGA_RETIRAR_CHAVE);
        jButtonCancelar.setName(ConstantesTelas.BTN_CANCELAR_CONSULTA_ALUNOS);

        jRadioButtonSomenteAtivos.setName(ConstantesTelas.BTN_FILTRO_ATIVOS);
        jRadioButtonSomenteInativos.setName(ConstantesTelas.BTN_FILTRO_INATIVOS);
        jRadioButtonTodos.setName(ConstantesTelas.BTN_FILTRO_TODOS);

        jButtonBuscar.setToolTipText(!isRetiradaChave ? ConstantesTelas.TT_BTN_BUSCAR : ConstantesTelas.TT_BTN_BUSCAR_ALUNO_RETIRADA);
        jButtonEditar.setToolTipText(!isRetiradaChave ? ConstantesTelas.TT_BTN_EDITAR : ConstantesTelas.TT_BTN_CARREGAR_RETIRADA_CHAVE);
        jButtonLimpar.setToolTipText(ConstantesTelas.TT_BTN_LIMPAR_PESQUISA);
        jButtonCancelar.setToolTipText(ConstantesTelas.TT_BTN_CANCELAR);

        jTextFieldNome.requestFocus();

        this.alunoController = new AlunoController(this);
        this.consultarAlunoViewAction = new ConsultarAlunoViewAction(alunoController);

        if (isRetiradaChave) {
            alunoController.getThreadConsultarAlunoAtivo().start();
            jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/chave3.png")));
        }

        removeListeners();
        adicionaListeners();

    }

    private void configurarItens() {

        TelaRenderUtil.habilitarCampos(jTableTabela, false);
        TelaRenderUtil.habilitarCampos(jScrollPane1, false);

    }

    private void configurarCampos() {

        jTextFieldIdAluno.setEnabled(true);
        jTextFieldIdAluno.setEditable(true);

    }

    private boolean checaAlunoSelecionado(boolean isBusca) {

        if (!isBusca) {

            return listaAlunos != null && !listaAlunos.isEmpty() && getAlunoSelecionado() != null;

        } else {

            return true;

        }

    }

    private void configurarBotoes(boolean habilitar, boolean isBusca) {

        TelaRenderUtil.habilitarBotao(jButtonBuscar, habilitar);
        TelaRenderUtil.habilitarBotao(jButtonEditar, !isRetiradaChave ? habilitar : checaAlunoSelecionado(isBusca));
        TelaRenderUtil.habilitarBotao(jButtonLimpar, habilitar);

        configurarBotoesFiltro(!isRetiradaChave ? habilitar : false);

    }

    private void configurarBotoesFiltro(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jRadioButtonSomenteAtivos, habilitar);
        TelaRenderUtil.habilitarBotao(jRadioButtonSomenteInativos, habilitar);
        TelaRenderUtil.habilitarBotao(jRadioButtonTodos, habilitar);

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

        aluno = new br.com.etefgarcia.armarios.model.Aluno();
        jPanelFundo = new javax.swing.JPanel();
        jPanelEsquerdo = new javax.swing.JPanel();
        jLabelIdAluno = new javax.swing.JLabel();
        jTextFieldIdAluno = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jPanelInternoFiltrosBusca = new javax.swing.JPanel();
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
        setTitle(ConstantesTelas.TITULO_JANELA_PRINCIPAL + " - " + ConstantesTelas.TITULO_CONSULTAR_ALUNO);
        setMinimumSize(new java.awt.Dimension(640, 480));
        setResizable(false);

        jPanelFundo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelFundo.setMaximumSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setMinimumSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setPreferredSize(new java.awt.Dimension(640, 480));
        jPanelFundo.setRequestFocusEnabled(false);
        jPanelFundo.setLayout(new javax.swing.BoxLayout(jPanelFundo, javax.swing.BoxLayout.LINE_AXIS));

        jPanelEsquerdo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, ConstantesTelas.TITULO_CONSULTAR_ALUNO
            , javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14)), javax.swing.BorderFactory.createEtchedBorder())); // NOI18N
jPanelEsquerdo.setMaximumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setMinimumSize(new java.awt.Dimension(500, 459));
jPanelEsquerdo.setPreferredSize(new java.awt.Dimension(500, 459));

jLabelIdAluno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
jLabelIdAluno.setText("RM.:");
jPanelEsquerdo.add(jLabelIdAluno);

jTextFieldIdAluno.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
jTextFieldIdAluno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
jTextFieldIdAluno.setDisabledTextColor(new java.awt.Color(210, 20, 8));
jTextFieldIdAluno.setPreferredSize(new java.awt.Dimension(90, 28));
jTextFieldIdAluno.addKeyListener(new java.awt.event.KeyAdapter() {
    public void keyTyped(java.awt.event.KeyEvent evt) {
        jTextFieldIdAlunoKeyTyped(evt);
    }
    });
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

    jPanelInternoFiltrosBusca.setEnabled(false);
    jPanelInternoFiltrosBusca.setOpaque(false);
    jPanelInternoFiltrosBusca.setPreferredSize(new java.awt.Dimension(483, 80));

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

//        if (listaAlunos == null || listaAlunos.isEmpty() || getAlunoSelecionado() == null) {
//
//            Mensagens.mostraMensagemAlerta(jPanelEsquerdo, "Selecione um aluno da lista!");
//
//        }

    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jTextFieldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyTyped

        SwingUtilities.invokeLater(threadChecaCampoNome);
    }//GEN-LAST:event_jTextFieldNomeKeyTyped

    private void jTextFieldNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNomeFocusLost

        if (!jTextFieldNome.getText().trim().isEmpty()) {
            configurarBotoes(true, true);
        }
    }//GEN-LAST:event_jTextFieldNomeFocusLost

    private void jTextFieldIdAlunoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIdAlunoKeyTyped
        SwingUtilities.invokeLater(threadChecaCampoCodigo);
    }//GEN-LAST:event_jTextFieldIdAlunoKeyTyped

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
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JLabel jLabelIdAluno;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelEsquerdo;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelInternoFiltrosBusca;
    private javax.swing.JRadioButton jRadioButtonSomenteAtivos;
    private javax.swing.JRadioButton jRadioButtonSomenteInativos;
    private javax.swing.JRadioButton jRadioButtonTodos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableTabela;
    private javax.swing.JTextField jTextFieldIdAluno;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables

    private void removeListeners() {

        jButtonBuscar.removeMouseListener(consultarAlunoViewAction);
        jButtonCancelar.removeMouseListener(consultarAlunoViewAction);
        jButtonLimpar.removeMouseListener(consultarAlunoViewAction);
        jButtonEditar.removeMouseListener(consultarAlunoViewAction);

        jTableTabela.removeMouseListener(consultarAlunoViewAction);

        jRadioButtonSomenteAtivos.removeMouseListener(consultarAlunoViewAction);
        jRadioButtonSomenteInativos.removeMouseListener(consultarAlunoViewAction);
        jRadioButtonTodos.removeMouseListener(consultarAlunoViewAction);

    }

    private void adicionaListeners() {

        jButtonBuscar.addMouseListener(consultarAlunoViewAction);
        jButtonCancelar.addMouseListener(consultarAlunoViewAction);
        jButtonLimpar.addMouseListener(consultarAlunoViewAction);
        jButtonEditar.addMouseListener(consultarAlunoViewAction);

        jTableTabela.addMouseListener(consultarAlunoViewAction);

        jRadioButtonSomenteAtivos.addMouseListener(consultarAlunoViewAction);
        jRadioButtonSomenteInativos.addMouseListener(consultarAlunoViewAction);
        jRadioButtonTodos.addMouseListener(consultarAlunoViewAction);

    }

    private void validaCampoNome() {

        if (jTextFieldNome.getText().length() > 3) {

            TelaRenderUtil.habilitarBotao(jButtonBuscar, true);

            configurarBotoes(true, true);
            configurarBotoesFiltro(!isRetiradaChave);

            alunoController.getThreadConsultarAlunoGeral().start();

        } else {
            mostrarTabelas(false);
            configurarBotoes(false, true);

            TelaRenderUtil.habilitarBotao(jButtonBuscar, true);
            configurarBotoesFiltro(!isRetiradaChave);

            TelaRenderUtil.habilitarBotao(jButtonBuscar, false);

        }

    }

    private void validaCampocodigo() {

        if (jTextFieldIdAluno.getText().trim().length() > 0) {

            configurarBotoes(true, true);
            configurarBotoesFiltro(false);

        } else {

            configurarBotoes(false, true);

        }

    }

    public void limparCampos() {

        jTextFieldNome.setText("");
        jTextFieldIdAluno.setText("");

        configurarBotoes(false, true);

        jScrollPane1.setVisible(false);
        jTableTabela.setVisible(false);

        this.aluno = null;
        this.listaAlunos = null;

        inicializar();

    }

    private void mostrarTabela(boolean mostrar) {

        if (listaAlunos != null && mostrar) {
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
        modelTabela.addColumn("RM");
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

    private void habilitarEditar(boolean habilitar) {

        TelaRenderUtil.habilitarBotao(jButtonEditar, habilitar);
        TelaRenderUtil.habilitarBotao(jButtonLimpar, habilitar);

    }

    private void inicializarRadioButtons() {

        ButtonGroup bg1 = new ButtonGroup();

        bg1.add(jRadioButtonTodos);
        bg1.add(jRadioButtonSomenteAtivos);
        bg1.add(jRadioButtonSomenteInativos);

    }

    public javax.swing.JComponent getPainel() {

        return this.jPanelEsquerdo;

    }

    public Aluno getAluno() {

        return this.aluno;

    }

    public final void mostrarTabelas(boolean mostrar) {

        mostrarTabela(mostrar);
        habilitarEditar(mostrar);

    }

    public void setListaAlunos(List<Aluno> listaAlunos) {

        this.listaAlunos = listaAlunos;

    }

    public String getNome() {

        return jTextFieldNome.getText();

    }

    public String getCodigo() {

        return jTextFieldIdAluno.getText();

    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Aluno getAlunoSelecionado() {

        int linhaSelecionada = jTableTabela.getSelectedRow();

        if (linhaSelecionada < 0) {

            Mensagens.mostraMensagemAlerta(jPanelEsquerdo, "Primeiramente, selecione um Aluno na tabela.");

            return null;

        } else {

            return listaAlunos.get(linhaSelecionada);

        }
    }

    public String getFiltroSelecionado() {

        if (jRadioButtonSomenteAtivos.isSelected()) {

            return jRadioButtonSomenteAtivos.getName();

        } else if (jRadioButtonSomenteInativos.isSelected()) {

            return jRadioButtonSomenteInativos.getName();

        } else if (jRadioButtonTodos.isSelected()) {

            return jRadioButtonTodos.getName();

        }

        return jRadioButtonTodos.getName();

    }

}
