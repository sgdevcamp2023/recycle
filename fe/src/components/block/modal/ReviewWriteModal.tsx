import React, { useState } from 'react';
import MarkdownEditor from '@uiw/react-md-editor';
import styled from 'styled-components';
import DefaultButton from '@components/atom/Button/DefaultButton';
import Text from '@components/atom/Text';

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
`;
const ButtonBox = styled.div`
  display: flex;
  margin-top: 0.3125rem;
  position: absolute;
  bottom: 0.3125rem;
  right: 0.3125rem;
`;

const ReviewWriteModal = () => {
  const [markdown, setMarkdown] = useState('');

  const handleMarkdownChange = (value: string | undefined) => {
    if (value) {
      setMarkdown(value);
    }
  };

  return (
    <div>
      <h2>리뷰어가 리뷰를 작성할 모달입니다</h2>
      <LoginBox>
        <MarkdownEditor height={'90%'} value={markdown} onChange={handleMarkdownChange} />
        <ButtonBox>
          <DefaultButton width={4} height={2}>
            <Text fontSize="xs">Submit</Text>
          </DefaultButton>
        </ButtonBox>
      </LoginBox>
    </div>
  );
};

export default ReviewWriteModal;
