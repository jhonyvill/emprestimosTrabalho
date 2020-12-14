/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.comemprestimostrabalho.dao;

import br.comemprestimostrabalho.entidade.Bibliotecario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jhony Vill da Silva.
 */
public class BibliotecarioDao implements Serializable{
    
    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;
    
    public void salvar(Bibliotecario bibliotecario) throws SQLException {
        String sql = "INSERT INTO bibliotecario(cdBibliotecario, Login, Senha, Nome, Logradouro, Cidade, Estado, CEP) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, bibliotecario.getCdBibliotecario());
            preparando.setString(2, bibliotecario.getLogin());
            preparando.setString(3, bibliotecario.getSenha());
            preparando.setString(4, bibliotecario.getNome());
            preparando.setString(5, bibliotecario.getLogradouro());
            preparando.setString(6, bibliotecario.getCidade());
            preparando.setString(7, bibliotecario.getEstado());
            preparando.setString(8, bibliotecario.getCep());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar bibliotecário " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public Bibliotecario pesquisarPorId(Integer cdBibliotecario) throws SQLException {
        Bibliotecario bibliotecario = null;
        String consulta = "SELECT * FROM bibliotecario WHERE cdBibliotecario = ?";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, cdBibliotecario);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                
                bibliotecario = new Bibliotecario();
                bibliotecario.setCdBibliotecario(resultSet.getInt(cdBibliotecario));
                bibliotecario.setLogin(resultSet.getString("Login"));
                bibliotecario.setSenha(resultSet.getString("Senha"));
                bibliotecario.setNome(resultSet.getString("Nome"));
                bibliotecario.setLogradouro(resultSet.getString("Logradouro"));
                bibliotecario.setCidade(resultSet.getString("Cidade"));
                bibliotecario.setEstado(resultSet.getString("Estado"));
                bibliotecario.setCep(resultSet.getString("CEP"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return bibliotecario;
    }
    
    public void alterar(Bibliotecario bibliotecario) throws SQLException {
        String sql = "UPDATE bibliotecario SET Login = ?, Senha = ?, Nome = ?, Logradouro = ?, Cidade = ?, Estado = ?, CEP = ?"
                + " WHERE cdBibliotecario = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, bibliotecario.getLogin());
            preparando.setString(2, bibliotecario.getSenha());
            preparando.setString(3, bibliotecario.getNome());
            preparando.setString(4, bibliotecario.getLogradouro());
            preparando.setString(5, bibliotecario.getCidade());
            preparando.setString(6, bibliotecario.getEstado());
            preparando.setString(7, bibliotecario.getCep());
            preparando.setInt(8, bibliotecario.getCdBibliotecario());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao alterar bibliotecário " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public void excluir(Integer cdBibliotecario) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM bibliotecario WHERE cdBibliotecario = ?");
            preparando.setInt(1, cdBibliotecario);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
}
