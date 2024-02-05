import { useQuery, QueryClient, QueryCache } from '@tanstack/react-query';
import memberApi from '@api/memberApi';
import { useCheckIdProps } from './useCheckId';

const useCheckIdDuplicate = ({ certification }: useCheckIdProps) => {
  return useQuery({
    queryKey: [certification],
    queryFn: () => memberApi.checkIdDuplicate({ certification }),
    staleTime: Infinity,
    meta: {
      errorMessage: 'Failed to fetch todos',
    },
  });
};

export default useCheckIdDuplicate;
