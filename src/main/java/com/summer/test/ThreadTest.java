package com.summer.test;

public class ThreadTest extends Thread {
    public ThreadTest(){
        System.out.println("------" + "构造函数开始" + "------");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println("------" + "构造函数结束" + "------");
    }
    @Override
    public void run() {
        System.out.println("------" + "run()开始" + "------");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println("Thread.currentThread() == this : " + (Thread.currentThread() == this));
        System.out.println("------" + "run()结束" + "------");
    }
    public static void main(String[] args) {
        // test1
        ThreadTest tt = new ThreadTest();
        tt.setName("线程-1");
        tt.start();

        // test2
        // ThreadTest tt = new ThreadTest();
        // 将线程对象以构造参数的方式传递给Thread对象进行start（）启动线程
        // Thread newThread = new Thread(tt);
        // newThread.setName("线程-1");
        // newThread.start();

    }
}
