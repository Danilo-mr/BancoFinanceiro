package view;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private JPanel panelLogin;
    private JTextField txtCPF;
    private JPasswordField txtSenha;
    private JLabel lblLogin;
    private JLabel lblRegistrar;
    private JButton btnLogin;

    public Login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cpf_usuario, senha_usuario;
                    cpf_usuario = txtCPF.getText();
                    senha_usuario = String.valueOf(txtSenha.getPassword());
                    UsuarioDTO objUsuarioDto = new UsuarioDTO();
                    objUsuarioDto.setCpf_usuario(cpf_usuario);
                    objUsuarioDto.setSenha_usuario(senha_usuario);

                    UsuarioDAO objUsuarioDao = new UsuarioDAO();
                    ResultSet rsUsuarioDao = objUsuarioDao.autenticacaoUsuario(objUsuarioDto);

                    if(rsUsuarioDao.next()) {
                        FrmPrincipalVIEW objFrmPrincipalView = new FrmPrincipalVIEW();
                        objFrmPrincipalView.setSize(400, 500);
                        objFrmPrincipalView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        objFrmPrincipalView.setLocationRelativeTo(null);
                        objFrmPrincipalView.setContentPane(objFrmPrincipalView.getJpTelaPrincipal());
                        objFrmPrincipalView.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha inválida");
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "LoginVIEW: " + erro);
                }

            }
        });
    }

    public static void main(String[] args) {
        Login loginFrame = new Login();
        loginFrame.setTitle("Bem-vindo!");
        loginFrame.setContentPane(loginFrame.panelLogin);
        loginFrame.setSize(400, 500);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }
}
