# pubsub-sandbox
sandbox project using pub-sub model by [RabbitMQ](https://www.rabbitmq.com/)

3つのキューに1producerからメッセージを送信して、3つのconsumerで受信

### Docker
```
cd docker && docker-compose up -d
```

```
cd docker && docker-compose down
```

### マルチプロジェクト化の参考資料
https://radiochemical.hatenablog.com/entry/2019/09/08/164542

### TODO
#### 調査
- policy
_ exchange(direct/fanout/topic)

### RabbitMq
* [AMQPによるメッセージング](https://labs.gree.jp/blog/2010/06/262/) -> 古い情報だが用語の説明などがされている
* [RabbitMQ でメッセージの送受信を行うまでの環境設定についてのメモ](https://qiita.com/ptiringo/items/c554fa66f0d985394fed) -> queueやbindingの説明など 