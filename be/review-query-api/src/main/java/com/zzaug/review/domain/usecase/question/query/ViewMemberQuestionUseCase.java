package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.query.QuestionQueryResponse;
import com.zzaug.review.domain.dto.review.query.ViewMemberQuestionUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViewMemberQuestionUseCase {
	private final QuestionQueryRepository questionQueryRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public List<QuestionQueryResponse> execute(ViewMemberQuestionUseCaseRequest request) {
		List<QuestionQueryEntity> result =
				questionQueryRepository.findAllByAuthorIdAndIsDeletedIsFalse(request.getAuthorId());
		return result.stream().map(QuestionQueryResponseConverter::from).collect(Collectors.toList());
	}
}
