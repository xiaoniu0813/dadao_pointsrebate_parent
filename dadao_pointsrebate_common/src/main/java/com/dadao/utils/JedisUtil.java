package com.dadao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JedisUtil {

    private static Logger log = LoggerFactory.getLogger(JedisUtil.class);

    //根据配置实例化jedis池
    public static JedisPool jedisPool;
    //静态代码初始化池配置
    static{
        initPool();
    }

    public static void initPool(){
        try {
            Properties props = new Properties();
            props.load(JedisUtil.class.getClassLoader().getResourceAsStream("redis.properties"));
            //创建jedis池配置实例
            JedisPoolConfig config = new JedisPoolConfig();
            //设置池配置项值
            config.setMaxIdle(Integer.valueOf(props.getProperty("redis.maxIdle")));

            //jedisPool = new JedisPool(config, props.getProperty("redis.host"), Integer.valueOf(props.getProperty("redis.port")));
            jedisPool = new JedisPool(config, props.getProperty("redis.host"), Integer.valueOf(props.getProperty("redis.port")), 0, props.getProperty("redis.password"));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static void set(String key, String value){
        set(key, value, 0);
    }

    public static void set(String key, String value, int expireSeconds){
        Jedis jedis = jedisPool.getResource();
        try {

            jedis.set(key, value);
            jedis.expire(key, expireSeconds);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }finally{
            jedisPool.returnResource(jedis);
        }

    }

    public static String get(String key){
        Jedis jedis = jedisPool.getResource();
        String value = null;
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }finally{
            jedisPool.returnResource(jedis);
        }
        return value;
    }


    public static Long geoadd(String key, double longitude, double latitude, String member){
        Jedis jedis = jedisPool.getResource();
        try {

            jedis.geoadd(key, longitude, latitude, member);
            //jedis.expire(key, expireSeconds);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }finally{
            jedis.close();
        }

        return 0l;
    }

    public static Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = jedisPool.getResource();
        try {

            jedis.geoadd(key, memberCoordinateMap);
            //jedis.expire(key, expireSeconds);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }finally{
            jedis.close();
        }

        return 0l;
    }

    public static List<GeoRadiusResponse> georadius(String key,
                                                    double longitude,
                                                    double latitude,
                                                    double radius,
                                                    GeoUnit unit,
                                                    GeoRadiusParam param){
        Jedis jedis = jedisPool.getResource();
        List<GeoRadiusResponse> radiusResponses = null;
        try {

            radiusResponses = jedis.georadius(key, longitude, latitude, radius, unit, param);
            //jedis.expire(key, expireSeconds);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }finally{
            jedis.close();
        }

        return radiusResponses;
    }

    public static void main(String[] args)throws Exception {

        JedisUtil.jedisPool.getResource().set("foo", "bar");
        JedisUtil.jedisPool.getResource().expire("foo", 3);//设置超时时间

        String value = jedisPool.getResource().get("foo");
        System.out.println(value);

        //jedisPool.getResource().geoadd()
		/*Thread.sleep(1000);
		value = jedisPool.getResource().get("foo");
		System.out.println(value);

		Thread.sleep(1000);
		value = jedisPool.getResource().get("foo");
		System.out.println(value);

		Thread.sleep(1000);
		value = jedisPool.getResource().get("foo");
		System.out.println(value);*/

    }
}
