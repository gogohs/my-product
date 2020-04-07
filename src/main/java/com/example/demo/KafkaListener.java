package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class KafkaListener {
    @Autowired  //JPA에서느 autowired 사용불가. JPA가 도는중 Spring의 라이프사이클이 다르기 때문에.
    ProductRepository productRepository;
    @StreamListener(Processor.INPUT)
    public void onEventByString(@Payload OrderPlaced orderPlaced){

        if(orderPlaced.getEventType().equals("OrderPlaced")){
            System.out.println("===============================================================");
            System.out.println("Change Qty");
            Product  p = new Product();
            p.setName((orderPlaced.getProductName()));
            p.setStock(orderPlaced.getQty());
            productRepository.save(p);
            System.out.println("===============================================================");
        }
    }

}
