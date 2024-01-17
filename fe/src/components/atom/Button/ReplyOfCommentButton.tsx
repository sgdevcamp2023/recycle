import styled from 'styled-components';
import plusImg from '../../../assets/icons/plus.png';
import minusImg from '../../../assets/icons/minus.png';

const ReplyStyleButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 4rem;
  height: 1.5rem;
  color: black;
  border-radius: 0.25rem;
  cursor: pointer;
  font-size: 0.6rem;
  transition: all 0.5s;
  padding: 0;

  &:hover {
    border: 0.063rem solid #1eb649;
  }

  &:focus {
    /* border: none; */
    outline: none;
  }
`;

const PlusIcon = styled.img`
  width: 0.875rem;
  height: 0.875rem;
  margin-right: 0.25rem;
`;

const MinusIcon = styled.img`
  width: 0.875rem;
  height: 0.875rem;
  margin-right: 0.25rem;
`;

const ReplyOfCommentButton = () => {
  return (
    <div>
      <ReplyStyleButton>
        <PlusIcon src={plusImg} alt='Plus Icon' />
        답글 달기
      </ReplyStyleButton>
      <ReplyStyleButton>
        <MinusIcon src={minusImg} alt='Plus Icon' />
        숨기기
      </ReplyStyleButton>
    </div>
  );
};

export default ReplyOfCommentButton;
