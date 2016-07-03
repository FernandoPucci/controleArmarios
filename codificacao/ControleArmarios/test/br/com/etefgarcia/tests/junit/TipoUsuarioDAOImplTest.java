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
package br.com.etefgarcia.tests.junit;

import br.com.etefgarcia.armarios.dao.TipoUsuarioDAO;
import br.com.etefgarcia.armarios.dao.impl.TipoUsuarioDAOImpl;
import br.com.etefgarcia.armarios.model.TipoUsuario;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fernando-pucci
 */
public class TipoUsuarioDAOImplTest {

    private static TipoUsuarioDAO dao = null;

    public TipoUsuarioDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        dao = new TipoUsuarioDAOImpl();
    }

    @AfterClass
    public static void tearDownClass() {

        dao = null;

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void getAllTipoUsuario() throws ClassNotFoundException, Exception {

        List<TipoUsuario> lista = dao.getAll(TipoUsuario.class);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
   
    }
    
    @Test
    public void getTipoUsuarioById() throws ClassNotFoundException, Exception {
        
        TipoUsuario usuario = dao.getById(TipoUsuario.class, 1L);
        assertNotNull(usuario);
        assertTrue(usuario.getDescricao().equals("ADMINISTRADOR"));
        
        
    }

}
