package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.review.query.ViewMemberQuestionUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViewMemberQuestionUseCase {
	private final QuestionQueryRepository questionQueryRepository;

	@Transactional
	public List<QuestionQueryResponse> execute(ViewMemberQuestionUseCaseRequest request) {
		List<QuestionQueryEntity> result =
				questionQueryRepository.findAllByAuthorId(request.getAuthorId());
		return result.stream().map(QuestionQueryResponseConverter::from).collect(Collectors.toList());
	}
}
