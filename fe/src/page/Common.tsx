import useTabStore from "@store/useTabStore";
import { Suspense, lazy, useEffect, useState } from "react";

const QuestionBody = lazy(() => import("@components/block/Content/Question"));
const ReviewBody = lazy(() => import("@components/block/Content/Review"));
const SettingBody = lazy(() => import("@components/block/Content/Setting"));
const RequestBody = lazy(() => import("@components/block/Content/Request"));

const Cotent = {
  question: <QuestionBody />,
  review: <ReviewBody />,
  request: <RequestBody />,
  setting: <SettingBody />,
};

const Common = () => {
  const [selectedContent, setSelectedContent] = useState<React.ReactNode | null>("");
  const { tabType } = useTabStore();
  useEffect(() => {
    if (tabType != null) {
      setSelectedContent(Cotent[tabType]);
    }
  }, [tabType]);
  return (
    <>
      <Suspense fallback={null}>{selectedContent}</Suspense>
    </>
  );
};

export default Common;
