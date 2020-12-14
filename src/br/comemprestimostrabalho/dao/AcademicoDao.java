/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.comemprestimostrabalho.dao;

import br.comemprestimostrabalho.entidade.Academico;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jhony Vill da Silva.
 */
public class AcademicoDao implements Serializable{
    
    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;
    
    public void salvar(Academico academico) throws SQLException {
        String sql = "INSERT INTO academico(cdMatricula, nmAcademico, cpf, dsEmail, dsEndereco, idSexo, idade) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, academico.getCdMatricula());
            preparando.setString(2, academico.getNmAcademico());
            preparando.setInt(3, academico.getCpf());
            preparando.setString(4, academico.getDsEmail());
            preparando.setString(5, academico.getDsEndereco());
            preparando.setInt(6, academico.getIdSexo());
            preparando.setInt(7, academico.getIdade());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao salvar academico " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public Academico pesquisarPorId(Integer cdMatricula) throws SQLException {
        Academico academico = null;
        String consulta = "SELECT * FROM academico WHERE cdMatricula = ?";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setInt(1, cdMatricula);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                
                academico = new Academico();
                academico.setCdMatricula(resultSet.getInt(cdMatricula));
                academico.setNmAcademico(resultSet.getString("nmAcademico"));
                academico.setCpf(resultSet.getInt("cpf"));
                academico.setDsEmail(resultSet.getString("dsEmail"));
                academico.setDsEndereco(resultSet.getString("dsEndereco"));
                academico.setIdSexo(resultSet.getInt("idSexo"));
                academico.setIdade(resultSet.getInt("idade"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return academico;
    }
    
    public void alterar(Academico academico) throws SQLException {
        String sql = "UPDATE academico SET nmAcademico = ?, cpf = ?, "
                + "dsEmail = ?, dsEndereco = ?, idSexo = ?, idade = ? WHERE cdMatricula = ?";

        try {
            conexao = FabricaConexao.abrirConexao();
            
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, academico.getNmAcademico());
            preparando.setInt(2, academico.getCpf());
            preparando.setString(3, academico.getDsEmail());
            preparando.setString(4, academico.getDsEndereco());
            preparando.setInt(5, academico.getIdSexo());
            preparando.setInt(6, academico.getIdade());
            preparando.setInt(7, academico.getCdMatricula());
            preparando.executeUpdate();

        } catch (SQLException eSQL) {
            System.err.println("Erro ao alterar academico " + eSQL.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public void excluir(Integer cdMatricula) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM academico WHERE cdMatricula = ?");
            preparando.setInt(1, cdMatricula);
            preparando.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
}
