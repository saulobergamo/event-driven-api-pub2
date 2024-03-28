package com.example.pub2.config

import org.springframework.amqp.core.FanoutExchange
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

//    @Value("\${spring.rabbitmq.name.queue1}")
//    private lateinit var queue1: String
//
//    @Value("\${spring.rabbitmq.name.queue2}")
//    private lateinit var queue2: String
//
//    @Value("\${spring.rabbitmq.name.queue3}")
//    private lateinit var queue3: String

    @Value("\${spring.rabbitmq.name.exchange}")
    private lateinit var exchange: String

    @Bean
    fun fanoutExchange(): FanoutExchange {
        return FanoutExchange(exchange)
    }
}
