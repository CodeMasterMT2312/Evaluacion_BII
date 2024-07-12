import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Resgistrar extends JFrame {
    private JTextField ID;
    private JTextField NumHisto;
    private JTextField Name;
    private JTextField LastName;
    private JTextField Telf;
    private JTextField edad;
    private JTextField Description;
    private JButton registrarButton;
    private JButton regresarButton;
    private JPanel RegistroPanel;

    public Resgistrar() {
        super("Registro");
        setContentPane(RegistroPanel);
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu men1 = new Menu();
                men1.iniciar();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Guardar();
            }
        });
    }

    public void iniciar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 500);
        setVisible(true);
    }

    public void Guardar(){
        try {
            String URL = "jdbc:mysql://localhost:3306/sistema_hospitalario"; // Nombre de la base de datos
            String userDB = "root";
            String password = "123456";

            Connection conn = DriverManager.getConnection(URL, userDB, password);
            String query = "INSERT INTO PACIENTE(cedula,n_historial_clinico,nombre,apellido,telefono,edad,descripcion_enfermedad) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement guardar = conn.prepareStatement(query);

            String CEDULA = ID.getText();
            int N_HISTORIAL_CLINICO = Integer.parseInt(NumHisto.getText());
            String NOMBRE = Name.getText();
            String APELLIDO = LastName.getText();
            String TELEFONO = Telf.getText();
            int EDAD = Integer.parseInt(edad.getText());
            String DESCRIPCION_ENFERMEDAD = Description.getText();

            guardar.setString(1, CEDULA);
            guardar.setInt(2, N_HISTORIAL_CLINICO);
            guardar.setString(3, NOMBRE);
            guardar.setString(4, APELLIDO);
            guardar.setString(5, TELEFONO);
            guardar.setInt(6, EDAD);
            guardar.setString(7, DESCRIPCION_ENFERMEDAD);

            int RegistroInsertado = guardar.executeUpdate();

            if (RegistroInsertado > 0) {
                JOptionPane.showMessageDialog(null, "Registro guardado exitosamente");
                ID.setText("");
                NumHisto.setText("");
                Name.setText("");
                LastName.setText("");
                Telf.setText("");
                edad.setText("");
                Description.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el registro");
            }
            conn.close(); //Cerrar conexion a la base de datos
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
}
