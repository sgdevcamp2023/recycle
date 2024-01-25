import DefaultButton from '@components/atom/Button/DefaultButton';
import CustomInput from '@components/atom/Input/CustomInput';
import Text from '@components/atom/Text';
import { flexCenter } from '@styles/flexCenter';
import styled from 'styled-components';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 29.25rem;
  height: 19.875rem;
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
  align-items: flex-start;
  gap: 0.5rem;
  bottom: 7.9375rem;
`;

const ButtonBox = styled.div`
  display: flex;
  flex-direction: column;
  ${flexCenter}
  position: absolute;
  bottom: 4.0625rem;
`;

const RegisterEmailModal = () => {
  return (
    <div>
      <LoginBox>
        <Text
          fontSize="lg"
          fontWeight="bold"
          style={{
            marginTop: '3rem',
            marginBottom: '1.25rem',
          }}
        >
          이메일 등록하기
        </Text>
        <FlexBox>
          <Text
            fontSize="base"
            fontWeight="bold"
            style={{
              marginTop: '1.5rem',
              display: 'block',
            }}
          >
            이메일
          </Text>
          <CustomInput type="email" width={22} height={3} placeholder="이메일 입력" />
        </FlexBox>
        <ButtonBox>
          {/* <RegisterButton /> */}
          <DefaultButton width={22.375} height={3} padding={1}>
            등록하기
          </DefaultButton>
        </ButtonBox>
      </LoginBox>
    </div>
  );
};

export default RegisterEmailModal;
