import { create } from 'zustand';

export const useMarkdownStore = create((set) => ({
  showCodeComment: false,
  setShowCodeComment: (newCodeComment) => set({ showCodeComment: newCodeComment }),
}));
