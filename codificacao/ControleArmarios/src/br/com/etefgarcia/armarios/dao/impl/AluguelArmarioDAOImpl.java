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

import br.com.etefgarcia.armarios.dao.AluguelArmarioDAO;
import br.com.etefgarcia.armarios.model.AluguelArmario;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author fernando-pucci
 */
public class AluguelArmarioDAOImpl extends BaseDAOImpl<AluguelArmario, Long> implements AluguelArmarioDAO {

    @Override
    public List<AluguelArmario> getAllAluguelArmarioEmAberto() throws Exception {

        List<AluguelArmario> listaSaida = null;

        TypedQuery<AluguelArmario> query = getEntityManager().createQuery("SELECT A "
                + " FROM AluguelArmario A "
                + " where  A.flgDevolvido = false", AluguelArmario.class);

        listaSaida = query.getResultList();

        return listaSaida;

    }

    @Override
    public List<AluguelArmario> getAllAluguelArmarioEmAberto(Date dataInicial, Date DataFinal) throws Exception {
        throw new UnsupportedOperationException("Método ainda não implementado"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AluguelArmario> getAllAluguelArmarioFechadas() throws Exception {

        List<AluguelArmario> listaSaida = null;

        TypedQuery<AluguelArmario> query = getEntityManager().createQuery("SELECT A "
                + " FROM AluguelArmario A "
                + " where  A.flgDevolvido = true", AluguelArmario.class);

        listaSaida = query.getResultList();

        return listaSaida;

    }

    @Override
    public List<AluguelArmario> getAllAluguelArmarioFechadas(Date dataInicial, Date DataFinal) throws Exception {
        throw new UnsupportedOperationException("Método ainda não implementado"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AluguelArmario> getAllAluguelArmarioBychave(Long chave) throws Exception {
        
        List<AluguelArmario> listaSaida = null;

        TypedQuery<AluguelArmario> query = getEntityManager().createQuery("SELECT A "
                + " FROM AluguelArmario A, Armario AR "
                + " where "
                + " A.flgDevolvido = false "
                + " and AR.chave = :chaveQuery", AluguelArmario.class);

        
        query.setParameter("chaveQuery", chave);
        
        listaSaida = query.getResultList();

        return listaSaida;
    }

}
