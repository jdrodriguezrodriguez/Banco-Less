package UI.Depositos;
import Controller.DepositosValidatorController;
import DAO.TransaccionDao;
import Service.DepositosService;
import UI.Login;
import javax.swing.*;
import java.awt.*;

public class Depositar extends JFrame {

    private JPanel panelDeposito;
    private JTextField txtMonto, txtDescripcion;
    private JButton btnDepositar, btnCancelar;

    public Depositar() {

        setTitle("Depositar " + Login.menu_user);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        panelDeposito = new JPanel();
        panelDeposito.setBackground(new Color(12, 25, 34));
        panelDeposito.setLayout(null);

        JLabel titulo = new JLabel("Depósito", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(new Color(246, 190, 0));
        titulo.setBounds(100, 20, 200, 30);
        panelDeposito.add(titulo);

        JLabel lblMonto = new JLabel("Valor a depositar:");
        lblMonto.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMonto.setForeground(Color.WHITE);
        lblMonto.setBounds(40, 80, 200, 25);
        panelDeposito.add(lblMonto);

        txtMonto = new JTextField();
        txtMonto.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMonto.setBounds(40, 110, 300, 30);
        panelDeposito.add(txtMonto);

        JLabel lblDescripcion = new JLabel("Descripcion del deposito:");
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDescripcion.setForeground(Color.WHITE);
        lblDescripcion.setBounds(40, 160, 200, 25);
        panelDeposito.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescripcion.setBounds(40, 190, 300, 30);
        panelDeposito.add(txtDescripcion);

        btnDepositar = new JButton("Depositar");
        btnDepositar.setFont(new Font("Arial", Font.BOLD, 16));
        btnDepositar.setBounds(40, 250, 130, 40);
        panelDeposito.add(btnDepositar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCancelar.setBounds(210, 250, 130, 40);
        panelDeposito.add(btnCancelar);

        add(panelDeposito);

        btnDepositar.addActionListener(e -> {

            int valor = Integer.parseInt(txtMonto.getText());
            String Descripcion = txtDescripcion.getText();

            if (DepositosService.VerificarCamposDeposito(valor)){

                DepositosValidatorController controller = new DepositosValidatorController(new DepositosService(new TransaccionDao()));
                boolean DepositoExitoso = controller.Depositar(valor, Descripcion);

                if (DepositoExitoso){
                    dispose();
                    JOptionPane.showMessageDialog(null, "DEPOSITO EXITOSO");
                }

            }else {
                JOptionPane.showMessageDialog(null, "El valor debe ser mayor a $0");
            }
        });

        btnCancelar.addActionListener(e ->{
            dispose();
        });
    }
}
