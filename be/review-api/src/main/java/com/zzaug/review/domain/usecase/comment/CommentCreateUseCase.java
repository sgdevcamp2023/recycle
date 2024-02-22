package com.zzaug.review.domain.usecase.comment;

import com.zzaug.review.domain.dto.comment.CommentCreateUseCaseRequest;
import com.zzaug.review.domain.model.comment.Comment;
import com.zzaug.review.domain.persistence.comment.CommentRepository;
import com.zzaug.review.domain.support.entity.CommentEntityConverter;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentCreateUseCase {
	private final CommentRepository commentRepository;
	private final CommentConverter commentConverter;

	@Transactional
	public void execute(CommentCreateUseCaseRequest request) {
		Comment comment = commentConverter.from(request);
		commentRepository.save(CommentEntityConverter.from(comment));
	}
}
