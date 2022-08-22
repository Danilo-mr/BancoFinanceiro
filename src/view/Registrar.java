package view;

import dao.UsuarioDAO;
import dto.UsuarioDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Registrar extends JFrame{
    private JPanel panelRegistrar;
    private JLabel lblLogin;
    private JTextField txtNome;
    private JLabel CPF;
    private JTextField txtCPF;
    private JPasswordField txtSenha;
    private JLabel loginLbl;
    private JButton registrarButton;

    public Registrar() {
        loginLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Login loginFrame = new Login();
                loginFrame.setSize(400, 500);
                loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginFrame.setLocationRelativeTo(null);
                loginFrame.setContentPane(loginFrame.getPanelLogin());
                loginFrame.setVisible(true);
                dispose();
            }
        });
        registrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                panelRegistrar.setCursor(cursor);
            }
        });
        registrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                panelRegistrar.setCursor(cursor);
            }
        });
        loginLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                panelRegistrar.setCursor(cursor);
            }
        });
        loginLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                panelRegistrar.setCursor(cursor);
            }
        });
        registrarButton.addActionListener(e -> {
            String nome, cpf, senha;
            nome = txtNome.getText();
            cpf = txtCPF.getText();
            senha = String.valueOf(txtSenha.getPassword());
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNome_usuario(nome);
            usuarioDTO.setCpf_usuario(cpf);
            usuarioDTO.setSenha_usuario(senha);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.cadastrarUsuario(usuarioDTO);
        });
    }

    public JPanel getPanelRegistrar() {
        return panelRegistrar;
    }

    public void setPanelRegistrar(JPanel panelRegistrar) {
        this.panelRegistrar = panelRegistrar;
    }

    public JLabel getLblLogin() {
        return lblLogin;
    }

    public void setLblLogin(JLabel lblLogin) {
        this.lblLogin = lblLogin;
    }
}
