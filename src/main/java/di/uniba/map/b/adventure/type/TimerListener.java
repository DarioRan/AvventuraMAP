package di.uniba.map.b.adventure.type;

import di.uniba.map.b.adventure.AdventureGameGUI;

public class TimerListener extends Thread {
    private volatile boolean isRunning = true;
    private int progress = 0;

    private AdventureGameGUI gui;
    private int delay;

    public void setGui(AdventureGameGUI gui) {
        this.gui = gui;
    }

    public TimerListener() {
        this.delay = 2500;
    }

    @Override
    public void run() {
        while (progress < 100 && isRunning) {
            try {
                Thread.sleep(delay);
                progress += 1;

                 gui.getProgressBar().setValue(progress);
                if (progress % 20 == 0 && progress != 100) {
                    gui.appendAreaText("Il livello delle radiazioni sta aumentando... Corri!\n");
                }
                gui.changeProgressBarColor(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (isRunning) {
            gui.die(""); // Muori
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