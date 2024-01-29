import questionApi, { SearchParamsProps } from '@api/questionApi';
import { useQuery } from '@tanstack/react-query';

const useGetReviews = ({ me, query, page, size }: SearchParamsProps) => {
  return useQuery({
    queryKey: ['reviewRequests', me, query, page, size],
    queryFn: () => questionApi.searchQuestions({ me, query, page, size }),
    staleTime: Infinity,
  });
};

export default useGetReviews;
