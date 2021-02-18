package com.sora.mq.pubsubsandbox.consumer.application

import org.springframework.stereotype.Service

@Service
class MqConsumer {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    fun consumeCharge(message: String) {
        log.info("START consuming message: $message")
        log.warn("charge data has created!!!")
        log.info("COMPLETE consuming message: $message")
    }

    fun consumePoint(message: String) {
        log.info("START consuming message: $message")
        log.warn("point data has created!!!")
        log.info("COMPLETE consuming message: $message")
    }

    fun consumeMail(message: String) {
        log.info("START consuming message: $message")
        log.warn("mail data has created!!!")
        log.info("COMPLETE consuming message: $message")
    }
}

