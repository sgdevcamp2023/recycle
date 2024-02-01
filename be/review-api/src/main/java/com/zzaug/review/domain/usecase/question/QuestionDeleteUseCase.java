package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.domain.dto.question.QuestionDeleteUseCaseRequest;

import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.entity.question.QuestionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionDeleteUseCase {
    private final QuestionRepository questionRepository;

    @Transactional
    public void execute(QuestionDeleteUseCaseRequest request){
        QuestionEntity question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다."));

        if (!question.getAuthorId().equals(request.getAuthorId())){
            throw new RuntimeException("접근 권한이 없습니다.");
        }

        questionRepository.deleteById(request.getQuestionId());
    }
}
