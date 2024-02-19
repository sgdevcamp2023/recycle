import BelowCommentWrite from '@components/atom/Comment/BelowCommentWrite';
import LineCommentView from '@components/atom/Comment/LineCommentView';
import LineCommentWrite from '@components/atom/Comment/LineCommentWrite';
import CommonInput from '@components/atom/Input/CommonInput';
import CustomInput from '@components/atom/Input/CustomInput';
import DefaultInput from '@components/atom/Input/CustomInput';
import BackgroundModal from '@components/block/modal/BackgroundModal';
import BelowCommentModal from '@components/block/modal/BelowCommentModal';
import ErrorModal from '@components/block/modal/ErrorModal';
import LoginModal from '@components/block/modal/LoginModal';
import RegisterEmailModal from '@components/block/modal/RegisterEmailModal';
import SignInModal from '@components/block/modal/SignInModal';

import styled from 'styled-components';
import RegisterEmail from './RegisterEmail';
import SendEmailModal from '@components/block/modal/SendEmailModal';
import ReviewWriteModal from '@components/block/modal/ReviewWriteModal';
import ReviewShowModal from '@components/block/modal/ReviewShowModal';
import AskLoginModal from '@components/block/modal/AskLoginModal';
import LockModal from '@components/block/modal/LockModal';
import ReviewToGitModal from '@components/block/modal/ReviewToGitModal';
import SaveModal from '@components/block/modal/SaveModal';
import SharingModal from '@components/block/modal/SharingModal';
import ShowReviewerModal from '@components/block/modal/ShowReviewerModal';

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
        {/* <AskLoginModal />
        <LockModal />
        <RegisterEmailModal />
        <ReviewToGitModal />
        <SaveModal />
        <SendEmailModal />
        <SharingModal /> */}
        <ShowReviewerModal />
      </CenterModalBox>
    </>
  );
};

export default TestPageThree;
