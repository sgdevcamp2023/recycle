import questionApi from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetReviews = () => {
  return useQuery({
    queryKey: ['questions'],
    queryFn: () => questionApi.getReviews(),
    staleTime: Infinity,
  });
};

export default useGetReviews;
