import BelowCommentButton from '@components/atom/Button/BelowCommentButton';
import CommonButton from '@components/atom/Button/CommonButton';
import DoubleCheckButton from '@components/atom/Button/DoubleCheckButton';
import ModifyButton from '@components/atom/Button/ModifyButton';
import PaginationButton from '@components/atom/Button/PaginationButton';
import ReplyOfCommentButton from '@components/atom/Button/ReplyOfCommentButton';
import ReturnButton from '@components/atom/Button/ReturnButton';
import ReviewButton from '@components/atom/Button/ReviewButton';
import TemporaryStorageButton from '@components/atom/Button/TemporaryStorageButton';
import ToggleButton from '@components/atom/Button/ToggleButton';

const TestPage = () => {
  return (
    <>
      <>테스트페이지임여</>
      <CommonButton></CommonButton>
      <ReviewButton></ReviewButton>
      <BelowCommentButton></BelowCommentButton>
      <DoubleCheckButton></DoubleCheckButton>
      <ReturnButton></ReturnButton>
      <TemporaryStorageButton></TemporaryStorageButton>
      <ModifyButton></ModifyButton>
      <ReplyOfCommentButton></ReplyOfCommentButton>
      <ToggleButton></ToggleButton>
      <PaginationButton></PaginationButton>
    </>
  );
};

export default TestPage;
