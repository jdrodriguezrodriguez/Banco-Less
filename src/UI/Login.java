package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

import Controller.FormValidatorController;
import DAO.*;
import Service.UsuariosService;

public class Login extends JFrame {

    public static String menu_user = "";

    private JPanel panelLogin;
    private JLabel label_titulo, label_usuario, label_pass, label_alerta;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegistrar;

    public Login() {

        setTitle("Login - Banco Less");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panelLogin = new JPanel();
        panelLogin.setLayout(null);
        panelLogin.setBackground(new Color(246, 190, 0));

        Font font = new Font("Arial", Font.BOLD, 16);

        //TITULO
        label_titulo = new JLabel("Banco Less", SwingConstants.CENTER);
        label_titulo.setFont(new Font("Arial", Font.BOLD, 50));
        label_titulo.setForeground(Color.BLACK);
        label_titulo.setBounds(0, 30, 650, 50);

        Border bordeRedondeado = new MatteBorder(2, 2, 2, 2, new Color(100, 100, 100));

        //USUARIO
        label_usuario = new JLabel("Usuario:");
        label_usuario.setFont(font);
        label_usuario.setForeground(Color.BLACK);
        label_usuario.setBounds(180, 160, 120, 30);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(300, 160, 180, 35);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setBackground(new Color(255, 255, 255));
        txtUsuario.setForeground(new Color(50, 50, 50));
        txtUsuario.setBorder(bordeRedondeado);
        txtUsuario.setMargin(new Insets(5, 10, 5, 10));

        //CONTRASEÑA
        label_pass = new JLabel("Contraseña:");
        label_pass.setFont(font);
        label_pass.setForeground(Color.BLACK);
        label_pass.setBounds(180, 210, 120, 30);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(300, 210, 180, 35);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBackground(new Color(255, 255, 255));
        txtPassword.setForeground(new Color(50, 50, 50));
        txtPassword.setBorder(bordeRedondeado);
        txtPassword.setMargin(new Insets(5, 10, 5, 10));

        //LOGIN
        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(240, 300, 160, 40);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBackground(new Color(151, 5, 5));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(80, 2, 2), 2));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //ALERTA
        label_alerta = new JLabel("");
        label_alerta.setFont(new Font("Arial", Font.BOLD, 16));
        label_alerta.setBounds(265,410,250,40);
        label_alerta.setForeground(new Color(150, 5, 5));

        //REGISTRO
        btnRegistrar = new JButton("Crea tu cuenta");
        btnRegistrar.setBounds(240, 350, 160, 40);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegistrar.setBackground(new Color(24, 151, 24));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBorder(BorderFactory.createLineBorder(new Color(80, 2, 2), 2));
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelLogin.add(label_titulo);
        panelLogin.add(label_usuario);
        panelLogin.add(txtUsuario);
        panelLogin.add(label_pass);
        panelLogin.add(txtPassword);
        panelLogin.add(btnLogin);
        panelLogin.add(label_alerta);
        panelLogin.add(btnRegistrar);

        setContentPane(panelLogin);
        setVisible(true);

        btnLogin.addActionListener(e -> {

            String username = txtUsuario.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (UsuariosService.ValidarCaposLogin(username, password)){

                FormValidatorController controller = new FormValidatorController(new UsuariosService(new PersonaDao(), new UsuarioDao(), new CuentaDao()));
                boolean LoginExitoso = controller.LoginUsuario(username, password);

                if (LoginExitoso){
                    menu_user = username;
                    dispose();
                }else{
                    txtPassword.setText("");
                    txtUsuario.setText("");
                    label_alerta.setText("Datos incorrectos");
                    txtUsuario.setBackground(Color.RED);
                    txtPassword.setBackground(Color.RED);
                }
            }else {
                label_alerta.setText("Campos vacios");
                txtUsuario.setBackground(Color.RED);
                txtPassword.setBackground(Color.RED);
            }
        });

        btnRegistrar.addActionListener(e -> {
            dispose();
            new Registro().setVisible(true);
        });
    }

}
