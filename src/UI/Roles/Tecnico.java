package UI.Roles;

import java.awt.*;

import javax.swing.*;

public class Tecnico  extends JFrame {

    JPanel panelTecnico = new JPanel();

    public Tecnico() {
        setTitle("Banco Less - panelTecnico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panelTecnico = new JPanel();
        panelTecnico.setLayout(null);
        panelTecnico.setVisible(true);

        setContentPane(panelTecnico);
        panelTecnico.setBackground(Color.WHITE);

    }
}

