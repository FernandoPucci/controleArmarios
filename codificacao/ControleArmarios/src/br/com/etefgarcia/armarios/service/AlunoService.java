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

import br.com.etefgarcia.armarios.dao.AlunoDAO;
import br.com.etefgarcia.armarios.dao.impl.AlunoDAOImpl;
import br.com.etefgarcia.armarios.exceptions.NegocioException;
import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.util.ServiceUtils;
import br.com.etefgarcia.armarios.util.TelaUtils;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import java.util.List;

/**
 *
 * @author fernando-pucci
 */
public class AlunoService {

    public AlunoService(AlunoDAO dao) {

        if (dao == null) {

            dao = dao;

        }

    }

    private static AlunoDAO dao = null;

    public void cadastrarAluno(Aluno aluno) throws NegocioException, SistemaException {

        if (aluno == null) {
            throw new NegocioException("Aluno nulo!");
        }

        if (dao == null) {

            dao = new AlunoDAOImpl();

        }

        cadastrarAtualizarAlunoService(aluno.getIdAluno(), aluno.getNome(), aluno.getSexo() + "", aluno.getTelefone(), aluno.getEmail(), aluno.getFlgAtivo());

    }

    public static void cadastrarAtualizarAlunoService(Long idAluno, String nome, String sexo, String telefone, String email, Boolean flgAtivo) throws NegocioException, SistemaException {

        int err = 0;
        StringBuilder sb = new StringBuilder();

        if (email == null) {
            email = "";
        }

        if (nome == null || nome.isEmpty()) {
            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Nome vazio.");
            err++;
        }

        if (sexo == null || sexo.isEmpty()) {
            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Sexo vazio.");
            err++;
        }

        if (email.trim().isEmpty() && (telefone == null || telefone.trim().isEmpty())) {

            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "O aluno deve possuir um contato. E-mail ou telefone");
            err++;
        }

        if (!email.trim().isEmpty() && !TelaUtils.validaEmail(email)) {
            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Entre com email valido.");
            err++;
        }

        if (sexo != null && (!sexo.equals("M") && !sexo.equals("F"))) {
            sb.append(ConstantesTelas.ERR_TOKEN_LISTA + "Selecione campo Sexo..");
            err++;
        }

        if (err > 0) {

            throw new NegocioException(sb.toString());

        }

        Aluno a = new Aluno();

        a.setIdAluno(idAluno);
        a.setNome(nome.trim().toUpperCase());
        a.setSexo(sexo);
        a.setTelefone(ServiceUtils.limpaTelefone(telefone));
        a.setEmail(email);
        a.setFlgAtivo(flgAtivo);

        try {

            if (dao == null) {

                dao = new AlunoDAOImpl();

            }

            dao.save(a);

        } catch (Exception ex) {

            throw new SistemaException(AlunoService.class, ex.getMessage());

        }
    }

    public static List<Aluno> consultarAlunosService(boolean buscarAtivos) throws SistemaException {

        List<Aluno> listaAlunos = null;

        try {

            dao = new AlunoDAOImpl();

            listaAlunos = buscarAtivos ? dao.getAllAtivos(Aluno.class) : dao.getAllInativos(Aluno.class);

        } catch (Exception ex) {

            throw new SistemaException(AlunoService.class, ex.getMessage());

        }

        return listaAlunos;
    }

    public static List<Aluno> consultarAlunosByNomeService(String nome, boolean buscarAtivos) throws SistemaException {

        List<Aluno> listaAlunos = null;

        try {

            dao = new AlunoDAOImpl();

            listaAlunos = dao.getAlunoByNomeDao(nome, buscarAtivos);

        } catch (Exception ex) {

            throw new SistemaException(AlunoService.class, ex.getMessage());

        }

        return listaAlunos;
    }

    public static Aluno consultarAlunosByCodigoService(String codigo) throws SistemaException, NegocioException {

        Aluno alunoSaida = null;

        if (codigo == null || Long.parseLong(codigo) <= 0) {

            throw new NegocioException("Código inválido.");

        }

        try {

            dao = new AlunoDAOImpl();

            alunoSaida = dao.getById(Aluno.class, Long.parseLong(codigo));

        } catch (Exception ex) {

            throw new SistemaException(AlunoService.class, ex.getMessage());

        }

        return alunoSaida;
    }

    public static List<Aluno> consultarTodosAlunosService() throws SistemaException, NegocioException {

        List<Aluno> listaAlunos = null;

        try {

            dao = new AlunoDAOImpl();

            listaAlunos = dao.getAll(Aluno.class);

        } catch (Exception ex) {

            throw new SistemaException(AlunoService.class, ex.getMessage());

        }

        return listaAlunos;
    }

}
