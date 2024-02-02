package com.zzaug.review.entity.question;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionRequestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionReqId;

	private String requester;

	@Column(name = "requester_id")
	private Long requesterId;

	private String receiver;

	@Column(name = "receiver_id")
	private Long receiverId;

	@Column(name = "question_id")
	private Long questionId;
}
