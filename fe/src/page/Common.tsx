import useTabStore, { TabType } from '@store/useTabStore';
import { ReactNode, Suspense, lazy, useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const QuestionBody = lazy(() => import('@components/block/Content/Question'));
const ReviewBody = lazy(() => import('@components/block/Content/Review'));
const SettingBody = lazy(() => import('@components/block/Content/Setting'));
const RequestBody = lazy(() => import('@components/block/Content/Request'));
const CreateQuestionBody = lazy(() => import('@components/block/Content/CreateQuestion'));
const CreateReviewBody = lazy(() => import('@components/block/Content/CreateReview'));

//변수면 대소문자
const Content: Record<string, ReactNode> = {
  Question: <QuestionBody />,
  Review: <ReviewBody />,
  Request: <RequestBody />,
  Setting: <SettingBody />,
  CreateQuestion: <CreateQuestionBody />,
  CreateReview: <CreateReviewBody />,
};

const Common = () => {
  const { pathname } = useLocation();
  const [selectedContent, setSelectedContent] = useState<React.ReactNode | null>('');
  const { tabType, setTabType } = useTabStore();
  useEffect(() => {
    if (pathname.startsWith('/createQuestion')) {
      setSelectedContent(Content.CreateQuestion);
    } else if (pathname.startsWith('/createReview')) {
      setSelectedContent(Content.CreateReview);
    } else if (tabType != null) {
      console.log(tabType);
      setSelectedContent(Content[pathname.replace('/', '')]);
      const tabTypeFromPath = pathname.replace('/', '') as TabType;
      setTabType(tabTypeFromPath);
    }
  }, [tabType, pathname]);
  return (
    <>
      <Suspense fallback={null}>{selectedContent}</Suspense>
    </>
  );
};

export default Common;
