package UI.Depositos;

import Controller.DepositosValidatorController;
import DAO.TransaccionDao;
import Service.DepositosService;
import UI.Login;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class Consultar extends JFrame {
    private String menu_sesion = Login.menu_user;
    private JPanel panelSaldo;
    private JLabel lblSaldo;
    private JButton btnActualizar, btnCerrar;

    public Consultar() {
        setTitle("Consultar Saldo - " + menu_sesion);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        panelSaldo = new JPanel();
        panelSaldo.setBackground(new Color(12, 25, 34));
        panelSaldo.setLayout(null);

        JLabel titulo = new JLabel("Saldo Disponible", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(new Color(246, 190, 0));
        titulo.setBounds(50, 20, 300, 30);
        panelSaldo.add(titulo);

        lblSaldo = new JLabel("", SwingConstants.CENTER);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 24));
        lblSaldo.setForeground(Color.WHITE);
        lblSaldo.setBounds(50, 80, 300, 40);
        panelSaldo.add(lblSaldo);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCerrar.setBounds(130, 180, 130, 40);
        panelSaldo.add(btnCerrar);

        add(panelSaldo);


        DepositosValidatorController controller = new DepositosValidatorController(new DepositosService (new TransaccionDao()));

        Integer Saldo = controller.consultarSaldo();

        NumberFormat formato = NumberFormat.getNumberInstance(Locale.US);
        String saldoFormateado = formato.format(Saldo);

        if (Saldo != null){
            lblSaldo.setText("$" + saldoFormateado);
        }else {
            lblSaldo.setText("Contacte al banco");
        }



        btnCerrar.addActionListener(e ->{
            dispose();
        });
    }
}
