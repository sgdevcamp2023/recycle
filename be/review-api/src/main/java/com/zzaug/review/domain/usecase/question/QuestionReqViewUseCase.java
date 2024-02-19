package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.config.JpaDataSourceConfig;
import com.zzaug.review.domain.dto.question.QuestionReqResponse;
import com.zzaug.review.domain.dto.question.QuestionReqViewUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.persistence.question.QuestionRequestRepository;
import com.zzaug.review.domain.usecase.question.converter.QuestionReqResponseConverter;
import com.zzaug.review.entity.question.QuestionEntity;
import com.zzaug.review.entity.question.QuestionRequestEntity;
import java.util.*;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionReqViewUseCase {
	private final QuestionRequestRepository questionRequestRepository;
	private final QuestionRepository questionRepository;

	@Transactional(JpaDataSourceConfig.TRANSACTION_MANAGER_NAME)
	public List<QuestionReqResponse> execute(QuestionReqViewUseCaseRequest request) {

		List<QuestionRequestEntity> result =
				questionRequestRepository.findAllByReceiverId(request.getReceiverId());
		// 리뷰 요청 result를 가지고 리뷰 요청을 받은 사람의 질문을 가져온다.
		LinkedHashSet<Long> reqQuestionIds = new LinkedHashSet<>();
		for (QuestionRequestEntity questionRequestEntity : result) {
			reqQuestionIds.add(questionRequestEntity.getQuestionId());
		}
		// 질문을 가져온다.
		List<QuestionReqResponse> reqResponses = new ArrayList<>();
		for (Long questionId : reqQuestionIds) {
			QuestionEntity questionEntity =
					questionRepository
							.findById(questionId)
							.orElseThrow(() -> new NoSuchElementException("No question found"));

			List<String> requesters =
					result.stream()
							.filter(QuestionEntity -> Objects.equals(QuestionEntity.getQuestionId(), questionId))
							.map(QuestionRequestEntity::getRequester)
							.collect(Collectors.toList());

			QuestionReqResponse reqResponse = QuestionReqResponseConverter.from(questionEntity);
			reqResponse.setRequester(requesters.toArray(new String[0]));

			reqResponses.add(reqResponse);
		}

		return reqResponses;
	}
}
