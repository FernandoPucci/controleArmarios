/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etefgarcia.armarios.dao.impl;

import br.com.etefgarcia.armarios.dao.BaseDAO;
import br.com.etefgarcia.armarios.dao.factory.Conexao;
import br.com.etefgarcia.armarios.util.ConfigUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author fernando-pucci
 */
public abstract class BaseDAOImpl<T, I extends Serializable> implements BaseDAO<T, I> {

    private Conexao conexao;

    @Override
    public T save(T entity) throws IOException, ClassNotFoundException, Exception {

        T saved = null;

        getEntityManager().getTransaction().begin();
        saved = getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();

        return saved;
    }

    @Override
    public void remove(T entity) throws IOException, ClassNotFoundException, Exception {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(entity);
        getEntityManager().getTransaction().commit();

    }

    @Override
    public T getById(Class<T> classe, I pk) throws IOException, ClassNotFoundException, Exception {

        try {
            return getEntityManager().find(classe, pk);
        } catch (NoResultException e) {
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll(Class<T> classe) throws IOException, ClassNotFoundException, Exception {

        return getEntityManager().createQuery("select o from " + classe.getSimpleName() + " o ").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAllAtivos(Class<T> classe) throws IOException, ClassNotFoundException, Exception {

        return getEntityManager().createQuery("select o from " + classe.getSimpleName() + " o WHERE o.flgAtivo = 1").getResultList();
    }

    @Override
    public EntityManager getEntityManager()throws IOException, ClassNotFoundException, Exception {

        if (conexao == null) {
            conexao = new Conexao();
        }

        return conexao.getEntityManager(ConfigUtils.getConfiguracao());

    }

}
