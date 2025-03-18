package UI.Depositos;

import Controller.FormValidatorController;
import Model.UsuarioActivo;
import Service.UsuariosService;

import javax.swing.*;
import java.awt.*;

public class Cuenta extends JFrame {
    private JPanel panel;
    private JTextField txtNombre, txtApellido, txtUsuario, txtCorreo;
    private JPasswordField txtPassword;
    private JButton btnGuardar, btnCancelar;

    public Cuenta() {
        setTitle("Modificar Cuenta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setBackground(new Color(30, 40, 50));
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Modificar Datos", SwingConstants.CENTER);
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

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setForeground(Color.WHITE);
        lblCorreo.setBounds(50, 150, 100, 25);
        lblCorreo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 150, 200, 25);
        panel.add(txtCorreo);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(50, 190, 100, 25);
        lblUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 190, 200, 25);
        panel.add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBounds(50, 240, 100, 25);
        lblPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 240, 200, 25);
        panel.add(txtPassword);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setBounds(80, 290, 100, 30);
        panel.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setBounds(220, 290, 100, 30);
        panel.add(btnCancelar);

        add(panel);

        UsuarioActivo usuarioActivo = UsuarioActivo.getLinea();
        txtUsuario.setText(usuarioActivo.getUsername());
        txtUsuario.setEditable(false);

        btnGuardar.addActionListener(e -> {
            String newNombre = txtNombre.getText();
            String newApellido = txtApellido.getText();
            String newPassword = new String(txtPassword.getPassword()).trim();
            String newCorreo = txtCorreo.getText();



        });

        btnCancelar.addActionListener(e -> {
            dispose();
        });
    }
}
