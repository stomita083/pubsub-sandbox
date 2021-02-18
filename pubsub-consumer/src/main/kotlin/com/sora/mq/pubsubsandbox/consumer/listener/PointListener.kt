package com.sora.mq.pubsubsandbox.consumer.listener

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
        queues = ["\${rabbitmq.charge.queue-name:point}"],
        containerFactory = "rabbitListenerContainerFactory"
    )
    fun listenMessage(@Payload message: String) {
        log.info("consume point message: $log")
        mqConsumer.consumePoint(message)
    }
}