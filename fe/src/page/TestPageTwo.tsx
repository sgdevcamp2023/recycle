import Text from "@components/atom/Text";
import DefaultCard from "@components/atom/card/DefaultCard";
import ReviewCard from "@components/atom/card/ReviewCard";
// import IconBox from "@components/atom/icon/IconBox";
import BackgroundModal from "@components/block/modal/BackgroundModal";
import DefaultTab from "@components/block/navbar/DefaultTab";
import SideBar from "@components/block/sideBar/SideBar";
import useModalStore from "@store/useModalStore";
import { DefaultTabType } from "@store/useTabStore";
import { Suspense } from "react";
import styled from "styled-components";

const TestPageTwo = () => {
  const { showModal, setShowModal } = useModalStore();
  const handleOnClickModal = () => {
    setShowModal(!showModal);
  };

  const items: Record<string, DefaultTabType> = { "내가 작성한 질문": "myQuestion", "임시 저장": "questionDrafts" };
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
        <DefaultCard type="add" width={20} height={22} />
        <br />
        <DefaultCard type="question" title="타이틀임" commentCount={32} width={20} height={22} />
        <br />
        <DefaultCard type="review" commentCount={32} title="타이틀임" width={20} height={22} />
      </div>
      <div>
        <ReviewCard></ReviewCard>
      </div>
      <div>
        <DefaultTab items={items} />
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
