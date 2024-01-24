package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryEditUseCaseRequest;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.support.entity.QuestionQueryEntityConverter;
import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryEditUseCase {
    private final QuestionQueryRepository questionQueryRepository;
    private final QuestionQueryConverter questionQueryConverter;

    public void execute(QuestionQueryEditUseCaseRequest request) {
        QuestionQueryEntity question = questionQueryRepository.findById(request.getQuestionId()).orElseThrow(RuntimeException::new);
        QuestionQuery questionQuery = questionQueryConverter.from(request, question);
        questionQueryRepository.save(QuestionQueryEntityConverter.from(questionQuery));
    }
}
