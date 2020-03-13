# Kafka Stream health check actuator
A Kafka Stream Spring health check actuator.

# Description
The health checks for the Kafka Stream tasks sanity though `org.springframework.kafka.config.StreamsBuilderFactoryBean`.
The Stream tasks are not considered _healthy_ unless all their states are _running_.

## Enabling the health check endpoint
The kafka stream health check component is not enabled by default, it must enabled through the configuration property
````
    management.health.kafkastreams.enabled=true
````

## Tasks checked state
The kafka stream health check component checks for a _Running_ state of the tasks, this can be changed to another state
 through the configuration property
````
    management.health.kafkastreams.ensure-state=running
````



