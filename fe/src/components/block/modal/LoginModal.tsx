import CommonInput from '@components/atom/Input/CommonInput';
import Text from '@components/atom/Text';
import CommonButton from '@components/atom/button/CommonButton';
import { flexCenter } from '@styles/flexCenter';
import styled from 'styled-components';
import logo from '../../../assets/logos/ZzaugLogo.png';
import DefaultButton from '@components/atom/Button/DefaultButton';

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
  bottom: 4rem;
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
  bottom: 2.5rem;
  left: 50%;
  transform: translateX(-50%);

  p {
    font-size: 0.75rem;
  }
`;

const LoginModal = () => {
  return (
    <div>
      <LoginBox>
        <Text
          fontSize='xxl'
          fontWeight='bold'
          style={{
            marginTop: '3rem',
            marginBottom: '2.375rem',
          }}
        >
          로그인
        </Text>
        <FlexBox>
          <CommonInput
            placeholder='이메일 입력'
            // value=''
          ></CommonInput>
          <CommonInput
            placeholder='비밀번호 입력'
            border='1px solid #1eb649'
            // value=''
          ></CommonInput>
        </FlexBox>
        <ButtonBox>
          <DefaultButton width={22.375} height={3} padding={1}>
            로그인
          </DefaultButton>
        </ButtonBox>
        <TextBox>
          <p>아직</p>
          <Logo src={logo} alt='zzaug main logo' />
          <p>계정이 없으신가요?</p>
          <p
            style={{
              marginLeft: '0.25rem',
            }}
          >
            회원가입
          </p>
        </TextBox>
      </LoginBox>
    </div>
  );
};

export default LoginModal;
