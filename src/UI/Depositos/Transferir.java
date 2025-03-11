package UI.Depositos;

import Controller.DepositosValidatorController;
import DAO.TransaccionDao;
import Service.DepositosService;
import UI.Login;

import javax.swing.*;
import java.awt.*;

public class Transferir extends JFrame {
    private String menu_sesion = Login.menu_user;
    private JPanel panelTransferencia;
    private JTextField txtCuentaDestino, txtMonto, txtDescripcion;
    private JButton btnTransferir, btnCancelar;

    public Transferir() {
        setTitle("Transferir " + menu_sesion);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        panelTransferencia = new JPanel();
        panelTransferencia.setBackground(new Color(12, 25, 34));
        panelTransferencia.setLayout(null);

        JLabel titulo = new JLabel("Transferencia", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(new Color(246, 190, 0));
        titulo.setBounds(100, 20, 200, 30);
        panelTransferencia.add(titulo);

        JLabel lblCuentaDestino = new JLabel("Cuenta destino:");
        lblCuentaDestino.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCuentaDestino.setForeground(Color.WHITE);
        lblCuentaDestino.setBounds(40, 70, 200, 25);
        panelTransferencia.add(lblCuentaDestino);

        txtCuentaDestino = new JTextField();
        txtCuentaDestino.setFont(new Font("Arial", Font.PLAIN, 14));
        txtCuentaDestino.setBounds(40, 100, 300, 30);
        panelTransferencia.add(txtCuentaDestino);

        JLabel lblMonto = new JLabel("Valor a transferir:");
        lblMonto.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMonto.setForeground(Color.WHITE);
        lblMonto.setBounds(40, 150, 200, 25);
        panelTransferencia.add(lblMonto);

        txtMonto = new JTextField();
        txtMonto.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMonto.setBounds(40, 180, 300, 30);
        panelTransferencia.add(txtMonto);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDescripcion.setForeground(Color.WHITE);
        lblDescripcion.setBounds(40, 230, 200, 25);
        panelTransferencia.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescripcion.setBounds(40, 260, 300, 30);
        panelTransferencia.add(txtDescripcion);

        btnTransferir = new JButton("Transferir");
        btnTransferir.setFont(new Font("Arial", Font.BOLD, 16));
        btnTransferir.setBounds(40, 320, 130, 40);
        panelTransferencia.add(btnTransferir);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCancelar.setBounds(210, 320, 130, 40);
        panelTransferencia.add(btnCancelar);

        add(panelTransferencia);

        btnTransferir.addActionListener(e -> {
            int CuentaDestino = Integer.parseInt(txtCuentaDestino.getText());
            String Descripcion = txtDescripcion.getText();
            int valor = Integer.parseInt(txtMonto.getText());

            if (DepositosService.CamposTransferencia(valor, CuentaDestino)){
                DepositosValidatorController controller = new DepositosValidatorController(new DepositosService(new TransaccionDao()));

                boolean validar = controller.TransferirSaldo(valor, Descripcion, CuentaDestino);

                if (validar){
                    dispose();
                    JOptionPane.showMessageDialog(null, "TRANSFERENCIA EXITOSA");
                }
            }else{
                txtMonto.setBackground(Color.RED);
                txtCuentaDestino.setBackground(Color.RED);
            }
        });

        btnCancelar.addActionListener(e -> {
            dispose();
        });
    }
}
