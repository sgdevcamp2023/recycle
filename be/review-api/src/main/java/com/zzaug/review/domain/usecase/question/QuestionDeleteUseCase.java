package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.domain.dto.question.QuestionDeleteUseCaseRequest;
import com.zzaug.review.domain.exception.DataNotFoundException;
import com.zzaug.review.domain.exception.UnauthorizedAuthorException;
import com.zzaug.review.domain.model.question.Question;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.entity.question.QuestionEntity;
import com.zzaug.review.exception.ExternalIntegrationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionDeleteUseCase {
    private final QuestionRepository questionRepository;

    @Transactional
    public void execute(QuestionDeleteUseCaseRequest request){
        QuestionEntity question = questionRepository.findById(request.getQuestion_id())
                .orElseThrow(() -> new DataNotFoundException("요청에 대한 응답을 찾을 수 없습니다."));

        if (!question.getAuthor_id().equals(request.getAuthor_id())){
            System.out.println("401 오류 발생");
            throw new UnauthorizedAuthorException("접근 권한이 없습니다.");
        }

        questionRepository.deleteById(request.getQuestion_id());
    }
}
