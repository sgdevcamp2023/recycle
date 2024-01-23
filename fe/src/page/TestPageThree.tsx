import { Button } from '@/stories/Button';
import DefaultButton from '@components/atom/Button/DefaultButton';
import EmailEnrollButton from '@components/atom/Button/EmailEnrollButton';
import ExperienceButton from '@components/atom/Button/ExperienceButton';
import GreyButton from '@components/atom/Button/GreyButton';
import InviteButton from '@components/atom/Button/InviteButton';
import LinkToGitButton from '@components/atom/Button/LinkToGitButton';
import ModifyButton from '@components/atom/Button/ModifyButton';
import RegisterButton from '@components/atom/Button/RegisterButton';
import ReverseButton from '@components/atom/Button/ReverseButton';
import SaveReviewButton from '@components/atom/Button/SaveReviewButton';
import LineCommentView from '@components/atom/Comment/LineCommentView';
import LineCommentWrite from '@components/atom/Comment/LineCommentWrite';
import BelowCommentButton from '@components/atom/button/BelowCommentButton';
import DoubleCheckButton from '@components/atom/button/DoubleCheckButton';
import ReplyOfCommentButton from '@components/atom/button/ReplyOfCommentButton';
import ReturnButton from '@components/atom/button/ReturnButton';
import ReviewButton from '@components/atom/button/ReviewButton';
import TemporaryStorageButton from '@components/atom/button/TemporaryStorageButton';
import AskLoginModal from '@components/block/modal/AskLoginModal';
import BackgroundModal from '@components/block/modal/BackgroundModal';
import BelowCommentModal from '@components/block/modal/BelowCommentModal';
import BelowCommentReplyModal from '@components/block/modal/BelowCommentReplyModal';
import LockModal from '@components/block/modal/LockModal';
import LoginModal from '@components/block/modal/LoginModal';
import RegisterEmailModal from '@components/block/modal/RegisterEmailModal';
import ReviewToGitModal from '@components/block/modal/ReviewToGitModal';
import SaveModal from '@components/block/modal/SaveModal';
import SendEmailModal from '@components/block/modal/SendEmailModal';
import SharingModal from '@components/block/modal/SharingModal';
import ShowReviewerModal from '@components/block/modal/ShowReviewerModal';
import SignInModal from '@components/block/modal/SignInModal';
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
        {/* <SignInModal /> */}
        {/* <SaveModal /> */}
        {/* <ShowReviewerModal /> */}
        {/* <SharingModal /> */}
        {/* <LockModal /> */}
        {/* <RegisterEmailModal /> */}
        {/* <ReviewToGitModal /> */}
        {/* <SendEmailModal /> */}
        {/* <BelowCommentModal /> */}
        {/* <BelowCommentReplyModal /> */}
        <LineCommentView />
        <ModifyButton />
        <SaveModal />
      </CenterModalBox>
    </>
  );
};

export default TestPageThree;
