import questionApi, { QuestionProps } from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetQuestionReviewer = ({ questionId }: QuestionProps) => {
  return useQuery({
    queryKey: ['reviewers', questionId],
    queryFn: () => questionApi.getQuestionReviwer({ questionId }),
    staleTime: Infinity,
  });
};

export default useGetQuestionReviewer;
