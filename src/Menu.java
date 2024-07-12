import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton registrarPacientesButton;
    private JButton búsquedaDePacientesButton;
    private JPanel MenuPanel;

    public Menu() {
        super("Menu");
        setContentPane(MenuPanel);
        registrarPacientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resgistrar regist = new Resgistrar();
                regist.iniciar();
                dispose();
            }
        });
        búsquedaDePacientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Busqueda busq = new Busqueda();
                busq.iniciar();
                dispose();
            }
        });
    }
    public void iniciar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setVisible(true);
    }
}
