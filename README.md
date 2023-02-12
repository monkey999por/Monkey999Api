# What is

APIいろいろ  
例えば[translation](https://github.com/monkey999por/translation)で使用するAPI

## Require

- java 17 (or more)
- maven 3.8.6 (or more)

## Start

## For apache maven

```
mvn install
mvn spring-boot:run
```

### For intellj

```
上部ツールバーから実行 -> run main
```

### For java

実行可能jarを作成 ※デプロイするときもこれで作ったjar(war)を使う

```shell
mvn package spring-boot:repackage
```

```shell
java -Dserver.port=8011 -jar ./target/Monkey999Api-0.0.1-SNAPSHOT.jar
```

java(apprication.properties切り替え方法)

```shell
java -Dserver.port=8011 -jar ./target/Monkey999Api-0.0.1-SNAPSHOT.jar --spring.profiles.active=pro
```

## Debug

1. コマンドラインからデバッグ起動

```
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar .\target\TranslationApi-0.0.1-SNAPSHOT.jar
```

2. intelljで設定したリモートデバッグを起動  
   参考：<https://reasonable-code.com/intellij-remote-debug/>

※↑をやらなくてもintelljで普通にデバッグでもできるっぽい？

## 動作確認

- bash

 ```
 curl -X POST http://{HOST}:8080/v1/translate \
  -H "Content-Type: application/json" \
  -d '{"text": "Hello world", "source": "en", "target":"ja","translation_client": "google", "certification":{"api_key": "your_api_key"}}'
 ```

## API仕様

 http:{HOST}:8080/swagger-ui/index.html
