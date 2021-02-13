package com.sora.mq.pubsubsandbox.controller

import com.sora.mq.pubsubsandbox.application.MqSender
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mq")
class ProducerController(private val mqSender: MqSender) {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }
    
    @PostMapping
    fun produce() {
        mqSender.produce()
    }
}