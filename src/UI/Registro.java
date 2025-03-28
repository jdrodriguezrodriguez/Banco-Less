package UI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import Controller.FormValidatorController;
import DAO.CuentaDao;
import DAO.PersonaDao;
import DAO.UsuarioDao;
import Service.UsuariosService;

public class Registro extends JFrame {

    private JPanel panelRegistro;
    private JTextField txtNombre, txtApellido, txtDocumento, txtNacimiento, txtCorreo;
    private JPasswordField txtPassword;
    private JButton btnRegistrar, btnCancelar;

    public Registro() {
        setTitle("Banco Less - Registro");
        setSize(400, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        panelRegistro = new JPanel();
        panelRegistro.setLayout(null);
        panelRegistro.setBackground(new Color(12, 25, 34));

        JLabel lblTitulo = new JLabel("Registro");
        lblTitulo.setBounds(120, 30, 200, 50);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 40));
        lblTitulo.setForeground(Color.WHITE);
        panelRegistro.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 130, 100, 25);
        lblNombre.setForeground(Color.WHITE);
        panelRegistro.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 130, 200, 25);
        panelRegistro.add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(50, 180, 100, 25);
        lblApellido.setForeground(Color.WHITE);
        panelRegistro.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(150, 180, 200, 25);
        panelRegistro.add(txtApellido);

        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setBounds(50, 230, 100, 25);
        lblDocumento.setForeground(Color.WHITE);
        panelRegistro.add(lblDocumento);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(150, 230, 200, 25);
        panelRegistro.add(txtDocumento);

        JLabel lblNacimiento = new JLabel("Nacimiento:");
        lblNacimiento.setBounds(50, 280, 100, 25);
        lblNacimiento.setForeground(Color.WHITE);
        panelRegistro.add(lblNacimiento);

        txtNacimiento = new JTextField();
        txtNacimiento.setBounds(150, 280, 200, 25);
        panelRegistro.add(txtNacimiento);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 330, 100, 25);
        lblCorreo.setForeground(Color.WHITE);
        panelRegistro.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 330, 200, 25);
        panelRegistro.add(txtCorreo);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 380, 100, 25);
        lblPassword.setForeground(Color.WHITE);
        panelRegistro.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 380, 200, 25);
        panelRegistro.add(txtPassword);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(50, 440, 300, 40);
        btnRegistrar.setBackground(new Color(246, 190, 0));
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelRegistro.add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 500, 300, 40);
        btnCancelar.setBackground(new Color(223, 45, 45));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelRegistro.add(btnCancelar);

        JLabel label_alerta = new JLabel("");
        label_alerta.setFont(new Font("Arial", Font.BOLD, 16));
        label_alerta.setBounds(130,550,250,40);
        label_alerta.setForeground(new Color(200, 10, 5));
        panelRegistro.add(label_alerta);

        setContentPane(panelRegistro);

        //BOTON REGISTRAR
        btnRegistrar.addActionListener(e ->{
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String documento = txtDocumento.getText().trim();
            String nacimiento = txtNacimiento.getText().trim();
            String correo = txtCorreo.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (UsuariosService.ValidarCamposRegistro(nombre,apellido,documento,nacimiento, correo, password)) {

                FormValidatorController controller = new FormValidatorController(new UsuariosService(new PersonaDao(), new UsuarioDao(), new CuentaDao()));
                boolean registroExitoso = false;

                try {
                    registroExitoso = controller.registrarNuevaPersona(nombre, apellido, documento, nacimiento, correo, password);

                    if (registroExitoso){

                        dispose();
                        new Login().setVisible(true);
                        JOptionPane.showMessageDialog(null, "REGISTRO COMPLETADO.");

                    }else{
                        label_alerta.setText("Error al registrar");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                label_alerta.setText("Verifique los campos");
                txtNombre.setBackground(Color.RED);
                txtApellido.setBackground(Color.RED);
                txtDocumento.setBackground(Color.RED);
                txtPassword.setBackground(Color.RED);
                txtNacimiento.setBackground(Color.RED);
            }
        });

        btnCancelar.addActionListener(e ->{
            dispose();
            new Login().setVisible(true);
        });
    }
}

