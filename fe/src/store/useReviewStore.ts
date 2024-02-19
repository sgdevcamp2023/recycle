import { create } from 'zustand';

const useReviewStore = create((set) => ({
  id: '',
  review: '',
  reviewList: [],
  setId: (newId) => set({ id: newId }),
  setReview: (newReview) => set({ review: newReview }),
  setReviewList: (reviewList) => set({ reviewList: reviewList }),
}));

export default useReviewStore;
