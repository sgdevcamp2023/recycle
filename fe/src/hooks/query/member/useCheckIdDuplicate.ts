import { useQuery } from '@tanstack/react-query';
import memberApi from '@api/memberApi';

interface useCheckIdDuplicateProps {
  certification: string;
}

const useCheckIdDuplicate = ({ certification }: useCheckIdDuplicateProps) => {
  return useQuery({
    queryKey: [certification],
    queryFn: () => memberApi.checkIdDuplicate({ certification }),
    staleTime: Infinity,
  });
};

export default useCheckIdDuplicate;
