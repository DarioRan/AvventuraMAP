package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.db.GameStatus;
import di.uniba.map.b.adventure.games.EscapeFromLabGame;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.Command;
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
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import di.uniba.map.b.adventure.type.CommandGUIType;
import di.uniba.map.b.adventure.type.TimerListener;

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
    //private final Engine engine;
    private boolean shouldCloseGame = false;
    private JProgressBar progressBar;
    private Printer printer;
    private boolean isDead = false;
    ProgressBarListener progressBarListener = null;

    private static Client client;
    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public AdventureGameGUI() {
        try {
            client = new Client();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setTitle("Escape from LABS");
        initMainPanel();
        initStartPanel();
        setVisible(true);
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
                if (textArea != null && !isDead) {
                    int scelta = JOptionPane.showConfirmDialog(frame, "Vuoi salvare la partita in corso?", "Conferma", JOptionPane.YES_NO_OPTION);
                    if (scelta == JOptionPane.YES_OPTION) {
                        try {
                            openUsernameInputDialog(e);
                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        shouldCloseGame = true; // Imposta la variabile shouldCloseGame a false se si seleziona "No" per la conferma
                        e.getWindow().dispose(); // Chiude solo la finestra
                        try {
                            client.executeCommand("STOPTIMER");
                            progressBarListener.stopListener();
                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else if(isDead){
                    shouldCloseGame = true;
                    e.getWindow().dispose();
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

    private void openUsernameInputDialog(WindowEvent e)
            throws IOException, ClassNotFoundException {
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
                    client.executeCommand("STOPTIMER");
                    progressBarListener.stopListener();
                    client.sendResourcesToServer("username:"+username);
                    client.executeCommand("SAVEGAME");
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
                try {
                    startGame(); // Carica il gioco
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
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
                } catch (SQLException | ClassNotFoundException | IOException ex) {
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
    private void initSidePanel() throws IOException, ClassNotFoundException {
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
        initProgressBar(sidePanel); // Inizializza la barra di avanzamento
        mainPanel.add(sidePanel, BorderLayout.EAST);
    }

    /**
     * Inizializza la barra di avanzamento
     */
    private void initProgressBar(JPanel sidePanel)
            throws IOException, ClassNotFoundException {
        CommandGUIOutput response;
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(240, 50));
        int progressBarBottomPadding = 10; // Spazio desiderato dal bordo inferiore
        progressBar.setBorder(BorderFactory.createEmptyBorder(0, 0, progressBarBottomPadding, 0));
        progressBar.setForeground(new Color(0, 200, 0));
        sidePanel.add(progressBar, BorderLayout.SOUTH);
        // Avvio del timer
        client.executeCommand("STARTTIMER");
        progressBarListener = new ProgressBarListener(3000);
        progressBarListener.start();
    }

    public void changeProgressBarColor(int progress){
        Color color = progressBar.getForeground();
        int red = color.getRed();
        int green = color.getGreen();
        if(red < 255)
            progressBar.setForeground(new Color(red + 5, 200, 0));
        else
            progressBar.setForeground(new Color(red, green - 3, 0));
    }

    public void incrementProgressBarValue(int progress)
            throws IOException, ClassNotFoundException {
        this.getProgressBar().setValue(progress);
        if (progress % 20 == 0 && progress != 100) {
            this.appendAreaText("Il livello delle radiazioni sta aumentando... Corri!\n");
        } else if (progress == 100) {
            this.die("");
        }
        this.changeProgressBarColor(progress);
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
    private void initOutputArea() throws IOException, ClassNotFoundException {

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
        performCommand(new CommandGUIOutput(CommandGUIType.SHOW_TEXT, "Benvenuto in Escape Room!"));

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
        textField.setPreferredSize(new Dimension(getWidth()-250, 50));
        textField.setForeground(Color.WHITE); // Colore del testo
        textField.setFont(new Font("Consolas", Font.BOLD, 18)); // Font del testo
        backgroundPanel.add(textField, BorderLayout.SOUTH);

        textField.addActionListener(e -> {
            CommandGUIOutput responseToGUI;
            Printer printer = new Printer(textArea, 15);
            printer.setDelay(15);
            String inputText = textField.getText(); // Ottieni il testo inserito nella JTextField
            try {
                responseToGUI=client.executeCommand(inputText); // Esegui il comando inserito nella JTextField
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                performCommand(responseToGUI); // Stampa la risposta carattere per carattere nella JTextArea
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
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

    public void setBackgroundImageFromPath(String path) {
        this.backgroundImage = new ImageIcon(path).getImage();
        backgroundPanel.repaint();
    }

    public void performCommand(CommandGUIOutput command)
            throws IOException, ClassNotFoundException {
        switch (command.getType())
        {
            case MOVE:
            case TURN_ON:
            case TURN_OFF:
                this.setBackgroundImageFromPath((String) command.getResource());
                appendAreaText(command.getText());
                System.out.println(command.getResource());
                break;
            case SHOW_TEXT:
                appendAreaText(command.getText());
                break;
            case LOAD_GAME:

                startLoadedGame( Integer.parseInt(command.getResource()));
                break;
            case END:
                die(command.getText());
                break;
            case HELP:
                appendAreaText(printHelp());
                break;
            case INCREMENT_PB_VALUE:
                incrementProgressBarValue(Integer.parseInt(command.getResource()));
                break;
        }
    }

    public void appendAreaText(String text) {
        printer.printText(text);
    }

    public String printHelp(){
        return ("Il tuo obbiettivo principale è uscire vivo dal laboratorio in cui ti trovi intrappolato.\n" +
                "Per farcela dovrai affrontare molti enigmi che metteranno a dura prova la tua astuzia.\n" +
                "\n" +
                "Per muoverti usa:\n" +
                "\n" +
                "- NORD, SUD, EST, OVEST oppure soltanto:\n" +
                "\n" +
                "- N, S, E, O\n" +
                "\n" +
                "Io ti darò la descrizione completa di ogni luogo la prima volta che vi entri,\n" +
                "poi darò solo una descrizione breve. Se vuoi la descrizione completa dimmi:\n" +
                "\n" +
                "- OSSERVA\n" +
                "\n" +
                "Azioni fondamentali sono:\n" +
                "\n" +
                "- PRENDI oggetto\n" +
                "- USA oggetto\n" +
                "- ACCENDI oggetto\n" +
                "- SPEGNI oggetto\n" +
                "- APRI oggetto\n" +
                "- SBLOCCA oggetto\n" +
                "\n" +
                "Se devi sbloccare un oggetto con una password usa\n" +
                "\n" +
                "- SBLOCCA oggetto \"password\"\n" +
                "\n" +
                "Altri comandi utili:\n" +
                "\n" +
                "- INV elenca gli oggetti nel tuo inventario\n" +
                "- HELP ti ripete questa descrizione.\n");
    }

    private void startGame() throws IOException, ClassNotFoundException {
        mainPanel.remove(startPanel);
        initBackgroundPanel(1);
        initOutputArea();
        initInputArea();
        initSidePanel();
        revalidate();
    }

    private void startLoadedGame(int id)
            throws IOException, ClassNotFoundException {
        initSidePanel();
        initBackgroundPanel(id);
        initOutputArea();
        initInputArea();
        revalidate();
    }

    private void loadGame()
            throws SQLException, IOException, ClassNotFoundException {
        mainPanel.remove(startPanel);
        initLoadGameBackgroundPanel();
        initOutputLoadedGamesArea();
        showSavedGames();
        revalidate();
    }

    private void showSavedGames()
            throws SQLException, IOException, ClassNotFoundException {

        List<GameStatus> savedGames =
                (List<GameStatus>) client.getResourcesFromServer("GETSAVEDGAMES");

        Color backgroundColor = new Color(0, 0, 0, 0); // Colore di sfondo con opacità ridotta (valori RGB: 0, 0, 0, opacità)
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, savedGames.size() * 50));
        scrollPane.setViewportView(new SavedGame(savedGames, mainPanel, contentPanel) {
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

    public void die(String command) throws IOException, ClassNotFoundException {
        textField.setEditable(false);
        progressBarListener.stopListener();
        appendAreaText(command + "Il livello delle radiazioni è aumentato troppo, ti senti stanco e non riesci " +
                "più a correre. Ti accasci a terra e muori. \n\nGAME OVER");
        isDead = true;
        client.executeCommand("STOPTIMER");
    }

    /**
     * Pannello che mostra le partite salvate
     */
    public class SavedGame extends JPanel {
        /**
         * Create the panel.
         */
        public SavedGame(List<GameStatus> savedGames, JPanel mainPanel, JPanel contentPanel) {
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
                            client.sendResourcesToServer("username:"+game.getUsername());
                            CommandGUIOutput response = client.executeCommand("LOADGAME"); // Carica la partita
                            performCommand(response); // Esegue il comando

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                add(panel); // Aggiunge il pannello alla lista
            }
        }
    }

    /**
     * Classe per la stampa del testo con un effetto di scrittura
     */
    public class Printer {
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

    public class ProgressBarListener extends Thread{

        private int delay;

        private volatile boolean isRunning = true;

        public ProgressBarListener(int delay){
            this.delay = delay;
        }
        public void run(){
            while (isRunning){
                try {
                    Thread.sleep(delay);
                    CommandGUIOutput response = client.executeCommand("INCREMENTPBVALUE");
                    setDelay(Integer.parseInt(response.getText()));
                    performCommand(response);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void stopListener(){
            isRunning = false;
        }
        public void setDelay(int delay){
            this.delay = delay;
        }

    }

    /**
     * Classe che implementa un timer per la progress bar
     */

    public static void main(String[] args) {

        AdventureGameGUI gui = new AdventureGameGUI();

    }


}

