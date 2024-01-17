import { create } from "zustand";
import { devtools } from "zustand/middleware";

export type TabType = "question" | "review" | "setting" | null;

interface TabState {
  tabType: TabType;
}

interface TabAction {
  setTabType: (tabType: TabType) => void;
}

const useTabStore = create<TabState & TabAction>()(
  devtools((set) => ({
    tabType: null,

    setTabType: (tabType: TabType) => set({ tabType }),
  }))
);

export default useTabStore;
