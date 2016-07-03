/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public EntityManager getEntityManager() throws IOException, ClassNotFoundException, Exception;
}
