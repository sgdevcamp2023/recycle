import { create } from 'zustand';

export interface reviewData {
  reviewId?: string | null;
  startIdx?: number;
  endIdx?: number;
  reviewText?: string;
}

const useReviewStore = create((set) => ({
  id: '',
  review: '',
  reviewList: <reviewData>[],
  data: <reviewData>[],
  setId: (newId) => set({ id: newId }),
  setReview: (newReview) => set({ review: newReview }),
  setReviewList: (reviewList) => set({ reviewList: reviewList }),
  setData: (data) => set({ data: data }),
}));

export default useReviewStore;
