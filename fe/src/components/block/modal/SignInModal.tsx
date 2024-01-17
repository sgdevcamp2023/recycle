import CommonInput from '@components/atom/Input/CommonInput';
import Text from '@components/atom/Text';
import CommonButton from '@components/atom/button/CommonButton';
import { flexCenter } from '@styles/flexCenter';
import styled from 'styled-components';
import logo from '../../../assets/logos/ZzaugLogo.png';
import DoubleCheckButton from '@components/atom/button/DoubleCheckButton';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 29.25rem;
  height: 33rem;
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
  gap: 0.5rem;
`;

const IdBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const ButtonBox = styled.div`
  display: flex;
  flex-direction: column;
  ${flexCenter}
  position: absolute;
  bottom: 3.5rem;
`;

const SignInModal = () => {
  return (
    <div>
      <LoginBox>
        <Text
          fontSize='xxl'
          fontWeight='bold'
          style={{
            marginTop: '3rem',
            marginBottom: '1.25rem',
          }}
        >
          회원가입
        </Text>
        <Text fontSize='lg'>아이디</Text>
        <IdBox>
          <CommonInput
            placeholder='이름 입력'
            width='15.5rem'
            height='3rem'
            // value=''
          />
          <DoubleCheckButton />
        </IdBox>
        <Text
          fontSize='lg'
          style={{
            marginTop: '1.5rem',
          }}
        >
          비밀번호
        </Text>

        <FlexBox>
          <CommonInput
            placeholder='비밀번호 입력'
            // value=''
          />
          <CommonInput
            placeholder='비밀번호 확인'
            // value=''
          />
        </FlexBox>
        <ButtonBox>
          <CommonButton />
        </ButtonBox>
      </LoginBox>
    </div>
  );
};

export default SignInModal;
