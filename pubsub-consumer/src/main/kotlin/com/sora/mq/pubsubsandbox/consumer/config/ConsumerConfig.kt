package com.sora.mq.pubsubsandbox.consumer.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class ConsumerConfig {

    @Value("\${rabbitmq.charge.queue-name}")
    var queueName: String? = null

    @Bean
    fun queue(): Queue {
        return Queue(queueName, true)
    }

    @Bean
    fun dlqQueue(): Queue {
        return Queue("exchange.dead", true)
    }

    // Ref: https://blog.mookjp.io/memo/spring-amqp%E3%81%AE%E4%BD%BF%E3%81%84%E6%96%B9/#message%E3%81%AE%E5%8F%97%E4%BF%A1
    @Bean
    fun rabbitListenerContainerFactory(
    ): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
        factory.setConcurrentConsumers(3)
        factory.setMaxConcurrentConsumers(10)
        return factory
    }

    @Bean
    fun dlqListenerContainerFactory(
    ): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
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