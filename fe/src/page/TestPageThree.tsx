import AskLoginModal from '@components/block/modal/AskLoginModal';
import SaveModal from '@components/block/modal/SaveModal';
import ShowReviewerModal from '@components/block/modal/ShowReviewerModal';
import styled from 'styled-components';

const CenterModalBox = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const TestPageThree = () => {
  return (
    <>
      <CenterModalBox>
        {/* <AskLoginModal /> */}
        {/* <SaveModal /> */}
        <ShowReviewerModal />
      </CenterModalBox>
    </>
  );
};

export default TestPageThree;
