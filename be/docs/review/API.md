# Review API

리뷰어가 남긴 리뷰를 저장, 임시 저장, 수정, 삭제 및 목록 조회 기능을 제공합니다.

| API 종류 | 메서드    | URI | 설명 | 바로가기         |
| --- |--------| --- | --- |--------------|
| Reviews | POST   | /api/v1/quetions/{quetion_id}/reviews | 리뷰 생성  | [링크](#리뷰-생성) |
|  |        | /api/v1/quetions/{quetion_id}/reviews/temp | 리뷰 임시 저장 | [링크](#리뷰-임시저장) |
|  | GET    | /api/v1/me/reviews | 멤버가 작성한 리뷰 목록 조회 | [링크](#멤버-기준-리뷰-목록-조회) |
|  | GET    | /api/v1/quetions/{quetion_id}/reviews | 질문 글 기준 리뷰 목록 조회 | [링크](#질문-글-기준-리뷰-목록-조회) |
|  | GET    | /api/v1/quetions/{quetion_id}/reviews/temp?t_id={temp_question_id:nullable} | 질문 글 기준 임시 리뷰 목록 조회 | [링크](#질문-글-기준-임시-리뷰-목록-리뷰-조회) |
|  | GET    | /api/v1/reviews/search?me={boolean:false}&query={검색내용}&page={페이지수:nullable}&size={한 페이지에 담을수:nullable} | 멤버가 질문글에 작성한 리뷰 목록에서 검색 | [링크](#멤버가-질문글에-작성한-리뷰-목록에서-검색) |
|  | PUT    | /api/v1/quetions/{quetion_id}/reviews/{review_id} | 리뷰 수정 | [링크](#리뷰-수정) |
|  | DELETE | /api/v1/quetions/{quetion_id}/reviews/{review_id} | 리뷰 삭제 | [링크](#리뷰-삭제) |

Review 도메인

| 필드 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| review_id | Long | NOT NULL, PK | 리뷰 고유 번호 |
| quetion_id | Long | NOT NULL | 리뷰가 달려있는 포스트 id |
| content | String | NOT NULL | 리뷰 본문 |
| author | String | NOT NULL | 리뷰 작성자 |
| author_id | Long | NOT NULL | 리뷰 작성자 id |
| location | String | NOT NULL | 리뷰가 달린 위치 |
| created_at | Timestamp | NOT NULL | 리뷰 작성일자 |
| updated_at | Timestamp |  | 리뷰 수정일자 |
| tag | String | NOT NULL | 라인 리뷰와 코드 리뷰를 구분하는 태그  |

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

## 리뷰 생성

리뷰를 생성합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| POST | https://{SERVER_URL}/api/v1/quetions/{quetion_id}/reviews |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 사용자 정보 로드 |
| Content-Type | String | NOT NULL | application/json |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| quetion_id | Long | NOT NULL | 리뷰를 조회 할 질문 글의 id  |

**Request Elements**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| content | String | NOT NULL | 내용 |
| location | String | NOT NULL | 리뷰가 달린 위치 |
| tag | String | NOT NULL | 라인 리뷰와 코드 리뷰를 구분하는 태그  |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                   | message    | 설명                  |
|---------------|-------------------------|------------|---------------------|
| 400 | request.quetion_id.invalid | 잘못된 요청입니다. | quetion_id가 없거나 잘못된 경우 |
|       400     | request.content.invalid | 잘못된 요청입니다. | content가 없거나 잘못된 경우 |
|       400     | request.location.invalid | 잘못된 요청입니다. | location이 없거나 잘못된 경우 |
|       400     | request.tag.invalid | 잘못된 요청입니다. | tag가 없거나 잘못된 경우 |
| 401 | fail.authentication | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
---

## 리뷰 임시저장

리뷰를 임시 저장합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| POST | https://{SERVER_URL}/api/v1/quetions/{quetion_id}/reviews/temp |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 사용자 정보 로드 |
| Content-Type | String | NOT NULL | application/json |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| quetion_id | Long | NOT NULL | 리뷰를 조회 할 질문 글의 id  |

**Request Elements**

| 파라미터     | 타입 | 제약 조건 | 설명 |
|----------| --- | --- | --- |
| t_id     | String | NOT NULL | 식별을 위한 키값 |
| content  | String | NOT NULL | 내용 |
| location | String | NOT NULL | 리뷰가 달린 위치 |
| tag      | String | NOT NULL | 라인 리뷰와 코드 리뷰를 구분하는 태그  |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                   | message    | 설명                  |
|---------------|-------------------------|------------|---------------------|
| 400 | request.quetion_id.invalid | 잘못된 요청입니다. | quetion_id가 없거나 잘못된 경우 |
| 400 | request.t_id.invalid | 잘못된 요청입니다. | t_id가 없거나 잘못된 경우 |
|       400     | request.content.invalid | 잘못된 요청입니다. | content가 없거나 잘못된 경우 |
|       400     | request.location.invalid | 잘못된 요청입니다. | location이 없거나 잘못된 경우 |
|       400     | request.tag.invalid | 잘못된 요청입니다. | tag가 없거나 잘못된 경우 |
| 401 | fail.authentication | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
---

### 멤버 기준 리뷰 목록 조회

멤버를 기준으로 리뷰의 목록을 조회합니다.

### Request

**Request Syntax**

| 매서드  | 요청 URL                                 |
|------|----------------------------------------|
| GET  | https://{SERVER_URL}/api/v1/me/reviews |

**Request Header**

| 파라미터          | 타입     | 제약 조건    | 설명                     |
|---------------|--------|----------|------------------------|
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

### Response

**Response Elements**

| 필드               | 타입               | 제약 조건 | 설명                  |
|------------------|------------------|-------|---------------------|
| ReviewResponse[] | ReviewResponse[] |       | 멤버를 기준으로 검색한 리뷰의 목록 |

**HTTP status code**

| HTTP status code | error | message | 설명 |
|------------------|-------|---------|---|
| 200              | X     | OK      | 성공 |

### Exception
| HTTP status code | error                    | message     | 설명                           |
|------------------|--------------------------|-------------|------------------------------|
| 401              | fail.authentication      | 인증이 필요합니다.  | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
---

### 질문 글 기준 리뷰 목록 조회

질문 글을 기준으로 리뷰 목록을 조회합니다.

### Request

**Request Syntax**

| 매서드  | 요청 URL                                                    |
|------|-----------------------------------------------------------|
| GET  | https://{SERVER_URL}/api/v1/quetions/{quetion_id}/reviews |

**Request Header**

| 파라미터          | 타입     | 제약 조건    | 설명                     |
|---------------|--------|----------|------------------------|
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터      | 타입   | 제약 조건    | 설명            |
|-----------|------|----------|---------------|
| quetion_id | Long | NOT NULL | 조회 할 질문 글의 id |

### Response

**Response Elements**

| 필드               | 타입               | 제약 조건 | 설명                   |
|------------------|------------------|-------|----------------------|
| ReviewResponse[] | ReviewResponse[] |       | 질문 글을 기준으로 검색한 리뷰 목록 |

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                      | message     | 설명                           |
|------------------|----------------------------|-------------|------------------------------|
| 400              | request.quetion_id.invalid | 잘못된 요청입니다.  | quetion_id가 없거나 잘못된 경우       |
| 401              | fail.authentication        | 인증이 필요합니다.  | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
---

### 질문 글 기준 임시 리뷰 목록, 리뷰 조회

임시 저장된 리뷰 목록 또는 리뷰를 조회합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| GET | https://{SERVER_URL}/api/v1/quetions/{quetion_id}/reviews/temp?t_id={temp_question_id:nullable} |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명                                                 |
| --- | --- | --- |----------------------------------------------------|
| quetion_id | Long | NOT NULL | 조회 할 질문 글의 id  |
| t_id | string |  | 조회 할 임시저장된 질문 글의 id, null이면 목록 조회하고 값이 있으면 해당 글 조회 |

### Response

**Response Elements**

| 구분                           | 필드                   | 타입                   | 제약 조건    | 설명                                                |
|------------------------------|----------------------|----------------------|----------|---------------------------------------------------|
| ReviewTempResponse[]         | ReviewTempResponse[] | ReviewTempResponse[] |          | temp_question의 리스트                                |
| ReviewTempResponse                | t_id                 | String               | NOT NULL | 조회 할 임시저장된 질문 글의 id, null이면 목록 조회, 값이 있으면 해당 글 조회 |
|                             | question_id          | Long                 | NOT NULL | 내용                                                |
|                              | content              | String               | NOT NULL | 내용                                                |
|                              | tag                  | String               | NOT NULL | 라인 리뷰와 코드 리뷰를 구분하는 태그                             |
|                              | created_at           | timestamp            | NOT NULL | 만들어진 시간                                           |

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                   | message    | 설명              |
|---------------|-------------------------|------------|-----------------|
| 400 | request.quetion_id.invalid | 잘못된 요청입니다. | quetion_id가 없거나 잘못된 경우 |
| 400 | request.t_id.invalid | 잘못된 요청입니다. | t_id가 잘못된 경우 |
| 401 | fail.authentication | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |

---

### 멤버가 질문글에 작성한 리뷰 목록에서 검색

멤버 기준 리뷰 목록에서 리뷰에 ‘검색어’를 포함한 내용을 가진 글의 리스트를 조회합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| GET | https://{SERVER_URL}/api/v1/me/reviews/search?query={검색내용}&page={페이지수:nullable}&size={한 페이지에 담을수:nullable} |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**Request Elements**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| query | string | NOT NULL | 검색어 |
| page | int | NOT NULL | 페이지 번호 |
| size | int | NOT NULL | 한 페이지에 담을 질문 글 수 |

### Response

**Response Elements**

| 필드               | 타입               | 제약 조건 | 설명                                          |
|------------------|------------------|-------|---------------------------------------------|
| ReviewResponse[] | ReviewResponse[] |       | 멤버를 기준으로 질문 글 목록에서 ‘검색어’를 포함한 내용을 가진 글의 리스트 |

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                   | message    | 설명              |
|---------------|-------------------------|------------|-----------------|
| 400 | request.query.invalid | 잘못된 요청입니다. | query가 없거나 잘못된 경우 |
| 400 | request.page.invalid | 잘못된 요청입니다. | page가 없거나 잘못된 경우 |
| 400 | request.size.invalid | 잘못된 요청입니다. | size가 없거나 잘못된 경우 |
| 401 | fail.authentication | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |

---

### 리뷰 수정

해당 리뷰를 수정합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| PUT | https://{SERVER_URL}/api/v1/quetions/{quetion_id}/reviews/{review_id} |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| quetion_id | Long | NOT NULL | 수정 할 리뷰가 달린 질문 글의 id  |
| review_id | Long | NOT NULL | 수정 할 리뷰의 id |

**Request Elements**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| content | String | NOT NULL | 내용 |
| location | String | NOT NULL | 리뷰가 달린 위치 |
| tag | String | NOT NULL | 라인 리뷰와 코드 리뷰를 구분하는 태그  |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                   | message    | 설명              |
|---------------|-------------------------|------------|-----------------|
| 400 | request.quetion_id.invalid | 잘못된 요청입니다. | quetion_id가 없거나 잘못된 경우 |
| 400 | request.review_id.invalid | 잘못된 요청입니다. | review_id가 없거나 잘못된 경우 |
| 400 | request.content.invalid | 잘못된 요청입니다. | content가 없거나 잘못된 경우 |
| 401 | fail.authentication | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |

---

### 리뷰 삭제

해당 리뷰를 삭제합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| DELETE | https://{SERVER_URL}/api/v1/quetions/{quetion_id}/reviews/{review_id} |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| quetion_id | Long | NOT NULL | 삭제 할 리뷰가 달린 질문 글의 id  |
| review_id | Long | NOT NULL | 삭제 할 리뷰의 id |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                   | message    | 설명              |
|---------------|-------------------------|------------|-----------------|
| 400 | request.quetion_id.invalid | 잘못된 요청입니다. | quetion_id가 없거나 잘못된 경우 |
| 400 | request.review_id.invalid | 잘못된 요청입니다. | review_id가 없거나 잘못된 경우 |
| 401 | fail.authentication | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |


---
