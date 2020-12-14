/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.comemprestimostrabalho.dao;

import br.comemprestimostrabalho.entidade.Emprestimo;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jhony Vill da Silva.
 */
public class EmprestimoDao implements Serializable{
    
    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;
    
    public void salvar(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO livros(cdEmprestimo, cdUsuario, cdBibliotecario, cdLivro, dtDevolucao, dtRetirada) "
                + "VALUES(?, ?, ?, ?, ?, ?)";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, emprestimo.getCdEmprestimo());
            preparando.setString(2, emprestimo.getCdUsuario());
            preparando.setString(3, emprestimo.getCdBibliotecario());
            preparando.setString(4, emprestimo.getCdLivro());
            preparando.setString(5, emprestimo.getDtDevolucao());
            preparando.setString(6, emprestimo.getDtRetirada());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar emprestimo " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public Emprestimo pesquisarPorId(Integer cdEmprestimo) throws SQLException {
        Emprestimo emprestimo = null;
        String consulta = "SELECT * FROM emprestimo WHERE cdEmprestimo = ?";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, cdEmprestimo);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                
                emprestimo = new Emprestimo();
                emprestimo.setCdEmprestimo(resultSet.getInt(cdEmprestimo));
                emprestimo.setCdUsuario(resultSet.getString("cdUsuario"));
                emprestimo.setCdBibliotecario(resultSet.getString("cdBibliotecario"));
                emprestimo.setCdLivro(resultSet.getString("cdLivro"));
                emprestimo.setDtDevolucao(resultSet.getString("dtDevolucao"));
                emprestimo.setDtRetirada(resultSet.getString("dtRetirada"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return emprestimo;
    }
    
    public void alterar(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimo SET cdUsuario = ?, cdBibliotecario = ?, cdLivro = ?, "
                + "deDevolucao = ?, dtretirada = ? WHERE cdEmprestimo = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, emprestimo.getCdUsuario());
            preparando.setString(2, emprestimo.getCdBibliotecario());
            preparando.setString(3, emprestimo.getCdLivro());
            preparando.setString(4, emprestimo.getDtDevolucao());
            preparando.setString(5, emprestimo.getDtRetirada());
            preparando.setInt(6, emprestimo.getCdEmprestimo());
            
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao alterar emprestimo " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public void excluir(Integer cdEmprestimo) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM emprestimo WHERE cdEmprestimo = ?");
            preparando.setInt(1, cdEmprestimo);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
}
