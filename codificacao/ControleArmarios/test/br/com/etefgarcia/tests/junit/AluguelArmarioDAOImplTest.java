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

import br.com.etefgarcia.armarios.dao.AluguelArmarioDAO;
import br.com.etefgarcia.armarios.dao.AlunoDAO;
import br.com.etefgarcia.armarios.dao.ArmarioDAO;
import br.com.etefgarcia.armarios.dao.UsuarioDAO;
import br.com.etefgarcia.armarios.dao.impl.AluguelArmarioDAOImpl;
import br.com.etefgarcia.armarios.dao.impl.AlunoDAOImpl;
import br.com.etefgarcia.armarios.dao.impl.ArmarioDAOImpl;
import br.com.etefgarcia.armarios.dao.impl.UsuarioDAOImpl;
import br.com.etefgarcia.armarios.model.AluguelArmario;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.model.Armario;
import br.com.etefgarcia.armarios.model.TipoUsuario;
import br.com.etefgarcia.armarios.model.Usuario;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author fernando-pucci
 */
public class AluguelArmarioDAOImplTest {

    private static AluguelArmarioDAO dao = null;
    private static ArmarioDAO daoArmario = null;
    private static AlunoDAO daoAluno = null;
    private static UsuarioDAO daoUsuario = null;

    private static AluguelArmario aluguelArmario = null;
    private static Aluno aluno = null;
    private static Armario armario = null;
    private static Usuario usuario = null;

    public AluguelArmarioDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws ClassNotFoundException, Exception {

        dao = new AluguelArmarioDAOImpl();
        daoArmario = new ArmarioDAOImpl();;
        daoAluno = new AlunoDAOImpl();
        daoUsuario = new UsuarioDAOImpl();

        armario = new Armario(null, Long.MIN_VALUE, "ARMARIO 1", Boolean.FALSE, Boolean.TRUE);
        aluno = new Aluno(null, "NOME ALUNO 1", 'M', "99999999999", "teste@teste.com", Boolean.TRUE);
        usuario = new Usuario(null, new TipoUsuario(1L, null, Boolean.TRUE), "NOME USUARIO 1", Boolean.TRUE);

        aluguelArmario = new AluguelArmario(null, armario, aluno, usuario, new Date(), null, 0, Boolean.FALSE);

        daoUsuario.save(usuario);
        daoAluno.save(aluno);
        daoArmario.save(armario);

    }

    @AfterClass
    public static void tearDownClass() throws ClassNotFoundException, Exception {

        for (Armario a : daoArmario.getAll(Armario.class)) {

            daoArmario.remove(a);
        }

        for (Usuario a : daoUsuario.getAll(Usuario.class)) {

            daoUsuario.remove(a);
        }

        for (Aluno a : daoAluno.getAll(Aluno.class)) {

            daoAluno.remove(a);
        }

        for (Armario a : daoArmario.getAll(Armario.class)) {
            daoArmario.remove(a);
        }

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws ClassNotFoundException, Exception {

        //limpa aluguel armario
        for (AluguelArmario a : dao.getAll(AluguelArmario.class)) {

            dao.remove(a);
            
        }

    }

        @Test
    @Transactional
    public void getAllAluguelArmarioEmAberto() throws ClassNotFoundException, Exception {

        salvarAluguelArmario();

        List<AluguelArmario> lista = dao.getAllAluguelArmarioEmAberto();
        
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertNull(lista.get(0).getDataDevolucao());
        
    }
    
    @Test
    @Transactional
    public void getAllAluguelArmario() throws ClassNotFoundException, Exception {

        salvarAluguelArmario();

        List<AluguelArmario> lista = dao.getAll(AluguelArmario.class);

        
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        
    }

    @Test
    @Transactional
    public void salvarAluguelArmario() throws ClassNotFoundException, Exception {

        Aluno alunoAluguel = daoAluno.getAllAtivos(Aluno.class).get(0);
        Armario armarioAluguel = daoArmario.getAllAtivos(Armario.class).get(0);
        Usuario usuarioAluguel = daoUsuario.getAllAtivos(Usuario.class).get(0);

        AluguelArmario aluguel = new AluguelArmario(null, armarioAluguel, alunoAluguel, usuarioAluguel, new Date(), null, 0, Boolean.FALSE);
        dao.save(aluguel);

    }
    
    @Test
    public void getAllAluguelArmarioBychaveTest()throws Exception{
    
    
    List<AluguelArmario> listaSaida = null;
    
    listaSaida = dao.getAllAluguelArmarioBychave(5L);
    
        assertNotNull(listaSaida);
        assertTrue(listaSaida.size()==1);
        System.out.println(listaSaida.get(0));
    
    
    }
}
