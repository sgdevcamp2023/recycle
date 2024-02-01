# Question EVENT

## 발행

### 질문 글 생성 이벤트

질문 글이 생성 된 이후 발행됩니다.

#### 내역

| 이름 | 타입 | 설명          |
| --- | --- |-------------|
| question_id | Long | 질문글의 고유 ID  |
| content | String | 질문글의 내용     |
| author | String | 질문글의 작성자    |
| author_id | Long | 질문글의 작성자 ID |
| review_cnt | Int  | 질문글의 리뷰 수   |
| created_at | LocalDateTime | 질문글의 작성일자   |
| updated_at | LocalDateTime | 질문글의 수정일자   |
| event_at | LocalDateTime | 이벤트 발행 일자   |

### 질문 글 수정 이벤트

질문 글이 수정 된 이후 발행됩니다.

#### 내역

| 이름 | 타입 | 설명          |
| --- | --- |-------------|
| question_id | Long | 질문글의 고유 ID  |
| content | String | 질문글의 내용     |
| author | String | 질문글의 작성자    |
| author_id | Long | 질문글의 작성자 ID |
| updated_at | LocalDateTime | 질문글의 수정일자   |
| event_at | LocalDateTime | 이벤트 발행 일자   |

### 질문 글 삭제 이벤트

질문 글이 삭제 된 이후 발행합니다.

#### 내역

| 이름 | 타입 | 설명          |
| --- | --- |-------------|
| question_id | Long | 질문글의 고유 ID  |
| author | String | 질문글의 작성자    |
| author_id | Long | 질문글의 작성자 ID |
| event_at | LocalDateTime | 이벤트 발행 일자   |
