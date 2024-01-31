import styled from 'styled-components';
import logo from '../assets/logos/ZzaugLogo.png';
import Text from '@components/atom/Text';
import ReviewToGitModal from '@components/block/modal/ReviewToGitModal';
import SendEmailModal from '@components/block/modal/SendEmailModal';
import SideBar from '@components/block/sideBar/SideBar';
import ContentTab from '@components/block/navbar/ContentTab';

const ModalBox = styled.div`
  width: 38.75rem;
  height: 38.25rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 3rem;
  margin-left: 10rem;
`;

const TextBox = styled.div`
  width: 38.75rem;
  height: 2.375rem;
  display: flex;
  align-items: center;
  justify-content: center;
`;
const Logo = styled.img`
  height: 3rem;
  margin-right: 0.25rem;
`;

const LayoutWrapper = styled.div`
  width: 100vw;
  height: 100vh;
  display: grid;
  grid-template-columns: 2fr 10fr;
  grid-template-areas: 'Sidebar';
`;
const MainWrapper = styled.div`
  display: grid;
  grid-template-rows: 1fr 11fr;
  grid-template-columns: 7fr 3fr;
`;

const TopHeader = styled.div`
  grid-column: 1 / span 10;
  grid-row: 1 / span 1;
  display: flex;
  justify-content: end;
`;

const MainContent = styled.div`
  display: inline;
  grid-column: 1 / span 7;
  grid-row: 2 / span 11;
  max-width: calc(70% - 2rem);
  padding: 1rem;
`;

const RightContent = styled.div`
  grid-column: 2 / span 3; /* Updated grid-column */
  grid-row: 2 / span 20; /* Updated grid-row */
  padding: 1rem;
`;

const AccountLinking = () => {
  return (
    <LayoutWrapper>
      <div>
        <SideBar />
      </div>
      <MainWrapper>
        <TopHeader>
          <ContentTab />
        </TopHeader>
        <MainContent>
          <ModalBox>
            <TextBox>
              <Logo src={logo} alt="zzaug main logo" />
              <Text fontSize="lg" fontWeight="bold">
                에서는 이런 서비스들을 제공해요!
              </Text>
            </TextBox>
            <ReviewToGitModal />
            <SendEmailModal />
          </ModalBox>
        </MainContent>
        <RightContent>RightBar</RightContent>
      </MainWrapper>
    </LayoutWrapper>
  );
};

export default AccountLinking;
