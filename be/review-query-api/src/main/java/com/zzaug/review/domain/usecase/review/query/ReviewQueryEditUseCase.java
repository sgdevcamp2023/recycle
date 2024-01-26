package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryEditUseCaseRequest;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.domain.support.entity.ReviewQueryEntityConverter;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryEditUseCase {
    private final ReviewQueryRepository reviewQueryRepository;
    private final ReviewQueryConverter reviewQueryConverter;

    public void execute(ReviewQueryEditUseCaseRequest request) {
        ReviewQueryEntity reviewQueryEntity = reviewQueryRepository.findById(request.getReviewId()).orElseThrow(RuntimeException::new);
        ReviewQuery review = reviewQueryConverter.from(request, reviewQueryEntity);
        reviewQueryRepository.save(ReviewQueryEntityConverter.from(review));
    }
}
