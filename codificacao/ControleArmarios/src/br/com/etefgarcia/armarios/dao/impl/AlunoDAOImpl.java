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
package br.com.etefgarcia.armarios.dao.impl;

import br.com.etefgarcia.armarios.dao.AlunoDAO;
import br.com.etefgarcia.armarios.model.Aluno;
import java.io.IOException;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author fernando-pucci
 */
public class AlunoDAOImpl extends BaseDAOImpl<Aluno, Long> implements AlunoDAO {

    @Override
    public List<Aluno> getAlunoByNomeDao(String nome, Boolean ativo) throws IOException, ClassNotFoundException, Exception {

        List<Aluno> listaSaida = null;

        TypedQuery<Aluno> query = getEntityManager().createQuery("SELECT A "
                + "FROM Aluno A "
                + "where  A.nome like :nomeQuery "
                + (ativo?"and A.flgAtivo = true ": "")
                , Aluno.class);

        query.setParameter("nomeQuery", "%" + nome + "%");
        listaSaida = query.getResultList();

        return listaSaida;

    }

}
