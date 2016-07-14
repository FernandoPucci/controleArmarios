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

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 *
 * @author fernando-pucci
 *
 * Classe representativa da tabela LKP_TIPO_USUARIO
 */
@Entity
@Table(name = "LKP_TIPO_USUARIO")
@DynamicUpdate(true)
@SelectBeforeUpdate(true)
public class TipoUsuario implements Serializable {

    public TipoUsuario(Long idTipoUsuario, String descricao, Boolean flgAtivo) {
        this.idTipoUsuario = idTipoUsuario;
        this.descricao = descricao != null ? descricao.toUpperCase() : descricao;;
        this.flgAtivo = flgAtivo;
    }

    public TipoUsuario() {
        this.flgAtivo = Boolean.TRUE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_LKP_TIPO_USUARIO", nullable = false)
    private Long idTipoUsuario;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "FLG_ATIVO", nullable = false)
    private Boolean flgAtivo;

    public Long getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Long idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao != null ? descricao.toUpperCase() : descricao;;
    }

    public Boolean getFlgAtivo() {
        return flgAtivo;
    }

    public String getFlgAtivoStr() {

        if (flgAtivo == null) {

            this.flgAtivo = Boolean.FALSE;

        }

        return flgAtivo ? "SIM" : "NÃO";
    }

    public void setFlgAtivo(Boolean flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" + "idTipoUsuario=" + idTipoUsuario + ", descricao=" + descricao + ", flgAtivo=" + flgAtivo + '}';
    }

}
