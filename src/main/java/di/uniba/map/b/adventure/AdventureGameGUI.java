package di.uniba.map.b.adventure;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AdventureGameGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel sidePanel;
    private JTextField inputField;
    private JLabel statsLabel;
    private JPanel startPanel;
    private JButton startButton;

    int panelWidth = getWidth();
    int panelHeight = getHeight();
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

        // Creazione del pannello di avvio del gioco
        startPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImageIcon = new ImageIcon("resources/start.png");
                Image backgroundImage = backgroundImageIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        startPanel.setLayout(null); // Imposta il layout come null per utilizzare un layout personalizzato
        mainPanel.add(startPanel, BorderLayout.CENTER);

// Calcola le dimensioni del pannello
        int panelWidth = getWidth();
        int panelHeight = getHeight();

// Imposta le dimensioni e la posizione del pulsante
        int buttonWidth = 200;
        int buttonHeight = 50;
        int buttonX = (panelWidth - buttonWidth) / 2; // Posiziona il pulsante al centro orizzontalmente
        int buttonY = panelHeight - 100 - buttonHeight; // Posiziona il pulsante a 100 pixel dal fondo
        startButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImageIcon = new ImageIcon("resources/button.png");
                Image backgroundImage = backgroundImageIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        startPanel.add(startButton); // Aggiungi il pulsante al pannello



        setVisible(true);
    }


    private void startGame() {
        mainPanel.remove(startPanel);

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
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight(), Image.SCALE_SMOOTH);
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
        Color background = new Color(0, 0, 0, 150); // Colore di sfondo con opacità ridotta (valori RGB: 0, 0, 0, opacità)
        JTextArea textArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(background);
                g2.fillRect(0, 0, getWidth(), getHeight()); // Riempie l'area con il colore di sfondo
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Arial", Font.BOLD, 16));
        textArea.setEditable(false); // Rendi la JTextArea non modificabile
        textArea.setOpaque(false); // Rendi lo sfondo trasparente
        textArea.setForeground(Color.WHITE); // Colore del testo
        textArea.setFont(new Font("Arial", Font.BOLD, 16)); // Font del testo
        textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, getHeight()/2)); // Imposta l'altezza massima della JTextArea

        // Crea la JScrollPane per avvolgere la JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, getHeight()/2));
        scrollPane.setOpaque(false); // Rendi lo sfondo trasparente
        scrollPane.getViewport().setOpaque(false); // Rendi lo sfondo del viewport trasparente
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        backgroundPanel.add(scrollPane, BorderLayout.NORTH);

        backgroundPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(scrollPane.isVisible())
                    scrollPane.setVisible(false);
                else
                    scrollPane.setVisible(true);
            }
        });

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
            scrollPane.setVisible(true); // Mostra la JScrollPane
            // Scrolla la JTextArea fino alla fine
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });

        // Imposta la JTextArea per lo scorrimento automatico
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // Mostra la finestra
        setVisible(true);
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdventureGameGUI::new);
    }
}
