/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.comemprestimostrabalho.dao;

import br.comemprestimostrabalho.entidade.Autores;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jhony Vill da Silva.
 */
public class AutoresDao implements Serializable{
    
    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;
    
    public void salvar(Autores autores) throws SQLException {
        String sql = "INSERT INTO autores(cdAutores, nmAutor, cdLivro_FK) "
                + "VALUES(?, ?, ?)";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, autores.getCdAutores());
            preparando.setString(2, autores.getNmAutor());
            preparando.setInt(3, autores.getCdLivro_FK());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar autor " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public Autores pesquisarPorId(Integer cdAutores) throws SQLException {
        Autores autores = null;
        String consulta = "SELECT * FROM autores WHERE cdAutores = ?";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, cdAutores);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                
                autores = new Autores();
                autores.setCdAutores(resultSet.getInt(cdAutores));
                autores.setNmAutor(resultSet.getString("nmAutor"));
                autores.setCdLivro_FK(resultSet.getInt("cdLivro_FK"));

            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return autores;
    }
    
    public void alterar(Autores autores) throws SQLException {
        String sql = "UPDATE autores SET nmAutor = ?, cdLivro_FK = ? WHERE cdAutores = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, autores.getNmAutor());
            preparando.setInt(2, autores.getCdLivro_FK());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao alterar autor " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public void excluir(Integer cdAutores) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM autores WHERE cdAutores = ?");
            preparando.setInt(1, cdAutores);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
}
