import redis.clients.jedis.Jedis;

import java.util.HashSet;

public class RedisTest {
    public RedisTest(){}
    public static void main(String[] args) {
        redisStringTest();
        redisListTest();
        redisSetTest();
        redisHashTest();
    }

    public static void redisHashTest(){
        Jedis jedis = new Jedis("localhost", 6379, 100000);
        HashSet hashSet=new HashSet();
        int i = 0;
        try {
            long start = System.currentTimeMillis();// 开始毫秒数
            while ((System.currentTimeMillis()-start)<1000) {
                i++;
                jedis.hset("Test"+i,String.valueOf(i),String.valueOf(i));
            }
        } finally {// 关闭连接
            jedis.close();
        }
        //打印1秒内对Redis的操作次数
        System.out.println("redis每秒操作：" + i + "次");
    }

    public static void redisSetTest(){
        Jedis jedis = new Jedis("localhost", 6379, 100000);
        int i = 0;
        try {
            long start = System.currentTimeMillis();// 开始毫秒数
            while ((System.currentTimeMillis()-start)<1000) {
                i++;
                jedis.sadd("tests","");
            }
        } finally {// 关闭连接
            jedis.close();
        }
        //打印1秒内对Redis的操作次数
        System.out.println("redis每秒操作：" + i + "次");
    }

    public static void redisListTest(){
        Jedis jedis = new Jedis("localhost", 6379, 100000);
        int i = 0;
        try {
            long start = System.currentTimeMillis();// 开始毫秒数
            while ((System.currentTimeMillis()-start)<1000) {
                i++;
                jedis.rpush("test", i + "");
            }
        } finally {// 关闭连接
            jedis.close();
        }
        //打印1秒内对Redis的操作次数
        System.out.println("redis每秒操作：" + i + "次");
    }

    public static void redisStringTest(){
        Jedis jedis = new Jedis("localhost", 6379, 100000);
        int i = 0;
        try {
            long start = System.currentTimeMillis();// 开始毫秒数
            while ((System.currentTimeMillis()-start)<1000) {
                i++;
                jedis.set("test" + i, i + "");
            }
        } finally {// 关闭连接
            jedis.close();
        }
        //打印1秒内对Redis的操作次数
        System.out.println("redis每秒操作：" + i + "次");
    }


}