package view;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        btnLogin.addActionListener(e -> {
            try {
                String cpf_usuario, senha_usuario;
                cpf_usuario = txtCPF.getText();
                senha_usuario = String.valueOf(txtSenha.getPassword());
                UsuarioDTO objUsuarioDto = new UsuarioDTO();
                objUsuarioDto.setCpf_usuario(cpf_usuario);
                objUsuarioDto.setSenha_usuario(senha_usuario);

                UsuarioDAO objUsuarioDao = new UsuarioDAO();
                ResultSet rsUsuarioDao = objUsuarioDao.autenticacaoUsuario(objUsuarioDto);

                if (rsUsuarioDao.next()) {
                    ContaCliente objContaCliente = new ContaCliente();
                    objContaCliente.setSize(400, 500);
                    objContaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    objContaCliente.setLocationRelativeTo(null);
                    objContaCliente.setContentPane(objContaCliente.getJpTelaPrincipal());
                    objContaCliente.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválida");
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "LoginVIEW: " + erro);
            }

        });
        lblRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Registrar objRegistrar = new Registrar();
                objRegistrar.setSize(400, 500);
                objRegistrar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                objRegistrar.setLocationRelativeTo(null);
                objRegistrar.setContentPane(objRegistrar.getPanelRegistrar());
                objRegistrar.setVisible(true);
                dispose();
            }
        });
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                panelLogin.setCursor(cursor);
            }
        });

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                panelLogin.setCursor(cursor);
            }
        });

        lblRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                panelLogin.setCursor(cursor);
            }
        });

        lblRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                panelLogin.setCursor(cursor);
            }
        });
    }

    public JPanel getPanelLogin() {
        return panelLogin;
    }

    public void setPanelLogin(JPanel panelLogin) {
        this.panelLogin = panelLogin;
    }

    public JTextField getTxtCPF() {
        return txtCPF;
    }

    public void setTxtCPF(JTextField txtCPF) {
        this.txtCPF = txtCPF;
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }

    public void setTxtSenha(JPasswordField txtSenha) {
        this.txtSenha = txtSenha;
    }

    public JLabel getLblLogin() {
        return lblLogin;
    }

    public void setLblLogin(JLabel lblLogin) {
        this.lblLogin = lblLogin;
    }

    public JLabel getLblRegistrar() {
        return lblRegistrar;
    }

    public void setLblRegistrar(JLabel lblRegistrar) {
        this.lblRegistrar = lblRegistrar;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(JButton btnLogin) {
        this.btnLogin = btnLogin;
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
