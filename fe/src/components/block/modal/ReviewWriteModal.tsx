import React, { useEffect, useState } from 'react';
import MarkdownEditor from '@uiw/react-md-editor';
import styled from 'styled-components';
import DefaultButton from '@components/atom/Button/DefaultButton';
import Text from '@components/atom/Text';
import GreyButton from '@components/atom/Button/GreyButton';
import ReverseButton from '@components/atom/Button/ReverseButton';
import { useMarkdownStore } from '@store/useMarkdownStore';
import useReviewStore from '@store/useReviewStore';
import useSaveReview from '@hooks/query/review/useSaveReview';
import { ReviewSubmitProps } from '../Content/CreateReview';
import { useParams } from 'react-router-dom';

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
  const { reviewId } = useParams<{ reviewId: string }>();

  const handleMarkdownChange = (value: string | undefined) => {
    if (value) {
      setInput(value);
    }
  };
  const findObjectById = () => {
    const foundItem = review.find((item) => item.id === id);
    return foundItem ? foundItem : false;
  };
  const handleSubmit = () => {
    console.log(id, input);
    // newItem.comment = input;
    setItem({ id: id, comment: input });
    if (!review) {
      setReview([{ id: parseInt(id), comment: input }]);
    } else {
      setReview([...review, { id: parseInt(id), comment: input }]);
    }

    const testReview: ReviewSubmitProps = {
      content: input,
      startPoint: {
        point: id,
        index: 0,
      },
      endPoint: {
        point: id,
        index: 0,
      },
      tag: 'CODE',
    };
    mutate({ content: testReview, questionId: reviewId });
    setShowCodeComment(false);
  };

  const { mutate } = useSaveReview();

  const findObjectByIdTwo = (keyId) => {
    if (review) {
      console.log(review);
      const foundItem = review.find((item) => item.id === keyId);
      console.log(foundItem);
      return foundItem ? foundItem : false;
    } else {
      return false;
    }
  };
  useEffect(() => {
    console.log(id);
    if (findObjectByIdTwo(parseInt(id))) {
      setItem(findObjectByIdTwo(parseInt(id)));
    } else {
      setItem({ id: 0, comment: '' });
    }
    setInput(item?.comment);
  }, [id, item?.comment]);

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
