package UI.Roles;

import java.awt.*;

import javax.swing.*;

public class Admin extends JFrame {

    JPanel panelAdmin = new JPanel();

    public Admin() {
        setTitle("Banco Less - Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panelAdmin = new JPanel();
        panelAdmin.setLayout(null);
        panelAdmin.setVisible(true);

        setContentPane(panelAdmin);
        panelAdmin.setBackground(Color.WHITE);
    }
}
