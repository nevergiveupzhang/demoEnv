package com.example.demo.java.concurrent.distributed.lock.zookeeper;

public abstract class AbstractLock implements Lock {
    public synchronized void getLock(){
        if(tryLock()){
        }else {
            waitLock();
            getLock();
        }
    }

    protected abstract boolean tryLock();

    protected abstract void waitLock();
}
