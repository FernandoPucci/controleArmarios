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
package br.com.etefgarcia.armarios.main;

import br.com.etefgarcia.armarios.controller.ConfigController;
import br.com.etefgarcia.armarios.controller.TelaPrincipalController;

import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.view.ConfigInicialView;

/**
 *
 * @author fernando-pucci
 *
 * Classe principal de inicialização do programa
 *
 *
 */
public class Principal {

    public static void main(String[] args) {

        ConfigController configController;
        TelaPrincipalController telaPrincipalController;

        try {

            configController = new ConfigController(new ConfigInicialView());

            if (configController.getConfig().getIsPrimeiroAcesso()) {

                configController.getThreadShowConfigInicialView().start();

            } else {

                configController.testarConfiguracaoDbGeral();
                telaPrincipalController = new TelaPrincipalController();

                telaPrincipalController.getThreadShowTelaPrincipalView().start();

            }
        } catch (Exception ex) {

            Mensagens.mostraMensagemErro(ex.getMessage());
            System.exit(0);

        }

    }

}
