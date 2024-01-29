import questionApi from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetReviews = () => {
  return useQuery({
    queryKey: ['reviewRequests'],
    queryFn: () => questionApi.getReviewReqeusts(),
    staleTime: Infinity,
  });
};

export default useGetReviews;
