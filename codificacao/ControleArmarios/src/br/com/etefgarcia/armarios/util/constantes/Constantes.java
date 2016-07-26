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

    //FORMATO DATA
    public static final String FORMATO_DATA_HORA = "dd/MM/yyyy - HH:mm:ss";
    public static final String FORMATO_DATA = "dd/MM/yyyy";

}
