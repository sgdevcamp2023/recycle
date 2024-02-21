import commentApi from '@api/commentApi';
import { useMutation } from '@tanstack/react-query';

const useUpdateBelowComments = () => {
  return useMutation({
    mutationFn: commentApi.updateBelowComments,
    onSuccess: (data) => {
      console.log('요청 성공');
      console.log(data);
    },
    onError: (err) => {
      console.error('에러 발생');
      console.log(err);
    },
    onSettled: (data) => {
      console.log('결과에 관계 없이 무언가 실행됨');
      console.log(data);
    },
  });
};

export default useUpdateBelowComments;