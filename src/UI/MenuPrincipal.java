package UI;

import Model.UsuarioActivo;
import UI.Depositos.*;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private JPanel panelMenu;
    private JButton btnDepositar, btnTransferir, btnConsultarSaldo, btnHistorial, btnCuenta, btnCerrar;

    public MenuPrincipal() {
        setTitle("Banco Menú Principal");
        SwingUtilities.invokeLater(() -> {
            setTitle("Banco Menú Principal - " + Login.menu_user);
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(12, 25, 34));
        panelMenu.setLayout(null);

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(200, 30, 300, 40);
        panelMenu.add(titulo);

        btnDepositar = new JButton("Depositar");
        btnTransferir = new JButton("Transferir");
        btnConsultarSaldo = new JButton("Consultar Saldo");
        btnHistorial = new JButton("Historial");
        btnCuenta = new JButton("Cuenta");

        JButton[] botones = {btnDepositar, btnTransferir, btnConsultarSaldo, btnHistorial, btnCuenta};
        int yPosition = 130;

        for (JButton boton : botones) {
            boton.setBounds(250, yPosition, 200, 50);
            boton.setBackground(new Color(246, 190, 0));
            boton.setForeground(new Color(12, 25, 34));
            boton.setFont(new Font("Arial", Font.BOLD, 16));
            boton.setFocusPainted(false);
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelMenu.add(boton);
            yPosition += 70;
        }

        btnCerrar = new JButton("Cerrar sesión");
        btnCerrar.setBounds(560, 30, 110, 20);
        btnCerrar.setBackground(new Color(135, 8, 25));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 10));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelMenu.add(btnCerrar);

        setContentPane(panelMenu);

        btnDepositar.addActionListener(e -> {
            new Depositar().setVisible(true);
        });

        btnTransferir.addActionListener(e -> {
            new Transferir().setVisible(true);
        });

        btnConsultarSaldo.addActionListener(e ->{
            new Consultar().setVisible(true);
        });

        btnHistorial.addActionListener(e ->{
            new Historial().setVisible(true);
        });

        btnCuenta.addActionListener(e ->{
            new Cuenta().setVisible(true);
        });

        btnCerrar.addActionListener(e -> {
            dispose();
            UsuarioActivo.cerrarSesion();
            new Login().setVisible(true);
        });
    }
}

