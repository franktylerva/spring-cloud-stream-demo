Start the Confluent Kafka Platform
```bash
docker-compose up -d
```

Set the topic compatibility mode.
```
curl -X PUT http://127.0.0.1:8081/config -d '{"compatibility": "NONE"}' -H "Content-Type:application/json"
curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"compatibility": "FULL_TRANSITIVE"}' http://localhost:8081/config/sensor-topic-value
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