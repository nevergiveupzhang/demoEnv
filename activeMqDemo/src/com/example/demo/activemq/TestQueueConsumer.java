package com.example.demo.activemq;

public class TestQueueConsumer {
    public static void main(String[] args){
        QueueConsumer comsumer = new QueueConsumer();
        comsumer.init();
        TestQueueConsumer testConsumer = new TestQueueConsumer();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
    }

    private class ConsumerMq implements Runnable{
        QueueConsumer comsumer;
        public ConsumerMq(QueueConsumer comsumer){
            this.comsumer = comsumer;
        }

        @Override
        public void run() {
            while(true){
                try {
                    comsumer.getMessage("Jaycekon-MQ");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
