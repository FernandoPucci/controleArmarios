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

            properties.put(ConstantesDb.JAVAX_DRIVER, ConstantesDb.DRIVER_NAME);
            properties.put(ConstantesDb.JAVAX_URL, ConstantesDb.JDBC + config.getHost() + ":" + config.getServidorPorta() + "/" + config.getBanco() + ConstantesDb.CONVERT_ZERO_TO_NULL_PROPERTY);
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
