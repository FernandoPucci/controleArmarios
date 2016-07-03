/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etefgarcia.armarios.exceptions;

/**
 *
 * @author fernando-pucci
 */
public class NegocioException extends Exception {

    public NegocioException(String message) {
        super("Erro de Neg?cio:\n" + message);
    }
}
