/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etefgarcia.armarios.util.constantes;

/**
 *
 * @author fernando-pucci
 */
public class Constantes {

    //VERSAO
    public static final String VERSION = "0.1";

    //AMBIENTE
    public static final String ENVIRONMENT = "DEV";

    //DIRETORIO DE TRABALHO DO SISTEMA (WINDOWS)
    public static final String FOLDER_PATH = "C:\\ControleArmarios\\prop";

    //NOME DO ARQUIVO DE CONFIGURACOES
    public static final String FILE_PATH = "\\properties.etfg";

    //CONTEUDO DO ARQUIVO DE CONFIGURACOES
    public static final String SERVER = "localhost";
    public static final String PORT = "3306";
    public static final String DB = "CONTROLE_ARMARIOS_" + ENVIRONMENT;
  
    //PERSISTENCE UNIT
    public static final String UNIT_NAME = "PersistenciaPU";

}
