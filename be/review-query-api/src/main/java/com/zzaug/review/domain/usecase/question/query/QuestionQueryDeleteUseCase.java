package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryDeleteUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryDeleteUseCase {
    private final QuestionQueryRepository questionQueryRepository;

    @Transactional
    public void execute(QuestionQueryDeleteUseCaseRequest request) {
        if (questionQueryRepository.findById(request.getQuestionId()).isPresent()) {
            questionQueryRepository.deleteById(request.getQuestionId());
        } else {
            throw new RuntimeException("Question not found");
        }
    }
}
