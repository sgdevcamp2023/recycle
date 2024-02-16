package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.QuestionTempResponse;
import com.zzaug.review.domain.dto.question.QuestionTempViewUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionTempRepository;
import com.zzaug.review.domain.usecase.question.converter.QuestionTempResponseConverter;
import com.zzaug.review.entity.question.QuestionTempEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionTempViewUseCase {
	private final QuestionTempRepository questionTempRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public List<QuestionTempResponse> execute(QuestionTempViewUseCaseRequest request) {
		if (request.getTempId() != null) {
			System.out.println(request.getTempId());
			List<QuestionTempEntity> result = questionTempRepository.findByTempId(request.getTempId());
			return result.stream().map(QuestionTempResponseConverter::from).collect(Collectors.toList());
		} else {
			List<QuestionTempEntity> result =
					questionTempRepository.findAllByAuthorId(request.getAuthorId());
			return result.stream().map(QuestionTempResponseConverter::from).collect(Collectors.toList());
		}
	}
}
