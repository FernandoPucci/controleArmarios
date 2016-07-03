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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 *
 * @author fernando-pucci
 *
 * Classe representativa da tabela ALUGUEL_ARMARIO
 */
@Entity
@Table(name = "ALUGUEL_ARMARIO")
@DynamicUpdate(true)
@SelectBeforeUpdate(true)
public class AluguelArmario implements Serializable {

    public AluguelArmario(Long idAluguelArmario, Armario armario, Aluno aluno, Usuario usuarioResponsavel, Date dataAluguel, Date dataDevolucao, Boolean flgDevolvido) {
        this.idAluguelArmario = idAluguelArmario;
        this.armario = armario;
        this.aluno = aluno;
        this.usuarioResponsavel = usuarioResponsavel;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
        this.flgDevolvido = flgDevolvido;
    }

    public AluguelArmario() {

        this.flgDevolvido = Boolean.FALSE;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ALUGUEL_ARMARIO", nullable = false)
    private Long idAluguelArmario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("ID_ARMARIO"))
    private Armario armario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("ID_ALUNO"))
    private Aluno aluno;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("ID_USUARIO_RESPONSAVEL"))
    private Usuario usuarioResponsavel;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_ALUGUEL", nullable = true, insertable = false) //configuracoes para permitir insercao de datas null
    private Date dataAluguel;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_DEVOLUCAO", nullable = true, insertable = false)//configuracoes para permitir insercao de datas null
    private Date dataDevolucao;

    @Column(name = "FLG_DEVOLVIDO")
    private Boolean flgDevolvido;

    public Long getIdAluguelArmario() {
        return idAluguelArmario;
    }

    public void setIdAluguelArmario(Long idAluguelArmario) {
        this.idAluguelArmario = idAluguelArmario;
    }

    public Armario getArmario() {
        return armario;
    }

    public void setArmario(Armario armario) {
        this.armario = armario;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel == null ? new Date() : dataAluguel;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao == null ? new Date() : dataDevolucao;
    }

    public Boolean getFlgDevolvido() {
        return flgDevolvido;
    }

    public void setFlgDevolvido(Boolean flgDevolvido) {
        this.flgDevolvido = flgDevolvido;
    }

    @Override
    public String toString() {
        return "AluguelArmario{" + "idAluguelArmario=" + idAluguelArmario + ", armario=" + armario + ", aluno=" + aluno + ", usuarioResponsavel=" + usuarioResponsavel + ", dataAluguel=" + dataAluguel + ", dataDevolucao=" + dataDevolucao + ", flgDevolvido=" + flgDevolvido + '}';
    }

}
