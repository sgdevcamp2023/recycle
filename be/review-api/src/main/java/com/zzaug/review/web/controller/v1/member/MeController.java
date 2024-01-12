package com.zzaug.review.web.controller.v1.member;

import com.zzaug.review.domain.dto.member.MemberResponse;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/me")
@RequiredArgsConstructor
public class MeController {

	@GetMapping("/questions/reviewers")
	public ApiResponse<ApiResponse.SuccessBody<List<MemberResponse>>> viewReviewerList(
			@AuthenticationPrincipal TokenUserDetails userDetails, @RequestParam Long question_id) {

		List<MemberResponse> res = new ArrayList<>();
		MemberResponse element =
				MemberResponse.builder().question_id(1L).author("author").author_id(1L).build();
		res.add(element);
		return ApiResponseGenerator.success(res, HttpStatus.OK);
	}
}
