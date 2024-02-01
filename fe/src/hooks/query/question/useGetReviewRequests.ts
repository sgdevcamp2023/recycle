import questionApi from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetReviewRequests = () => {
  return useQuery({
    queryKey: ['reviewRequests'],
    queryFn: () => questionApi.getReviewReqeusts(),
    staleTime: Infinity,
  });
};

export default useGetReviewRequests;
