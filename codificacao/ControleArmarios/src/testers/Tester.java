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
package testers;

import br.com.etefgarcia.armarios.dao.AluguelArmarioDAO;
import br.com.etefgarcia.armarios.dao.AlunoDAO;
import br.com.etefgarcia.armarios.dao.ArmarioDAO;
import br.com.etefgarcia.armarios.dao.UsuarioDAO;
import br.com.etefgarcia.armarios.dao.impl.AluguelArmarioDAOImpl;
import br.com.etefgarcia.armarios.dao.impl.AlunoDAOImpl;
import br.com.etefgarcia.armarios.dao.impl.ArmarioDAOImpl;
import br.com.etefgarcia.armarios.dao.impl.UsuarioDAOImpl;
import br.com.etefgarcia.armarios.model.AluguelArmario;
import br.com.etefgarcia.armarios.model.Aluno;
import br.com.etefgarcia.armarios.model.Armario;
import br.com.etefgarcia.armarios.model.Config;
import br.com.etefgarcia.armarios.model.TipoUsuario;
import br.com.etefgarcia.armarios.model.Usuario;
import br.com.etefgarcia.armarios.util.ConfigUtils;
import br.com.etefgarcia.armarios.view.CadastrarAluguelArmarioView;
import br.com.etefgarcia.armarios.view.CadastrarAlunoView;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando-pucci
 */
public class Tester {

    private static AluguelArmarioDAO dao = null;
    private static ArmarioDAO daoArmario = null;
    private static AlunoDAO daoAluno = null;
    private static UsuarioDAO daoUsuario = null;

    public static void main(String[] args) {

        try {
            Config c = ConfigUtils.getConfiguracao();

            System.out.println(c);

           // inicializar();

            //testarInsercaoMassa();
            //testarAluguel();
            
//            List<AluguelArmario> alugueisAbertos = dao.getAllAluguelArmarioEmAberto();
//        
//            for (AluguelArmario a : alugueisAbertos) {
//                System.out.println("****** " + a);
//
//            }
//
//            testarFecharAluguelByIdArmario();
//            
//            List<AluguelArmario> alugueisFechados = dao.getAllAluguelArmarioFechadas();
//
//            for (AluguelArmario a : alugueisFechados) {
//                System.out.println("XXXXXX " + a);
//
//            }
//            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          //  System.exit(0);
        }
        
        Aluno a = new Aluno();
        a.setIdAluno(39L);
        a.setNome("FERNANDO PUCCI");
        a.setFlgAtivo(Boolean.TRUE);
        a.setSexo('M');
        a.setTelefone("19981271009");
        a.setEmail("fernando@teste.com");
        CadastrarAlunoView c = new CadastrarAlunoView(a, true);
        c.setVisible(true);

    }

    private static void inicializar() throws Exception {

        dao = new AluguelArmarioDAOImpl();
        daoArmario = new ArmarioDAOImpl();
        daoAluno = new AlunoDAOImpl();
        daoUsuario = new UsuarioDAOImpl();

    }

    private static void testarInsercaoMassa() throws Exception {

        Armario armario = new Armario(null, Long.MIN_VALUE, "ARMARIO 1", Boolean.FALSE, Boolean.TRUE);
        Aluno aluno = new Aluno(null, "NOME ALUNO 1", 'M', "99999999999", "teste@teste.com", Boolean.TRUE);
        Usuario usuario = new Usuario(null, new TipoUsuario(1L, null, Boolean.TRUE), "NOME USUARIO 1", Boolean.TRUE);

        daoAluno.save(aluno);
        daoArmario.save(armario);
        daoUsuario.save(usuario);
    }

    private static void testarFecharAluguelByIdArmario() throws Exception {
//        Aluno alunoAluguel = daoAluno.getById(Aluno.class, 2L);
//        Armario armarioAluguel = daoArmario.getById(Armario.class, 3L);
//        Usuario usuarioAluguel = daoUsuario.getById(Usuario.class, 1L);

        AluguelArmario aluguel = dao.getById(AluguelArmario.class, 11L);
        aluguel.setDataDevolucao(new Date());
        aluguel.setFlgDevolvido(Boolean.TRUE);

        dao.save(aluguel);

    }

    private static void testarAluguel() throws Exception {

        Aluno alunoAluguel = daoAluno.getAllAtivos(Aluno.class).get(0);
        Armario armarioAluguel = daoArmario.getAllAtivos(Armario.class).get(0);
        Usuario usuarioAluguel = daoUsuario.getAllAtivos(Usuario.class).get(0);

        
        AluguelArmario aluguel = new AluguelArmario(null, armarioAluguel, alunoAluguel, usuarioAluguel, new Date(), null, Boolean.FALSE);
        dao.save(aluguel);
    }

}
