import memberApi from '@api/memberApi';
import { useMutation } from '@tanstack/react-query';

const useLogin = () => {
  return useMutation({
    mutationFn: memberApi.login,
    onSuccess: (data) => {
      console.log('요청 성공');
      console.log(data);

      const tokenData = data?.data?.data

      const access = tokenData.accessToken;
      const refresh = tokenData.refreshToken;
      console.log(access,refresh)
      if (access && refresh) {
        console.log(access, refresh)
      } else {
        console.error('조졌다')
      }
    },
    onError: (err) => {
      console.error('에러 발생');
      console.log(err);
    },
    // onSettled: (data) => {
    //   console.log('결과에 관계 없이 무언가 실행됨');
    //   console.log(data);
    // },
  });
};

export default useLogin;
