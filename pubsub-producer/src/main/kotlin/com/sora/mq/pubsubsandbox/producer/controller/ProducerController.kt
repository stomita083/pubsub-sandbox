package com.sora.mq.pubsubsandbox.producer.controller

import com.sora.mq.pubsubsandbox.producer.application.MqSender
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mq")
class ProducerController(private val mqSender: MqSender) {

    @PostMapping
    fun produce(): String {
        mqSender.produceCharge()
        return "success"
    }
}