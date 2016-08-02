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

import br.com.etefgarcia.armarios.dao.AluguelArmarioDAO;
import br.com.etefgarcia.armarios.dao.impl.AluguelArmarioDAOImpl;
import br.com.etefgarcia.armarios.exceptions.NegocioException;
import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.AluguelArmario;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.model.Armario;
import br.com.etefgarcia.armarios.model.Usuario;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import java.util.List;

/**
 *
 * @author fernando-pucci
 */
public class AluguelArmarioService {

    private static AluguelArmarioDAO dao = null;

    public static void cadastrarAtualizarArmarioService(Aluno aluno, Armario armario, Usuario usuario) throws NegocioException, SistemaException {

        int err = 0;
        StringBuilder sb = new StringBuilder();

        if (armario == null) {
            throw new NegocioException("Armario Inválido ou nulo");
        }

        if (aluno == null) {
            throw new NegocioException("Aluno Inválido ou nulo");
        }

        if (usuario == null) {
            throw new NegocioException("Usuario Inválido ou nulo");
        }

        if (armario.getChave() == null) {
            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Chave vazia.");
            err++;
        }

        if (aluno.getIdAluno() == null) {
            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Aluno com RM vazio.");
            err++;
        }

        if (!armario.getFlgAtivo()) {

            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Armario Inativo.");
            err++;
        }

        if (armario.getFlgOcupado()) {

            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Armario Ocupado. Verifique Status.");
            err++;

        }

        if (!aluno.getFlgAtivo()) {

            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Aluno Inativo.");
            err++;

        }

        if (err > 0) {

            throw new NegocioException(sb.toString());

        }

        AluguelArmario a = new AluguelArmario();

        a.setAluno(aluno);
        a.setArmario(armario);
        a.setUsuarioResponsavel(usuario);

        try {

            dao = new AluguelArmarioDAOImpl();

            dao.save(a);

        } catch (NegocioException nex) {

            throw nex;

        } catch (Exception ex) {

            throw new SistemaException(AluguelArmarioService.class, ex.getMessage());

        }
    }

    public static List<AluguelArmario> getAllAluguelArmarioByChaveService(Long chave) throws NegocioException, SistemaException {

        int err = 0;
        StringBuilder sb = new StringBuilder();

        List<AluguelArmario> listaSaida = null;

        if (chave == null) {
            throw new NegocioException("Chave Inválida ou nula");
        }

        if (err > 0) {

            throw new NegocioException(sb.toString());

        }

        try {

            dao = new AluguelArmarioDAOImpl();

            listaSaida = dao.getAllAluguelArmarioBychave(chave);

        } catch (NegocioException nex) {

            throw nex;

        } catch (Exception ex) {

            throw new SistemaException(AluguelArmarioService.class, ex.getMessage());

        }

        return listaSaida;

    }
}