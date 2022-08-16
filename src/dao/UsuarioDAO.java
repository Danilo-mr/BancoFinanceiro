package dao;

import dto.UsuarioDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    Connection conn;
    public ResultSet autenticacaoUsuario(UsuarioDTO objUsuarioDto) {
        conn = new ConexaoDAO().conectaBD();

        try {
            String sql = "select * from usuario where cpf_usuario = ? and senha_usuario = ?";

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

}
