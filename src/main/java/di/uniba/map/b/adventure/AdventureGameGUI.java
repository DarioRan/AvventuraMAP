package di.uniba.map.b.adventure;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdventureGameGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel sidePanel;
    private JTextArea textArea;
    private JTextField inputField;
    private JLabel statsLabel;

    public AdventureGameGUI() {
        // Impostazioni della finestra principale
        setTitle("Adventure Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        ImageIcon img = new ImageIcon("resources/logo3.jpg");
        Image image = img.getImage().getScaledInstance(600, 1900, Image.SCALE_SMOOTH);

        // Pannello principale
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Pannello del testo
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        textArea.setBackground(Color.getColor("0e49b6"));

        // Pannello di input
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Campo di input per l'utente
        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);


        // Pannello laterale per le statistiche
        sidePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica l'immagine di sfondo
                try {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sidePanel.setPreferredSize(new Dimension(250, 0));
        sidePanel.setLayout(new FlowLayout());

        JProgressBar progressBar = new JProgressBar(0, 100); // Imposta il valore minimo e massimo per la barra di progresso
        progressBar.setStringPainted(true); // Mostra la percentuale come testo all'interno della barra di progresso
        sidePanel.add(progressBar);


        mainPanel.add(sidePanel, BorderLayout.EAST);


        // Etichetta per le statistiche
        statsLabel = new JLabel();
        sidePanel.add(statsLabel);

        // Mostra la finestra
        setVisible(true);





    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdventureGameGUI();
        });
    }
}
