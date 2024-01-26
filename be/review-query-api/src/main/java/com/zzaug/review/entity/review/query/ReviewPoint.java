package com.zzaug.review.entity.review.query;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewPoint {
    private int point;
    private int index;
}
