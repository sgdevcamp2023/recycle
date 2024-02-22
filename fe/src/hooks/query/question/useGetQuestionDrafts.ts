import questionApi, { QuestionProps } from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetQuestionDrafts = ({ questionId }: QuestionProps) => {
  return useQuery({
    queryKey: ['question', questionId],
    queryFn: () => questionApi.getQuestionDrafts({ questionId }),
    staleTime: Infinity,
  });
};

export default useGetQuestionDrafts;
