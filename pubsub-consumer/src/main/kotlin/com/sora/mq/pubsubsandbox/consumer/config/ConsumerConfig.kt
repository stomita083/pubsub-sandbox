package com.sora.mq.pubsubsandbox.consumer.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class ConsumerConfig {

    @Bean
    fun jsonMessageConverter(): Jackson2JsonMessageConverter {
        val objectMapper = Jackson2ObjectMapperBuilder.json()
            .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
//            .modules(JavaTimeModule())
//            .dateFormat(StdDateFormat())
//            .timeZone("Asia/Tokyo")
            .build<ObjectMapper>()
        return Jackson2JsonMessageConverter(objectMapper)
    }

    // Ref: https://blog.mookjp.io/memo/spring-amqp%E3%81%AE%E4%BD%BF%E3%81%84%E6%96%B9/#message%E3%81%AE%E5%8F%97%E4%BF%A1
    @Bean
    fun chargeRabbitListenerContainerFactory(
    ): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
        factory.setMessageConverter(jsonMessageConverter())
        factory.setConcurrentConsumers(3)
        factory.setMaxConcurrentConsumers(10)
        return factory
    }

    @Bean
    fun dlqListenerContainerFactory(
    ): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
        factory.setMessageConverter(jsonMessageConverter())
        factory.setConcurrentConsumers(3)
        factory.setMaxConcurrentConsumers(10)
        return factory
    }

    // TODO
    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory("localhost")
        connectionFactory.username = "app-user"
        connectionFactory.setPassword("admin")
        connectionFactory.virtualHost = "my-vhost"
        connectionFactory.setExecutor(rabbitConnectionExecutor())
        return connectionFactory
    }

    @Bean
    fun rabbitConnectionExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 10
        executor.maxPoolSize = 10
        return executor
    }
}