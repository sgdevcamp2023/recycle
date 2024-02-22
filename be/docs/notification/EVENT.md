# Notification EVENT

## 발행

### 리뷰 요청 이벤트

다른 멤버에게 리뷰를 요청한 경우 발행됩니다.

#### 내역

| 이름                | 타입            | 설명             |
|-------------------|---------------|----------------|
| question_id       | Long          | 요청할 질문글 id     |
| question_owner    | Long          | 질문글 주인 멤버 id   |
| request_member_id | Long          | 리뷰 요청할 멤버 id   |
| event_at          | LocalDateTime | 회원가입 이벤트 발행 일자 |
