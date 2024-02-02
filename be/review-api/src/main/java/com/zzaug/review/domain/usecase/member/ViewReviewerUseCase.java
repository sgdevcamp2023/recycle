package com.zzaug.review.domain.usecase.member;

import com.zzaug.review.domain.dto.member.MemberResponse;
import com.zzaug.review.domain.dto.member.ViewReviewerUseCaseRequest;
import com.zzaug.review.domain.persistence.member.ReviewerListRepository;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.entity.member.ReviewerListEntity;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViewReviewerUseCase {

	private final ReviewerListRepository reviewerListRepository;
	private final QuestionRepository questionRepository;

	@Transactional
	public List<MemberResponse> execute(ViewReviewerUseCaseRequest request) {
		if (!questionRepository.existsById(request.getQuestionId())) {
			throw new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다.");
		}
		List<ReviewerListEntity> result =
				reviewerListRepository.findAllByQuestionId(request.getQuestionId());
		return result.stream().map(MemberResponseConverter::from).collect(Collectors.toList());
	}
}
