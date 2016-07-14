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
 * Classe representativa da tabela ALUNO
 */
@Entity
@Table(name = "ALUNO")
@DynamicUpdate(true)
@SelectBeforeUpdate(true)
public class Aluno implements Serializable {

    public Aluno(Long idAluno, String nome, Character sexo, String telefone, String email, Boolean flgAtivo) {
        this.idAluno = idAluno;
        this.nome = nome != null ? nome.toUpperCase() : nome;;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.flgAtivo = flgAtivo;
    }

    public Aluno() {

        this.flgAtivo = Boolean.TRUE;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ALUNO", nullable = false)
    private Long idAluno;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SEXO")
    private Character sexo;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FLG_ATIVO", nullable = false)
    private Boolean flgAtivo;

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome != null ? nome.toUpperCase() : nome;;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo.equals("Masculino") ? 'M'
                : sexo.equals("Feminino") ? 'F'
                        : sexo.equals("M") ? 'M'
                                : sexo.equals("F") ? 'F'
                                        : '?';
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Aluno{" + "idAluno=" + idAluno + ", nome=" + nome + ", sexo=" + sexo + ", telefone=" + telefone + ", email=" + email + ", flgAtivo=" + flgAtivo + '}';
    }

}
