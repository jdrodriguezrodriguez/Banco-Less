package UI.Depositos.GestionCuenta;
import Controller.FormValidatorController;
import DAO.CuentaDao;
import DAO.PersonaDao;
import DAO.UsuarioDao;
import Service.UsuariosService;

import javax.swing.*;
import java.awt.*;

public class ModificarPass extends JFrame {
    private JPanel panel;
    private JPasswordField txtPassword, txtPassConfirm;
    private JButton btnModificar, btnCancelar;

    public ModificarPass() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Cambiar contraseña");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 40, 50));
        add(panel);
    }

    private void inicializarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Cambiar Contraseña", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(255, 215, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        JLabel lblPass = new JLabel("Nueva contraseña:");
        lblPass.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblPass, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        JLabel lblPassConfirm = new JLabel("Confirmar contraseña:");
        lblPassConfirm.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPassConfirm, gbc);

        txtPassConfirm = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtPassConfirm, gbc);

        btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
        btnModificar.setBackground(new Color(46, 204, 113));
        btnModificar.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnModificar, gbc);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);
        gbc.gridx = 1;
        panel.add(btnCancelar, gbc);

        btnModificar.addActionListener(e -> {
            String pass = new String(txtPassword.getText().trim());
            String passConfirm = new String(txtPassConfirm.getText().trim());

            FormValidatorController controller = new FormValidatorController(new UsuariosService(new PersonaDao(), new UsuarioDao(), new CuentaDao()));

            if (pass.equals(passConfirm)){
                boolean CambioPass = controller.ActualizarContraseña(pass);

                if (CambioPass){
                    JOptionPane.showMessageDialog(null, "Contraseña cambiada");
                    dispose();
                }
            }else{
                txtPassword.setBackground(Color.RED);
                txtPassConfirm.setBackground(Color.RED);
                JOptionPane.showMessageDialog(null, "Las contraseñas no son semejantes");
            }



        });

        btnCancelar.addActionListener(e -> {
            dispose();
        });
    }
}