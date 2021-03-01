package com.sora.mq.pubsubsandbox.producer.application

import lombok.Builder
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Service

@Service
class MqSender(private val chargeAmqpTemplate: AmqpTemplate) {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    fun produceCharge() {
        val productIdAndChargeIds = listOf(
            ProductIdAndChargeId(1, 30),
            ProductIdAndChargeId(2, 40),
            ProductIdAndChargeId(3, 50),
        )
        val message = 
            ChargeMessage("user001"
                , 1000
                , 100
                , productIdAndChargeIds
                , "シネマチケット"
                , "TICKET")
     
        log.info("START sending message: $message")
        chargeAmqpTemplate.convertAndSend(message)
        log.info("COMPLETE sending message")
    }

    /** charge */
    data class ChargeMessage(

        val userId: String? = null,

        val price: Int = 0,

        val point: Int = 0,
        
        val productIdAndChargeIds: List<ProductIdAndChargeId>,

        val productName: String? = null,

        val productType: String? = null
    )
    
    data class ProductIdAndChargeId(
        val productId: Long,
        val chargeId: Long,
    )
}

