package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.domain.dto.review.ReviewTempResponse;
import com.zzaug.review.domain.dto.review.ReviewTempViewUseCaseRequest;
import com.zzaug.review.domain.persistence.review.ReviewTempRepository;
import com.zzaug.review.domain.usecase.review.converter.ReviewTempResponseConverter;
import com.zzaug.review.entity.review.ReviewTempEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewTempViewUseCase {

	private final ReviewTempRepository reviewTempRepository;

	@Transactional
	public List<ReviewTempResponse> execute(ReviewTempViewUseCaseRequest request) {

		if (request.getTempId() != null) {
			List<ReviewTempEntity> result = reviewTempRepository.findByTempId(request.getTempId());
			return result.stream().map(ReviewTempResponseConverter::from).collect(Collectors.toList());
		} else {
			List<ReviewTempEntity> result =
					reviewTempRepository.findAllByAuthorIdAndQuestionId(
							request.getAuthorId(), request.getQuestionId());
			return result.stream().map(ReviewTempResponseConverter::from).collect(Collectors.toList());
		}
	}
}
