import Text from '@components/atom/Text';
import useLogout from '@hooks/query/member/useLogout';
import useTabStore, { TabType } from '@store/useTabStore';
import { flexCenter } from '@styles/flexCenter';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

const SideBar = () => {
  const { setTabType, tabType } = useTabStore();
  const navigate = useNavigate();

  const handleClickTab = (word: TabType) => {
    setTabType(word);
    navigate(`/${word}`);
  };

  const { mutate: logout } = useLogout();
  const handleLogout = () => {
    logout();
  };

  return (
    <SideBarContainer>
      <SideBarHeader>
        <h2>ZZAUG</h2>
      </SideBarHeader>
      <SideBarContent>
        <Text fontSize="lg" color="black" fontWeight="bold">
          Menu
        </Text>
        <MenuTabContainer>
          <TabButton $isActive={tabType === 'Question'} onClick={() => handleClickTab('Question')}>
            <Text fontSize="lg" fontWeight="bold">
              Question
            </Text>
          </TabButton>
          <TabButton $isActive={tabType === 'Review'} onClick={() => handleClickTab('Review')}>
            <Text fontSize="lg" fontWeight="bold">
              Review
            </Text>
          </TabButton>
          <TabButton $isActive={tabType === 'Request'} onClick={() => handleClickTab('Request')}>
            <Text fontSize="lg" fontWeight="bold">
              Request
            </Text>
          </TabButton>
          <TabButton $isActive={tabType === 'Setting'} onClick={() => handleClickTab('Setting')}>
            <Text fontSize="lg" fontWeight="bold">
              Setting
            </Text>
          </TabButton>
          <TabButton
            onClick={() => {
              handleClickTab('Question');
              alert('logout');
              handleLogout();
              navigate('/main');
            }}
          >
            <Text fontSize="lg" fontWeight="bold">
              SignOut
            </Text>
          </TabButton>
        </MenuTabContainer>
      </SideBarContent>
      <SideBarFooter>SmileGate RE:Camp RE:Cycle</SideBarFooter>
    </SideBarContainer>
  );
};

export default SideBar;

const SideBarContainer = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
  background-color: ${({ theme }) => theme.backgroundColor.grey300};
`;
const SideBarHeader = styled.div`
  ${flexCenter};
  flex: 1;
`;
const SideBarContent = styled.div`
  flex: 10;
  display: flex;
  flex-direction: column;
  padding: 1rem;
  gap: 1rem;
`;
const SideBarFooter = styled.div`
  ${flexCenter};
  flex: 1;
`;

const MenuTabContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
`;

const TabButton = styled.button<{
  $isActive?: boolean;
}>`
  background-color: ${({ $isActive, theme }) =>
    $isActive ? theme.backgroundColor.green300 : theme.backgroundColor.grey300};
  border: none;
  border-radius: 4px;
  padding: 0.5rem;
  cursor: pointer;
  color: ${({ $isActive, theme }) =>
    $isActive
      ? theme.backgroundColor.white
      : theme.backgroundColor.black}; // Change color based on isActive
  font-weight: ${({ $isActive }) =>
    $isActive ? '600' : 'normal'}; // Change font-weight based on isActive

  &:hover {
    background-color: ${({ theme }) => theme.backgroundColor.green300};
    color: white;
    font-weight: 600;
  }
`;
