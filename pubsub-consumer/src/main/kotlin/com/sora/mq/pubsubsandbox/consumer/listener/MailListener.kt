package com.sora.mq.pubsubsandbox.consumer.listener

import com.sora.mq.pubsubsandbox.consumer.application.MqConsumer
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class MailListener(private val mqConsumer: MqConsumer) {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    @RabbitListener(
        queues = ["\${rabbitmq.mail.queue-name:mail}"],
        containerFactory = "chargeRabbitListenerContainerFactory"
    )
    fun listenMessage(@Payload message: String) {
        log.info("consume mail message: $log")
        mqConsumer.consumeMail(message)
    }
}