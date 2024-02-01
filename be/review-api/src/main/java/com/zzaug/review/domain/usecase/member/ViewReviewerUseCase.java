package com.zzaug.review.domain.usecase.member;

import com.zzaug.review.domain.dto.member.MemberResponse;
import com.zzaug.review.domain.dto.member.ViewReviewerUseCaseRequest;
import com.zzaug.review.domain.persistence.member.ReviewerListRepository;
import com.zzaug.review.entity.member.ReviewerListEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViewReviewerUseCase {

    private final ReviewerListRepository reviewerListRepository;
    @Transactional
    public List<MemberResponse> execute(ViewReviewerUseCaseRequest request){
        List<ReviewerListEntity> result = reviewerListRepository.findAllByQuestionId(request.getQuestionId());
        return result.stream().map(MemberResponseConverter::from).collect(Collectors.toList());
    }
}
