import React, { useState } from 'react';
import MDEditor from '@uiw/react-md-editor';
import styled from 'styled-components';
import DefaultButton from '@components/atom/Button/DefaultButton';
import Text from '@components/atom/Text';
import useReviewStore from '@store/useReviewStore';
import { useMarkdownStore } from '@store/useMarkdownStore';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 37.5rem;
  height: 25.5rem;
  padding: 0.5rem;
  background-color: white;
  color: black;
  border: 1px solid #1eb649;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  position: relative;
`;

const EditorBox = styled.div`
  box-sizing: border-box;
  width: 100%;
  height: 90%;
  overflow-y: hidden; /* 항상 수직 스크롤바를 감춤 */
  overflow-x: hidden; /* 가로 스크롤바를 감춤 */
  &:hover {
    overflow-y: auto; /* 마우스를 올렸을 때 수직 스크롤바를 표시 */
  }
`;

const ButtonBox = styled.div`
  display: flex;
  margin-top: 0.3125rem;
  position: absolute;
  bottom: 0.3125rem;
  right: 0.3125rem;
`;

const ReviewShowModal = ({ top }) => {
  const { review, setReview, id } = useReviewStore();
  const { showCodeComment, setShowCodeComment } = useMarkdownStore();

  const handleCloseClick = () => {
    setShowCodeComment(false);
  };

  return (
    <div style={{ position: 'absolute', top }}>
      <LoginBox>
        <EditorBox>
          <MDEditor.Markdown source={review} />
        </EditorBox>
        <ButtonBox>
          <DefaultButton width={4} height={2} onClick={handleCloseClick}>
            <Text fontSize="xs">Close</Text>
          </DefaultButton>
        </ButtonBox>
      </LoginBox>
    </div>
  );
};

export default ReviewShowModal;
