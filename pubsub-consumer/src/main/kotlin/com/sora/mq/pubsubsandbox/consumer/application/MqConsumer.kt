package com.sora.mq.pubsubsandbox.consumer.application

import org.springframework.stereotype.Service

@Service
class MqConsumer {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    fun consume(message: String) {
        log.info("START consuming message: $message")
        log.warn("charge data has created!!!")
        log.info("COMPLETE consuming message: $message")
    }
}

