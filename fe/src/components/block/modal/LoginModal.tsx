import Text from '@components/atom/Text';
import { flexCenter } from '@styles/flexCenter';
import styled from 'styled-components';
import logo from '../../../assets/logos/ZzaugLogo.png';
import DefaultButton from '@components/atom/Button/DefaultButton';
import CustomInput from '@components/atom/Input/CustomInput';
import useMovePage from '@hooks/common/useMovePage';
import { useState } from 'react';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 29.25rem;
  height: 27.5rem;
  padding: 0.25rem 16px;
  background-color: white;
  color: black;
  border: 1px solid #1eb649;
  border-radius: 10px;
  padding-right: 55px;
  padding-left: 3.4375rem;
  position: relative;
`;

const FlexBox = styled.div`
  display: flex;
  flex-direction: column;
  ${flexCenter}
  position: absolute;
  flex-wrap: wrap;
  gap: 16px;
`;

const ButtonBox = styled.div`
  display: flex;
  flex-direction: column;
  ${flexCenter}
  position: absolute;
  bottom: 5rem;
`;

const Logo = styled.img`
  width: 2.1875rem;
  height: 1.0625rem;
`;

const TextBox = styled.div`
  width: 12.5rem;
  height: 1.25rem;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  bottom: 3.5rem;
  left: 50%;
  transform: translateX(-50%);

  p {
    font-size: 0.75rem;
  }
`;

const LoginModal = () => {
  const move = useMovePage();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const validateForm = () => {
    if (email.trim() === '') {
      alert('아이디 입력하셈');
    } else if (password.trim() === '') {
      alert('비밀번호 입력하셈');
    } else if (password.trim().length < 8 || !/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(password)) {
      alert('비밀번호는 8글자 이상, 영문, 숫자, 특수문자 사용');
    } else {
      // 모든 조건을 통과하면 로그인 처리 또는 다른 작업 수행
      alert('로그인 성공');
    }
  };

  return (
    <div>
      <LoginBox>
        <Text
          fontSize="xxl"
          fontWeight="bold"
          style={{
            marginTop: '3rem',
            marginBottom: '2.375rem',
          }}
        >
          로그인
        </Text>
        <FlexBox>
          <CustomInput
            width={22}
            height={3}
            type="email"
            placeholder="아이디 입력"
            fontSize="base"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEmail(e.target.value)}
          ></CustomInput>
          <CustomInput
            width={22}
            height={3}
            type="password"
            placeholder="비밀번호 입력"
            fontSize="base"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setPassword(e.target.value)}
          ></CustomInput>
        </FlexBox>
        <ButtonBox>
          <DefaultButton width={22.375} height={3} padding={1} onClick={validateForm}>
            로그인
          </DefaultButton>
        </ButtonBox>
        <TextBox>
          <p>아직</p>
          <Logo src={logo} alt="zzaug main logo" />
          <p>계정이 없으신가요?</p>
          <Text
            fontWeight='bold'
            style={{
              marginLeft: '0.25rem',
              cursor: 'pointer',
              color: '#1eb649'
            }}
            onClick={() => move('signup')}
          >
            회원가입
          </Text>
        </TextBox>
      </LoginBox>
    </div>
  );
};

export default LoginModal;
