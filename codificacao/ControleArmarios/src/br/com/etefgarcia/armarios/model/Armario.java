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
 * Classe representativa da tabela ARMARIO
 */
@Entity
@Table(name = "ARMARIO")
@DynamicUpdate(true)
@SelectBeforeUpdate(true)
public class Armario implements Serializable {

    public Armario(Long idArmario, Long chave, String descricao, Boolean flgOcupado, Boolean flgAtivo) {
        this.idArmario = idArmario;
        this.chave = chave;
        this.descricao = descricao != null ? descricao.toUpperCase() : descricao;;
        this.flgOcupado = flgOcupado;
        this.flgAtivo = flgAtivo;
    }

    public Armario() {

        this.flgOcupado = Boolean.FALSE;
        this.flgAtivo = Boolean.TRUE;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ARMARIO", nullable = false)
    private Long idArmario;

    @Column(name = "CHAVE")
    private Long chave;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "FLG_OCUPADO", nullable = false)
    private Boolean flgOcupado;

    @Column(name = "FLG_ATIVO", nullable = false)
    private Boolean flgAtivo;

    public Long getIdArmario() {
        return idArmario;
    }

    public void setIdArmario(Long idArmario) {
        this.idArmario = idArmario;
    }

    public Long getChave() {
        return chave;
    }

    public void setChave(Long chave) {
        this.chave = chave;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao != null ? descricao.toUpperCase() : descricao;;
    }

    public Boolean getFlgOcupado() {
        return flgOcupado;
    }

    public void setFlgOcupado(Boolean flgOcupado) {
        this.flgOcupado = flgOcupado;
    }

    public Boolean getFlgAtivo() {
        return flgAtivo;
    }

    public void setFlgAtivo(Boolean flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

    @Override
    public String toString() {
        return "Armario{" + "idArmario=" + idArmario + ", chave=" + chave + ", descricao=" + descricao + ", flgOcupado=" + flgOcupado + ", flgAtivo=" + flgAtivo + '}';
    }

}
