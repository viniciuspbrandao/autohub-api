package com.vb.autohubapi.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Value("${topic.autohub}")
    private String topicAutohub;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    void sendMessage(String message){
        System.out.println("entrou aqui");
        this.kafkaTemplate.send(topicAutohub,message);
    }

}
