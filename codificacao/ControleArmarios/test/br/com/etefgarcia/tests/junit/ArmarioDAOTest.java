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

import br.com.etefgarcia.armarios.dao.ArmarioDAO;
import br.com.etefgarcia.armarios.dao.impl.ArmarioDAOImpl;
import br.com.etefgarcia.armarios.model.Armario;
import java.util.List;
import javax.transaction.Transactional;
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
public class ArmarioDAOTest {

    private static ArmarioDAO dao = null;
    private static Armario armarioAtivo = null;
    private static Armario armarioInativo = null;

    public ArmarioDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        dao = new ArmarioDAOImpl();

        armarioAtivo = new Armario();
        armarioInativo = new Armario();

        //cria um armario ativo
        armarioAtivo.setDescricao("");
        armarioAtivo.setChave(Long.MIN_VALUE);

        //cria um armario inativo
        armarioInativo.setDescricao("");
        armarioInativo.setChave(Long.MIN_VALUE);
        armarioAtivo.setFlgAtivo(Boolean.FALSE);

    }

    @AfterClass
    public static void tearDownClass() throws ClassNotFoundException, Exception {

        dao = null;

    }

    @Before
    public void setUp() throws ClassNotFoundException, Exception {

        dao.save(armarioInativo);
        dao.save(armarioAtivo);

    }

    @After
    public void tearDown() throws ClassNotFoundException, Exception {

        //limpa armarios
        for (Armario a : dao.getAll(Armario.class)) {

            dao.remove(a);

        }

    }

    @Test
    @Transactional
    public void saveArmario() throws ClassNotFoundException, Exception {

        dao.save(armarioInativo);
        dao.save(armarioAtivo);

    }

    @Test
    @Transactional
    public void getAllArmariosOcupados() throws ClassNotFoundException, Exception {

        Armario armarioUpdate = new Armario(null, Long.MIN_VALUE, "ARMARIO OC. TEST", Boolean.TRUE, Boolean.TRUE);

        dao.save(armarioUpdate);

        List<Armario> lista = dao.getAllArmariosLivres(Boolean.TRUE, null);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() == 1);

    }

    @Test
    @Transactional
    public void getAllArmariosLivres() throws ClassNotFoundException, Exception {

        List<Armario> lista = dao.getAllArmariosLivres(Boolean.FALSE, null);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        for (Armario a : lista) {

            assertFalse(a.getFlgOcupado());

        }

    }

    @Test
    @Transactional
    public void getAllArmario() throws ClassNotFoundException, Exception {

        List<Armario> lista = dao.getAll(Armario.class);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() == 2);

    }

    @Test
    @Transactional
    public void getAllArmarioAtivo() throws ClassNotFoundException, Exception {

        List<Armario> lista = dao.getAllAtivos(Armario.class);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() == 1);

    }

}
