import styled from 'styled-components';
import Text from '@components/atom/Text';
import mainLogo from '../../../assets/logos/ZzaugLogo.png';
import phoneLogo from '../../../assets/icons/PhoneIcon.png';
import EmailEnrollButton from '@components/atom/Button/EmailEnrollButton';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 38.75rem;
  height: 14.375rem;
  padding: 0.75rem 0.625rem;
  background-color: white;
  color: black;
  border: 1px solid black;
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const GithubLogo = styled.img`
  width: 13.75rem;
  position: absolute;
  /* height: 11.25rem; */
  right: 0;
  bottom: -0.8125rem;
`;

const LeftBox = styled.div`
  width: 20rem;
  height: 11.25rem;
  /* border: 1px solid black; */
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  position: relative;
`;

const Title = styled.div`
  font-size: 1.5rem;
  font-weight: 700;
`;

const Content = styled.div`
  width: 20rem;
  height: 2.5rem;
  /* border: 1px solid black; */
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  /* gap: 0.25rem; */
  justify-content: space-between;
  position: absolute;
  top: 3.25rem;
`;

const ButtonBox = styled.div`
  width: 20rem;
  height: 3.375rem;
  /* border: 1px solid black; */
  display: flex;
  align-items: flex-end;
  justify-content: flex-start;
  position: absolute;
  bottom: 0;
  gap: 0.25rem;
`;

const Logo = styled.img`
  /* width: 3rem; */
  height: 1.2rem;
  display: inline;
  float: left;
`;

const SendEmailModal = () => {
  return (
    <div>
      <LoginBox>
        <LeftBox>
          <Title>내 리뷰를 깃허브에 올려봐요</Title>
          <Content>
            <Text fontSize='xs'>언제 리뷰가 달릴지 기다리지 마세요!</Text>
            <Text
              fontSize='xs'
              style={{ display: 'flex', alignItems: 'center' }}
            >
              <Logo src={mainLogo} alt='zzaug main logo' />
              에서는 등록한 메일로 알람을 보내드립니다!
            </Text>
          </Content>
          <ButtonBox>
            <EmailEnrollButton />
          </ButtonBox>
        </LeftBox>
        <GithubLogo src={phoneLogo} alt='Phone Icon' />
      </LoginBox>
    </div>
  );
};

export default SendEmailModal;
