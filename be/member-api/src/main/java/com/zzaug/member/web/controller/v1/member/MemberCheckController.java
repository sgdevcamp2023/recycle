package com.zzaug.member.web.controller.v1.member;

import com.zzaug.member.domain.dto.member.CheckDuplicationUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckDuplicationUseCaseResponse;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseResponse;
import com.zzaug.member.domain.dto.member.EmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.EmailAuthUseCaseResponse;
import com.zzaug.member.domain.usecase.member.CheckDuplicationUseCase;
import com.zzaug.member.web.dto.member.CheckEmailAuthRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import com.zzaug.web.support.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members/check")
@RequiredArgsConstructor
public class MemberCheckController {

	private final CheckDuplicationUseCase checkDuplicationUseCase;

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<CheckDuplicationUseCaseResponse>> check(
			@RequestParam(value = "certification", required = true) String certification) {
		CheckDuplicationUseCaseRequest useCaseRequest =
				CheckDuplicationUseCaseRequest.builder().certification(certification).build();
		CheckDuplicationUseCaseResponse response =
				CheckDuplicationUseCaseResponse.builder().duplication(true).build();
		//		CheckDuplicationUseCaseResponse response = checkDuplicationUseCase.execute(useCaseRequest);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@GetMapping("/email")
	public ApiResponse<ApiResponse.SuccessBody<EmailAuthUseCaseResponse>> emailAuth(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "nonce", required = true) String nonce) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		EmailAuthUseCaseRequest useCaseRequest =
				EmailAuthUseCaseRequest.builder().memberId(memberId).email(email).nonce(nonce).build();
		EmailAuthUseCaseResponse response =
				EmailAuthUseCaseResponse.builder().duplication(true).build();
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@PostMapping("/email")
	public ApiResponse<ApiResponse.SuccessBody<CheckEmailAuthUseCaseResponse>> checkEmailAuth(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestBody CheckEmailAuthRequest request) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		CheckEmailAuthUseCaseRequest useCaseRequest =
				CheckEmailAuthUseCaseRequest.builder()
						.memberId(memberId)
						.code(request.getCode())
						.email(request.getEmail())
						.nonce(request.getNonce())
						.build();
		CheckEmailAuthUseCaseResponse response =
				CheckEmailAuthUseCaseResponse.builder().authentication(true).tryCount(3).build();
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
