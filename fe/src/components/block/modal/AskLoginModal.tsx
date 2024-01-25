import Text from '@components/atom/Text';
import { flexCenter } from '@styles/flexCenter';
import styled from 'styled-components';
import logo from '../../../assets/logos/ZzaugLogo.png';
import DefaultButton from '@components/atom/Button/DefaultButton';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 29.25rem;
  height: 18.875rem;
  padding: 0.25rem 1rem;
  background-color: white;
  color: black;
  border: 1px solid #1eb649;
  border-radius: 0.625rem;
  padding-right: 3.4375rem;
  padding-left: 3.4375rem;
  position: relative;
`;

const ButtonBox = styled.div`
  display: flex;
  flex-direction: column;
  ${flexCenter}
  position: absolute;
  bottom: 5.75rem;
`;

const TitleLogo = styled.img`
  width: 5.75rem;
  height: 2.75rem;
  margin: 0 0.125rem;
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
  bottom: 3.75rem;
  left: 50%;
  transform: translateX(-50%);

  p {
    font-size: 0.75rem;
  }
`;

const TitleBox = styled.div`
  width: 100%;
  height: 1.25rem;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  top: 4.625rem;
`;

const AskLoginModal = () => {
  return (
    <div>
      <LoginBox>
        <TitleBox>
          <Text fontSize="lg" fontWeight="bold">
            로그인 후
          </Text>
          <TitleLogo src={logo} alt="zzaug main logo" />
          <Text fontSize="lg" fontWeight="bold">
            이용이 가능해요
          </Text>
        </TitleBox>
        <ButtonBox>
          <DefaultButton width={22.375} height={3} padding={1}>
            로그인
          </DefaultButton>
        </ButtonBox>
        <TextBox>
          <p>아직</p>
          <Logo src={logo} alt="zzaug main logo" />
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

export default AskLoginModal;
