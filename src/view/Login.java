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
import java.text.ParseException;

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

                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setCpf_usuario(cpf_usuario);
                usuario.setSenha_usuario(senha_usuario);

                UsuarioDAO objUsuarioDao = new UsuarioDAO();
                ResultSet rsUsuarioDao = objUsuarioDao.autenticacaoUsuario(usuario);

                if (rsUsuarioDao.next()) {
                    usuario.setNome_usuario(rsUsuarioDao.getString(1));
                    usuario.setSaldo_usuario(rsUsuarioDao.getFloat(2));
                    usuario.setId_usuario(rsUsuarioDao.getInt(3));
                    ContaCliente frameContaCliente = new ContaCliente(usuario);
                    frameContaCliente.setSize(400, 500);
                    frameContaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameContaCliente.setLocationRelativeTo(null);
                    frameContaCliente.setContentPane(frameContaCliente.getJpTelaPrincipal());
                    frameContaCliente.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválida");
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "LoginVIEW: " + erro);
            } catch (ParseException ex) {
                ex.printStackTrace();
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
