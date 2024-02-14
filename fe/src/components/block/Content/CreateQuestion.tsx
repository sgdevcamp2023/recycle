import styled from 'styled-components';
import DefaultInput from '../Search/DefaultInput';
import { useEffect, useRef, useState } from 'react';
import MarkdownEditor from '@uiw/react-md-editor';

const MarkdownBox = styled.div`
  box-sizing: border-box;
  height: 90%;
  padding: 0.25rem 0.25rem 0.25rem 3rem;
  background-color: white;
  border: 1px solid black;
  overflow-y: hidden; /* 항상 수직 스크롤바를 감춤 */
  overflow-x: hidden; /* 가로 스크롤바를 감춤 */
  &:hover {
    overflow-y: auto; /* 마우스를 올렸을 때 수직 스크롤바를 표시 */
  }
`;

const CreateQuestion = () => {
  const [markdown, setMarkdown] = useState('');

  const handleMarkdownChange = (value: string | undefined) => {
    if (value) {
      setMarkdown(value);
    }
  };

  const ref = useRef<HTMLInputElement>(null);

  useEffect(() => {}, [ref?.current?.value]);

  return (
    <>
      <TitleWrapper>
        <DefaultInput
          type="text"
          height={40}
          ref={ref}
          fontSize="xl"
          $backgroundColor="white"
          placeholder="제목을 입력해주세요"
          padding="0 0.5rem 0.5rem 0.5rem"
        />
      </TitleWrapper>
      <MarkdownBox>
        <MarkdownEditor height={'100%'} value={markdown} onChange={handleMarkdownChange} />
      </MarkdownBox>
    </>
  );
};

export default CreateQuestion;

const TitleWrapper = styled.div`
  width: calc(100% - 2.5rem);
  padding-left: 3rem;
`;
