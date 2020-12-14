/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.comemprestimostrabalho.entidade;

import java.io.Serializable;

/**
 *
 * @author Jhony Vill da Silva.
 */
public class Autores implements Serializable{
    
    private Integer cdAutores;
    private String nmAutor;
    private Integer cdLivro_FK;

    public Autores() {
    }

    public Autores(Integer cdAutores, String nmAutor, Integer cdLivro_FK) {
        this.cdAutores = cdAutores;
        this.nmAutor = nmAutor;
        this.cdLivro_FK = cdLivro_FK;
    }

    public Integer getCdAutores() {
        return cdAutores;
    }

    public void setCdAutores(Integer cdAutores) {
        this.cdAutores = cdAutores;
    }

    public String getNmAutor() {
        return nmAutor;
    }

    public void setNmAutor(String nmAutor) {
        this.nmAutor = nmAutor;
    }

    public Integer getCdLivro_FK() {
        return cdLivro_FK;
    }

    public void setCdLivro_FK(Integer cdLivro_FK) {
        this.cdLivro_FK = cdLivro_FK;
    }

    

}