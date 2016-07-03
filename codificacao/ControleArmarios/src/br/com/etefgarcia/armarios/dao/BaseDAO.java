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
package br.com.etefgarcia.armarios.dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author fernando-pucci
 */
public interface BaseDAO<T, I extends Serializable> {

    public T save(T entity) throws IOException, ClassNotFoundException, Exception;

    public void remove(T entity) throws IOException, ClassNotFoundException, Exception;

    public T getById(Class<T> classe, I pk) throws IOException, ClassNotFoundException, Exception;

    public List<T> getAll(Class<T> classe) throws IOException, ClassNotFoundException, Exception;

    public List<T> getAllAtivos(Class<T> classe) throws IOException, ClassNotFoundException, Exception;

    public EntityManager getEntityManager() throws IOException, ClassNotFoundException, Exception;
}
