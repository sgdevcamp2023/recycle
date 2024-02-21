import questionApi from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetQuestions = () => {
  return useQuery({
    queryKey: ['questions'],
    queryFn: () => questionApi.getQuestions(),
    staleTime: 3000,
  });
};

export default useGetQuestions;
