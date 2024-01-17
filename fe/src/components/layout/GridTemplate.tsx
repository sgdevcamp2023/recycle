import SideBar from "@components/block/sideBar/SideBar";
import styled from "styled-components";

const GridTemplate = () => {
  return (
    <LayoutWrapper>
      <div>
        <SideBar />
      </div>
      <div>Main</div>
      <div>RightBar</div>
    </LayoutWrapper>
  );
};

export default GridTemplate;

const LayoutWrapper = styled.div`
  width: 100vw;
  height: 100vh;
  display: grid;
  grid-template-columns: 2fr 7fr 3fr;
`;
