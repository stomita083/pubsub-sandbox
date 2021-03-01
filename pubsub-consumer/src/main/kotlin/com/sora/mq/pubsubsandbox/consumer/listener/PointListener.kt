package com.sora.mq.pubsubsandbox.consumer.listener

import com.sora.mq.pubsubsandbox.consumer.application.ChargeMessage
import com.sora.mq.pubsubsandbox.consumer.application.MqConsumer
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class PointListener(private val mqConsumer: MqConsumer) {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    @RabbitListener(
        queues = ["\${rabbitmq.point.queue-name:point}"],
        containerFactory = "chargeRabbitListenerContainerFactory"
    )
    fun listenMessage(@Payload message: ChargeMessage) {
        log.info("consume point message: $message")
        mqConsumer.consumePoint(message)
    }
}