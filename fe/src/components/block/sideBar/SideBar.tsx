import Text from '@components/atom/Text';
import { flexCenter } from '@styles/flexCenter';
import styled from 'styled-components';

const SideBar = () => {
  return (
    <SideBarContainer>
      <SideBarHeader></SideBarHeader>
      <SideBarContent>
        <Text color='black' fontWeight='bold' fontSize='base'>
          Menu
        </Text>
        <MenuTabContainer>
          <TabButton>
            <Text fontWeight='bold'>
              <QuestionIcon />
              Question
            </Text>
          </TabButton>
          <TabButton>
            <Text fontWeight='bold'>Review</Text>
          </TabButton>
          <TabButton>
            <Text fontWeight='bold'>Setting</Text>
          </TabButton>
          <TabButton>
            <Text fontWeight='bold'>SignOut</Text>
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
  background-color: ${({ theme }) => theme.backgroundColor.grey3};
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
`;

const TabButton = styled.button`
  background-color: ${({ theme }) => theme.backgroundColor.grey3};
  border: none;
  cursor: pointer;
  &:hover {
    background-color: ${({ theme }) => theme.backgroundColor.green3};
    color: white;
    font-weight: 600;
  }
`;
