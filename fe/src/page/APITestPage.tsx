import DefaultButton from '@components/atom/Button/DefaultButton';
import DefaultInput from '@components/block/Search/DefaultInput';
import useCheckId from '@hooks/query/member/useCheckId';
import useCheckIdDuplicate from '@hooks/query/member/useCheckIdDuplicate';
import useLogin from '@hooks/query/member/useLogin';
import useSignUp from '@hooks/query/member/useSignUp';
import { useCallback, useRef, useState } from 'react';
import styled from 'styled-components';

const APITestPage = () => {
  const [certifcation, setCertification] = useState<string>('');
  const [id, setId] = useState<string>('');
  const [password, setPassword] = useState<string>('');

  // 회원가입
  const idRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);
  const { mutate: signUp } = useSignUp();
  const handleSignUp = useCallback(() => {
    signUp({
      certification: id,
      password: password,
    });
  }, [idRef, passwordRef]);

  // 로그인
  const { mutate: login } = useLogin();
  const handleLogin = useCallback(() => {
    if (!idRef?.current?.value || !passwordRef?.current?.value) {
      return console.log('모든 값을 입력해주세요.');
    }
    login({
      certification: idRef!.current!.value,
      password: passwordRef!.current!.value,
    });
  }, [idRef, passwordRef]);

  const { data: duplicateData } = useCheckIdDuplicate({ certification: certifcation });
  // 아디이 중복 테스트
  const handleIdDuplicate = () => {
    if (!idRef!.current!.value) {
      console.log(idRef!.current!.value);
      return console.log('모든 값을 입력해주세요.');
    }
    setCertification(idRef!.current!.value);
    console.log(duplicateData);
  };

  // 아디이 조회
  const { data: idData } = useCheckId({ certification: 'certification' });
  const handleCheckId = () => {
    if (!idRef!.current!.value) {
      return console.log('모든 값을 입력해주세요.');
    }
    setCertification(idRef!.current!.value);
    console.log(certifcation);
    console.log(idData);
  };

  return (
    <>
      <div>
        <h1>member</h1>
        <Box>
          <h2>회원가입</h2>
          <DefaultInput height="2rem" type="text" ref={idRef} />
          <DefaultInput height="2rem" type="password" ref={passwordRef} />
          <DefaultButton height="2rem" onClick={() => handleSignUp()}>
            회원가입
          </DefaultButton>
        </Box>
        <Box>
          <h2>로그인</h2>
          <DefaultInput height="2rem" type="text" ref={idRef} />
          <DefaultInput height="2rem" type="password" ref={passwordRef} />
          <DefaultButton height="2rem" onClick={() => handleLogin()}>
            로그인
          </DefaultButton>
        </Box>
        <Box>
          <h2>아이디 중복 체크</h2>
          <DefaultInput height="2rem" type="text" ref={idRef} />
          <DefaultButton height="2rem" onClick={() => handleIdDuplicate()}>
            체크
          </DefaultButton>
        </Box>
        <Box>
          <h2>아이디 조회</h2>
          <DefaultInput height="2rem" type="text" ref={idRef} />
          <DefaultButton height="2rem" onClick={() => handleCheckId()}>
            체크
          </DefaultButton>
        </Box>
      </div>
      <div>
        <h1>review</h1>
      </div>
      <div>
        <h1>notification</h1>
      </div>
    </>
  );
};

export default APITestPage;

const Box = styled.div`
  width: 20rem;
  height: 20rem;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
`;
