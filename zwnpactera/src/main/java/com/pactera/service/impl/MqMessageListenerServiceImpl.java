//package com.pactera.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Map;
//
///**
// * 自定义mq消费(有多种写法:也可以下下面的这中写法)
// * Created by bo on 2018/12/17.
// * Ready代表消费者还可以读到的条数，Unacked:代表还有多少条没有被应答
// */
//@Service
//public class MqMessageListenerServiceImpl {
//
//    @RabbitListener(queues = "SHIDI_CUST_DATA_CENTER_INPUT_QUEUE")
//    public void getRabbitMqMessage(Message message, Channel channel) throws IOException {
//        String messagestr = new String(message.getBody(), "UTF-8");
//        try {
//            Map retMaps = (Map) JSON.parse(messagestr);
//            Map headerMap = (Map) retMaps.get("header");
//            Map bodyMap = (Map) retMaps.get("body");
//            //消息确认
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//            System.out.print("收到信息:" + bodyMap);
//        } catch (Exception e) {
//            // logger.info("{}无法识别的消息,报文:{},进行拒绝接收======={}",message.getMessageProperties().getDeliveryTag() , message,e.getMessage());
//            //批量退回或删除,中间的参数 是否批量 true是/false否 (也就是只一条)
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * @Component
//     @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "roberto.order.add", durable = "true", autoDelete = "false", exclusive = "false"), exchange = @Exchange(name = "roberto.order"))})
//     public class SpringAMQPMessageHandle {
//     @RabbitHandler public void add(byte[] body) {
//     System.out.println("----------byte[]方法进行处理----------");
//     try {
//     System.out.println(new String(body, "UTF-8"));
//     } catch (UnsupportedEncodingException e) {
//     e.printStackTrace();
//     }
//     }
//     }
//     */
//}
