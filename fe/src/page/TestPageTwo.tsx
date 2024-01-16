import Text from "@components/atom/Text";
import BackgroundModal from "@components/block/modal/BackgroundModal";
import useModalStore from "@store/useModalStore";
import { Suspense } from "react";

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
    </>
  );
};

export default TestPageTwo;
