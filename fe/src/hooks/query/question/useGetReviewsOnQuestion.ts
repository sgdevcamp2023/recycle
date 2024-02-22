import questionApi, { QuestionProps } from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetReviewsOnQuestion = ({ questionId }: QuestionProps) => {
  return useQuery({
    queryKey: ['reviewOnQuestions', questionId],
    queryFn: () => questionApi.getReviewsOnQuestion({ questionId }),
    staleTime: 3000,
  });
};

export default useGetReviewsOnQuestion;
