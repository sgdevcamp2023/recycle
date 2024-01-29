import memberApi from '@api/memberApi';
import { useQuery } from '@tanstack/react-query';

export interface useCheckIdProps {
  certification: string;
}

const useCheckId = ({ certification }: useCheckIdProps) => {
  return useQuery({
    queryKey: [certification],
    queryFn: () => memberApi.checkId({ certification }),
    staleTime: Infinity,
  });
};

export default useCheckId;
