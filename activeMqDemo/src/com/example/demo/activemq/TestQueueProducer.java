package com.example.demo.activemq;

public class TestQueueProducer {
    public static void main(String[] args){
        QueueProducer producter = new QueueProducer();
        producter.init();
        TestQueueProducer testMq = new TestQueueProducer();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Thread 1
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 2
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 3
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 4
        new Thread(testMq.new ProductorMq(producter)).start();
        //Thread 5
        new Thread(testMq.new ProductorMq(producter)).start();
    }

    private class ProductorMq implements Runnable{
        QueueProducer producter;
        public ProductorMq(QueueProducer producter){
            this.producter = producter;
        }

        @Override
        public void run() {
            while(true){
                try {
                    producter.sendMessage("201905291339-MQ");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
