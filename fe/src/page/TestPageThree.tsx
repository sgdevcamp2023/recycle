import AskLoginModal from '@components/block/modal/AskLoginModal';
import BelowCommentModal from '@components/block/modal/BelowCommentModal';
import BelowCommentReplyModal from '@components/block/modal/BelowCommentReplyModal';
import LockModal from '@components/block/modal/LockModal';
import RegisterEmailModal from '@components/block/modal/RegisterEmailModal';
import ReviewToGitModal from '@components/block/modal/ReviewToGitModal';
import SaveModal from '@components/block/modal/SaveModal';
import SendEmailModal from '@components/block/modal/SendEmailModal';
import SharingModal from '@components/block/modal/SharingModal';
import ShowReviewerModal from '@components/block/modal/ShowReviewerModal';
import styled from 'styled-components';

const CenterModalBox = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
`;

const TestPageThree = () => {
  return (
    <>
      <CenterModalBox>
        {/* <AskLoginModal /> */}
        {/* <SaveModal /> */}
        {/* <ShowReviewerModal /> */}
        {/* <SharingModal /> */}
        {/* <LockModal /> */}
        {/* <RegisterEmailModal /> */}
        {/* <ReviewToGitModal /> */}
        {/* <SendEmailModal /> */}
        <BelowCommentModal />
        <BelowCommentReplyModal />
      </CenterModalBox>
    </>
  );
};

export default TestPageThree;
