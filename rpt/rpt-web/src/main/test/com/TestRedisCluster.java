//package com;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//public class TestRedisCluster {
//
//    public static void main(String[] args) {
//        Logger logger= LoggerFactory.getLogger(TestRedisCluster.class);
//        Set<HostAndPort> nodesList=new HashSet<>();
//        nodesList.add(new HostAndPort("127.0.0.1",7000));
//        nodesList.add(new HostAndPort("127.0.0.1",7001));
//        nodesList.add(new HostAndPort("127.0.0.1",7002));
//        nodesList.add(new HostAndPort("127.0.0.1",7003));
//        nodesList.add(new HostAndPort("127.0.0.1",7004));
//        nodesList.add(new HostAndPort("127.0.0.1",7005));
//
//        // Jedis连接池配置
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        // 最大空闲连接数, 默认8个
//        jedisPoolConfig.setMaxIdle(200);
//        // 最大连接数, 默认8个
//        jedisPoolConfig.setMaxTotal(1000);
//        //最小空闲连接数, 默认0
//        jedisPoolConfig.setMinIdle(100);
//        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
//        jedisPoolConfig.setMaxWaitMillis(3000); // 设置2秒
//        //对拿到的connection进行validateObject校验
//        jedisPoolConfig.setTestOnBorrow(false);
////        MyRedisCluster redisCluster=new MyRedisCluster(nodesList,2000,2000,5,"123456",jedisPoolConfig);
////        System.out.println(redisCluster.mset("k1", "v1", "k2", "v2", "k3", "v3"));
////        System.out.println(redisCluster.mget("k1","k2", "k3" ));
//
//        JedisCluster jedisCluster=new JedisCluster(nodesList,2000,2000,5,"xiaobi",jedisPoolConfig);
//        while (true) {
//            try {
//                String s = UUID.randomUUID().toString();
//                jedisCluster.set("k" + s, "v" + s);
//                System.out.println(jedisCluster.get("k" + s));
//                Thread.sleep(1000);
//            }catch (Exception e) {
//                logger.error(e.getMessage());
//            }
//        }
//    }
//}
