package di.uniba.map.b.adventure.type;


import di.uniba.map.b.adventure.Engine;
import java.io.IOException;

/**
 * classe che implementa un timer per la progress bar della GUI
 */
public class TimerListener extends Thread {

    /**
     * isRunning boolean che indica se il timer è in esecuzione
     */
    private volatile boolean isRunning = true;

    /**
     * progress int che indica il progresso della progress bar
     */
    private int progress = 0;

    /**
     * engine Engine che rappresenta il motore di gioco
     */
    private Engine engine;

    /**
     * delay int che indica il tempo di attesa tra un incremento e l'altro della progress bar
     */
    private int delay;

    /**
     * Costruttore della classe TimerListener
     * @param engine Engine che rappresenta il motore di gioco
     */

    public TimerListener(Engine engine) {
        this.delay = 6000;
        this.engine = engine;
    }

    /**
     * Metodo che incrementa il progresso della progress bar
     */
    @Override
    public void run() {
        while (progress < 100 && isRunning) {
            try {
                Thread.sleep(delay);
                progress += 1;
                engine.incrementProgressBarValue(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Metodo che ferma il timer
     */
    public void stopTimer() {
        isRunning = false;
    }

    /**
     * Metodo che setta il delay del timer
     * @param delay int che indica il tempo di attesa tra un incremento e l'altro della progress bar
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Metodo che restituisce il delay del timer
     * @return int che indica il tempo di attesa tra un incremento e l'altro della progress bar
     */
    public int getDelay() {
        return this.delay;
    }

    /**
     * Metodo che restituisce il progresso della progress bar
     * @return int che indica il progresso della progress bar
     */
    public int getProgress() {
        return this.progress;
    }

    /**
     * Metodo che setta il progresso della progress bar
     * @param progress int che indica il progresso della progress bar
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }
}