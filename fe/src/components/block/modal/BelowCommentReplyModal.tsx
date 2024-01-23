import styled from 'styled-components';
import reviewerIcon from '../../../assets/icons/reviewerIcon.png';
import Text from '@components/atom/Text';
import ReplyOfCommentButton from '@components/atom/button/ReplyOfCommentButton';
import DefaultButton from '@components/atom/Button/DefaultButton';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 44.25rem;
  height: 11.875rem;
  padding: 0.875rem;
  background-color: white;
  color: black;
  border: 1px solid black;
  border-radius: 0.3125rem;
  position: relative;
  display: flex;
  flex-direction: column;
`;

const UserBox = styled.div`
  width: 42.25rem;
  height: 3.125rem;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  /* border: 1px solid black; */
`;

const ReviewerIcon = styled.img`
  width: 3.125rem;
  height: 3.125rem;
  color: #858585;
`;

const UserInfoBox = styled.div`
  width: 5rem;
  height: 3.125rem;
  /* border: 1px solid black; */
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  gap: 0.25rem;
  margin-left: 0.5rem;
`;

const CommentBox = styled.div`
  width: 42.25rem;
  height: 1.5rem;
  display: flex;
  justify-content: flex-start;
  margin-top: 1rem;
  margin-bottom: 8px;
  /* border: 1px solid black; */
  /* position: absolute; */
`;

const ReplyButtonBox = styled.div`
  width: 42.25rem;
  height: 2.5rem;
  display: flex;
  justify-content: center;
  align-items: center;

  border-top: 1px solid black;
  position: absolute;
  bottom: 0.25rem;
`;

const BelowCommentReplyModal = () => {
  return (
    <div>
      <LoginBox>
        <UserBox>
          <ReviewerIcon src={reviewerIcon} />
          <UserInfoBox>
            <Text fontSize='base' fontWeight='bold'>
              이규민
            </Text>
            <Text fontSize='xxs'>2024년 1월 22일</Text>
          </UserInfoBox>
        </UserBox>
        <CommentBox>
          <Text fontSize='xs'>죄송합니다... 열심히 하겠습니다...</Text>
        </CommentBox>
        <ReplyOfCommentButton />
        <ReplyButtonBox>
          <DefaultButton height={2} fontSize='xs'>
            댓글 작성
          </DefaultButton>
        </ReplyButtonBox>
      </LoginBox>
    </div>
  );
};

export default BelowCommentReplyModal;
