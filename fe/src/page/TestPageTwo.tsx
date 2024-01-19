import Text from "@components/atom/Text";
import DefaultCard from "@components/atom/card/DefaultCard";
// import IconBox from "@components/atom/icon/IconBox";
import BackgroundModal from "@components/block/modal/BackgroundModal";
import SideBar from "@components/block/sideBar/SideBar";
import useModalStore from "@store/useModalStore";
import { Suspense } from "react";
import styled from "styled-components";

const TestPageTwo = () => {
  const { showModal, setShowModal } = useModalStore();
  const handleOnClickModal = () => {
    setShowModal(!showModal);
  };
  return (
    <>
      <div>
        <Text>Hello</Text>
        <button onClick={() => handleOnClickModal()}>open</button>
        {showModal && (
          <Suspense fallback={null}>
            <BackgroundModal>
              Hello
              <button onClick={() => handleOnClickModal()}>close</button>
            </BackgroundModal>
          </Suspense>
        )}
      </div>
      <SidebarWrapper>
        <SideBar />
      </SidebarWrapper>
      <div>
        <DefaultCard type="add" />
        <br />
        <DefaultCard type="question" title="타이틀임" />
        <br />
        <DefaultCard type="review" commentCount={32} title="타이틀임" />
      </div>
    </>
  );
};

export default TestPageTwo;

const SidebarWrapper = styled.div`
  width: 15rem;
  height: 60rem;
  border: 1px solid black;
`;
