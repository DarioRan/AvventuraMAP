package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.db.GameStatus;
import di.uniba.map.b.adventure.type.CommandGUIOutput;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

public class AdventureGameGUI extends JFrame {

    private JPanel mainPanel = null;
    private JPanel startPanel = null;
    private JTextArea textArea = null;
    private JScrollPane scrollPane = null;
    private JTextField textField = null;
    private JPanel sidePanel = null;
    private JPanel contentPanel = null;
    private JPanel backgroundPanel = null;
    private Image backgroundImage = null;
    private final Engine engine;
    private boolean shouldCloseGame = false;
    private Printer printer;

    public AdventureGameGUI(Engine engine) {
        setTitle("Escape from LABS");
        initMainPanel();
        initStartPanel();
        setVisible(true);
        this.engine = engine;
    }

    /**
     * Inizializza il pannello principale
     */
    private void initMainPanel() {
        // Ottieni le dimensioni dello schermo
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        JOptionPane frame = new JOptionPane();

        // Impostazioni della finestra principale
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (shouldCloseGame) {
                    System.exit(0); // Chiude il gioco solo se shouldCloseGame è true
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (textArea != null) {
                    int scelta = JOptionPane.showConfirmDialog(frame, "Vuoi salvare la partita in corso?", "Conferma", JOptionPane.YES_NO_OPTION);
                    if (scelta == JOptionPane.YES_OPTION) {
                        openUsernameInputDialog(e);
                    } else {
                        shouldCloseGame = true; // Imposta la variabile shouldCloseGame a false se si seleziona "No" per la conferma
                        e.getWindow().dispose(); // Chiude solo la finestra
                    }
                } else {
                    shouldCloseGame = true; // Imposta la variabile shouldCloseGame a false se non c'è una partita in corso
                    e.getWindow().dispose(); // Chiude solo la finestra
                }
            }
        });

        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Pannello principale
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);
    }

    private void openUsernameInputDialog(WindowEvent e) {
        boolean validUsername = false;

        while (!validUsername) {
            JOptionPane input = new JOptionPane();
            JTextField usernameField = new JTextField();
            Object[] message = {
                    "Username:", usernameField
            };

            int option = JOptionPane.showOptionDialog(input, message, "Inserisci Username",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{"OK", "Cancel"}, "OK");

            if (option == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(input, "Il campo username non può essere vuoto.", "Errore", JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("Username: " + username);
                    engine.saveGame(username);
                    validUsername = true;
                    shouldCloseGame = true;
                    e.getWindow().dispose();
                }
            } else {
                validUsername = true;
                shouldCloseGame = false;
            }
        }
    }

    /**
     * Inizializza il pannello di avvio del gioco
     */
    private void initStartPanel(){
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
        int buttonY = panelHeight - (2 * buttonHeight) - 170; // Posiziona il pulsante a 100 pixel dal fondo
        int buttonY2 = panelHeight - 150 - buttonHeight; // Posiziona il pulsante a 100 pixel dal fondo
        JButton startButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImageIcon =
                        new ImageIcon("resources/button.png");
                Image backgroundImage = backgroundImageIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),
                        this);
            }
        };
        startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // Carica il gioco
            }
        });
        JButton loadGameButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImageIcon =
                        new ImageIcon("resources/button2.png");
                Image backgroundImage = backgroundImageIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),
                        this);
            }
        };
        loadGameButton.setBounds(buttonX, buttonY2, buttonWidth, buttonHeight);
        loadGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadGame(); // Avvia il gioco
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        startPanel.add(startButton); // Aggiungi il pulsante al pannello di avvio
        startPanel.add(loadGameButton); // Aggiungi il pulsante al pannello di avvio
    }

    /**
     * Inizializza il pannello laterale
     */
    private void initSidePanel(){
        // Immagine laterale
        ImageIcon latImage = new ImageIcon("resources/logo3.jpg");
        Image lat = latImage.getImage().getScaledInstance(600, 1900, Image.SCALE_SMOOTH);

        // Pannello laterale per le statistiche
        // Carica l'immagine di sfondo
        JPanel sidePanel = new JPanel() {
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
        JLabel statsLabel = new JLabel();
        sidePanel.add(statsLabel);
    }

    /**
     * Inizializza il pannello di sfondo
     */
    private void initBackgroundPanel(int roomId){
        ImageIcon backgroundImageIcon = new ImageIcon("resources/" +roomId+".png");
        backgroundImage = backgroundImageIcon.getImage().getScaledInstance(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight(), Image.SCALE_SMOOTH);
        // Creazione del pannello per l'immagine sopra l'inputPanel e a sinistra del sidePanel
        backgroundPanel = new JPanel() {
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
    }

    private void initLoadGameBackgroundPanel(){
        ImageIcon backgroundImageIcon = new ImageIcon("resources/start.png");
        backgroundImage = backgroundImageIcon.getImage().getScaledInstance(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight(), Image.SCALE_SMOOTH);
        // Creazione del pannello per l'immagine sopra l'inputPanel e a sinistra del sidePanel
        backgroundPanel = new JPanel() {
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
        mainPanel.add(backgroundPanel, BorderLayout.CENTER);
    }

    /**
     * Inizializza il pannello di output
     */
    private void initOutputArea(){

        // Crea la JTextArea
        Color background = new Color(0, 0, 0, 150); // Colore di sfondo con opacità ridotta (valori RGB: 0, 0, 0, opacità)
        textArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(background);
                g2.fillRect(0, 0, getWidth(), getHeight()); // Riempie l'area con il colore di sfondo
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        printer= new Printer(textArea, 15);
        performCommand(engine.execute());

        textArea.setFont(new Font("Consolas", Font.BOLD, 18));
        textArea.setEditable(false); // Rendi la JTextArea non modificabile
        textArea.setOpaque(false); // Rendi lo sfondo trasparente
        textArea.setForeground(Color.WHITE); // Colore del testo
        textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, getHeight()/2)); // Imposta l'altezza massima della JTextArea
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true); // Rendi il testo a capo quando raggiunge il bordo della JTextArea

        // Crea la JScrollPane per avvolgere la JTextArea
        scrollPane = new JScrollPane(textArea);
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
                scrollPane.setVisible(!scrollPane.isVisible());
            }
        });
    }

    private void initOutputLoadedGamesArea(){

        Color backgroundColor = new Color(0, 0, 0, 150); // Colore di sfondo con opacità ridotta (valori RGB: 0, 0, 0, opacità)

        contentPanel = new JPanel(); // Pannello principale che conterrà i pannelli delle righe
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Layout per allineare verticalmente gli elementi
        backgroundPanel.add(contentPanel, BorderLayout.CENTER);


        // Crea la JTextArea

        scrollPane = new JScrollPane(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(backgroundColor);
                g2.fillRect(0, 0, getWidth(), getHeight()); // Riempie l'area con il colore di sfondo
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        scrollPane.setPreferredSize(new Dimension(getWidth(),getHeight()));
        scrollPane.setOpaque(false); // Rendi lo sfondo trasparente
        scrollPane.getViewport().setOpaque(false); // Rendi lo sfondo del viewport trasparente
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.add(scrollPane, BorderLayout.NORTH);
    }

    /**
     * Inizializza il pannello di input
     */
    private void initInputArea(){
        // Aggiungi la JTextField al pannello di sfondo
        textField = new JTextField();
        textField.setOpaque(false); // Rendi lo sfondo trasparente
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 50));
        textField.setForeground(Color.WHITE); // Colore del testo
        textField.setFont(new Font("Consolas", Font.BOLD, 18)); // Font del testo
        backgroundPanel.add(textField, BorderLayout.SOUTH);

        textField.addActionListener(e -> {
            CommandGUIOutput responseToGUI;
            Printer printer = new Printer(textArea, 15);
            printer.setDelay(15);
            String inputText = textField.getText(); // Ottieni il testo inserito nella JTextField
            responseToGUI=engine.executeCommand(inputText); // Esegui il comando inserito nella JTextField
            performCommand(responseToGUI); // Stampa la risposta carattere per carattere nella JTextArea
            textField.setText(""); // Resetta il contenuto della JTextField
            scrollPane.setVisible(true); // Mostra la JScrollPane
            textArea.setCaretPosition(textArea.getDocument().getLength()); // Scrolla la JTextArea fino alla fine del testo
        });

        // Imposta la JTextArea per lo scorrimento automatico
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        backgroundPanel.repaint();
    }

    public void performCommand(CommandGUIOutput command)
    {
        switch (command.getType())
        {
            case MOVE:
            case TURN_ON:
            case TURN_OFF:
                this.setBackgroundImage((Image) command.getResource());
                appendAreaText(command.getText());
                break;
            case SHOW_TEXT:
                appendAreaText(command.getText());
                break;
            case LOAD_GAME:
                startLoadedGame((int) command.getResource());
                break;
        }
    }

    public void appendAreaText(String text) {
        printer.printText(text);
    }

    private void startGame() {
        mainPanel.remove(startPanel);
        initSidePanel();
        initBackgroundPanel(1);
        initOutputArea();
        initInputArea();
        revalidate();
    }

    private void startLoadedGame(int id) {
        initSidePanel();
        initBackgroundPanel(id);
        initOutputArea();
        initInputArea();
        revalidate();
    }

    private void loadGame() throws SQLException {
        mainPanel.remove(startPanel);
        initLoadGameBackgroundPanel();
        initOutputLoadedGamesArea();
        showSavedGames();
        revalidate();
    }

    private void showSavedGames() throws SQLException {

        List<GameStatus> savedGames = engine.getSavedGames();
        Color backgroundColor = new Color(0, 0, 0, 0); // Colore di sfondo con opacità ridotta (valori RGB: 0, 0, 0, opacità)
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, savedGames.size() * 50));
        scrollPane.setViewportView(new SavedGame(savedGames, engine, mainPanel, contentPanel) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(backgroundColor);
                g2.fillRect(0, 0, getWidth(), getHeight()); // Riempie l'area con il colore di sfondo
                super.paintComponent(g2);
                g2.dispose();
            }
        });

        // Aggiorna la visualizzazione della scroll pane
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public static class SavedGame extends JPanel {
        /**
         * Create the panel.
         */
        public SavedGame(List<GameStatus> savedGames, Engine engine, JPanel mainPanel, JPanel contentPanel) {
            setLayout(new GridLayout(savedGames.size(), 1)); // Imposta il layout con una riga per ogni partita salvata
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(this.getPreferredSize().width, savedGames.size() * 50)); // Imposta la dimensione del pannello
            for (GameStatus game : savedGames) {
                Color background = new Color(0, 0, 0, 0);
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Imposta il layout con allineamento sinistro
                panel.setOpaque(false); // Imposta lo sfondo trasparente
                panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, 50)); // Imposta la dimensione del pannello
                String rowString = game.getUsername() + " - " + game.getLastRoomId() + " - " + game.getTime().toString();
                JLabel rowLabel = new JLabel(rowString) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setColor(background);
                        g2.fillRect(0, 0, getWidth(), getHeight()); // Riempie l'area con il colore di sfondo
                        super.paintComponent(g2);
                        g2.dispose();
                    }
                };
                rowLabel.setOpaque(false);
                rowLabel.setFont(new Font("Consolas", Font.BOLD, 18));
                rowLabel.setForeground(Color.WHITE);
                panel.add(rowLabel);

                // Aggiunge un listener per il click del mouse e per il passaggio sopra con il mouse
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) { // Quando passi sopra con il mouse
                        panel.setOpaque(true);
                        Color hoverColor = new Color(70, 70, 70, 255);
                        panel.setBackground(hoverColor); // Imposta il colore di sfondo quando passi sopra con il mouse
                        panel.repaint(); // Forza l'aggiornamento grafico del pannello
                    }

                    @Override
                    public void mouseExited(MouseEvent e) { // Quando esci con il mouse
                        panel.setOpaque(false); // Ripristina l'opacità del pannello a false
                        panel.setBackground(new Color(0, 0, 0, 0)); // Ripristina il colore di sfondo trasparente
                        panel.repaint(); // Forza l'aggiornamento grafico del pannello
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mainPanel.remove(contentPanel);
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        try {
                            engine.loadGame(game.getUsername()); // Carica la partita
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                add(panel); // Aggiunge il pannello alla lista
            }
        }
    }

    public static class Printer {
        private JTextArea textArea;
        private int delay;

        public Printer() {
            this.delay = 15;
        }
        public Printer(JTextArea textArea, int delay) {
            this.textArea = textArea;
            this.delay = delay;
        }
        public void setDelay(int delay) {
            this.delay = delay;
        }

        public void printText(String inputText) {
            SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
                @Override
                protected Void doInBackground() throws Exception {
                    String[] chars = inputText.split("");
                    for (String c : chars) {
                        publish(c);
                        Thread.sleep(delay);
                    }
                    return null;
                }

                @Override
                protected void process(java.util.List<String> chunks) {
                    for (String c : chunks) {
                        textArea.append(c); // Aggiungi il testo alla JTextArea, aggiungendo un a capo
                    }
                }
            };

            worker.execute();
            textArea.append("\n"); // Aggiungi un a capo alla fine del testo
        }

    }
}

