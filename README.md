### What is

APIいろいろ  
例えば[translation](https://github.com/monkey999por/translation)で使用するAPI

### require

- java 17 (or more)
- maven 3.8.6 (or more)

## 起動

mvn

```
mvn install
mvn spring-boot:run
```

intellj

```
上部ツールバーから実行 -> run main
```

java

```
java -Dserver.port=8011 -jar ./target/Monkey999Api-0.0.1-SNAPSHOT.jar
```

java(pro)

```
java -Dserver.port=8011 -jar ./target/Monkey999Api-0.0.1-SNAPSHOT.jar --spring.profiles.active=pro
```

## デバッグ

1. 実行可能jarを作成

```
mvn package spring-boot:repackage
```

2. コマンドラインからデバッグ起動

```
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar .\target\TranslationApi-0.0.1-SNAPSHOT.jar
```

3. intelljで設定したリモートデバッグを起動  
参考：<https://reasonable-code.com/intellij-remote-debug/>

### 動作確認

- windows cmd.exe

 ```
 curl -X POST http://localhost:8080/test -H "Content-Type: application/json" -d "{\"text\": \"Hello\", \"source\": \"en\" , \"translateReq\": {}}"
 ```

- bash

 ```
 curl -X POST http://{HOST}:8080/translate \
  -H "Content-Type: application/json" \
  -d '{"text": "Hello world", "source": "en", "translationClient": "google"}'
 ```
