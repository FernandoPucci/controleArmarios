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
package br.com.etefgarcia.armarios.util.constantes.telas;

/**
 *
 * @author fernando-pucci
 */
public class ConstantesTelas {

    public static final int PISCA_SUCESSO = 1;
    public static final int PISCA_ERRO = 2;
    public static final int PISCA_INFO = 3;

    public static final String ERR_TOKEN_LISTA = "\n- ";

    //Janelas
    public static final String TITULO_JANELA_PRINCIPAL = ".:: CONTROLE DE ARMARIOS";
    public static final String SUBTITULO_JANELA_CONFIG = "Configuração Inicial";

    public static final String ETEC = " - Etec Francisco Garcia";

    public static final String TITULO_ERRO = ".:: ERRO";
    public static final String TITULO_SUCESSO = ".:: SUCESSO";
    public static final String TITULO_PERGUNTA = ".::QUESTAO";
    public static final String TITULO_ALERTA = ".:: ATENÇAO";

    public static final String TITULO_CADASTRAR_ALUNO = "Cadastrar Aluno";
    public static final String TITULO_CONSULTAR_ALUNO = "Consultar Aluno";
    public static final String TITULO_CARREGAR_ARQUIVO = "Carregar Arquivo de Alunos";
    public static final String TITULO_RETIRAR_CHAVE = "Retirar Chave";

    public static final String TITULO_CADASTRAR_ARMARIO = "Cadastrar Armario";
    public static final String TITULO_CONSULTAR_ARMARIO = "Consultar Armario";

    public static final String TITULO_PROCESSAMENTO = "Processando, aguarde.";

    public static final String TEXTO_INICIAL_TELA_CARREGAR_ARQUIVO = "Clique no botão da lupa para selecionar o arquivo.";

    //Botoes
    public static final String BTN_CONSULTAR_ALUNOS = "buscarAlunos";
    public static final String BTN_CONSULTAR_ARMARIOS = "buscarArmarios";
    
    public static final String BTN_CONSULTAR_EMPRESTIMOS = "buscarEmprestimos";
    
    public static final String BTN_CONSULTAR_CHAVES_EM_USO = "buscarChavesEmUso";
    public static final String BTN_DEVOLVER_CHAVES = "devolverChaves";
    public static final String BTN_RETIRAR_CHAVES = "retirarChaves";

    public static final String BTN_CANCELAR = "cancelar";
    public static final String BTN_SALVAR = "salvar";
    public static final String BTN_BUSCAR = "buscar";
    public static final String BTN_BUSCAR_TELA_BUSCA = "buscarConsulta";
    public static final String BTN_BUSCAR_TELA_BUSCA_ALUNO_RETIRADA = "consultarAlunoRetirada";
    public static final String BTN_LIMPAR = "limpar";
    public static final String BTN_EDITAR = "edit";
    public static final String BTN_CARREGA_RETIRAR_CHAVE = "chamaTelaRetirarChave";
    public static final String BTN_SALVAR_CONFIGURACAO = "salvarConfiguracao";
    public static final String BTN_TESTAR_CONFIGURACAO = "testarConfiguracao";

    public static final String BTN_SALVAR_ATUALIZAR_CONFIGURACAO = "atualizarConfiguracao";
    public static final String BTN_CANCELAR_ATUALIZAR_CONFIGURACAO = "cancelarAtualizarConfiguracao";
    public static final String BTN_CANCELAR_CONSULTA_ALUNOS = "cancelarBuscarAluno";
    public static final String BTN_CANCELAR_CONSULTA_ARMARIOS = "cancelarBuscarArmarios";

    public static final String BTN_SALVAR_PLANILHA = "salvarPlanilhaPOI";
    public static final String BTN_CARREGAR_ARQUIVO = "fileChoose";
    public static final String BTN_CANCELAR_ENVIO = "cancelarTelaUpload";

    public static final String BTN_FILTRO_TODOS = "consultarFiltroTodos";
    public static final String BTN_FILTRO_ATIVOS = "consultarFiltroAtivos";
    public static final String BTN_FILTRO_INATIVOS = "consultarFiltroInativos";

    public static final String BTN_FILTRO_ARMARIOS_TODOS = "consultarFiltroTodosArmarios";
    public static final String BTN_FILTRO_ARMARIOS_OCUPADOS = "consultarFiltroArmariosOc";
    public static final String BTN_FILTRO_ARMARIOS_LIVRES = "consultarFiltroArmariosLv";

    public static final String BTN_SAIR = "sair";

    //Itens
    public static final String ITM_TABELA = "tabelaSelecionar";
    public static final String ITM_TABELA_CONSULTAR = "tabelaSelecionarConsultar";

    //Tooltips
    public static final String TT_BTN_BUSCAR_ALUNOS = "Pesquisar Alunos...";
    public static final String TT_BTN_BUSCAR_ARMARIOS = "Pesquisar Armários...";
    public static final String TT_BTN_RETIRADA_CHAVES = "Consultar Aluno e Retirar Chaves...";

        public static final String TT_BTN_CONSULTAR_EMPRESTIMOS = "Ver Empréstimos";
    
    public static final String TT_BTN_CONSULTAR_CHAVES_EM_USO = "Pesquisar Chaves em uso...";
    public static final String TT_BTN_DEVOLVER_CHAVES = "Efetuar Devolução de Chaves";
    
    
    
    public static final String TT_BTN_CANCELAR = "Fechar Tela";
    public static final String TT_BTN_SAIR_PROGRAMA = "Encerrar Programa.";
    public static final String TT_BTN_SALVAR = "Gravar";
    public static final String TT_BTN_EDITAR = "Abrir tela de Edição";
    public static final String TT_BTN_BUSCAR = "Pesquisar...";
    public static final String TT_BTN_LIMPAR = "Limpar Campos";
    public static final String TT_BTN_LIMPAR_PESQUISA = "Limpar Pesquisa";
    public static final String TT_BTN_SALVAR_UPLOAD = " Carregar Planilha para o Banco de Dados";
    public static final String TT_BTN_CARREGAR_ARQUIVO = "Escolher arquivo";
    public static final String TT_BTN_BUSCAR_ALUNO_RETIRADA = "Consultar Aluno para Retirada Chaves";
    public static final String TT_BTN_CARREGAR_RETIRADA_CHAVE = "Consultar chaves para retirada";

}
