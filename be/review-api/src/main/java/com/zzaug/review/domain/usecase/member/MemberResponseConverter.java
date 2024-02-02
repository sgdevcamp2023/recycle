package com.zzaug.review.domain.usecase.member;

import com.zzaug.review.domain.dto.member.MemberResponse;
import com.zzaug.review.entity.member.ReviewerListEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberResponseConverter {

	public static MemberResponse from(ReviewerListEntity source) {
		return MemberResponse.builder()
				.reviewerId(source.getReviewerId())
				.reviewerName(source.getReviewerName())
				.build();
	}
}
