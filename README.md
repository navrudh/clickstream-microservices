# Clickstream Project

## Assumptions Made

### Validation of Data Consumed

- data that does not conform to the following schema will be rejected

```javascript
  {
      "userid": "<UUID>:<UUID>",
      "url": "<URL>",
      "type": "<HTTPMethod>",
      "timestamp": <PositiveLong>
  }
  ```

- example of valid click data

```javascript
{
    "userid": "b20524ae-befe-11e3-b335-425861b86ab6:b20527e2-befe-11e3-b335-425861b86ab6",
    "url": "http://www.someamazingwebsite.com/county-cricket2014/engine/match/692721.html?cluster=undefined;view=comparison;wrappertype=none;xhr=1#cricketlive",
    "type": "GET",
    "timestamp": 1360662163000
}
```


### Decisions Taken When Architecting The Application

#### Why Kafka?

Incoming click data will be of a [streaming](https://en.wikipedia.org/wiki/Streaming_data) nature. Therefore it is expected that the rate of incoming data will be higher at times which would block our server threads and strain our servers. This can be avoided by using a stream processor such as Kafka. Therefore we can model our application as a component that pushes the incoming data into a stream and a consumer component that processes items from the data stream. This architecture now allowes us to scale our services according to the loads experienced during runtime.

The flow of data within the application is as follows:

The current architecture looks like this:

(REST API + Publisher) => (Kafka Stream) => (Consumer that manipulates database)

This allows us to:
1. increase the amount of publishers if the API hit rate crosses set thresholds
2. increase the number of consumers when there is a lot of processing that needs to be done

#### Why MongoDB?

1. There will be a large number of reads in our use case. Therefore a database that allows concurrent reads and writes was necessary
2. MongoDB has powerful aggregation features built into it that allow us to perform computations of a large amount of data fairly quickly and accurately even when the collections are sharded

#### Other Assumptions

- There is no check for duplicates performed on the click stream data. The client providing the data is assumed to to be without faults.

## Build Instructions

Note: The following build instructions are platform independent.

###### Build Jars

```shell
gradlew bootJar
```

###### Docker Containers

Please install docker and docker-compose

```shell
docker-compose -f deploy\application.yml -f deploy\stack.yml up --build -d
```

## Working with the application

###### Submit Click Data

```curl
curl -X POST \
  http://localhost:9001/v1/click/aggregate \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
 "userid": "b20524ae-befe-11e3-b335-425861b86ab6:b20527e2-befe-11e3-b335-425861b86ab6",
 "url": "http://www.someamazingwebsite.com/county-cricket2014/engine/match/692721.html?cluster=undefined;view=comparison;wrappertype=none;xhr=1#cricketlive",
 "type": "GET",
 "timestamp": 1360662163000
}'
```

###### Fetch Click Count By Url

```curl
curl -X POST \
  http://localhost:9001/v1/click/count \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'url=http://www.someamazingwebsite.com/county-cricket2014/engine/match/692721.html?cluster=undefined;view=comparison;wrappertype=none;xhr=1#cricketlive'
```
