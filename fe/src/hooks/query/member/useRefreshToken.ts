import memberApi from '@api/memberApi';
import { useMutation } from '@tanstack/react-query';

const useRefreshToken = () => {
  return useMutation({
    mutationFn: memberApi.refreshToken,
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

export default useRefreshToken;
