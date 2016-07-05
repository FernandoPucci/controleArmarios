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

/**
 *
 * @author fernando-pucci
 */
public class ServiceUtils {

    public static String limpaTelefone(String telefone) {

        String telefoneSaida = "";

        if (telefone == null) {

            return telefoneSaida;
        }

        for (int i = 0; i < telefone.length(); i++) {
            if (Character.isDigit(telefone.charAt(i))) {
                telefoneSaida += telefone.charAt(i);
            }

        }

        return telefoneSaida;

    }

}
