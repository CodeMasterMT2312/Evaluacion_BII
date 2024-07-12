import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JTextField User;
    private JButton LOGINButton;
    private JPasswordField pass;
    private JPanel LoginPanel;

    public Login() {
        super("Inicio de Sesion");
        setContentPane(LoginPanel);
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iniciarSesion();
                } catch (SQLException ex) {
                    System.out.println("Error al: " + ex.getMessage());
                }
            }
        });
    }

    public void iniciar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setVisible(true);
    }
    public void iniciarSesion() throws SQLException{
        try {
            String URL = "jdbc:mysql://localhost:3306/sistema_hospitalario"; // Nombre de la base de datos
            String userDB = "root";
            String password = "123456";

            Connection conn = DriverManager.getConnection(URL, userDB, password);
            String query = "SELECT username,password FROM USUARIO;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String correo = rs.getString("username");
                String CorreoBD = new String(User.getText());
                String passBD = rs.getString("password");
                String passUsuario = new String(pass.getPassword());

                if (correo.equals(User.getText()) && passBD.equals(passUsuario)) {
                    JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente");
                    Menu adminmenu = new Menu();
                    adminmenu.iniciar();
                    dispose(); //Cerrar ventana de login
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
            conn.close(); //Cerrar conexion a la base de datos
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }
}


