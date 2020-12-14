/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.comemprestimostrabalho.dao;

import br.comemprestimostrabalho.entidade.Livros;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jhony Vill da Silva.
 */
public class LivrosDao implements Serializable{
    
    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;
    
    public void salvar(Livros livros) throws SQLException {
        String sql = "INSERT INTO livros(cdLivro, titulo, resumo, dtPublicacao, edicao, nmEditora, cidade, Estado) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, livros.getCdLivro());
            preparando.setString(2, livros.getTitulo());
            preparando.setString(3, livros.getResumo());
            preparando.setString(4, livros.getDtPublicacao());
            preparando.setString(5, livros.getEdicao());
            preparando.setString(6, livros.getNmEditora());
            preparando.setString(7, livros.getCidade());
            preparando.setString(8, livros.getEstado());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar livro " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public Livros pesquisarPorId(Integer cdLivro) throws SQLException {
        Livros livros = null;
        String consulta = "SELECT * FROM livros WHERE cdLivro = ?";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, cdLivro);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                
                livros = new Livros();
                livros.setCdLivro(resultSet.getInt(cdLivro));
                livros.setTitulo(resultSet.getString("Titulo"));
                livros.setResumo(resultSet.getString("Resumo"));
                livros.setDtPublicacao(resultSet.getString("dtPublicacao"));
                livros.setEdicao(resultSet.getString("Edicao"));
                livros.setNmEditora(resultSet.getString("nmEditora"));
                livros.setCidade(resultSet.getString("Cidade"));
                livros.setEstado(resultSet.getString("Estado"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return livros;
    }
    
    public void alterar(Livros livros) throws SQLException {
        String sql = "UPDATE livros SET titulo = ?, resumo = ?, dtPublicacao = ?, "
                + "edicao = ?, nmEditora = ?, cidade = ?, estado = ? WHERE cdLivro = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, livros.getTitulo());
            preparando.setString(2, livros.getResumo());
            preparando.setString(3, livros.getDtPublicacao());
            preparando.setString(4, livros.getEdicao());
            preparando.setString(5, livros.getNmEditora());
            preparando.setString(6, livros.getCidade());
            preparando.setString(7, livros.getEstado());
            preparando.setInt(8, livros.getCdLivro());
            
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao alterar livro " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public void excluir(Integer cdLivro) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM livros WHERE cdLivro = ?");
            preparando.setInt(1, cdLivro);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
}
