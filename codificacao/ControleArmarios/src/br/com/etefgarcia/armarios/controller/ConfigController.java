/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etefgarcia.armarios.controller;

import br.com.etefgarcia.armarios.dao.TesteDbConfigDAO;
import br.com.etefgarcia.armarios.dao.impl.TesteDbConfigDAOImpl;
import br.com.etefgarcia.armarios.exceptions.SistemaException;
import br.com.etefgarcia.armarios.model.Config;
import br.com.etefgarcia.armarios.model.TesteDbConfig;
import br.com.etefgarcia.armarios.util.ConfigUtils;
import br.com.etefgarcia.armarios.util.Mensagens;
import br.com.etefgarcia.armarios.util.constantes.telas.ConstantesTelas;
import br.com.etefgarcia.armarios.view.ConfigInicialView;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;

/**
 *
 * @author fernando-pucci
 */
public class ConfigController {

    private ConfigInicialView configInicialView;
    private Config config;
    private Config configOld;

    public ConfigController(ConfigInicialView configInicialView) throws IOException, ClassNotFoundException {

        this.configInicialView = configInicialView;
        this.config = ConfigUtils.getConfiguracao();
        this.configOld = this.config;

    }

    public Config getConfig() {

        return this.config;

    }

    public Thread getThreadShowConfigInicialView() {

        return new Thread() {

            public void run() {
                try {

                    config = ConfigUtils.getConfiguracao();
                    new ConfigInicialView().setVisible(true);

                } catch (IOException ex) {

                    Mensagens.mostraMensagemErro("Erro ao inicializar configuração. Arquivo não encontrado;\n\n" + ex.getMessage());

                } catch (ClassNotFoundException ex) {

                    Mensagens.mostraMensagemErro("Erro ao inicializar configuração. Classe não encontrada; \n\n" + ex.getMessage());

                }
            }

        };

    }

    public void testarConfiguracaoDbGeral() throws Exception {

        TesteDbConfigDAO dao = new TesteDbConfigDAOImpl();

        List<TesteDbConfig> listaTeste = dao.getAll(TesteDbConfig.class);

        if (listaTeste == null || listaTeste.isEmpty()) {
            
            throw new Exception("Erro ao se conectar com o banco de dados. Impossivel iniciar.");

        }

    }

    public void testarConfiguracaoDb() {

        try {

            config = configInicialView.getDadosAtualizar();

            ConfigUtils.atualizaArquivoConfiguracao(config);

            TesteDbConfigDAO dao = new TesteDbConfigDAOImpl();

            List<TesteDbConfig> listaTeste = dao.getAll(TesteDbConfig.class);

            if (listaTeste != null && !listaTeste.isEmpty()) {

                Mensagens.mostraMensagemSucesso("Configuração com Banco de Dados OK!");

            } else {

                ConfigUtils.atualizaArquivoConfiguracao(configOld);
                Mensagens.mostraMensagemErro("Erro ao se conectar ao Banco de Dados.");

            }
        } catch (Exception ex) {

            Mensagens.mostraMensagemErro("Erro ao inicializar configuração.\n\n" + ex.getMessage());

        }

    }

    private Thread getThreadSalvarConfiguracao() {

        return new Thread() {

            public void run() {

                try {
                    config = configInicialView.getDadosAtualizar();
                    ConfigUtils.atualizaArquivoConfiguracao(config);
                    Mensagens.mostraMensagemSucesso("Configurações efetuadas com sucesso.\n Reinicialize o programa.");
                    System.exit(0);

                } catch (IOException ex) {
                    Mensagens.mostraMensagemErro("Erro ao salvar configuração.\n\n" + ex.getMessage());

                }

            }

        };

    }

    private Thread getThreadConfirmarSaida() {

        return new Thread() {

            public void run() {

                try {
                    int opt = Mensagens.mostraMensagemPergunta("Tem certeza que deseja Sair e cancelar toda configuração?");
                    if (opt == 0) {
                        config.setIsPrimeiroAcesso(Boolean.TRUE);
                        ConfigUtils.atualizaArquivoConfiguracao(config);
                        System.exit(0);
                    }

                } catch (IOException ex) {
                    Mensagens.mostraMensagemErro("Erro ao salvar configuração. Arquivo não encontrado;\n\n" + ex.getMessage());

                }

            }

        };
    }

    //ACOES
    //trata ações dos botoes clicados
    public void acaoClickController(AbstractButton botao) {

        if (botao.isEnabled()) {
            acaoController(botao.getName());
        }
    }

    private void acaoController(String botao) {

        switch (botao) {

            case ConstantesTelas.BTN_CANCELAR:
                getThreadConfirmarSaida().start();
                break;
            case ConstantesTelas.BTN_SALVAR_CONFIGURACAO:
                getThreadSalvarConfiguracao().start();
                break;
            case ConstantesTelas.BTN_TESTAR_CONFIGURACAO:
                testarConfiguracaoDb();//.start();
                break;

        }

        //TEST:
        System.out.println(botao);

    }
}
