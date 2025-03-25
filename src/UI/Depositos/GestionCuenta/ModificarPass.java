package UI.Depositos.GestionCuenta;

import javax.swing.*;
import java.awt.*;

public class ModificarPass extends JFrame {
    private JPanel panel;
    private JPasswordField txtPassword, txtPassConfirm;
    private JButton btnModificar, btnCancelar;

    public ModificarPass() {
        setTitle("Cambiar contraseña");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setBackground(new Color(30, 40, 50));
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Cambiar contraseña", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setBounds(50, 20, 300, 30);
        panel.add(lblTitulo);

        JLabel lblPass = new JLabel("Nueva contraseña:");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(50, 70, 100, 25);
        lblPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 70, 200, 25);
        panel.add(txtPassword);

        JLabel lblPassConfirm = new JLabel("Confirmar contraseña:");
        lblPassConfirm.setForeground(Color.WHITE);
        lblPassConfirm.setBounds(50, 110, 100, 25);
        lblPassConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblPassConfirm);

        txtPassConfirm = new JPasswordField();
        txtPassConfirm.setBounds(150, 110, 200, 25);
        panel.add(txtPassConfirm);

        btnModificar = new JButton("Modificar contraseña");
        btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
        btnModificar.setBounds(80, 290, 180, 30);
        panel.add(btnModificar);

        btnCancelar = new JButton("Salir");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setBounds(220, 290, 100, 30);
        panel.add(btnCancelar);

        add(panel);

        btnModificar.addActionListener(e -> {
            String pass = new String(txtPassword.getText().trim());
            String passConfirm = new String(txtPassConfirm.getText().trim());
        });

        btnCancelar.addActionListener(e -> {
            dispose();
        });
    }
    }