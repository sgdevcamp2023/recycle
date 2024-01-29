import questionApi, { QuestionProps } from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetReviewsOnQuestion = ({ questionId }: QuestionProps) => {
  return useQuery({
    queryKey: ['question', questionId],
    queryFn: () => questionApi.getReviewsOnQuestion({ questionId }),
    staleTime: Infinity,
  });
};

export default useGetReviewsOnQuestion;
