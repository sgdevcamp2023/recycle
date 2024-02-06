package com.zzaug.review.domain.usecase.comment;

import com.zzaug.review.domain.dto.comment.CommentResponse;
import com.zzaug.review.domain.dto.comment.CommentViewUseCaseRequest;
import com.zzaug.review.domain.persistence.comment.CommentRepository;
import com.zzaug.review.domain.support.entity.CommentEntityConverter;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentViewUseCase {
	private final CommentRepository commentRepository;

	@Transactional
	public List<CommentResponse> execute(CommentViewUseCaseRequest request) {
		List<CommentResponse> responses =
				commentRepository.findAllByQuestionIdAndIsDeletedFalse(request.getQuestionId()).stream()
						.map(CommentEntityConverter::from)
						.collect(Collectors.toList());

		return responses;
	}
}
