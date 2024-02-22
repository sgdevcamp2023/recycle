import memberApi from '@api/memberApi';
import useMovePage from '@hooks/common/useMovePage';
import { useMutation } from '@tanstack/react-query';

const useSignUp = () => {
  const move = useMovePage();
  return useMutation({
    mutationFn: memberApi.signUp,
    onSuccess: (data) => {
      console.log('요청 성공');
      console.log(data.status);

      if (data.status >= 200 && data.status < 300) {
        move('main');
      }
    },
    onError: (err) => {
      console.error('에러 발생');
      console.log(err);

      if (err && err.response?.status >= 400 && err.response?.status < 500) {
        console.log('에러 처리 로직 추가');
        // 아마 400 번대면 재사용하도록 하기

        // 아래 부분은 localStorage 저장을 위한 코드입니다.
        // const storedData = {
        //   email: 'stored-email-value',
        //   password: 'stored-password-value',
        // };

        // localStorage.setItem('storedSignUpData', JSON.stringify(storedData));

        alert('회원가입을 다시 시도해주세요');
      }

      if (err && err.response?.status >= 500 && err.response?.status < 600) {
        move('error');
      }
    },

    onSettled: (data) => {
      console.log('결과에 관계 없이 무언가 실행됨');
      console.log(data);
    },
  });
};

export default useSignUp;
