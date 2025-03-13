package UI.Depositos;

import Controller.DepositosValidatorController;
import DAO.TransaccionDao;
import Service.DepositosService;
import UI.Login;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Historial extends JFrame {
    private String menu_sesion = Login.menu_user;
    private JPanel panelHistorial;
    private JTable tablaHistorial;
    private JButton btnCerrar;
    DefaultTableModel modeloHistorial = new DefaultTableModel(); //TABLE ADD/ROW

    public Historial() {
        setTitle("Historial de Traspasos - " + menu_sesion);
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

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCerrar.setBounds(130, 180, 130, 40);
        panelHistorial.add(btnCerrar);

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
        List<Object[]> historial = controller.ConsultarHistorial();

        if (historial != null && !historial.isEmpty()) {
            for (Object[] row : historial) {
                modeloHistorial.addRow(row);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay datos en el historial.");
        }


        btnCerrar.addActionListener(e ->{
            dispose();
        });
    }
}
