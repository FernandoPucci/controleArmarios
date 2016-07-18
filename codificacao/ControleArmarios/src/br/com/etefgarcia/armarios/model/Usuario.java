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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 *
 * @author fernando-pucci
 *
 * Classe representativa da tabela USUARIO
 */
@Entity
@Table(name = "USUARIO")
@DynamicUpdate(true)
@SelectBeforeUpdate(true)
public class Usuario implements Serializable {

    public Usuario(Long idUsuario, TipoUsuario tipoUsuario, String nome, Boolean flgAtivo) {
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.nome = nome != null ? nome.toUpperCase() : nome;
        this.flgAtivo = flgAtivo;
    }

    public Usuario() {

        this.flgAtivo = Boolean.TRUE;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("ID_LKP_TIPO_USUARIO"))
    private TipoUsuario tipoUsuario;

    @Column(name = "NOME")
    private String nome;
    
    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "PW")
    private String pw;

    @Column(name = "FLG_ATIVO", nullable = false)
    private Boolean flgAtivo;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome != null ? nome.toUpperCase() : nome;
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
        return "<html>" + nome +", <br/><hr/>" + tipoUsuario.getDescricao()+"</html>" ;
    }

}
