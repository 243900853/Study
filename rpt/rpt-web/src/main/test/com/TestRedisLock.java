//package com;
//import com.xiaobi.util.RedisLock;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.redisson.Redisson;
//import org.redisson.RedissonRedLock;
//import org.redisson.api.RLock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringRunner;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.params.SetParams;
//
//import java.util.UUID;
//
////红锁测试
//@RunWith(SpringRunner.class)
//@org.springframework.boot.test.context.SpringBootTest(classes = ConsumerApp.class)
//public class TestRedisLock {
//    private int count=10;
//    @Autowired
//    private RedisLock lock;
//
//    @Autowired
//    private Redisson redisson;
//
//    @Test
//    public void testRedis(){
//        Jedis jedis=new Jedis("127.0.0.1",6379);
//        SetParams setParams=new SetParams();
//        setParams.ex(6);  //setex     设置值的同时设置过期时间
//        setParams.nx();  //
//        String s = UUID.randomUUID().toString();
//        String lock = jedis.set("lock", s,setParams);
////        Long setnx = jedis.setnx("lock", "value2");
////        if(setnx==1){
////            jedis.expire("lock",10);
////        }
//
//        System.out.println(lock);
//    }
//
//    @Test
//    public void Test() throws InterruptedException {
//        TicketsRunBle ticketsRunBle=new TicketsRunBle();
//        Thread thread1=new Thread(ticketsRunBle,"窗口1");
//        Thread thread2=new Thread(ticketsRunBle,"窗口2");
//        Thread thread3=new Thread(ticketsRunBle,"窗口3");
//        Thread thread4=new Thread(ticketsRunBle,"窗口4");
//        Thread thread5=new Thread(ticketsRunBle,"窗口5");
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
//        Thread.currentThread().join();
//    }
//
//
//    public class TicketsRunBle implements Runnable{
//
//        @Override
//        public void run() {
//            while (count>0){
//                //红锁--集群
//                //为什么要锁3个对象？
//                // 因为，当一个线程进来的时候，会先判断线程是否获取到锁，但此时的线程是分布式的，不知道是那个服务器的线程
//                // 所以将3个对方分别放到不同的redis实例里面，并锁住对象
//                // 然后分布式线程来获取锁，当线程获取到超过一半锁的情况下，redis就认为这个线程获取锁资源，并执行线程程序
//                RLock lock1 = redisson.getLock("{taibai0}:100001320055");  //100001320055
//                RLock lock2 = redisson.getLock("{taibai1}:100001320055");
//                RLock lock3 = redisson.getLock("{taibai2}:100001320055");
//                //红锁--单机--3个redis实例redisson6379、redisson6380、redisson6381
////                RLock lock1 = redisson6379.getLock("lock");
////                RLock lock2 = redisson6380.getLock("lock");
////                RLock lock3 = redisson6381.getLock("lock");
//                RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3);
//                lock.lock();
//                try {
//                    if(count>0){
////                        Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
//                        System.out.println(Thread.currentThread().getName()+"售出第"+count--+"张票");
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    try {
//                        lock.unlock();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//}
