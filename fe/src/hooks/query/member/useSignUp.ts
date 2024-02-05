import memberApi from '@api/memberApi';
import { useMutation } from '@tanstack/react-query';
import { userProps } from '../../../api/memberApi';

const useSignUp = ({ certification, password }: userProps) => {
  return useMutation({
    mutationFn: () => memberApi.signUp({ certification, password }),
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
      console.log('data:', data);
    },
  });
};

export default useSignUp;
