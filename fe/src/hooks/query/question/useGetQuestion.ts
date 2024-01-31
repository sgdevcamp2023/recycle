import questionApi, { QuestionProps } from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetQuestion = ({ questionId }: QuestionProps) => {
  return useQuery({
    queryKey: ['question', questionId],
    queryFn: () => questionApi.getQuestion({ questionId }),
    staleTime: Infinity,
  });
};

export default useGetQuestion;
