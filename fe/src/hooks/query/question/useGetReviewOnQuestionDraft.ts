import questionApi, { QuestionProps } from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetReviewOnQuestionDraft = ({ questionId, tId }: QuestionProps) => {
  return useQuery({
    queryKey: ['question', questionId],
    queryFn: () => questionApi.getReviewOnQuestionDraft({ questionId, tId }),
    staleTime: Infinity,
  });
};

export default useGetReviewOnQuestionDraft;
