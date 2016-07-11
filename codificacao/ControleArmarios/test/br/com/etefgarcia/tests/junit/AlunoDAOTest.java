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

import br.com.etefgarcia.armarios.dao.AlunoDAO;
import br.com.etefgarcia.armarios.dao.impl.AlunoDAOImpl;
import br.com.etefgarcia.armarios.model.Aluno;
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
public class AlunoDAOTest {

    private static AlunoDAO dao = null;
    private static Aluno alunoAtivo = null;
    private static Aluno alunoInativo = null;

    public AlunoDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        dao = new AlunoDAOImpl();

        alunoAtivo = new Aluno();
        alunoInativo = new Aluno();

        //cria um aluno ativo
        alunoAtivo.setFlgAtivo(Boolean.TRUE);

        //cria um aluno inativo
        alunoInativo.setFlgAtivo(Boolean.FALSE);

    }

    @AfterClass
    public static void tearDownClass() throws ClassNotFoundException, Exception {

        dao = null;

    }

    @Before
    public void setUp() throws ClassNotFoundException, Exception {

        dao.save(alunoInativo);
        dao.save(alunoAtivo);

    }

    @After
    public void tearDown() throws ClassNotFoundException, Exception {

        //limpa alunos
        for (Aluno a : dao.getAll(Aluno.class)) {

            dao.remove(a);

        }

    }

    @Test
    @Transactional
    public void saveAluno() throws ClassNotFoundException, Exception {

        dao.save(alunoInativo);
        dao.save(alunoAtivo);

    }

    @Test
    @Transactional
    public void getAllAluno() throws ClassNotFoundException, Exception {

        List<Aluno> lista = dao.getAll(Aluno.class);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() == 2);

    }

    @Test
    @Transactional
    public void getAllAlunoAtivo() throws ClassNotFoundException, Exception {

        List<Aluno> lista = dao.getAllAtivos(Aluno.class);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.size() == 1);

    }

    @Test
    @Transactional
    public void getAlunoByNomeDaoTester() throws ClassNotFoundException, Exception {

        List<Aluno> lista = dao.getAlunoByNomeDao("FER", true);

        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        for (Aluno a : lista) {
            System.out.println(a.getNome());

        }

    }
}
