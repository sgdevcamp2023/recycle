import { create } from 'zustand';

export interface reviewDataProps {
  reviewId?: string | null;
  startIdx?: number;
  endIdx?: number;
  reviewText?: string;
  reviewComment?: string;
  createdAt?: string;
  reviewPoint: number;
}

const useReviewStore = create((set) => ({
  id: 0,
  review: [],
  reviewList: <reviewDataProps>[],
  data: <reviewDataProps>[],
  setId: (newId) => set({ id: newId }),
  setReview: (newReview) => set({ review: newReview }),
  setReviewList: (reviewList) => set({ reviewList: reviewList }),
  setData: (data) => set({ data: data }),
}));

export default useReviewStore;
