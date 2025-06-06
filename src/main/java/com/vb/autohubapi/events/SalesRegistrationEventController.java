package com.vb.autohubapi.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale-event")
public class SalesRegistrationEventController {

    @Autowired
    KafkaProducer producer;

    @PostMapping
    public ResponseEntity<String> sendEventSale(@RequestBody String saleEvent){
        producer.sendMessage(saleEvent);
        return ResponseEntity.ok().body("Sale Record Sent: " + saleEvent);
    }

}
