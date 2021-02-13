package com.sora.mq.pubsubsandbox.producer.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class RabbitMqConfig {

    @Bean
    fun myFactory(
        connectionFactory: ConnectionFactory?,
        configurer: SimpleRabbitListenerContainerFactoryConfigurer
    ): SimpleRabbitListenerContainerFactory? {
        val factory = SimpleRabbitListenerContainerFactory()
        configurer.configure(factory, connectionFactory)
        //val executor = Executors.newCachedThreadPool(HogeThreadFactory("amqp-%d"))
        //factory.setTaskExecutor(executor)
        return factory
    }

//    class HogeThreadFactory private constructor(private val format: String) : ThreadFactory {
//        private val counter = AtomicInteger(0)
//        override fun newThread(r: Runnable): Thread {
//            val name = String.format(format, counter.incrementAndGet())
//            return Thread(null, r, name)
//        }
//    }

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

//    /** ChargeAmqpTemplate  */
//    @Bean
//    //@ConfigurationProperties(prefix = "rabbitmq.charge")
//    fun chargeAmqpTemplate(val connectionFactory: ConnectionFactory): AmqpTemplate {
//        //val connectionFactory: ConnectionFactory = SimpleRabbitListenerContainerFactory()
//        val template = RabbitTemplate(connectionFactory)
//        template.setExchange("charge")
//        //template.routingKey("charge.create")
//            //.messageConverter(jsonMessageConverter())
//        //configurer.configureRabbitTemplate(template, connectionFactory)
//        return template
//    }
}