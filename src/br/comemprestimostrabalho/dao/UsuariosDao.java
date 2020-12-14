/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.comemprestimostrabalho.dao;

import br.comemprestimostrabalho.entidade.Usuarios;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jhony Vill da Silva.
 */
public class UsuariosDao implements Serializable{
    
    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;
    
    public void salvar(Usuarios usuarios) throws SQLException {
        String sql = "INSERT INTO usuarios(cdUsuario, Nome, Login, Senha, Logradouro, Cidade, Estado, CEP) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, usuarios.getCdUsuario());
            preparando.setString(2, usuarios.getNome());
            preparando.setString(3, usuarios.getLogin());
            preparando.setString(4, usuarios.getSenha());
            preparando.setString(5, usuarios.getLogradouro());
            preparando.setString(6, usuarios.getCidade());
            preparando.setString(7, usuarios.getEstado());
            preparando.setString(8, usuarios.getCep());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar usuário " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public Usuarios pesquisarPorId(Integer cdUsuario) throws SQLException {
        Usuarios usuarios = null;
        String consulta = "SELECT * FROM usuarios WHERE cdUsuario = ?";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, cdUsuario);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                
                usuarios = new Usuarios();
                usuarios.setCdUsuario(resultSet.getInt(cdUsuario));
                usuarios.setNome(resultSet.getString("Nome"));
                usuarios.setLogin(resultSet.getString("Login"));
                usuarios.setSenha(resultSet.getString("Senha"));
                usuarios.setLogradouro(resultSet.getString("Logradouro"));
                usuarios.setCidade(resultSet.getString("Cidade"));
                usuarios.setEstado(resultSet.getString("Estado"));
                usuarios.setCep(resultSet.getString("CEP"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return usuarios;
    }
    
    public void alterar(Usuarios usuarios) throws SQLException {
        String sql = "UPDATE usuarios SET Nome = ?, Login = ?, Senha = ?, Logradouro = ?, Cidade = ?, Estado = ?, CEP = ?"
                + " WHERE cdUsuario = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, usuarios.getNome());
            preparando.setString(2, usuarios.getLogin());
            preparando.setString(3, usuarios.getSenha());
            preparando.setString(4, usuarios.getLogradouro());
            preparando.setString(5, usuarios.getCidade());
            preparando.setString(6, usuarios.getEstado());
            preparando.setString(7, usuarios.getCep());
            preparando.setInt(8, usuarios.getCdUsuario());
            
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao alterar usuário " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public void excluir(Integer cdUsuario) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM usuarios WHERE cdUsuario = ?");
            preparando.setInt(1, cdUsuario);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
}
