package com.zzaug.review.entity.review;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.zzaug.review.entity.review.query.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long reviewId;

	@Column(name = "question_id")
	private Long questionId;

	@Column(length = 10000)
	private String content;

	private String author;

	@Column(name = "author_id")
	private Long authorId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "point", column = @Column(name = "start_point_point")),
		@AttributeOverride(name = "index", column = @Column(name = "start_point_index"))
	})
	private ReviewPoint startPoint;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "point", column = @Column(name = "end_point_point")),
		@AttributeOverride(name = "index", column = @Column(name = "end_point_index"))
	})
	private ReviewPoint endPoint;

	private ReviewType tag;

	@ColumnDefault("false")
	private boolean isDeleted;

	public void update(
			String content, ReviewPoint startPoint, ReviewPoint endPoint, LocalDateTime updatedAt) {
		this.content = content;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.updatedAt = updatedAt;
	}

	public void deleteReview() {
		this.isDeleted = true;
	}
}
