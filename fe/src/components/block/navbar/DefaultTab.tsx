import DefaultButton from '@components/atom/Button/DefaultButton';
import Text from '@components/atom/Text';
import useTabStore, { DefaultTabType } from '@store/useTabStore';
import { useState } from 'react';
import styled from 'styled-components';

interface DefaultTabProps {
  items: Record<string, DefaultTabType>;
}

interface ItemProps {
  key: string;
  label: DefaultTabType;
}

const DefaultTab = ({ items }: DefaultTabProps) => {
  const [activeKey, setActiveKey] = useState(Object.keys(items)[0]);

  const { setDefaultTabType } = useTabStore();

  const handleItemClick = ({ key, label }: ItemProps) => {
    console.log(key, label);
    setActiveKey(key);
    setDefaultTabType(label);
  };

  return (
    <TabWrapper>
      <NavbarContainer>
        {Object.entries(items).map(([key, label]) => (
          <NavItem isActive={key === activeKey}>
            <DefaultButton
              key={key}
              isActive={key === activeKey}
              onClick={() => handleItemClick({ key, label })}
              height={3}
              width={15}
              padding={1}
              isTabButton={true} // 해당 버튼이 탭 버튼인지 여부
            >
              <Text fontSize="lg" fontWeight="bold">
                {key}
              </Text>
            </DefaultButton>
          </NavItem>
        ))}
      </NavbarContainer>
    </TabWrapper>
  );
};

export default DefaultTab;

const TabWrapper = styled.div`
  width: auto;
  height: 3rem;
`;

const NavbarContainer = styled.div`
  display: flex;
  background-color: white;
  gap: 0.5rem;
`;

const NavItem = styled.div<{ isActive: boolean }>`
  color: ${({ isActive }) => (isActive ? 'white' : '#888')};
  background-color: ${({ theme, isActive }) =>
    isActive ? theme.backgroundColor.green200 : theme.backgroundColor.white};
  border-radius: 8px;
  &:hover {
    background-color: ${({ theme }) => theme.backgroundColor.green200};
    color: white;
  }
`;