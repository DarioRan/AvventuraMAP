package di.uniba.map.b.adventure;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.IOException;

public class AdventureGameGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel sidePanel;
    private JTextField inputField;
    private JLabel statsLabel;

    public AdventureGameGUI() {

        setTitle("Adventure Game");
        // Ottieni le dimensioni dello schermo
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Impostazioni della finestra principale
        setTitle("Adventure Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Pannello principale
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);


        // Immagine laterale
        ImageIcon latImage = new ImageIcon("resources/logo3.jpg");
        Image lat = latImage.getImage().getScaledInstance(600, 1900, Image.SCALE_SMOOTH);

        // Pannello laterale per le statistiche
        sidePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica l'immagine di sfondo
                try {
                    g.drawImage(lat, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sidePanel.setPreferredSize(new Dimension(250, 0));
        sidePanel.setLayout(new BorderLayout()); // Modifica il layout in BorderLayout

        // Aggiungi la JProgressBar nella parte inferiore del pannello laterale con uno spazio dal bordo inferiore
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        int progressBarBottomPadding = 10; // Spazio desiderato dal bordo inferiore
        progressBar.setBorder(BorderFactory.createEmptyBorder(0, 0, progressBarBottomPadding, 0));
        sidePanel.add(progressBar, BorderLayout.SOUTH);


        mainPanel.add(sidePanel, BorderLayout.EAST);


        // Etichetta per le statistiche
        statsLabel = new JLabel();
        sidePanel.add(statsLabel);


        ImageIcon backgroundImageIcon = new ImageIcon("resources/1.png");
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(600, 1900, Image.SCALE_SMOOTH);
        // Creazione del pannello per l'immagine sopra l'inputPanel e a sinistra del sidePanel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica l'immagine di sfondo
                try {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundPanel.setPreferredSize(new Dimension(getWidth()-250, 0));
        backgroundPanel.setLayout(new BorderLayout());
        mainPanel.add(backgroundPanel, BorderLayout.WEST);


        // Crea la JTextArea
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Rendi la JTextArea non modificabile
        textArea.setOpaque(false); // Rendi lo sfondo trasparente
        textArea.setForeground(Color.WHITE); // Colore del testo
        textArea.setFont(new Font("Arial", Font.BOLD, 16)); // Font del testo

// Crea la JScrollPane per avvolgere la JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 100));
        scrollPane.setOpaque(false); // Rendi lo sfondo trasparente
        scrollPane.getViewport().setOpaque(false); // Rendi lo sfondo del viewport trasparente

// Imposta l'altezza massima della JTextArea
        textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

// Aggiungi la JScrollPane al pannello di sfondo
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

// Aggiungi la JTextField al pannello di sfondo
        JTextField textField = new JTextField();
        textField.setOpaque(false); // Rendi lo sfondo trasparente
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 50));
        textField.setForeground(Color.WHITE); // Colore del testo
        textField.setFont(new Font("Arial", Font.BOLD, 16)); // Font del testo
        backgroundPanel.add(textField, BorderLayout.SOUTH);


        textField.addActionListener(e -> {
            String inputText = textField.getText(); // Ottieni il testo inserito nella JTextField
            textArea.append(inputText + "\n"); // Aggiungi il testo alla JTextArea, aggiungendo un a capo
            textField.setText(""); // Resetta il contenuto della JTextField

            int lineCount = textArea.getLineCount();

            // Se abbiamo superato il 15 append
            if (lineCount > 15) {
                // Ottieni l'offset del testo corrispondente alla prima riga
                int offset = 0;
                try {
                    offset = textArea.getLineEndOffset(1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                textArea.replaceRange("", 0, offset);
            }

            // Scrolla la JTextArea fino alla fine
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });

    // Imposta la JTextArea per lo scorrimento automatico
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // Mostra la finestra
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdventureGameGUI();
        });
    }
}
