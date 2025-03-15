package UI.Depositos;

import Controller.DepositosValidatorController;
import DAO.TransaccionDao;
import Model.Transaccion;
import Service.DepositosService;
import UI.Login;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class Historial extends JFrame {

    private JPanel panelHistorial;
    private JTable tablaHistorial;
    private JButton btnCerrar, btnDepositos, btnTransferencias;
    DefaultTableModel modeloHistorial = new DefaultTableModel(); //TABLE ADD/ROW

    public Historial() {
        setTitle("Historial de Traspasos - " + Login.menu_user);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        panelHistorial = new JPanel();
        panelHistorial.setBackground(new Color(12, 25, 34));
        panelHistorial.setLayout(null);

        JLabel titulo = new JLabel("Historial de Traspasos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(246, 190, 0));
        titulo.setBounds(280, 20, 400, 30);
        panelHistorial.add(titulo);

        tablaHistorial = new JTable(modeloHistorial);
        JScrollPane scrollPane = new JScrollPane(tablaHistorial);
        scrollPane.setBounds(42, 70, 900, 200);
        scrollPane.setBorder(null);
        panelHistorial.add(scrollPane);

        btnCerrar = new JButton("Depositos");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCerrar.setBounds(200, 290, 130, 40);
        panelHistorial.add(btnCerrar);

        btnTransferencias = new JButton("Transferencias");
        btnTransferencias.setFont(new Font("Arial", Font.BOLD, 16));
        btnTransferencias.setBounds(425, 290, 150, 40);
        panelHistorial.add(btnTransferencias);

        btnDepositos = new JButton("Cerrar");
        btnDepositos.setFont(new Font("Arial", Font.BOLD, 16));
        btnDepositos.setBounds(650, 290, 130, 40);
        panelHistorial.add(btnDepositos);

        add(panelHistorial);

        //MOSTRAR HISTORIAL
        modeloHistorial.addColumn("");
        modeloHistorial.addColumn("Cuenta");
        modeloHistorial.addColumn("Cuenta traspaso");
        modeloHistorial.addColumn("Movimiento");
        modeloHistorial.addColumn("Monto");
        modeloHistorial.addColumn("Fecha");
        modeloHistorial.addColumn("Descripcion");

        DepositosValidatorController controller = new DepositosValidatorController(new DepositosService(new TransaccionDao()));

        List<Transaccion> historial = controller.ConsultarHistorial();
        NumberFormat formato = NumberFormat.getNumberInstance(Locale.US);

        modeloHistorial.setRowCount(0);

        if (historial != null) {
            historial.forEach(transaccion -> {
                modeloHistorial.addRow(new Object[]{
                        transaccion.getIdTransaccion(),
                        transaccion.getNum_cuenta(),
                        transaccion.getCuentaDestino(),
                        transaccion.getTipo_entrega(),
                        formato.format(transaccion.getMonto()),
                        transaccion.getFecha(),
                        transaccion.getDescripcion()
                });
            });
        }

        btnDepositos.addActionListener(e ->{
            modeloHistorial.setRowCount(0);

            if (historial != null) {
                Stream<Transaccion> Datos = historial.stream().filter(transaccion -> transaccion.getTipo_entrega().equals("TRANSFERENCIA"));

                Datos.forEach(transaccion -> {
                    modeloHistorial.addRow(new Object[]{
                            transaccion.getIdTransaccion(),
                            transaccion.getNum_cuenta(),
                            transaccion.getCuentaDestino(),
                            transaccion.getTipo_entrega(),
                            formato.format(transaccion.getMonto()),
                            transaccion.getFecha(),
                            transaccion.getDescripcion()
                    });
                });

            }
        });

        btnTransferencias.addActionListener(e ->{

        });

        btnCerrar.addActionListener(e ->{
            dispose();
        });
    }
}
