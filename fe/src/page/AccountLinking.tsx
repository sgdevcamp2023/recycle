import styled from 'styled-components';
import logo from '../assets/logos/ZzaugLogo.png';
import Text from '@components/atom/Text';
import ReviewToGitModal from '@components/block/modal/ReviewToGitModal';
import SendEmailModal from '@components/block/modal/SendEmailModal';

const CenterModalBox = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ModalBox = styled.div`
  width: 38.75rem;
  height: 38.25rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 3rem;
`;

const TextBox = styled.div`
  width: 38.75rem;
  height: 2.375rem;
  display: flex;
  align-items: center;
  justify-content: center;
`
const Logo = styled.img`
  height: 3rem;
  margin-right: 0.25rem;
`;


const AccountLinking = () => {
  return (
    <CenterModalBox>
      <ModalBox>
        <TextBox>
          <Logo src={logo} alt="zzaug main logo" />
          <Text fontSize='lg' fontWeight='bold'>에서는 이런 서비스들을 제공해요!</Text>
        </TextBox>
        <ReviewToGitModal/>
        <SendEmailModal/>
      </ModalBox>
    </CenterModalBox>
  );
};

export default AccountLinking;