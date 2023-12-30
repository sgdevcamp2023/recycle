package com.zzaug.member.domain.usecase.member;

import com.zzaug.member.domain.dto.member.MemberResponse;
import com.zzaug.member.domain.dto.member.MemberUseCaseRequest;
import com.zzaug.member.domain.model.member.Member;
import com.zzaug.member.domain.persistence.member.MemberRepository;
import com.zzaug.member.domain.support.entity.MemberEntityConverter;
import com.zzaug.member.domain.util.UtilSomething;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberUseCase {

	private final MemberRepository memberRepository;
	private final MemberConverter memberConverter;

	@Transactional
	public MemberResponse execute(MemberUseCaseRequest request) {
		Member member = memberConverter.from(request);

		memberRepository.save(MemberEntityConverter.from(member));

		List<Member> all =
				memberRepository.findAll().stream().map(memberConverter::to).collect(Collectors.toList());

		UtilSomething.a("a");
		UtilSomething.b("b");
		UtilSomething.c("c");

		return MemberResponse.builder().build();
	}
}
