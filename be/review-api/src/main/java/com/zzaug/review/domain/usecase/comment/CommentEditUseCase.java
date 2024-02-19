package com.zzaug.review.domain.usecase.comment;

import com.zzaug.review.domain.dto.comment.CommentEditUseCaseRequest;
import com.zzaug.review.domain.exception.AlreadyDeletedException;
import com.zzaug.review.domain.exception.UnAuthorizationException;
import com.zzaug.review.domain.persistence.comment.CommentRepository;
import com.zzaug.review.entity.comment.CommentEntity;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentEditUseCase {

	private final CommentRepository commentRepository;

	@Transactional
	public void execute(CommentEditUseCaseRequest request) {
		CommentEntity comment =
				commentRepository
						.findById(request.getCommentId())
						.orElseThrow(() -> new NoSuchElementException("요청에 대한 응답을 찾을 수 없습니다."));

		if (comment.isDeleted()) {
			throw new AlreadyDeletedException("삭제된 댓글입니다.");
		}

		if (!comment.getAuthorId().equals(request.getAuthorId())) {
			throw new UnAuthorizationException("접근 권한이 없습니다.");
		}

		comment.update(request.getContent(), request.getUpdatedAt());
	}
}
