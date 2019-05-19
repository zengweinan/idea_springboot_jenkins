package com.pactera.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pactera.common.General;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * 发送mq控制器
 * Created by zwn on 2018/12/10.
 */
@Service
public class SendMqMessageServiceImpl {
    private Logger logger = LoggerFactory.getLogger(SendMqMessageServiceImpl.class);
    //1.创建连接工厂
    private static ConnectionFactory factory = new ConnectionFactory();
    //3.新建一个连接
    private static Connection connection;
    private static Channel channel;
    private String host = General.getConfigurationInformation("rabbitmq.properties", "spring.rabbitmq.host");
    private String port = General.getConfigurationInformation("rabbitmq.properties", "spring.rabbitmq.port");
    private String username = General.getConfigurationInformation("rabbitmq.properties", "spring.rabbitmq.username");
    private String password = General.getConfigurationInformation("rabbitmq.properties", "spring.rabbitmq.password");
    private String queueName = General.getConfigurationInformation("rabbitmq.properties", "spring.rabbitmq.queue_name");
    private String exchangeName = General.getConfigurationInformation("rabbitmq.properties", "spring.rabbitmq.exchange_name");
    private String routingKey = General.getConfigurationInformation("rabbitmq.properties", "sales.system.MQ.route_key");
    private String virtualHost = General.getConfigurationInformation("rabbitmq.properties", "sales.system.MQ.vhost");
//    {
//        factory.setHost(host);
//        factory.setPort(Integer.valueOf(port));
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setVirtualHost(virtualHost);
//    }

    /**
     * 2.创建工厂的RabbitMQ server的主机
     *
     * @return
     * @throws TimeoutException
     * @throws IOException
     */
    public Connection getInstance() throws IOException, TimeoutException {
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            logger.info("RabbitMQ发生连接时IO异常");
            throw new RuntimeException("创建连接失败");
        } catch (TimeoutException e) {
            logger.info("RabbitMQ发生连接超时异常");
            throw new RuntimeException("创建连接失败");
        }
        return connection;
    }

    private boolean sendMq(Map<String, Object> result, boolean flag, JSONObject jsonObj, Map<String, String> map) {
        //Channel channel = null;
        try {
            //创建联接
            if (channel == null || connection == null) {
                try {
                    Connection con = getInstance();
                    channel = con.createChannel();
                    channel.confirmSelect();//开启发送确认机制
                    channel.addConfirmListener(new ConfirmListener() {
                        @Override
                        public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                            result.put("sendResult", "failed");
                            logger.info("发送失败(handleNack)------------  deliveryTag: " + deliveryTag + ", multiple: " + multiple);
                            if (map != null) {//为null表示错误不记录
                                if (channel != null) {//关闭连接
                                    try {
                                        channel.close();
                                        channel = null;
                                    } catch (IOException y) {
                                        logger.info("通道关闭io异常", y);
                                    } catch (TimeoutException y) {
                                        logger.info("通道关闭超时异常", y);
                                    }
                                }
                            }
                        }

                        @Override
                        public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                            logger.info("发送成功(handleAck)------------deliveryTag: " + deliveryTag + ", multiple: " + multiple);
                            result.put("sendResult", "success");
                            if (map != null) {//为null表示不记录
                            }
                        }
                    });
                } catch (IOException e) {
                    logger.info("RabbitMQ创建通道时IO异常");
                    throw new RuntimeException("RabbitMQ创建通道时IO异常");
                }
            }
            //设置mandatory=true，当路由不到队列时返回给消息发送者，在return监听器中接收(这是用return监听器的)
            //发送消息
            channel.basicPublish(exchangeName, routingKey, null, jsonObj.toString().getBytes());
            System.out.println("msg========" + jsonObj.toString());
            flag = true;
            //关闭连接
        } catch (Exception e) {
            logger.info("mq发送消息时异常", e);
            result.put("msg", "mq发送消息时异常");
            //关闭连接
            if (channel != null) {
                try {
                    channel.close();
                    channel = null;
                } catch (IOException y) {
                    logger.info("通道关闭io异常", y);
                } catch (TimeoutException y) {
                    logger.info("通道关闭超时异常", y);
                }
            }
        }
        return flag;
    }
    public void sendRabblitMqmssage() {
        Map<String, Object> result = new HashMap<String, Object>();
        boolean flag = false;
        result.put("sendResult", "");
        JSONObject jsonHeader = new JSONObject();
        jsonHeader.put("bizSystem", 11);
        jsonHeader.put("messageType", "queryCustInfoByProperty");
        jsonHeader.put("requestDate", new SimpleDateFormat("YYYYMMddHHmmssSSS").format(new Date()));
        jsonHeader.put("messageId", UUID.randomUUID().toString());//"1d481f12-43bd-48e5-97f2-4a95e67eb885"
        jsonHeader.put("version", "1.0.1");
        JSONObject jsonBody = new JSONObject();
        // jsonBody.put("operatorName", "移动验房");
        jsonBody.put("operationDate", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        // jsonBody.put("roomId", map.get("roomId"));
        // jsonBody.put("projectId", map.get("projectId"));
        JSONObject jsonPro = new JSONObject();
        jsonPro.put("projectInfo", jsonBody);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("header", jsonHeader);
        jsonObj.put("body", jsonPro);
        flag = sendMq(result, flag, jsonObj, null);
        sendMq(result, flag, jsonObj, null);
    }

}
