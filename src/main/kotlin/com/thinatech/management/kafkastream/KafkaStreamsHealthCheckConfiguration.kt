package com.thinatech.management.kafkastream

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.StreamsBuilderFactoryBean

@Configuration
@ConditionalOnProperty(value = ["management.health.kafkastreams.enabled"], havingValue = "true")
open class KafkaStreamsHealthCheckConfiguration {

    @Bean
    open fun kafkaHealthCheck(streamsBuilderFactoryBean: StreamsBuilderFactoryBean) = KafkaStreamsHealthCheck(streamsBuilderFactoryBean)
}
