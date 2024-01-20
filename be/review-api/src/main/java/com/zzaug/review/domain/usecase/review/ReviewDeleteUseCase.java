package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.domain.dto.review.ReviewDeleteUseCaseRequest;
import com.zzaug.review.domain.exception.DataNotFoundException;
import com.zzaug.review.domain.exception.UnauthorizedAuthorException;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.entity.review.ReviewEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewDeleteUseCase {
    private final ReviewRepository reviewRepository;

    @Transactional
    public void execute(ReviewDeleteUseCaseRequest request){
        ReviewEntity review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(() -> new DataNotFoundException("요청에 대한 응답을 찾을 수 없습니다."));

        if (!review.getAuthorId().equals(request.getAuthorId())){
            throw new UnauthorizedAuthorException("접근 권한이 없습니다.");
        }

        reviewRepository.deleteById(request.getReviewId());
    }
}
