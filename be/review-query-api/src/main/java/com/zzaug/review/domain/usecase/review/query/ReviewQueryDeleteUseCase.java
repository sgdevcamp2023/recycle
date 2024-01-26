package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryDeleteUseCaseRequest;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryDeleteUseCase {
    private final ReviewQueryRepository reviewQueryRepository;

    @Transactional
    public void execute(ReviewQueryDeleteUseCaseRequest request) {
        if (reviewQueryRepository.findById(request.getReviewId()).isPresent()){
            reviewQueryRepository.deleteById(request.getReviewId());
        } else {
            throw new RuntimeException("Review not found");
        }
    }
}
