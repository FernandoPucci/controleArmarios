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

import br.com.etefgarcia.armarios.dao.UsuarioDAO;
import br.com.etefgarcia.armarios.dao.impl.UsuarioDAOImpl;
import br.com.etefgarcia.armarios.model.TipoUsuario;
import br.com.etefgarcia.armarios.model.Usuario;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author fernando-pucci
 */
public class UsuarioDAOImplTest {
    
    private static UsuarioDAO dao = null;
    private static Usuario usuarioAtivo = null;
    private static Usuario usuarioInativo = null;
    
    public UsuarioDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        dao = new UsuarioDAOImpl();
        
        usuarioAtivo = new Usuario(999L, new TipoUsuario(1L, null, Boolean.TRUE), "USUARIO ATIVO TESTE", Boolean.TRUE);
        usuarioInativo = new Usuario(9999L, new TipoUsuario(1L, null, Boolean.TRUE), "USUARIO INATIVO TESTE", Boolean.FALSE);
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
        dao = null;
        
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, Exception {
        
        dao.save(usuarioAtivo);
        dao.save(usuarioInativo);
        
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, Exception {

        //limpa usuarios
        for (Usuario a : dao.getAll(Usuario.class)) {
            
            dao.remove(a);
            
        }
        
    }
    
    @Test
    public void getAllUsuario() throws ClassNotFoundException, Exception {
        
        List<Usuario> lista = dao.getAll(Usuario.class);
        
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() == 2);
        
    }
    
    @Test
    public void getAllUsuarioAtivos() throws ClassNotFoundException, Exception {
        
        List<Usuario> lista = dao.getAllAtivos(Usuario.class);
        
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() == 1);
        
    }
    
    @Test
    public void getUsuarioById() throws ClassNotFoundException, Exception {
                
        Usuario usuario = dao.getById(Usuario.class, dao.getAll(Usuario.class).get(0).getIdUsuario());
        assertNotNull(usuario);
        assertNotNull(usuario.getTipoUsuario());
       
    }
    
    @Test
    public void saveUsuario() throws ClassNotFoundException, Exception {
        
        dao.save(usuarioAtivo);
        dao.save(usuarioInativo);
        
    }
}
