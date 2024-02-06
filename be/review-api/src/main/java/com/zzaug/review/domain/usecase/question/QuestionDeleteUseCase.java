package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.domain.dto.question.QuestionDeleteUseCaseRequest;
import com.zzaug.review.domain.exception.AlreadyDeletedException;
import com.zzaug.review.domain.exception.UnAuthorizationException;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.entity.question.QuestionEntity;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionDeleteUseCase {
	private final QuestionRepository questionRepository;

	@Transactional
	public void execute(QuestionDeleteUseCaseRequest request) {
		QuestionEntity question =
				questionRepository
						.findById(request.getQuestionId())
						.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다."));

		if (!question.getAuthorId().equals(request.getAuthorId())) {
			throw new UnAuthorizationException("접근 권한이 없습니다.");
		}

		if(question.isDeleted()) {
			throw new AlreadyDeletedException("이미 삭제된 질문입니다.");
		}

		question.deleteQuestion();
	}
}
