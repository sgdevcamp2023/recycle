# Comment API

리뷰어가 남긴 댓글 코멘트 및 대댓글 코멘트를 저장, 수정, 삭제 및 조회 기능을 제공합니다.

| API 종류 | 메서드 | URI | 설명 |
| --- | --- | --- | --- |
| Comments | POST | /api/quetions/{quetionId}/comments | 댓글 코멘트 생성 |
|  | GET | /api/quetions/{quetionId}/comments | 질문 글 기준 댓글 코멘트 조회 |
|  | PUT | /api/quetions/{quetionId}/comments/{commentId} | 댓글 코멘트 수정 |
|  | DELETE | /api/quetions/{quetionId}/comments/{commentId} | 댓글 코멘트 삭제 |

Comment 도메인

| 필드         | 타입        | 제약 조건        | 설명                                                |
|------------|-----------|--------------|---------------------------------------------------|
| commentId | Long      | NOT NULL, PK | 댓글 코멘트 고유 번호                                      |
| quetionId | Long      | NOT NULL     | 댓글이 달린 질문 글의 id                                   |
| content    | String    | NOT NULL     | 댓글 코멘트 본문                                         |
| author     | String    | NOT NULL     | 댓글 코멘트 작성자                                        |
| authorId  | Long      | NOT NULL     | 댓글 코멘트 작성자 id                                     |
| parentId  | Long      | NOT NULL     | 대댓글의 부모 댓글 고유번호 0일경우 댓글 0 이외의 양수 일 경우 해당 댓글의 대댓글  |
| createdAt | Timestamp | NOT NULL     | 댓글 코멘트 작성일자                                       |
| updatedAt | Timestamp |              |                                                   |

### Exception

Error code

| HTTP status code | code | message |  상황 |
| --- | --- | --- | --- |
| 400 | request.{파라미터}.invalid | 잘못된 요청입니다. | 필수 파라미터를 누락, 또는 잘못된 파라 미터를 요청으로 보냈을 경우 |
| 401 | fail.authentication | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
| 403 | access.denied | 접근 권한이 없습니다. | 사용자가 권한이 없는 요청을 시도한 경우 |
| 404 | resource.notfound | 요청에 대한 응답을 찾을 수 없습니다. | 잘못된 요청을 요청한 경우 (API 없음) |
| 404 | fail.notfound | 일치하는 결과를 찾을 수 없습니다. | 서버 내부에 처리할 코드가 없는경우 |
| 500 | fail | 알 수 없는 오류가 발생했습니다. | 서버에 문제가 발생한 경우, 필수 파라미터를 누락하고 요청을 보냈을 경우도 뜰 수 있음 |

---

## 댓글 생성

댓글 코멘트를 생성합니다.

### Request

**Request Syntax**

| 매서드  | 요청 URL                                                  |
|------|---------------------------------------------------------|
| POST | https://{SERVER_URL}/api/quetions/{quetionId}/comments |

**Request Header**

| 파라미터          | 타입     | 제약 조건    | 설명                         |
|---------------|--------|----------|----------------------------|
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 사용자 정보 로드 |
| Content-Type  | String | NOT NULL | application/json           |

**PathVariable**

| 파라미터       | 타입   | 제약 조건    | 설명                    |
|------------|------|----------|-----------------------|
| quetionId | Long | NOT NULL | 댓글 코멘트를 생성 할 질문 글의 id |

**Request Elements**

| 파라미터                             | 타입     | 제약 조건    | 설명                                               |
|----------------------------------|--------|----------|--------------------------------------------------|
| parentId                        | Long   | NOT NULL | 대댓글의 부모 댓글 고유번호 0일경우 댓글 0 이외의 양수 일 경우 해당 댓글의 대댓글 |
| content                          | String | NOT NULL | 내용                                               |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                | message    | 설명                           |
|---------------|----------------------|------------|------------------------------|
| 400 | request.quetionId.invalid | 잘못된 요청입니다. | 질문 글이 존재하지 않는 경우             | 
| 400 | request.parentId.invalid | 잘못된 요청입니다. | 부모 댓글이 존재하지 않는 경우            |
| 400 | request.content.invalid | 잘못된 요청입니다. | 내용이 없는 경우                    |
| 401 | fail.authentication  | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |


