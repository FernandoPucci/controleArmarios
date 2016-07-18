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

import br.com.etefgarcia.armarios.dao.ArmarioDAO;
import br.com.etefgarcia.armarios.model.Armario;
import java.io.IOException;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author fernando-pucci
 */
public class ArmarioDAOImpl extends BaseDAOImpl<Armario, Long> implements ArmarioDAO {

    @Override
    public List<Armario> getAllArmariosLivres(Boolean ocupados, Boolean ativos) throws Exception {

        List<Armario> listaSaida = null;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT A  FROM Armario A ");

        if (ocupados != null || ativos != null) {

            sb.append(" where ");

        }

        if (ocupados != null) {

            sb.append(" A.flgOcupado = ");
            sb.append(ocupados);

        }

        if (ativos != null) {

            if (ocupados != null) {
                sb.append(" and ");
            }

            sb.append(" A.flgAtivo = ");
            sb.append(ativos);

        }

        System.out.println("\n\n\n++++++++++++++++++++++++++++++++++ \n" + sb.toString() + "\n++++++++++++++++++++++++++++++++++\n\n\n");

        TypedQuery<Armario> query = getEntityManager().createQuery(sb.toString(), Armario.class);

//        TypedQuery<Armario> query = getEntityManager().createQuery("SELECT A "
//                + " FROM Armario A "
//                + " where  A.flgOcupado = false"
//                + " and A.flgAtivo = true ", Armario.class);
        listaSaida = query.getResultList();

        return listaSaida;
    }

    @Override
    public List<Armario> getArmarioByChaveDao(Long chave) throws IOException, ClassNotFoundException, Exception {

        List<Armario> listaSaida = null;

        TypedQuery<Armario> query = getEntityManager().createQuery("SELECT A "
                + "FROM Armario A "
                + "where  A.chave = :chaveQuery ", Armario.class);

        query.setParameter("chaveQuery", chave);
        listaSaida = query.getResultList();

        return listaSaida;

    }

}
