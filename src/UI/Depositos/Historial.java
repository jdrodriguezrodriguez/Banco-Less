package UI.Depositos;

import UI.Login;

import javax.swing.*;
import java.awt.*;

public class Historial extends JFrame {
    private String menu_sesion = Login.menu_user;
    private JPanel panelHistorial;
    private JLabel lblSaldo;
    private JButton btnActualizar, btnCerrar;

    public Historial() {
        setTitle("Historial traspasos - " + menu_sesion);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        panelHistorial = new JPanel();
        panelHistorial.setBackground(new Color(12, 25, 34));
        panelHistorial.setLayout(null);

        JLabel titulo = new JLabel("Historial", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(new Color(246, 190, 0));
        titulo.setBounds(50, 20, 300, 30);
        panelHistorial.add(titulo);

        lblSaldo = new JLabel("", SwingConstants.CENTER);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 24));
        lblSaldo.setForeground(Color.WHITE);
        lblSaldo.setBounds(50, 80, 300, 40);
        panelHistorial.add(lblSaldo);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCerrar.setBounds(130, 180, 130, 40);
        panelHistorial.add(btnCerrar);

        add(panelHistorial);
    }
}