import SideBar from "@components/block/sideBar/SideBar";
import styled from "styled-components";
import { Outlet } from "react-router-dom";
import ContentTab from "@components/block/navbar/ContentTab";

const GridTemplate = () => {
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
          <Outlet />
        </MainContent>
        <RightContent>RightBar</RightContent>
      </MainWrapper>
    </LayoutWrapper>
  );
};

export default GridTemplate;

const LayoutWrapper = styled.div`
  width: 100vw;
  height: 100vh;
  display: grid;
  grid-template-columns: 2fr 10fr;
  grid-template-areas: "Sidebar";
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
