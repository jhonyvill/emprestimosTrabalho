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
public class Emprestimo implements Serializable{
    
    private Integer cdEmprestimo;
    private String cdUsuario;
    private String cdBibliotecario;
    private String cdLivro;
    private String dtDevolucao;
    private String dtRetirada;

    public Emprestimo() {
    }

    public Emprestimo(Integer cdEmprestimo, String cdUsuario, String cdBibliotecario, String cdLivro, String dtDevolucao, String dtRetirada) {
        this.cdEmprestimo = cdEmprestimo;
        this.cdUsuario = cdUsuario;
        this.cdBibliotecario = cdBibliotecario;
        this.cdLivro = cdLivro;
        this.dtDevolucao = dtDevolucao;
        this.dtRetirada = dtRetirada;
    }

    public Integer getCdEmprestimo() {
        return cdEmprestimo;
    }

    public void setCdEmprestimo(Integer cdEmprestimo) {
        this.cdEmprestimo = cdEmprestimo;
    }

    public String getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(String cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getCdBibliotecario() {
        return cdBibliotecario;
    }

    public void setCdBibliotecario(String cdBibliotecario) {
        this.cdBibliotecario = cdBibliotecario;
    }

    public String getCdLivro() {
        return cdLivro;
    }

    public void setCdLivro(String cdLivro) {
        this.cdLivro = cdLivro;
    }

    public String getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(String dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    public String getDtRetirada() {
        return dtRetirada;
    }

    public void setDtRetirada(String dtRetirada) {
        this.dtRetirada = dtRetirada;
    }
}
