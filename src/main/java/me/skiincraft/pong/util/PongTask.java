package me.skiincraft.pong.util;

import java.util.concurrent.TimeUnit;

public class PongTask {

    private long delay;
    private Runnable task;
    private boolean end;

    public PongTask(long delaySeconds, Runnable task) {
        this.delay = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(delaySeconds);
        this.task = task;
        this.end = false;
    }

    public void update(){
        if (end){
            return;
        }

        if (System.currentTimeMillis() >= delay){
            task.run();
            end = true;
        }
    }

    public boolean isEnd() {
        return end;
    }

    public void restart(long delay){
        this.delay = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(delay);
        this.end = false;
    }
}
