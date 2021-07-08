# Part 1 - Setup and Prerequisites

## 4 July 2021 
- Download virtualbox https://www.virtualbox.org/
- Download and install ubuntu (Ubuntu 20.04.2.0 LTS) https://ubuntu.com/download/desktop
- Install docker (desktop) on window (V 3.5.1)
https://docs.docker.com/docker-for-windows/install/

## Kafka overview

[Apache Kafka](https://kafka.apache.org) is a distributed streaming platform. It provides publish-subscribe APIs and can store and process streams of records at large scale.

Kafka is made up of the following components:
- Broker: One of more brokers form a Kafka cluster
- Producer: Client to send records into Kafka
- Consumer: Client to read records from Kafka
- Admin: Client to manage Kafka clusters
- Connect: Runtime for Sink and Source Connectors to stream data between external systems and Kafka
- Streams: Library to process streams of records in real time

![Kafka Platform](./kafka-platform.png)

You can learn more about Kafka in this introductory article, "[What is Apache Kafka](https://developer.ibm.com/articles/an-introduction-to-apache-kafka/)" or in this conference presentation, "[Introducing Apache Kafka](https://developer.ibm.com/videos/an-introduction-to-apache-kafka/)."

## Prerequisites for this workshop

In order to complete this workshop, you need to have the following dependencies installed:

- [Java SDK](https://jdk.java.net/java-se-ri/10), Version 10 //Set Environment path
- [gradle](https://gradle.org/install/), Version 6 or above  //Set Environment path

## Downloading Apache Kafka

In this workshop, we will use the Kafka command line tools.

[Download](https://downloads.apache.org/kafka/2.7.0/kafka_2.13-2.7.0.tgz) source package from the Apache Kafka website, uncompress it.

```sh
> tar -xzf kafka-2.13-2.7.0.tgz
> cd kafka-2.13-2.7.0
```

## Kafka cluster

We want to use a local Kafka cluster, follow the [local Kafka setup](./local-kafka.md) steps.


## Next Steps

Continue to [Part 2](../part2/README.md).
