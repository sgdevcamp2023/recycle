import useTabStore from '@store/useTabStore';
import { ReactNode, Suspense, lazy, useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const QuestionBody = lazy(() => import('@components/block/Content/Question'));
const ReviewBody = lazy(() => import('@components/block/Content/Review'));
const SettingBody = lazy(() => import('@components/block/Content/Setting'));
const RequestBody = lazy(() => import('@components/block/Content/Request'));
const CreateQuestionBody = lazy(() => import('@components/block/Content/CreateQuestion'));
const CreateReviewBody = lazy(() => import('@components/block/Content/CreateReview'));

const Content: Record<string, ReactNode> = {
  question: <QuestionBody />,
  review: <ReviewBody />,
  request: <RequestBody />,
  setting: <SettingBody />,
  createQuestion: <CreateQuestionBody />,
  createReview: <CreateReviewBody />,
};

const Common = () => {
  const { pathname } = useLocation();
  const [selectedContent, setSelectedContent] = useState<React.ReactNode | null>('');
  const { tabType } = useTabStore();
  useEffect(() => {
    if (pathname.startsWith('/newQuestion')) {
      setSelectedContent(Content.createQuestion);
    } else if (tabType != null) {
      setSelectedContent(Content[tabType]);
    }
  }, [tabType, pathname]);
  return (
    <>
      <Suspense fallback={null}>{selectedContent}</Suspense>
    </>
  );
};

export default Common;
