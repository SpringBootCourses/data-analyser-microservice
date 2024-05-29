# Data analyser microservice

This is data analyser microservice
for [YouTube course](https://www.youtube.com/playlist?list=PL3Ur78l82EFBhKojbSO26BVqQ7n4AthHC).

This application receives data
from [Data generator service](https://github.com/IlyaLisov/data-generator-microservice)
with Apache Kafka.

Next, data is processed
by [Data store service](https://github.com/IlyaLisov/data-store-microservice).

### Usage

To start an application you need to pass variables to `.env` file.

You can use example `.env.example` file with some predefined environments.

You can find Docker compose file in `docker/docker-compose.yaml`.

Application is running on port `8082`.

All insignificant features (checkstyle, build check, dto validation) are not
presented.

Just after startup application will try to connect to Apache Kafka and begin to
listen topics from `KAFKA_SUBSCRIBED_TOPICS`.

### Docker

You can run all course applications via `docker-compose.yaml` from `docker`
folder.

It contains all needed configs.

**NOTE**: after Debezium connect is started, apply source config manually.

```shell
cd /on-startup/

sh run.sh
```

Note that all services must be in the same network to communicate with each
other.

Debezium needs different group id than Kafka uses, so default values from `.env`
are 1 and 2.

Debezium is configured to push messages to `data` topic due to routing in
configuration.