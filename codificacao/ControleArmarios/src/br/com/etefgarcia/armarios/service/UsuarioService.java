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
package br.com.etefgarcia.armarios.service;

import br.com.etefgarcia.armarios.dao.UsuarioDAO;
import br.com.etefgarcia.armarios.dao.impl.UsuarioDAOImpl;
import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando-pucci
 */
public class UsuarioService {

    private static UsuarioDAO dao = null;

    public static Usuario getUsuarioById(Long idUsuario) throws SistemaException {

        if (idUsuario == null) {

            throw new SistemaException(UsuarioService.class, "Codigo Usuario inválido;");

        }

        try {

            dao = new UsuarioDAOImpl();

            return dao.getById(Usuario.class, idUsuario);

        } catch (ClassNotFoundException ex) {

            throw new SistemaException(UsuarioService.class, ex.getMessage());

        } catch (Exception ex) {

            throw new SistemaException(UsuarioService.class, ex.getMessage());

        }

    }

}
