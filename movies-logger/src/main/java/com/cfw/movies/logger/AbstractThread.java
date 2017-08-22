package com.cfw.movies.logger;

/**
 * Created by Duskrain on 2017/8/13.
 */
public abstract class AbstractThread implements Runnable {

    private int threadsNumbers = 1;

    public AbstractThread(){}

    public AbstractThread(int threadNumbers){
        this.threadsNumbers = threadNumbers;
    }

    public void startup() {
        for(int i=0;i<this.threadsNumbers;i++){
            new Thread(this).start();
        }
    }
}
