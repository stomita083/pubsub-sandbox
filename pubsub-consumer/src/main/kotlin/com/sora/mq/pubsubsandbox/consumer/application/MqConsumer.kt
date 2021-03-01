package com.sora.mq.pubsubsandbox.consumer.application

import org.springframework.stereotype.Service

@Service
class MqConsumer {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    fun consumeCharge(message: ChargeMessage) {
        log.info("START consuming userId: ${message.userId}, productIdAndChargeIds: ${message.productIdAndChargeIds}")
        log.warn("charge data has created!!!")
        log.info("COMPLETE consuming message: $message")
    }

    fun consumePoint(message: ChargeMessage) {
        log.info("START consuming message: $message")
        log.warn("point data has created!!!")
        log.info("COMPLETE consuming message: $message")
    }

    fun consumeMail(message: ChargeMessage) {
        log.info("START consuming message: $message")
        log.warn("mail data has created!!!")
        log.info("COMPLETE consuming message: $message")
    }
}

