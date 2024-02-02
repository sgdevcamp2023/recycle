package com.zzaug.review.domain.model.question;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionRequest {
	private Long questionReqId;
	private String requester;
	private Long requesterId;
}
