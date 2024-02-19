package com.zzaug.review.entity.review;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPoint {
	@NotEmpty private int point;
	@NotEmpty private int index;
}
