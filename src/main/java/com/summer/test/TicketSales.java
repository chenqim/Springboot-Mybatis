package com.summer.test;

public class TicketSales extends Thread{

    private static int ticketNum = 20;

    public void sellTickets () {
        synchronized (this) {
            if (ticketNum > 0) {
                System.out.println(Thread.currentThread().getName() + "正在售票，还剩" + --ticketNum + "张票");
            }
        }
    }

    @Override
    public void run() {
        while (ticketNum > 0) {
            sellTickets();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketSales ts = new TicketSales();
        Thread t1 = new Thread(ts);
        Thread t2 = new Thread(ts);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t1.start();
        t2.start();
    }
}