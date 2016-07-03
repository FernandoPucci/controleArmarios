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
package br.com.etefgarcia.armarios.util;

import br.com.etefgarcia.armarios.util.constantes.Constantes;
import br.com.etefgarcia.armarios.model.Config;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author fernando-pucci
 *
 * Classe utilitaria para gravacao dos arquivos de configuracao
 *
 */
public class ConfigUtils {

    //Arquivo de configuracaoo
    public static Config getConfiguracao() throws IOException, ClassNotFoundException {

        File file = new File(Constantes.FOLDER_PATH + Constantes.FILE_PATH);

        if (!file.exists()) {
            File aux = new File(Constantes.FOLDER_PATH);
            aux.mkdirs();
            criaConfiguracaoPadrao(file);
        }

        Config c = carregaConfiguracao(file);

        return c;
    }

    public static void atualizaArquivoConfiguracao(Config c) throws FileNotFoundException, IOException {

        File file = new File(Constantes.FOLDER_PATH + Constantes.FILE_PATH);

        FileOutputStream fo = new FileOutputStream(file);

        ObjectOutputStream oo = new ObjectOutputStream(fo);

        oo.writeObject(c);

        oo.close();

    }

    private static Config carregaConfiguracao(File file) throws IOException, ClassNotFoundException {

        FileInputStream fin = new FileInputStream(file);

        ObjectInputStream oin = new ObjectInputStream(fin);

        Config c = (Config) oin.readObject();

        return c;

    }

    private static void criaConfiguracaoPadrao(File file) throws IOException {

        FileOutputStream fo = new FileOutputStream(file);

        Config c = new Config(Boolean.TRUE);

        ObjectOutputStream oo = new ObjectOutputStream(fo);

        oo.writeObject(c);

        oo.close();
    }
}
