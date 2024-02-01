# Review EVENT

## 발행

### 리뷰 글 생성 이벤트

리뷰 글이 생성 된 이후 발행됩니다.

#### 내역

| 이름 | 타입 | 설명          |
| --- | --- |-------------|
| review_id | Long | 리뷰글의 고유 ID  |
| question_id | Long | 리뷰글이 달린 질문글의 고유 ID  |
| content | String | 리뷰글의 내용     |
| author | String | 리뷰글의 작성자    |
| author_id | Long | 리뷰글의 작성자 ID |
| created_at | LocalDateTime | 리뷰글의 작성일자   |
| updated_at | LocalDateTime | 리뷰글의 수정일자   |
| tag | String | 리뷰글의 태그   |
| event_at | LocalDateTime | 이벤트 발행 일자   |

### 리뷰 글 수정 이벤트

리뷰 글이 수정 된 이후 발행됩니다.

#### 내역

| 이름 | 타입 | 설명          |
| --- | --- |-------------|
| review_id | Long | 리뷰글의 고유 ID  |
| question_id | Long | 리뷰글이 달린 질문글의 고유 ID  |
| content | String | 리뷰글의 내용     |
| author | String | 리뷰글의 작성자    |
| author_id | Long | 리뷰글의 작성자 ID |
| updated_at | LocalDateTime | 리뷰글의 수정일자   |
| event_at | LocalDateTime | 이벤트 발행 일자   |

### 리뷰 글 삭제 이벤트

리뷰 글이 삭제 된 이후 발행합니다.

#### 내역

| 이름 | 타입 | 설명          |
| --- | --- |-------------|
| review_id | Long | 리뷰글의 고유 ID  |
| question_id | Long | 리뷰글이 달린 질문글의 고유 ID  |
| author | String | 리뷰글의 작성자    |
| author_id | Long | 리뷰글의 작성자 ID |
| event_at | LocalDateTime | 이벤트 발행 일자   |
