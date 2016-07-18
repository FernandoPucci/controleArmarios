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
package br.com.etefgarcia.armarios.service;

import br.com.etefgarcia.armarios.dao.ArmarioDAO;
import br.com.etefgarcia.armarios.dao.impl.ArmarioDAOImpl;
import br.com.etefgarcia.armarios.exceptions.NegocioException;
import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.Armario;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import java.util.List;

/**
 *
 * @author fernando-pucci
 */
public class ArmarioService {

    private static ArmarioDAO dao = null;

    public static void cadastrarAtualizarArmarioService(Long idArmario, String chave, String descricao, Boolean flgOcupado, Boolean flgAtivo) throws NegocioException, SistemaException {

        int err = 0;
        StringBuilder sb = new StringBuilder();

        if (chave == null || chave.isEmpty()) {
            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Chave vazia.");
            err++;
        }

        if (descricao == null) {

            descricao = "";

        }

        if (err > 0) {

            throw new NegocioException(sb.toString());

        }

        Armario a = new Armario();

        a.setIdArmario(idArmario);
        a.setChave(new Long(chave.trim()));
        a.setDescricao(descricao.trim());
        a.setFlgOcupado(flgOcupado);
        a.setFlgAtivo(flgAtivo);

        try {

            List<Armario> aTeste = dao.getArmarioByChaveDao(a.getChave());

            if (aTeste != null && aTeste.size() > 0) {

                throw new NegocioException("Esta chave já está cadastrada.");

            }

            dao = new ArmarioDAOImpl();

            dao.save(a);

        } catch (NegocioException nex) {

            throw nex;

        } catch (Exception ex) {

            throw new SistemaException(ArmarioService.class, ex.getMessage());

        }
    }

    public static List<Armario> consultarArmariosService(Boolean buscarOcupados, Boolean buscarAtivos) throws SistemaException {

        List<Armario> listaArmarios = null;

        try {

            dao = new ArmarioDAOImpl();

            listaArmarios = dao.getAllArmariosLivres(buscarOcupados, buscarAtivos);

        } catch (Exception ex) {

            throw new SistemaException(ArmarioService.class, ex.getMessage());

        }

        return listaArmarios;
    }

    public static List<Armario> consultarArmariosByChaveService(Long chave, boolean buscarAtivos) throws SistemaException {

        List<Armario> listaArmarios = null;

        try {

            dao = new ArmarioDAOImpl();

            listaArmarios = dao.getArmarioByChaveDao(chave);

        } catch (Exception ex) {

            throw new SistemaException(ArmarioService.class, ex.getMessage());

        }

        return listaArmarios;
    }

    public static Armario consultarArmariosByCodigoService(String codigo) throws SistemaException, NegocioException {

        Armario armarioSaida = null;

        if (codigo == null || Long.parseLong(codigo) <= 0) {

            throw new NegocioException("Código inválido.");

        }

        try {

            dao = new ArmarioDAOImpl();

            armarioSaida = dao.getById(Armario.class, Long.parseLong(codigo));

        } catch (Exception ex) {

            throw new SistemaException(ArmarioService.class, ex.getMessage());

        }

        return armarioSaida;
    }

    public static List<Armario> consultarTodosArmariosService() throws SistemaException, NegocioException {

        List<Armario> listaArmarios = null;

        try {

            dao = new ArmarioDAOImpl();

            listaArmarios = dao.getAll(Armario.class);

        } catch (Exception ex) {

            throw new SistemaException(ArmarioService.class, ex.getMessage());

        }

        return listaArmarios;
    }

}
