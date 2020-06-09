package com.csdn.dataStructure.controller;

import com.csdn.dataStructure.kafka.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/7 21:18
 * @description：
 */

@RestController
public class KafkaController {

    @GetMapping("kafkaTest")
    public String kafkaTest(){
        new KafkaProducer().send();
        return "ok";
    }

}
