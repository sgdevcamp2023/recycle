# Question API

코드리뷰를 필요로 하는 질문 글의 작성, 임시저장, 조회, 목록 조회, 업데이트, 삭제기능을 제공합니다.

| API 종류 | 메서드 | URI | 설명 | 바로가기                                              |
| --- | --- | --- | --- |---------------------------------------------------|
| Questions | POST | /api/v1/questions | 질문 글 생성 | [링크](#질문-글-생성)                                    |
|  | POST | /api/v1/questions/temp | 질문 글 임시 저장 | [링크](#질문-글-임시-저장)                                 |
|  | GET | /api/v1/questions/{question_id} | 질문 글 조회 | [링크](#질문-글-조회)                                    |
|  | GET | /api/v1/me/questions | 멤버의 질문 글 목록 조회 | [링크](#멤버-기준-질문-글-목록-조회)                         |
|  | GET | /api/v1/me/questions/temp?t_id={temp_question_id:nullable} | 멤버의 임시 질문 글 목록 조회, 멤버의 임시 질문 글 조회, | [링크](#멤버의-임시-질문-글-목록-조회-멤버의-임시-질문-글-조회)         |
|  | GET | /api/v1/questions/search?me={boolean:false}&query={검색내용}&page={페이지수:nullable}&size={한 페이지에 담을수:nullable} | 멤버의 질문 글 목록에서 검색 | [링크](#멤버-기준-질문-글-목록에서-검색어를-포함한-내용을-가진-글-리스트-조회) |
|  | DELETE | /api/v1/questions/{question_id} | 질문 글 삭제 | [링크](#질문-글-삭제)                                  |
|  | GET | /api/v1/me/requests/reviews | 리뷰 요청 목록 조회 | [링크](#리뷰-요청-목록-조회)                              |
|  | GET | /api/v1/me/questions/reviewers?question_id={question_id:nullable} | 질문 글에 리뷰 단 리뷰어 목록 | [링크](#질문-글에-리뷰-단-리뷰어-목록)                        |

### Question 도메인

| 필드 | 타입 | 제약 조건 | 설명             |
| --- | --- | --- |----------------|
| question_id | Long | NOT NULL, PK | 질문 글 고유 번호     |
| content | String | NOT NULL | 질문 글 본문        |
| author | String | NOT NULL | 질문 글 작성자       |
| author_id | Long | NOT NULL | 질문 글 작성자의 id   |
| review_cnt | Int | NOT NULL | 질문 글에 달린 리뷰 갯수 |
| created_at | Timestamp | NOT NULL | 질문 글 작성일자      |
| updated_at | Timestamp |  | 질문 글 수정일자      |

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

## 질문 글 생성

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| POST | https://{SERVER_URL}/api/v1/questions |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 사용자 정보 로드 |
| Content-Type | String | NOT NULL | application/json |

**Request Parameter**

**Request Body**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| content | String | NOT NULL | 내용 |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                   | message    | 설명                  |
|---------------|-------------------------|------------|---------------------|
|       400     | request.content.invalid | 잘못된 요청입니다. | content가 없거나 잘못된 경우 |
| 401 | fail.authentication | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
---

## 질문 글 임시 저장

질문 글을 임시 저장합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| POST | https://{SERVER_URL}/api/v1/questions/temp |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 사용자 정보 로드 |
| Content-Type | String | NOT NULL | application/json |

**Request Elements**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| t_id | String | NOT NULL | 임시저장 식별을 위한 키값 |
| content | String | NOT NULL | 내용 |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                   | message    | 설명                  |
|---------------|-------------------------|------------|---------------------|
|       400     | request.content.invalid | 잘못된 요청입니다. | content가 없거나 잘못된 경우 |
| 400 | request.t_id.invalid | 잘못된 요청입니다. | t_id가 없거나 잘못된 경우 |
| 401 | fail.authentication | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
---

## 질문 글 조회

질문 글을 상세 조회합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| GET | https://{SERVER_URL}/api/v1/questions/{question_id} |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| question_id | Long | NOT NULL | 조회 할 질문 글의 id  |

### Response

**Response Elements**

| 필드 | 타입 | 제약 조건 | 설명             |
| --- | --- | --- |----------------|
| question_id | Long | NOT NULL, PK | 질문 글 고유 번호     |
| content | String | NOT NULL | 질문 글 본문        |
| author | String | NOT NULL | 질문 글 작성자       |
| author_id | Long | NOT NULL | 질문 글 작성자의 id   |
| review_cnt | Int | NOT NULL | 질문 글에 달린 리뷰 갯수 |
| created_at | Timestamp | NOT NULL | 질문 글 작성일자      |
| updated_at | Timestamp |  | 질문 글 수정일자      |

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                       | message    | 설명                           |
|---------------|-----------------------------|------------|------------------------------|
|       400     | request.question_id.invalid | 잘못된 요청입니다. | question_id가 없거나 잘못된 경우      |
| 401 | fail.authentication         | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |

---

## 멤버 기준 질문 글 목록 조회

멤버를 기준으로 질문 글의 목록을 조회합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| GET | https://{SERVER_URL}/api/v1/me/questions&page={페이지수}&size={한페이지에담을수} |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**Request Elements**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| page | int | NOT NULL | 페이지 번호 |
| size | int | NOT NULL | 한 페이지에 담을 질문 글 수 |

### Response

**Response Elements**

| 필드                 | 타입                 | 제약 조건 | 설명                    |
|--------------------|--------------------| --- |-----------------------|
| QuestionResponse[] | QuestionResponse[] |  | 멤버를 기준으로 검색한 질문 글의 목록 |

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                | message    | 설명                           |
|---------------|----------------------|------------|------------------------------|
|       400     | request.page.invalid | 잘못된 요청입니다. | page가 없거나 잘못된 경우             |
|       400     | request.size.invalid | 잘못된 요청입니다. | size가 없거나 잘못된 경우             |
| 401 | fail.authentication  | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |



---

## 멤버의 임시 질문 글 목록 조회, 멤버의 임시 질문 글 조회

임시 저장한 질문 글의 목록, 글을 조회합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| GET | https://{SERVER_URL}/api/v1/me/questions/temp?t_id={temp_question_id:nullable}&page={페이지수:nullable}&size={한 페이지에 담을수:nullable} |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**Request Elements**

| 파라미터 | 타입 | 제약 조건 | 설명                                                 |
|------| --- | --- |----------------------------------------------------|
| t_id | string | NOT NULL | 조회 할 임시저장된 질문 글의 id, null이면 목록 조회하고 값이 있으면 해당 글 조회 |
| page | int |  | 페이지 번호, t_id가 있으면 페이지 없음                           |
| size | int |  | 한 페이지에 담을 질문 글 수, t_id가 있으면 페이지 없음                 |

### Response

**Response Elements**

| 구분                 | 필드                 | 타입                 | 제약 조건    | 설명              |
|--------------------|--------------------|--------------------|----------|-----------------|
| QuestionResponse[] | QuestionResponse[] | QuestionResponse[] |          | 임시 저장한 질문 글의 목록 |
| QuestionResponse   | t_id               | String             | NOT NULL | 식별을 위한 키값       |
|                    | content            | String             | NOT NULL | 내용              |
|                    | author             | String             | NOT NULL | 작성자             |
|                    | author_id          | Long               | NOT NULL | 작성자의 id         |
|                    | created_at         | timestamp          | NOT NULL | 만들어진 시간         |

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                | message    | 설명                          |
|---------------|----------------------|------------|-----------------------------|
|       400     | request.t_id.invalid | 잘못된 요청입니다. | t_id가 없거나 잘못된 경우            |
|       400     | request.page.invalid | 잘못된 요청입니다. | page가 잘못된 경우                |
|       400     | request.size.invalid | 잘못된 요청입니다. | size가 잘못된 경우             |
| 401 | fail.authentication  | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |


---

## 멤버 기준 질문 글 목록에서 ‘검색어’를 포함한 내용을 가진 글 리스트 조회

멤버를 기준으로 질문 글 목록에서 ‘검색어’를 포함한 내용을 가진 글의 리스트를 조회합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| GET | https://{SERVER_URL/api/v1/me/questions?query={검색내용}&page={페이지수}&size={한페이지에담을수} |

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

| 필드                 | 타입                 | 제약 조건 | 설명                                       |
|--------------------|--------------------|-------|------------------------------------------|
| QuestionResponse[] | QuestionResponse[] |       | 멤버 기준 질문 글 목록에서 ‘검색어’를 포함한 내용을 가진 글의 리스트 |

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                 | message    | 설명                           |
|------------------|-----------------------|------------|------------------------------|
| 400              | request.query.invalid | 잘못된 요청입니다. | query가 없거나 잘못된 경우            |
| 400              | request.page.invalid  | 잘못된 요청입니다. | page가 없거나 잘못된 경우             |
| 400              | request.size.invalid  | 잘못된 요청입니다. | size가 없거나 잘못된 경우             |
| 401              | fail.authentication   | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |

---

## 질문 글 삭제

해당 질문 글을 삭제합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| DELETE | https://{SERVER_URL}/api/v1/questions/{question_id} |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| question_id | Long | NOT NULL | 삭제 할 질문 글의 id  |

### Response

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                       | message    | 설명                           |
|---------------|-----------------------------|------------|------------------------------|
|       400     | request.question_id.invalid | 잘못된 요청입니다. | question_id가 없거나 잘못된 경우      |
| 401 | fail.authentication         | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
| 403 | access.denied | 접근 권한이 없습니다. | 사용자가 권한이 없는 요청을 시도한 경우       |
| 404 | resource.notfound | 요청에 대한 응답을 찾을 수 없습니다. | 삭제 하려는 게시글이 이미 없을 경우        |


---

## 리뷰 요청 목록 조회

요청 받은 리뷰 목록을 조회합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| GET | https://{SERVER_URL}/api/v1/me/requests/reviews |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

### Response

**Response Elements**

| 파라미터             | 타입                 | 제약 조건 | 설명              |
|------------------|--------------------|-------|-----------------|
| QuestionResponse | QuestionResponse[] |       | 멤버가 요청 받은 리뷰 목록 |

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                | message    | 설명                           |
|---------------|----------------------|------------|------------------------------|
| 401 | fail.authentication  | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |


---

## 질문 글에 리뷰 단 리뷰어 목록

해당 질문 글에 리뷰를 단 리뷰어들의 목록을 조회합니다.

### Request

**Request Syntax**

| 매서드 | 요청 URL |
| --- | --- |
| GET | https://{SERVER_URL}/api/questions/{question_id}/reviewers |

**Request Header**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| Authorization | String | NOT NULL | JWT 인증 키, 토큰을 통해 인가 요청 |

**PathVariable**

| 파라미터 | 타입 | 제약 조건 | 설명 |
| --- | --- | --- | --- |
| question_id | Long | NOT NULL | 조회 할 질문 글의 id  |

### Response

**Response Elements**

| 구분               | 필드               | 타입      | 제약 조건    | 설명                       |
|------------------|------------------|---------|----------|--------------------------|
| MemberResponse[] | MemberResponse[] | user[]  | NOT NULL | 해당 질문 글에 리뷰를 단 리뷰어들의 목록  |
| MemberResponse   | question_id      | Long    | NOT NULL | 질문 글의 고유 id              |
|                  | author_id        | Long    | NOT NULL | 리뷰어의 고유 id               |
|                  | author           | String  | NOT NULL | 리뷰어의 이름                  |

**HTTP status code**

| HTTP status code | error | message | 설명 |
| --- | --- | --- | --- |
| 200 | X | OK | 성공 |

### Exception
| HTTP status code | error                | message    | 설명                           |
|---------------|----------------------|------------|------------------------------|
|      400     | request.question_id.invalid | 잘못된 요청입니다. | question_id가 없거나 잘못된 경우      |
| 401 | fail.authentication  | 인증이 필요합니다. | 로그인이 되어있지 않은 경우 / 토큰이 만료된 경우 |
