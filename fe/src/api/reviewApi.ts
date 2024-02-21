import clientApi from './axios';

const reviewApi = {
  // 리뷰 남기기 [post]
  saveReview: async ({ questionId: question_id, content, location, tag }: never) => {
    return await clientApi.common.post(`/questions/${question_id}/reviews`, {
      content,
      location,
      tag,
    });
  },

  // 리뷰 임시저장 하기 [post]
  saveReviewDraft: async ({
    questionId: question_id,
    content,
    location,
    tag,
    tId: t_id,
  }: never) => {
    return await clientApi.common.post(`/questions/${question_id}/reviews`, {
      content,
      location,
      tag,
      t_id,
    });
  },
  // 리뷰 수정 하기 [put]
  updateReviewDraft: async ({
    questionId: question_id,
    reviewId: review_id,
    content,
    location,
    tag,
    tId: t_id,
  }: never) => {
    return await clientApi.common.put(`/questions/${question_id}/reviews/${review_id}`, {
      content,
      location,
      tag,
      t_id,
    });
  },
  // 리뷰 삭제 하기 [delete]
  deleteReviewDraft: async ({ questionId: question_id, reviewId: review_id }: never) => {
    return await clientApi.common.delete(`/questions/${question_id}/reviews/${review_id}`);
  },
};

export default reviewApi;
