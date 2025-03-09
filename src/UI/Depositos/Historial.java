package UI.Depositos;

import UI.Login;

import javax.swing.*;
import java.awt.*;

public class Historial extends JFrame {
    private String menu_sesion = Login.menu_user;
    private JPanel panelHistorial;
    private JTable tablaHistorial;
    private JButton btnActualizar, btnCerrar;

    public Historial() {
        setTitle("Historial de Traspasos - " + menu_sesion);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        panelHistorial = new JPanel();
        panelHistorial.setBackground(new Color(12, 25, 34));
        panelHistorial.setLayout(null);

        JLabel titulo = new JLabel("Historial de Traspasos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(246, 190, 0));
        titulo.setBounds(50, 20, 400, 30);
        panelHistorial.add(titulo);

        String[] columnas = {"Fecha", "Cuenta Origen", "Cuenta Destino", "Monto"};
        Object[][] datos = {}; // Se llenará con datos desde la base de datos

        tablaHistorial = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tablaHistorial);
        scrollPane.setBounds(30, 70, 440, 200);
        panelHistorial.add(scrollPane);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCerrar.setBounds(130, 180, 130, 40);
        panelHistorial.add(btnCerrar);

        add(panelHistorial);

        btnCerrar.addActionListener(e ->{
            dispose();
        });
    }
}
