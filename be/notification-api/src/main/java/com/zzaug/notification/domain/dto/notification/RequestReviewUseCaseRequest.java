package com.zzaug.notification.domain.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RequestReviewUseCaseRequest {

	private Long memberId;
	private Long questionId;
	private Long requestMemberId;
}
