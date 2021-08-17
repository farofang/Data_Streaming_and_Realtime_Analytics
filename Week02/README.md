# Getting started with Kafka

In this workshop, you will learn how Apache Kafka works and how you can use it to build applications that react to events as they happen. We demonstrate how you can use Kafka as an event streaming platform. We cover the key concepts of Kafka and take a look at the components of the Kafka platform.

## [Part 1 - Setup and Prerequisites](./part1/README.md)

The first part gives an overview of the Kafka platform. It covers the main use cases for Kafka. It also contains the prerequisites for the workshop.

## [Part 2 - Sending and consuming messages](./part2/README.md)

The second part covers the most basic concepts of Apache Kafka such as topics, partitions, and the Producer and Consumer clients.

## [Part 3 - Integrating data with Kafka Connect](./part3/README.md)

The third part explains how existing systems can be connected to Kafka using Kafka Connect. Using built-in connectors, we will see how data can be imported into Kafka.

## [Part 4 - Processing data with Kafka Streams](./part4/README.md)

The fourth part introduces Kafka Streams and explains its data processing capabilities. It explores the `WordCountDemo` sample application by running it and detailing its processing logic.




## Another way (by using Docker)

Docker: https://betterprogramming.pub/a-simple-apache-kafka-cluster-with-docker-kafdrop-and-python-cf45ab99e2b9

Lab 1: reading real-time chat from Youtube live (Example of #Ch3ThailandNews)

Python package https://pypi.org/project/pytchat/

<img width="751" alt="Screen Shot 2564-08-11 at 11 50 23" src="https://user-images.githubusercontent.com/69342162/128971375-c60b39a2-0887-40ed-ae19-674cab440160.png">

```python
import pytchat
chat = pytchat.create(video_id="oTSzxLvoEJE")
while chat.is_alive():
    for c in chat.get().sync_items():
        print(f"{c.datetime} [{c.author.name}]- {c.message}")
```
Lab 2: Twitter stream

``` python
import tweepy
from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener

class listener(StreamListener):
    
    def on_data(self, data):
      	print (data.encode().decode('unicode_escape'))
      	return True
    
    def on_error(self, status):
        print (status)

auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)
twitterStream = Stream(auth, listener())
twitterStream.filter(track=["popcat"])
```

