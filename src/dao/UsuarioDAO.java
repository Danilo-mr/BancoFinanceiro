package dao;

import dto.UsuarioDTO;

import javax.swing.*;
import java.sql.*;


public class UsuarioDAO {
    Connection conn;
    public ResultSet autenticacaoUsuario(UsuarioDTO objUsuarioDto) {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "select nome_usuario, saldo_usuario, id_usuario from usuario where cpf_usuario = ? and senha_usuario = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUsuarioDto.getCpf_usuario());
            pstm.setString(2, objUsuarioDto.getSenha_usuario());

            ResultSet rs = pstm.executeQuery();


            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);
            return null;
        }
    }

    public void cadastrarUsuario(UsuarioDTO usuarioDTO) {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "insert into usuario (nome_usuario, cpf_usuario, senha_usuario) values (?,?,?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, usuarioDTO.getNome_usuario());
            pstm.setString(2, usuarioDTO.getCpf_usuario());
            pstm.setString(3, usuarioDTO.getSenha_usuario());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Registrado com sucesso!");

        }   catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "CPF já cadastrado!");
        }
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);
        }
    }

    public void atualizarSaldo(float novoSaldo, UsuarioDTO usuarioDTO){
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "update usuario set saldo_usuario = ? where id_usuario = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setFloat(1, usuarioDTO.getSaldo_usuario());
            pstm.setInt(2, usuarioDTO.getId_usuario());
            pstm.execute();
            pstm.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Atualizar Saldo: " + erro);
        }
    };
}
