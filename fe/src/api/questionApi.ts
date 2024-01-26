import clientApi from "./axios";

const questionApi = {
  // 질문 생성 [post]
  //! 주의할 점 : Content에 제목 같이 포함해서 넘겨 줘야 됨
  createQuestion: async ({ content }: never) => {
    return await clientApi.post(`/questions`, { content });
  },

  // 질문 임시저장 [post]
  saveQuestionDraft: async ({ content, tempId }: never) => {
    return await clientApi.post(`/questions`, { content, t_id: tempId });
  },

  // 질문 삭제 [delete]
  deleteQuestion: async ({ questionId: question_id }: never) => {
    return await clientApi.delete(`/questions/${question_id}`);
  },

  // 리뷰를 달아준 리뷰어 목록 조회 [get]
  getQuestionReviwer: async ({ questionId: question_id }: never) => {
    return await clientApi.get(`/me/questions/reviewers?${question_id}`);
  },

  // 질문글 목록 조회하기 [get]
  getQuestions: async () => {
    return await clientApi.get(`/me-query/questions`);
  },

  // 멤버가 작성한 리뷰 목록 조회 [get]
  getReviews: async () => {
    return await clientApi.get(`/me-query/reviews`);
  },

  // 임시 저장된 질문 글 목록 조회 [get]
  getQuestionDrafts: async ({ tId: t_id }: never) => {
    return await clientApi.get(`/me-query/questions/temp?${t_id}`);
  },

  // 리뷰 요청 목록 조회 [get]
  getReviewReqeusts: async () => {
    return await clientApi.get(`/me-query/requests/reviews`);
  },

  // 멤버의 질문 글 목록에서 검색 [get]
  searchQuestions: async ({ me, query, page, size }: never) => {
    return await clientApi.get(`/question-query/search?${me}?${query}?${page}?${size}`);
  },

  // 질문 글 조회 [get]
  getQuestion: async ({ questionId: question_id }: never) => {
    return await clientApi.get(`/question-query/${question_id}`);
  },

  // 질문 글에 달린 리뷰 목록 조회 [get]
  getReviewsOnQuestion: async ({ questionId: question_id }: never) => {
    return await clientApi.get(`/question-query/${question_id}/reviews`);
  },
  // 질문 글에 달린 임시 저장된 리뷰 목록 조회[get]
  getReviewOnQuestionDrafts: async ({ questionId: question_id, tId: t_id }: never) => {
    return await clientApi.get(`/question-query/${question_id}/reviews/temp?${t_id}`);
  },
};

export default questionApi;