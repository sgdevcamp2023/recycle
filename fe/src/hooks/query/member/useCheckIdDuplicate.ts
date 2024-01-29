import { useQuery } from '@tanstack/react-query';
import memberApi from '@api/memberApi';
import { useCheckIdProps } from './useCheckId';

const useCheckIdDuplicate = ({ certification }: useCheckIdProps) => {
  return useQuery({
    queryKey: [certification],
    queryFn: () => memberApi.checkIdDuplicate({ certification }),
    staleTime: Infinity,
  });
};

export default useCheckIdDuplicate;
