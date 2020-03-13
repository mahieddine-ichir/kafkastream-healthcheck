package com.thinatech.management.kafkastream

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.AbstractHealthIndicator
import org.springframework.boot.actuate.health.Health
import org.springframework.kafka.config.StreamsBuilderFactoryBean

open class KafkaStreamsHealthCheck(private val streamsBuilderFactoryBean: StreamsBuilderFactoryBean) : AbstractHealthIndicator() {

    @Value("\${management.health.kafkastreams.ensure-state:running}")
    lateinit var streamStateToEnsure: String

    @Throws(Exception::class)
    override fun doHealthCheck(builder: Health.Builder) {

        streamsBuilderFactoryBean.kafkaStreams
                .let { kStream -> kStream.localThreadsMetadata() }
                .associate { threadMetadata -> Pair(threadMetadata.threadName(), threadMetadata.threadState()) }
                .apply {

                    if (this.values.all { s -> s.equals(streamStateToEnsure, ignoreCase = true) })
                        builder.up().withDetails(this)
                    else
                        builder.down().withDetails(this)

                }

        streamsBuilderFactoryBean.kafkaStreams.apply {
            builder.withDetail("kafkaStreams", this.state())
        }
    }
}
