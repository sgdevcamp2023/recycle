import memberApi from '@api/memberApi';
import { useQuery } from '@tanstack/react-query';

export interface useGetMemberInfoProps {
  id: number;
}

const useGetMemberInfo = ({ id }: useGetMemberInfoProps) => {
  return useQuery({
    queryKey: ['memberInfo', id],
    queryFn: () => memberApi.getMemberInfo({ id }),
    staleTime: Infinity,
  });
};

export default useGetMemberInfo;
