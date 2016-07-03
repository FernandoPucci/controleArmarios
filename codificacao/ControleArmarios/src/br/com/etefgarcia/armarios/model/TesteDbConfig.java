/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etefgarcia.armarios.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author fernando-pucci
 * 
 * Classe para teste de conexao com base de dados
 */
@Entity
@Table(name = "DB_TESTE")
public class TesteDbConfig implements Serializable {
    
    @Id
    @Column(name = "ID")    
    private Long ID;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    
}
