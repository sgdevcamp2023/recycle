import memberApi from '@api/memberApi';
import useMovePage from '@hooks/common/useMovePage';
import { useMutation } from '@tanstack/react-query';

const useLogin = () => {
  const move = useMovePage();
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
        localStorage.setItem('access', access)
        localStorage.setItem('refresh', refresh)
        move('')
      } else {
        console.error('토큰을 발급받지 못했습니다')
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
