import { create } from 'zustand';

export interface reviewDataProps {
  reviewId?: string | null;
  startIdx?: number;
  endIdx?: number;
  reviewText?: string;
  reviewComment?: string;
}

const useReviewStore = create((set) => ({
  id: '',
  review: '안녕하세요 이규민입니다. 알아서 코드인줄 아세요~',
  reviewList: <reviewDataProps>[],
  data: <reviewDataProps>[],
  setId: (newId) => set({ id: newId }),
  setReview: (newReview) => set({ review: newReview }),
  setReviewList: (reviewList) => set({ reviewList: reviewList }),
  setData: (data) => set({ data: data }),
}));

export default useReviewStore;
