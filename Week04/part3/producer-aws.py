#Producer
from datetime import datetime
from time import sleep
from json import dumps
from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers=['ec2-13-229-46-113.ap-southeast-1.compute.amazonaws.com:9092'],
                         value_serializer=lambda x: 
                         dumps(x).encode('utf-8'))

for e in range(1000):
    data = {
        'timestamp' : datetime.now().strftime("%d/%m/%Y %H:%M:%S.%f'"),
        'ekarat' : e
    }
    print(data)
    producer.send('quickstart-events', value=data)
    sleep(1)