package view;

import dao.UsuarioDAO;
import dto.UsuarioDTO;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

public class ContaCliente extends JFrame {
    private JPanel jpTelaPrincipal;
    private JLabel lblBemVindo;
    private JLabel labelSaldo;
    private JRadioButton depositarRadioButton;
    private JRadioButton sacarRadioButton;
    private JButton efetuarButton;
    private JFormattedTextField formattedTextFieldQuantia;
    private JFormattedTextField saldoDisponivelLabel;

    public ContaCliente(UsuarioDTO usuario) throws ParseException {
        lblBemVindo.setText("Bem Vindo, " + usuario.getNome_usuario() + "!");
        saldoDisponivelLabel.setText("" + usuario.getSaldo_usuario());

        ButtonGroup depositarSacarGroup = new ButtonGroup();
        depositarSacarGroup.add(depositarRadioButton);
        depositarSacarGroup.add(sacarRadioButton);
        efetuarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                jpTelaPrincipal.setCursor(cursor);
            }
        });
        efetuarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                jpTelaPrincipal.setCursor(cursor);
            }
        });
        efetuarButton.addActionListener(e -> {
            if(!depositarRadioButton.isSelected() && !sacarRadioButton.isSelected()) {
                JOptionPane.showMessageDialog(null, "Selecione Sacar ou Depositar");
            } else {
                if(depositarRadioButton.isSelected()) {
                    float novoSaldo = usuario.getSaldo_usuario() + Float.valueOf(formattedTextFieldQuantia.getValue().toString());
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuarioDAO.atualizarSaldo(novoSaldo, usuario);
                    usuario.setSaldo_usuario(novoSaldo);
                    saldoDisponivelLabel.setText(Float.toString(novoSaldo));
                } else {
                    if (sacarRadioButton.isSelected()) {
                        float novoSaldo = usuario.getSaldo_usuario() - Float.valueOf(formattedTextFieldQuantia.getValue().toString());
                        UsuarioDAO usuarioDAO = new UsuarioDAO();
                        usuarioDAO.atualizarSaldo(novoSaldo, usuario);
                        usuario.setSaldo_usuario(novoSaldo);
                        saldoDisponivelLabel.setText(Float.toString(novoSaldo));
                    }
                }
            }
        });

        formattedTextFieldQuantia.setFormatterFactory( new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#,###.00"))));
        saldoDisponivelLabel.setEditable(false);
    }

    public JPanel getJpTelaPrincipal() {
        return jpTelaPrincipal;
    }

    public void setJpTelaPrincipal(JPanel jpTelaPrincipal) {
        this.jpTelaPrincipal = jpTelaPrincipal;
    }

    public JLabel getLblBemVindo() {
        return lblBemVindo;
    }

    public void setLblBemVindo(JLabel lblBemVindo) {
        this.lblBemVindo = lblBemVindo;
    }
}
