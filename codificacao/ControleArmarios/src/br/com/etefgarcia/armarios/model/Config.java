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
package br.com.etefgarcia.armarios.model;

import br.com.etefgarcia.armarios.util.ConfigUtils;
import br.com.etefgarcia.armarios.util.constantes.Constantes;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author fernando-pucci
 *
 * Arquivo de Configuracoes do Sistema (Banco de Dados)
 */
public class Config implements Serializable {

    public static Long id = 139423L;

    private String host;
    private String servidorPorta;
    private String banco;
    private String usuario;
    private String senha;
    private Boolean isPrimeiroAcesso;

    public Config(Boolean isPrimeiroAcesso) {

        if (isPrimeiroAcesso) {

            this.servidorPorta = Constantes.PORT;
            this.banco = Constantes.DB;
            this.isPrimeiroAcesso = isPrimeiroAcesso;

        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getServidorPorta() {
        return servidorPorta;
    }

    public void setServidorPorta(String servidorPorta) {
        this.servidorPorta = servidorPorta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getIsPrimeiroAcesso() {
        return isPrimeiroAcesso;
    }

    public void setIsPrimeiroAcesso(String isPrimeiroAcesso) {
        if (isPrimeiroAcesso == null) {
            this.isPrimeiroAcesso = Boolean.TRUE;
        }
        this.isPrimeiroAcesso = isPrimeiroAcesso.equalsIgnoreCase("SIM") ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setIsPrimeiroAcesso(Boolean isPrimeiroAcesso) {
        if (isPrimeiroAcesso == null) {
            isPrimeiroAcesso = Boolean.TRUE;
        }
        this.isPrimeiroAcesso = isPrimeiroAcesso;
    }

    public void atualizaConfiguracao() throws IOException {

        ConfigUtils.atualizaArquivoConfiguracao(this);

    }

    @Override
    public String toString() {
        return "Config{" + "host=" + host + ", servidorPorta=" + servidorPorta + ", banco=" + banco + ", usuario=" + usuario + ", senha=" + senha + ", isPrimeiroAcesso=" + isPrimeiroAcesso + '}';
    }

}
