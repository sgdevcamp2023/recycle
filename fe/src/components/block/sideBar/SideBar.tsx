import { flexCenter } from "@styles/flexCenter";
import styled from "styled-components";

const SideBar = () => {
  return (
    <SideBarContainer>
      <SideBarHeader>Logo</SideBarHeader>
      <SideBarContent></SideBarContent>
      <SideBarFooter>SmileGate RE:Camp RE:Cycle</SideBarFooter>
    </SideBarContainer>
  );
};

export default SideBar;

const SideBarContainer = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
`;
const SideBarHeader = styled.div`
  ${flexCenter};
  flex: 1;
  border: 1px solid black;
`;
const SideBarContent = styled.div`
  flex: 8;
  border: 1px solid black;
`;
const SideBarFooter = styled.div`
  ${flexCenter};
  flex: 1;
  border: 1px solid black;
`;
