package com.sora.mq.pubsubsandbox.consumer.application

import lombok.Builder
import lombok.Data
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Service

@Service
class MqSender(private val chargeAmqpTemplate: AmqpTemplate) {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    fun produce() {
        val message = ChargeMessage("user001", 1000, 100, "シネマチケット", "TICKET")
        log.info("START sending message: $message")
        //chargeAmqpTemplate.convertAndSend(message)
        chargeAmqpTemplate.convertAndSend("exchange", "charge.create", message.toString())
        log.info("COMPLETE sending message: $message")
    }

    /** charge */
    @Data
    @Builder
    //@NoArgsConstructor
    //@AllArgsConstructor
    //@JsonIgnoreProperties(ignoreUnknown = true)
    data class ChargeMessage(

        private val userId: String? = null,

        private val price: Int = 0,

        private val point: Int = 0,

        private val productName: String? = null,

        private val productType: String? = null
    )
}

