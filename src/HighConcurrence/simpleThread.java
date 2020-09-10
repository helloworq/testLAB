package HighConcurrence;

public class simpleThread implements Runnable{
    int count=5;
    @Override
    synchronized public void run() {
        System.out.println("当前处理线程："+Thread.currentThread().getName()
                +"---count值："+count
                +"---当前对象是否存活：" +Thread.currentThread().isAlive());
        count--;
    }
}
