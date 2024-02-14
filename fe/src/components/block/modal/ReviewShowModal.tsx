import React, { useState } from 'react';
import MDEditor from '@uiw/react-md-editor';
import styled from 'styled-components';
import DefaultButton from '@components/atom/Button/DefaultButton';
import Text from '@components/atom/Text';

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

const ReviewShowModal = () => {
  const [content, setContent] = useState(
    "## 대충 코드임\n안녕하세요\n\n저는 이규민입니다\n\n- 이건 코드입니다\n```js\nconst a = '규민'\nconsole.log(a)\n```\n\n- 이건 두번째 코드입니다\n```js\nconst b = '재진'\nconsole.log(b)\n```\n\n모든 코드를 전부 작성하였습니다",
  );

  return (
    <div>
      <h2>질문자가 리뷰를 보게 될 모달입니다</h2>
      <LoginBox>
        <EditorBox>
          <MDEditor.Markdown source={content} />
        </EditorBox>
        <ButtonBox>
          <DefaultButton width={4} height={2}>
            <Text fontSize="xs">Close</Text>
          </DefaultButton>
        </ButtonBox>
      </LoginBox>
    </div>
  );
};

export default ReviewShowModal;
