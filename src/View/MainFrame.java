package View;

import javax.swing.*;
import Controller.Controller;
import Model.Voiture;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private Controller controller;

    public MainFrame(Controller controller) {
        this.controller = controller;
        setTitle("Liste des Voitures");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        for (Voiture voiture : controller.getVoitureController().getVoitures()) {
            JPanel carPanel = new JPanel();
            carPanel.setLayout(new FlowLayout());
            carPanel.add(new JLabel(voiture.toString()));
            JButton rentButton = new JButton("Louer");
            rentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Code pour louer la voiture
                    controller.getVoitureController().modifierDisponibiliteVoiture(voiture.getVoitureID(), false);
                    JOptionPane.showMessageDialog(null, "Voiture louée : " + voiture);
                }
            });
            carPanel.add(rentButton);
            panel.add(carPanel);
        }

        setLocationRelativeTo(null);
    }
}
