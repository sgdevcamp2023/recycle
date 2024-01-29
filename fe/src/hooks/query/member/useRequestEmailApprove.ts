import memberApi from '@api/memberApi';
import { useQuery } from '@tanstack/react-query';

export interface useRequestEmailApproveProps {
  email: string;
  nonce: string;
}

const useRequestEmailApprove = ({ email, nonce }: useRequestEmailApproveProps) => {
  return useQuery({
    queryKey: ['emailRequest', email, nonce],
    queryFn: () => memberApi.getMemberInfo({ email, nonce }),
    staleTime: Infinity,
  });
};

export default useRequestEmailApprove;
