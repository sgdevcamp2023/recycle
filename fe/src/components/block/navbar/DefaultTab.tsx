import useTabStore, { DefaultTabType } from "@store/useTabStore";
import { useState } from "react";
import styled from "styled-components";

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
          <NavItem key={key} isActive={key === activeKey} onClick={() => handleItemClick({ key, label })}>
            {key}
          </NavItem>
        ))}
      </NavbarContainer>
    </TabWrapper>
  );
};

export default DefaultTab;

const TabWrapper = styled.div`
  width: auto;
  height: 4rem;
`;

const NavbarContainer = styled.div`
  display: flex;
  background-color: white;
  padding: 10px;
`;

const NavItem = styled.div<{ isActive: boolean }>`
  padding: 8px 16px;
  cursor: pointer;
  color: ${({ isActive }) => (isActive ? "#000" : "#888")};
  border-radius: 8px;
  &:hover {
    background-color: ${({ theme }) => theme.backgroundColor.green300};
    color: white;
  }
`;
