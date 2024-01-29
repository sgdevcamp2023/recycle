package com.zzaug.review.domain.usecase.question;

import com.zzaug.review.domain.dto.question.QuestionTempResponse;
import com.zzaug.review.domain.dto.question.QuestionTempViewUseCaseRequest;
import com.zzaug.review.domain.persistence.question.QuestionTempRepository;
import com.zzaug.review.domain.usecase.question.converter.QuestionTempResponseConverter;
import com.zzaug.review.entity.question.QuestionTempEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionTempViewUseCase {
    private final QuestionTempRepository questionTempRepository;

    @Transactional
    public List<QuestionTempResponse> execute(QuestionTempViewUseCaseRequest request){
        if (request.getTempId() != null){
            System.out.println(request.getTempId());
            List<QuestionTempEntity> result = questionTempRepository.findByTId(request.getTempId());
            return result.stream()
                    .map(QuestionTempResponseConverter::from)
                    .collect(Collectors.toList());
        }
        else {
            List<QuestionTempEntity> result = questionTempRepository.findAllByAuthorId(request.getAuthorId());
            return result.stream()
                    .map(QuestionTempResponseConverter::from)
                    .collect(Collectors.toList());
        }

    }
}
