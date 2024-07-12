import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Busqueda extends JFrame {

    private JTextField CedulaBus;
    private JTextArea BusquedaArea;
    private JButton regresarButton;
    private JButton buscarButton;
    private JPanel BusquedaPanel;

    public Busqueda() {
        super("Busqueda Registros");
        setContentPane(BusquedaPanel);
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu men2 = new Menu();
                men2.iniciar();
                dispose();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
    }

    public void iniciar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setVisible(true);
    }

    public void buscar(){
        try {
            String URL = "jdbc:mysql://localhost:3306/sistema_hospitalario"; // Nombre de la base de datos
            String userDB = "root";
            String password = "123456";

            Connection conn = DriverManager.getConnection(URL, userDB, password);
            String query = "select * from PACIENTE WHERE cedula= ?;";
            PreparedStatement search = conn.prepareStatement(query);
            ResultSet rs = search.executeQuery();

            String CEDULA = CedulaBus.getText();

            search.setString(1, CEDULA);

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String histoClinico = rs.getString("n_historial_clinico");
                String telefono = rs.getString("telefono");
                String AGE = rs.getString("edad");
                String Descp = rs.getString("descripcion_enfermedad");
                BusquedaArea.append("Cedula: " + CEDULA + "\n");
                BusquedaArea.append("Historial Clinico: " + histoClinico + "\n");
                BusquedaArea.append("Nombre: " + nombre + "\n");
                BusquedaArea.append("Apellido: " + apellido + "\n");
                BusquedaArea.append("Telefono: " + telefono + "\n");
                BusquedaArea.append("Edad: " + AGE + "\n");
                BusquedaArea.append("Descripcion: " + Descp + "\n");
            }
            conn.close(); //Cerrar conexion a la base de datos
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
}
