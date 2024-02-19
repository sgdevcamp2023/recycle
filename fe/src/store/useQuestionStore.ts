import { create } from 'zustand';

const useQuestionStore = create((set) => ({
  // ref 값인 타이틀도 받아와서 쓰고싶은데...
  title: '',
  content: '',
  setContent: (newContent) => set({ content: newContent }),
  setTitle: (newTitle) => set({ title: newTitle }),
}));

export default useQuestionStore;
