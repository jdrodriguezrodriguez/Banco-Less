package UI;

import Model.UsuarioActivo;
import UI.Depositos.Consultar;
import UI.Depositos.Depositar;
import UI.Depositos.Historial;
import UI.Depositos.Transferir;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private String menu_sesion = Login.menu_user;
    private JPanel panelMenu;
    private JButton btnDepositar, btnTransferir, btnConsultarSaldo, btnHistorial, btnCuenta;

    public MenuPrincipal() {

        setTitle("Banco Menú Principal - " + menu_sesion);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(12, 25, 34));
        panelMenu.setLayout(null);

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(200, 30, 300, 40);
        panelMenu.add(titulo);

        btnDepositar = new JButton("Depositar");
        btnTransferir = new JButton("Transferir");
        btnConsultarSaldo = new JButton("Consultar Saldo");
        btnHistorial = new JButton("Historial");
        btnCuenta = new JButton("Cuenta");

        JButton[] botones = {btnDepositar, btnTransferir, btnConsultarSaldo, btnHistorial, btnCuenta};
        int yPosition = 100;

        for (JButton boton : botones) {
            boton.setBounds(250, yPosition, 200, 50);
            boton.setBackground(new Color(246, 190, 0));
            boton.setForeground(new Color(12, 25, 34));
            boton.setFont(new Font("Arial", Font.BOLD, 16));
            panelMenu.add(boton);
            yPosition += 70;
        }

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
    }
}

