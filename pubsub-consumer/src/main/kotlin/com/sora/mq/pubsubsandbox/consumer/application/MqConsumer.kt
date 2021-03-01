package com.sora.mq.pubsubsandbox.consumer.application

import org.springframework.stereotype.Service

@Service
class MqConsumer {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    fun consumeCharge(message: ChargeMessage) {
        log.info("START consuming userId: ${message.userId}, productIdAndChargeIds: ${message.productIdAndChargeIds}")
        log.warn("Charge data has created!!!")
    }

    fun consumePoint(message: ChargeMessage) {
        log.info("START consuming userId: ${message.userId}, productIdAndChargeIds: ${message.productIdAndChargeIds}")
        log.warn("Point data has created!!!")
    }

    fun consumeMail(message: ChargeMessage) {
        log.info("START consuming userId: ${message.userId}, productIdAndChargeIds: ${message.productIdAndChargeIds}")
        log.warn("Mail data has created!!!")
    }
}

