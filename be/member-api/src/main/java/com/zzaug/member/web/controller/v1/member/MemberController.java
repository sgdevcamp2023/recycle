package com.zzaug.member.web.controller.v1.member;

import com.zzaug.member.domain.dto.member.DeleteMemberUseCaseRequest;
import com.zzaug.member.domain.dto.member.GetMemberUseCaseRequest;
import com.zzaug.member.domain.dto.member.GetMemberUseCaseResponse;
import com.zzaug.member.domain.dto.member.LogOutUseCaseRequest;
import com.zzaug.member.domain.dto.member.LoginUseCaseRequest;
import com.zzaug.member.domain.dto.member.MemberAuthToken;
import com.zzaug.member.domain.dto.member.PostMemberUseCaseRequest;
import com.zzaug.member.domain.dto.member.SearchMemberUseCaseRequest;
import com.zzaug.member.domain.dto.member.SearchMemberUseCaseResponse;
import com.zzaug.member.domain.dto.member.UpdateMemberUseCaseRequest;
import com.zzaug.member.domain.usecase.member.DeleteMemberUseCase;
import com.zzaug.member.domain.usecase.member.GetMemberUseCase;
import com.zzaug.member.domain.usecase.member.LogOutUseCase;
import com.zzaug.member.domain.usecase.member.LoginUseCase;
import com.zzaug.member.domain.usecase.member.UpdateMemberUseCase;
import com.zzaug.member.web.dto.member.LoginRequest;
import com.zzaug.member.web.dto.member.MemberSaveRequest;
import com.zzaug.member.web.dto.member.MemberUpdateRequest;
import com.zzaug.security.authentication.authority.Roles;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.security.token.AuthToken;
import com.zzaug.security.token.TokenGenerator;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import com.zzaug.web.support.CookieGenerator;
import com.zzaug.web.support.CookieSameSite;
import com.zzaug.web.support.MessageCode;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

	private static final String COOKIE_HEADER_KEY = "Set-Cookie";
	private static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";

	//	private final CookieGenerator cookieGenerator;
	private final TokenGenerator tokenGenerator;
	private final CookieGenerator cookieGenerator;

	private final GetMemberUseCase getMemberUseCase;
	private final UpdateMemberUseCase updateMemberUseCase;
	private final DeleteMemberUseCase deleteMemberUseCase;
	private final LoginUseCase loginUseCase;
	private final LogOutUseCase logOutUseCase;

	@PostMapping()
	public ApiResponse<ApiResponse.Success> save(@RequestBody MemberSaveRequest request) {
		PostMemberUseCaseRequest useCaseRequest =
				PostMemberUseCaseRequest.builder()
						.certification(request.getCertification())
						.password(request.getPassword())
						.build();
		return ApiResponseGenerator.success(HttpStatus.CREATED, MessageCode.RESOURCE_CREATED);
	}

	@PutMapping()
	public ApiResponse<ApiResponse.Success> update(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestBody MemberUpdateRequest request) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		UpdateMemberUseCaseRequest useCaseRequest =
				UpdateMemberUseCaseRequest.builder()
						.memberId(memberId)
						.certification(request.getCertification())
						.password(request.getPassword())
						.build();
		//		updateMemberUseCase.execute(useCaseRequest);
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_MODIFIED);
	}

	@DeleteMapping()
	public ApiResponse<ApiResponse.Success> delete(
			@AuthenticationPrincipal TokenUserDetails userDetails) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		DeleteMemberUseCaseRequest useCaseRequest =
				DeleteMemberUseCaseRequest.builder().memberId(memberId).build();
		//		deleteMemberUseCase.execute(useCaseRequest);
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
	}

	@PostMapping("/login")
	public ApiResponse<ApiResponse.SuccessBody<MemberAuthToken>> login(
			HttpServletRequest httpServletRequest,
			@RequestBody LoginRequest request,
			HttpServletResponse httpServletResponse) {
		LoginUseCaseRequest useCaseRequest =
				LoginUseCaseRequest.builder()
						.certification(request.getCertification())
						.password(request.getPassword())
						.userAgent(httpServletRequest.getHeader("User-Agent"))
						.build();
		MemberAuthToken response =
				MemberAuthToken.builder().accessToken("accessToken").refreshToken("refreshToken").build();
		//		MemberAuthToken response = loginUseCase.execute(useCaseRequest);
		ResponseCookie refreshToken =
				cookieGenerator.createCookie(
						CookieSameSite.LAX, REFRESH_TOKEN_COOKIE_NAME, response.getRefreshToken());
		httpServletResponse.addHeader(COOKIE_HEADER_KEY, refreshToken.toString());
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@PostMapping("/logout")
	public ApiResponse<ApiResponse.Success> logout(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			HttpServletResponse httpServletResponse) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		LogOutUseCaseRequest useCaseRequest = LogOutUseCaseRequest.builder().memberId(memberId).build();
		//		logOutUseCase.execute(useCaseRequest);
		ResponseCookie clearCookie =
				cookieGenerator.clearCookie(CookieSameSite.LAX, REFRESH_TOKEN_COOKIE_NAME);
		httpServletResponse.addHeader(COOKIE_HEADER_KEY, clearCookie.toString());
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.SUCCESS);
	}

	@GetMapping("/{id}")
	public ApiResponse<ApiResponse.SuccessBody<GetMemberUseCaseResponse>> read(
			@AuthenticationPrincipal TokenUserDetails userDetails, @PathVariable Long id) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		GetMemberUseCaseRequest useCaseRequest =
				GetMemberUseCaseRequest.builder().memberId(memberId).queryMemberId(id).build();
		GetMemberUseCaseResponse response =
				GetMemberUseCaseResponse.builder().id(1L).email("email").github("github").build();
//		GetMemberUseCaseResponse response = getMemberUseCase.execute(useCaseRequest);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<SearchMemberUseCaseResponse>> search(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@RequestParam(value = "certification", required = false) String certification) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		SearchMemberUseCaseRequest useCaseRequest =
				SearchMemberUseCaseRequest.builder()
						.memberId(memberId)
						.certification(certification)
						.build();
		SearchMemberUseCaseResponse response =
				SearchMemberUseCaseResponse.builder().id(1L).email("email").github("github").build();
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@PostMapping("/token")
	public ApiResponse<ApiResponse.SuccessBody<MemberAuthToken>> token(
			@AuthenticationPrincipal TokenUserDetails userDetails) {
		//		Long memberId = Long.valueOf(userDetails.getId());
		Long memberId = 1L;
		LogOutUseCaseRequest useCaseRequest = LogOutUseCaseRequest.builder().memberId(memberId).build();
		AuthToken authToken =
				tokenGenerator.generateAuthToken(
						1L, List.of(Roles.ROLE_USER), "certification", "email", "github");
		MemberAuthToken response =
				MemberAuthToken.builder()
						.accessToken(authToken.getAccessToken())
						.refreshToken(authToken.getRefreshToken())
						.build();
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
