package com.example.demo.springDemo.event;

import java.util.Observable;
import java.util.Observer;

public class ObserverDemo {
    public static void main(String[] args) {
        Observable observable = new EventObservable();
        observable.addObserver(new EventObserver());
        observable.notifyObservers("hello");
    }

    static class EventObservable extends Observable{
        @Override
        public void setChanged(){
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object args){
            setChanged();
            super.notifyObservers(args);
            clearChanged();
        }
    }
    static class EventObserver implements Observer{

        @Override
        public void update(Observable o, Object arg) {
            System.out.println(arg);
        }
    }
}
