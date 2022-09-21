Start the Confluent Kafka Platform
```bash
docker-compose up -d
```

Start the Producer:
```
cd producer
mvn spring-boot:run
```

Start the Consumer:
```
cd consumer
mvn spring-boot:run
```

Send a message:
```
curl -X POST http://localhost:8080/messages
```