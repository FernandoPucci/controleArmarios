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
package br.com.etefgarcia.armarios.util;

import br.com.etefgarcia.armarios.model.Aluno;
import java.io.Serializable;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

/**
 *
 * @author fernando-pucci
 *
 * Classe reponsável por sobrescrever o gerador de identidade do Hibernate
 *
 * Caso Id do Aluno venha vazio, ele preenche automaticamente, caso contrario,
 * atribui o que recebeu
 *
 */
public class GeraIdAluno extends IdentityGenerator {

    private static final Logger log = Logger.getLogger(GeraIdAluno.class.getName());

    @Override
    public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {
        if (obj == null) {
            throw new HibernateException(new NullPointerException());
        }

        if ((((Aluno) obj).getIdAluno()) == null) {
            Serializable id = super.generate(session, obj);
            return id;
        } else {
            return ((Aluno) obj).getIdAluno();

        }

    }
}
