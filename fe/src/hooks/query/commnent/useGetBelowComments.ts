import commentApi from '@api/commentApi';
import { useQuery } from '@tanstack/react-query';

export interface useCommentApiProps {
  questionId: number;
  parentId?: number;
  commentId?: number;
  content?: string;
}

const useGetMemberInfo = ({ questionId }: useCommentApiProps) => {
  return useQuery({
    queryKey: ['comments', questionId],
    queryFn: () => commentApi.getBelowComments({ questionId }),
    staleTime: Infinity,
  });
};

export default useGetMemberInfo;
