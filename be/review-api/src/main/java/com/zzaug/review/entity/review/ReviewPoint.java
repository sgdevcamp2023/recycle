package com.zzaug.review.entity.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPoint {
    @NotEmpty
    private int point;
    @NotEmpty
    private int index;
}
