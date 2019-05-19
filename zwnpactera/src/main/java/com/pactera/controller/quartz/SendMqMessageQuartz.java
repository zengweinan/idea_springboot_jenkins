package com.pactera.controller.quartz;

import com.pactera.service.impl.SendMqMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zwn on 2018/12/15.
 */
@Controller
//@RequestMapping("/send")
public class SendMqMessageQuartz {

    @Autowired
    private SendMqMessageServiceImpl mqListenerService;

   @Scheduled(cron = "0 */2 15 * * ?")
    //@RequestMapping("/mqMessage")
    public void sendMessage(){

        try {
            mqListenerService.sendRabblitMqmssage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long lt = new Long(1545014922829L);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        System.out.println(res);
    }
}
