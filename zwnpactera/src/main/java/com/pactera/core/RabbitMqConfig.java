//package com.pactera.core;
//
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
//import org.springframework.amqp.support.ConsumerTagStrategy;
//import org.springframework.context.annotation.Bean;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
///**
// * 开启MQ消费监听器
// * Created by zwn on 2018/12/17.
// */
////@Configuration
//public class RabbitMqConfig {
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();
//        connectionFactory.setHost("172.21.16.11");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/pactera");
//        connectionFactory.setUsername("root");
//        connectionFactory.setPassword("admin");
//        connectionFactory.setAutomaticRecoveryEnabled(true);
//        connectionFactory.setNetworkRecoveryInterval(10000);
//       // #----------------------------------------
//        Map<String, Object> connectionFactoryPropertiesMap = new HashMap();
//        connectionFactoryPropertiesMap.put("pactera", "RobertoHuang");
//        connectionFactory.setClientProperties(connectionFactoryPropertiesMap);//设置客户端属性,可以不用
//
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
//        return cachingConnectionFactory;
//    }
//
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    /**
//     * 把消费者监听交给容器
//     * @param connectionFactory
//     * @return
//     */
//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
//        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
//
//        // 设置消费者线程数
//        simpleRabbitListenerContainerFactory.setConcurrentConsumers(5);
//        // 设置最大消费者线程数
//        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(10);
//
//        // 设置消费者标签(可有可无)
//        simpleRabbitListenerContainerFactory.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String s) {
//                return "0000000000000000";
//            }
//        });
//
//        return simpleRabbitListenerContainerFactory;
//    }
//}
