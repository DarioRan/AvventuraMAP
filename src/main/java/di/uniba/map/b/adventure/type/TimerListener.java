package di.uniba.map.b.adventure.type;

import di.uniba.map.b.adventure.AdventureGameGUI;
import di.uniba.map.b.adventure.Engine;

import java.io.IOException;

public class TimerListener extends Thread {
    private volatile boolean isRunning = true;
    private int progress = 0;

    private Engine engine;
    private int delay;

    public TimerListener(Engine engine) {
        this.delay = 3000;
        this.engine = engine;
    }

    @Override
    public void run() {
        while (progress < 100 && isRunning) {
            try {
                System.out.println("Timer partito");
                Thread.sleep(delay);
                progress += 1;
                engine.incrementProgressBarValue(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (isRunning) {
            //gui.die(""); // Muori
        }
    }
    public void stopTimer() {
        isRunning = false;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return this.delay;
    }
}