package model;

import java.util.concurrent.ThreadFactory;

/**
 * Created by swetha on 27/11/17.
 */
public class TomatoThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