---

### 질문 글 기준 댓글 코멘트 조회

질문 글을 기준으로 댓글 코멘트를 조회합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| GET | https://{SERVER_URL}/api/quetions/{quetionId}/comments |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| quetionId | Long | NOT NULL | 조회 할 댓글 코멘트가 달린 질문 글의 id  |

### Response

**Response Elements**

| 필드                | 타입                | 제약 조건 | 설명                   |
|-------------------|-------------------|-------|----------------------|
| CommentResponse[] | CommentResponse[] |       | 질문 글을 기준으로 검색한 리뷰 목록 |

**HTTP status code**

| HTTP status code | error | message | 설명 |
|------------------|-------|---------| --- |
| 200              | X     | OK      | 성공 |

### Exception
| HTTP status code | error                | message    | 설명                           |
|---------------|----------------------|------------|------------------------------|
| 400 | request.quetionId.invalid | 잘못된 요청입니다. | 질문 글이 존재하지 않는 경우             |
| 401 | fail.authentication  | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |


---

### 댓글 코멘트 수정

해당 댓글 코멘트를 수정합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| PUT | https://{SERVER_URL}/api/quetions/{quetionId}/comments/{commentId} |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| quetionId | Long | NOT NULL | 수정 할 댓글 코멘트가 달린 질문 글의 id  |
| commentId | Long | NOT NULL | 수정 할 댓글 코멘트의 id |

**Request Elements**

| 파라미터      | 타입     | 제약 조건    | 설명                                               |
|-----------|--------|----------|--------------------------------------------------|
| parentId | Long   | NOT NULL | 대댓글의 부모 댓글 고유번호 0일경우 댓글 0 이외의 양수 일 경우 해당 댓글의 대댓글 |
| content   | String | NOT NULL | 내용                                               |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                | message    | 설명                           |
|---------------|----------------------|------------|------------------------------|
| 400 | request.quetionId.invalid | 잘못된 요청입니다. | 질문 글이 존재하지 않는 경우             | 
| 400 | request.commentId.invalid | 잘못된 요청입니다. | 댓글 코멘트가 존재하지 않는 경우           |
| 400 | request.content.invalid | 잘못된 요청입니다. | 내용이 없는 경우                    |
| 401 | fail.authentication  | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |

---

### 댓글 코멘트 삭제

해당 댓글 코멘트를 삭제합니다.

### Request

**Request Syntax**

| 매서드    | 요청 URL                                                               |
|--------|----------------------------------------------------------------------|
| DELETE | https://{SERVER_URL}/api/quetions/{quetionId}/comments/{commentId} |

**Request Header**

| 파라미터          | 타입     | 제약 조건    | 설명                     |
|---------------|--------|----------|------------------------|
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터       | 타입   | 제약 조건    | 설명                       |
|------------|------|----------|--------------------------|
| quetionId | Long | NOT NULL | 삭제 할 댓글 코멘트가 달린 질문 글의 id |
| commentId | Long | NOT NULL | 삭제 할 댓글 코멘트의 id          |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
|------------------|-------|---------|----|
| 200              | X     | OK      | 성공 |

### Exception
| HTTP status code | error                | message    | 설명                           |
|---------------|----------------------|------------|------------------------------|
| 400 | request.quetionId.invalid | 잘못된 요청입니다. | 질문 글이 존재하지 않는 경우             |
| 400 | request.commentId.invalid | 잘못된 요청입니다. | 댓글 코멘트가 존재하지 않는 경우           |
| 401 | fail.authentication  | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
