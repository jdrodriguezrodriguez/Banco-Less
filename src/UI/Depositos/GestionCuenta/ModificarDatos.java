package UI.Depositos.GestionCuenta;

import javax.swing.*;
import java.awt.*;

public class ModificarDatos extends JFrame {
    private JPanel panel;
    private JTextField txtNombre, txtNacimiento, txtUsuario, txtCorreo, txtApellido, txtDocumento;
    private JButton btnModificar, btnCancelar;

    public ModificarDatos() {
        setTitle("Modificando datos personales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 490);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setBackground(new Color(30, 40, 50));
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Cambiar datos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setBounds(50, 20, 300, 30);
        panel.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setBounds(50, 70, 100, 25);
        lblNombre.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 70, 200, 25);
        panel.add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setForeground(Color.WHITE);
        lblApellido.setBounds(50, 110, 100, 25);
        lblApellido.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(150, 110, 200, 25);
        panel.add(txtApellido);

        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setForeground(Color.WHITE);
        lblDocumento.setBounds(50, 150, 100, 25);
        lblDocumento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblDocumento);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(150, 150, 200, 25);
        panel.add(txtDocumento);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setForeground(Color.WHITE);
        lblCorreo.setBounds(50, 190, 100, 25);
        lblCorreo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 190, 200, 25);
        txtCorreo.setEditable(false);
        txtCorreo.setBackground(Color.RED);
        panel.add(txtCorreo);

        JLabel lblNacimiento = new JLabel("Nacimiento:");
        lblNacimiento.setForeground(Color.WHITE);
        lblNacimiento.setBounds(50, 230, 100, 25);
        lblNacimiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblNacimiento);

        txtNacimiento = new JTextField();
        txtNacimiento.setBounds(150, 230, 200, 25);
        panel.add(txtNacimiento);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(50, 270, 100, 25);
        lblUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 270, 200, 25);
        txtUsuario.setHorizontalAlignment(JTextField.CENTER);
        panel.add(txtUsuario);

        btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
        btnModificar.setBackground(new Color(46, 204, 113));
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setBounds(80, 340, 100, 30);
        panel.add(btnModificar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBounds(220, 340, 100, 30);
        panel.add(btnCancelar);

        add(panel);

        btnModificar.addActionListener(e -> {
            String newNombre = txtNombre.getText().trim();
            String newApellido = txtApellido.getText().trim();
            String newCorreo = txtCorreo.getText().trim().toLowerCase();
            String nacimiento = txtNacimiento.getText().trim();
        });

        btnCancelar.addActionListener(e -> {
            dispose();
        });
    }
}