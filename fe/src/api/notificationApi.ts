import clientApi from './axios';

const notificationApi = {
  //다른 멤버에게 리뷰 요청하기
  requestReview: async ({ questionId, requestMemberId }: never) => {
    return await clientApi.notification.post('/notifications/request/reviews', {
      question_id: questionId,
      request_member_id: requestMemberId,
    });
  },
};

export default notificationApi;
