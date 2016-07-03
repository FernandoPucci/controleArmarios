/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etefgarcia.armarios.dao.factory;

import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.Config;
import br.com.etefgarcia.armarios.util.constantes.Constantes;
import br.com.etefgarcia.armarios.util.constantes.ConstantesDb;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fernando-pucci
 */
public class Conexao {

    //nome da unidade de persistencia definia no persistence.xml
    private EntityManagerFactory emf = null;

    private EntityManager em = null;

    public EntityManager getEntityManager(Config config) throws SistemaException {

        if (config == null) {
            throw new SistemaException("Erro ao criar EM. Arquivo Config vazio.");
        }

        if (emf == null) {

            Map<String, String> properties = new HashMap<String, String>();

            //<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
            properties.put(ConstantesDb.JAVAX_DRIVER, ConstantesDb.DRIVER_NAME);
            properties.put(ConstantesDb.JAVAX_URL, ConstantesDb.JDBC + config.getHost() +":"+ config.getServidorPorta() + "/" + config.getBanco());
            properties.put(ConstantesDb.JAVAX_USER, config.getUsuario());
            properties.put(ConstantesDb.JAVAX_PASSWORD, config.getSenha());

            emf = Persistence.createEntityManagerFactory(Constantes.UNIT_NAME, properties);

        }

        if (em == null) {
            em = emf.createEntityManager();
        }

        return em;
    }

//  public EntityManager getEntityManager(Config config){
//   
//  if (emf == null) {
//   emf = Persistence.createEntityManagerFactory(Constantes.UNIT_NAME);
//  }
//   
//  if (em == null) {
//   em = emf.createEntityManager();
//  }
//   
//  return em;
// }
}
