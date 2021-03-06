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
package br.com.etefgarcia.armarios.exceptions;

/**
 *
 * @author fernando-pucci
 */
public class SistemaException extends Exception {

    public SistemaException(String message) {
        super("Erro de Sistema:\n" + message);
    }

    public SistemaException(Class clazz, String message) {
        super(clazz.getSimpleName() + "\n\n" + "Erro de Sistema:\n" + message);
    }
}
