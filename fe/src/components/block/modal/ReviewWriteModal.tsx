import React, { useEffect, useState } from 'react';
import MarkdownEditor from '@uiw/react-md-editor';
import styled from 'styled-components';
import DefaultButton from '@components/atom/Button/DefaultButton';
import Text from '@components/atom/Text';
import GreyButton from '@components/atom/Button/GreyButton';
import ReverseButton from '@components/atom/Button/ReverseButton';
import { useMarkdownStore } from '@store/useMarkdownStore';
import useReviewStore from '@store/useReviewStore';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 37.5rem;
  height: 25.5rem;
  padding: 0.25rem;
  background-color: white;
  color: black;
  border: 1px solid #1eb649;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  position: relative;
  z-index: 1;
`;
const ButtonBox = styled.div`
  display: flex;
  margin-top: 0.3125rem;
  position: absolute;
  bottom: 0.3125rem;
  right: 0.25rem;
  gap: 0.25rem;
`;

const ReviewWriteModal = ({ top }) => {
  const { review, setReview, id } = useReviewStore();
  const { showCodeComment, setShowCodeComment } = useMarkdownStore();
  const [item, setItem] = useState({ id: 0, comment: '' });
  const [input, setInput] = useState<string>('');

  const handleMarkdownChange = (value: string | undefined) => {
    if (value) {
      setInput(value);
    }
  };

  const findObjectById = () => {
    if (review.length > 0) {
      return review.find((item) => item.id === id);
    } else {
      console.log('없음');
    }
  };
  const handleSubmit = () => {
    console.log(id, input);
    // newItem.comment = input;
    setItem({ id: id, comment: input });
    setReview([...review, item]);
  };

  useEffect(() => {
    // setItem(findObjectById());
    // setInput(item?.comment);
    console.log(review);
  }, [id]);

  const handleCloseClick = () => {
    setShowCodeComment(false);
  };
  return (
    <div style={{ position: 'absolute', top }}>
      <LoginBox>
        <MarkdownEditor height={'90%'} value={input} onChange={handleMarkdownChange} />
        <ButtonBox>
          <DefaultButton width={4} height={2} padding={0} onClick={handleSubmit}>
            <Text fontSize="xs">Submit</Text>
          </DefaultButton>
          <ReverseButton width={4} height={2} padding={0} onClick={handleCloseClick}>
            <Text fontSize="xs">Close</Text>
          </ReverseButton>
        </ButtonBox>
      </LoginBox>
    </div>
  );
};

export default ReviewWriteModal;
