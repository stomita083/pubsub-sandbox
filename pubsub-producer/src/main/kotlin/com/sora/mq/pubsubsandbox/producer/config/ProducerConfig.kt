package com.sora.mq.pubsubsandbox.producer.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class ProducerConfig {

    companion object {
        val log = org.slf4j.LoggerFactory.getLogger(this::class.java.enclosingClass)!!
    }

    @Bean
    fun jsonMessageConverter(): Jackson2JsonMessageConverter {
        val objectMapper = Jackson2ObjectMapperBuilder.json()
            .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
            .modules(JavaTimeModule())
            .dateFormat(StdDateFormat())
            .timeZone("Asia/Tokyo")
            .build<ObjectMapper>()
        val ret = Jackson2JsonMessageConverter(objectMapper)
        ret.setCreateMessageIds(true)
        return ret
    }

    /** ChargeAmqpTemplate  */
    @Bean
    @ConfigurationProperties(prefix = "rabbitmq.charge")
    fun chargeAmqpTemplate(connectionFactory: ConnectionFactory): AmqpTemplate {
        val template = RabbitTemplate(connectionFactory)
        template.messageConverter = jsonMessageConverter()
        //log.info("exchange: ${template.exchange}, routing-key: ${template.routingKey}")
        return template
    }
}