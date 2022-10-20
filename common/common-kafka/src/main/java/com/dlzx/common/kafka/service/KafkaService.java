package com.dlzx.common.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KafkaService {
    @Resource
    private KafkaTemplate kafkaTemplate;

    public String send(String topic,String value){
        //使用kafka模板发送信息
        try {
            kafkaTemplate.send(topic, value);
        }catch (Exception e){
            return e.toString();
        }
        return "success";
    }
}
