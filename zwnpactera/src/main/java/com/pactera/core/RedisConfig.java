//package com.pactera.core;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
///**
// * Created by zwn on 2018/12/8.
// */
////@Configuration//启动加载
//@EnableCaching//启动缓存
//@PropertySource(value = "classpath:/redis.properties")//加载配置文件
//public class RedisConfig extends CachingConfigurerSupport {
//
//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.port}")
//    private int port;
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//
//    /**
//     * 秘钥生成工具,可用可不用
//     * @return
//     */
////    @Bean
////    public KeyGenerator wiselyKeyGenerator(){
////        return new KeyGenerator() {
////            @Override
////            public Object generate(Object target, Method method, Object... params) {
////                StringBuilder sb = new StringBuilder();
////                sb.append(target.getClass().getName());
////                sb.append(method.getName());
////                for (Object obj : params) {
////                    sb.append(obj.toString());
////                }
////                return sb.toString();
////            }
////        };
////    }
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        /**
//         *使用的是jedis单机版来链接redis服务器
//         */
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName(host);
//        factory.setPort(port);
//        factory.setPassword("admin");//使用jedis密码登录
//        factory.setTimeout(timeout); //设置连接超时时间
//        return factory;
//    }
//
//    /**
//     * 设计缓存管理器中模板的超时时间
//     * @param redisTemplate
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        cacheManager.setDefaultExpiration(10); //设置key-value超时时间
//        return cacheManager;
//    }
//
//    /**
//     * 获取链接工厂,对模板进行操作
//     * @param factory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//        setSerializer(template); //设置序列化工具，这样ReportBean不需要实现Serializable接口
//        template.afterPropertiesSet();
//        return template;
//    }
//
//    /**
//     * 序列化模板
//     * @param template
//     */
//    private void setSerializer(StringRedisTemplate template) {
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//    }
//}
