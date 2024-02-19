import { create } from 'zustand';

const useReviewStore = create((set) => ({
  id: '',
  review: '',
  setId: (newId) => set({ id: newId }),
  setReview: (newReview) => set({ review: newReview }),
}));

export default useReviewStore;
