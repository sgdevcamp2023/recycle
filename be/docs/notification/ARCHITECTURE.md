# Notification ARCHITECTURE

알림 아키텍처를 설명하기 전 알림 서비스에서 제공해야 하는 기능을 정리합니다.

## 기능

- 알림은 멤버에게 전달 할 수 있는 수단을 등록한 멤버에게만 전달합니다. ex) 이메일, 깃허브
- 사이트에 접속하지 않은 멤버에게 알림을 전송합니다.
- 전송한 알림 목록을 제공합니다.

## 1차

```
                            __________________                              _____________________
Commnet event (Reids) <--> |                  |                            |                     |
                           | notification-api | <--> queue (RabbitMQ) <--> | notification-sender |
Member event (Redis) <-->  |                  |                            |                     |
                           |__________________|                            |_____________________|
```

새로운 코멘트 이벤트가 발생하면 Redis 에 저장합니다.

사이트에 접속한 멤버 여부를 알기 위해 멤버 관련된 이벤트를 수신하고 이를 Redis 에 저장합니다.

1차 알림 아키텍처는 새로운 코멘트와 멤버의 접속 여부를 통한 알림 전송 대상 필터링 및 전송 내용 생성을 notification-api에서 담당합니다.

이후 생성된 내용을 RabbitMQ 를 통해 notification-sender 에 전달합니다.

## 2차

```
                            __________________                              _____________________
Commnet event (Reids) <--> |                  |                            |                     |
                           | notification-api | <--> queue (RabbitMQ) <--> | notification-worker |
Member event (Redis) <-->  |                  |                            |                     |
                           |__________________|                            |_____________________|
                                    |                                                |
                                    |                                                |
                                  queue (RabbitMQ)                                 queue (RabbitMQ)
                                    v                                                v
                                    __________________________________________________
                                   |                                                  |
                                   |                notification-sender               |
                                   |__________________________________________________|

```

2차 아키텍처는 notification-api가 하는 일을 단순화하는 것에 초점을 맞추었습니다.

단순한 알림 요청의 경우 notification-api 에서 바로 notification-sender 로 전달합니다.

사이트 접속 중인 멤버를 필터링 한 이후 전달하는 알림과 같은 복잡한 로직이 필요한 경우 notification-worker 를 통해 전달합니다.

이는 notification-api 에서 복잡한 로직을 처리하지 않고 notification-worker 에서 처리하도록 하여 notification-api 의 부하를 줄이기
위함입니다.

또한 notification-worker 를 여러 개 띄워서 병렬로 처리할 수 있도록 하여 처리 속도를 높일 수도 있습니다.
