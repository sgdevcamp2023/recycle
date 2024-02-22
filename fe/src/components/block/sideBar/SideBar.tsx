import Text from '@components/atom/Text';
import useLogout from '@hooks/query/member/useLogout';
import useTabStore, { TabType } from '@store/useTabStore';
import { flexCenter } from '@styles/flexCenter';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import HeaderHelmet from '@components/atom/Helmet';
import { useState } from 'react';

const SideBar = () => {
  const { setTabType, tabType } = useTabStore();

  const [title, setTitle] = useState<string>();
  const navigate = useNavigate();

  const handleClickTab = (word: TabType) => {
    setTabType(word);
    setTitle(word);
    navigate(`/${word}`);
  };

  const { mutate: logout } = useLogout();
  const handleLogout = () => {
    logout();
  };

  return (
    <SideBarContainer>
      <HeaderHelmet title={title} />
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
              질문하기
            </Text>
          </TabButton>
          <TabButton $isActive={tabType === 'Review'} onClick={() => handleClickTab('Review')}>
            <Text fontSize="lg" fontWeight="bold">
              리뷰하기
            </Text>
          </TabButton>
          <TabButton $isActive={tabType === 'Request'} onClick={() => handleClickTab('Request')}>
            <Text fontSize="lg" fontWeight="bold">
              요청된 리뷰
            </Text>
          </TabButton>
          <TabButton $isActive={tabType === 'Setting'} onClick={() => handleClickTab('Setting')}>
            <Text fontSize="lg" fontWeight="bold">
              설정
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
              로그아웃
            </Text>
          </TabButton>
        </MenuTabContainer>
      </SideBarContent>
      <SideBarFooter>
        <pre>SmileGate </pre>
        <pre>RE:Camp RE:Cycle</pre>
      </SideBarFooter>
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
  display: flex;
  flex-direction: column;
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
