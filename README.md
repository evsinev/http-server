# http-server

The simplest http server.

Features:
* no dependecies
* ability to run on Android
* small footprint

## How to add it into your app

### Maven


```xml
<repositories>
    <repository>
        <id>pne</id>
        <name>payneteasy repo</name>
        <url>https://maven.pne.io</url>
    </repository>
</repositories>
  
<dependency>
    <groupId>com.payneteasy.http-server</groupId>
    <artifactId>http-server-impl</artifactId>
    <version>1.0-10</version>
</dependency>
```

### Java code

```java
IHttpRequestHandler handler = aRequest -> HttpResponseBuilder.status(HttpResponseStatusLine.OK)
        .addHeader("Server", "Test")
        .body("Hello".getBytes())
        .build();

HttpServer server = new HttpServer(
        new InetSocketAddress(8081)
        , new HttpLoggerSystemOut()
        , Executors.newCachedThreadPool()
        , handler
        , 10_000
);
Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
server.acceptSocketAndWait();
```

