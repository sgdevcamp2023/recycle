import questionApi from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetReviews = () => {
  return useQuery({
    queryKey: ['reviews'],
    queryFn: () => questionApi.getReviews(),
    staleTime: 3000,
  });
};

export default useGetReviews;
