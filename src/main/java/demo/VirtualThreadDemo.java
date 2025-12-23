package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadDemo {
    public static void main(String[] args) throws Exception{
        //创建一个虚拟线程
        Thread vt=Thread.ofVirtual().start(()->{
            System.out.println("当前线程："+Thread.currentThread());
        });//当前线程：VirtualThread[#30]/runnable@ForkJoinPool-1-worker-1
        vt.join();

        //用Executor批量创建
        try(ExecutorService executor= Executors.newVirtualThreadPerTaskExecutor()){
            for(int i=0;i<100_00;i++){
                int id=i;
                executor.submit(()->{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("task "+id+" running in "+Thread.currentThread());
                });
            }
        }

/*
        task 9984 running in VirtualThread[#10032]/runnable@ForkJoinPool-1-worker-13
        task 9985 running in VirtualThread[#10033]/runnable@ForkJoinPool-1-worker-4
        task 9952 running in VirtualThread[#10000]/runnable@ForkJoinPool-1-worker-1
        task 9992 running in VirtualThread[#10040]/runnable@ForkJoinPool-1-worker-14
        这里真实OS线程是 ForkJoinPool-1-worker-13
        前面是虚拟线程 VirtualThread[#10032]
*/

    }
}
