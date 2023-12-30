package com.zzaug.member.web.controller.v1.member;

import com.zzaug.member.domain.dto.member.MemberResponse;
import com.zzaug.member.domain.dto.member.MemberUseCaseRequest;
import com.zzaug.member.domain.usecase.member.MemberUseCase;
import com.zzaug.member.support.ApiResponse;
import com.zzaug.member.support.ApiResponseGenerator;
import com.zzaug.member.web.dto.member.MemberRequest;
import com.zzaug.member.web.support.usecase.MemberUseCaseRequestConverter;
import com.zzaug.security.authentication.authority.Roles;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.security.token.AuthToken;
import com.zzaug.security.token.TokenGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase MemberUseCase;
    private final TokenGenerator tokenGenerator;

    @PostMapping()
    public ApiResponse<ApiResponse.SuccessBody<MemberResponse>> member(
        @AuthenticationPrincipal TokenUserDetails userDetails, MemberRequest request) {
        MemberUseCaseRequest useCaseRequest = MemberUseCaseRequestConverter.from(request);
        //		MemberResponse res = MemberUseCase.execute(useCaseRequest);
        MemberResponse res = MemberResponse.builder().name("name").build();
        return ApiResponseGenerator.success(res, HttpStatus.OK);
    }

    @GetMapping("token")
    public ApiResponse<ApiResponse.SuccessBody<AuthToken>> token() {
        AuthToken res = tokenGenerator.generateAuthToken(1L, List.of(Roles.ROLE_USER));
        return ApiResponseGenerator.success(res, HttpStatus.OK);
    }
}
