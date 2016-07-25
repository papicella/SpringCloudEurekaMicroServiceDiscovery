<h1>Spring Cloud Services using Pivotal Cloud Foundry (PWS Instance)</h1>

The following demo show show to use Service Discovery and the Circuit Breaker Spring Cloud services with a simple application.

![alt tag](https://dl.dropboxusercontent.com/u/15829935/platform-demos/images/piv-springcloud-eureka-1.png)

<h2> Steps </h2>

- Clone as follows

```
$ git clone https://github.com/papicella/SpringCloudEurekaMicroServiceDiscovery.git
```

- Change to following directorories and package both projects

```
$ cd SpringCloudEurekaMicroServiceDiscovery
$ cd StockService
$ mvn package
$ cd ../RibbonClient
$ mvn package
```

- Create services

```
$ cf create-service p-service-registry standard apples-service-registery
$ cf create-service p-circuit-breaker-dashboard standard apples-circuit-breaker-dashboard
```

- Push StockService artifact as follows from "StockService" directory

```
$ cf push
```

**manifest.yml**

```
---
applications:
- name: apples-stock-service
  memory: 512M
  instances: 1
  host: apples-stock-service-${random-word}
  path: ./target/StockService-0.0.1-SNAPSHOT.jar
  services:
    - apples-service-registery
```

- Push RibbonClient artifact as follows from "RibbonClient" directory

```
$ cf push
```

**manifest.yml**

```
---
applications:
- name: apples-ribbonclient
  memory: 512M
  instances: 1
  host: apples-ribbonclient-${random-word}
  path: ./target/RibbonClient-0.0.1-SNAPSHOT.jar
  env:
    JAVA_OPTS: -Djava.security.egd=file:///dev/urandom
    CF_TARGET: https://api.run.pivotal.io
  services:
    - apples-service-registery
    - apples-circuit-breaker-dashboard
```

<hr />

Pas Apicella [papicella at pivotal.io] is a Senior Platform Architect at Pivotal