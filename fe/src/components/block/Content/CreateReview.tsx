import Text from '@components/atom/Text';
import useQuestionStore from '@store/useQuestionStore';
import MDEditor from '@uiw/react-md-editor';
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import ReviewWriteModal from '../modal/ReviewWriteModal';

const CreateReview = () => {
  const { content } = useQuestionStore((state) => state);
  const [show, setShow] = useState(
    "## 대충 코드임\n안녕하세요\n\n저는 이규민입니다\n\n- 이건 코드입니다\n```js\nconst a = '규민'\nconsole.log(a)\n```\n\n- 이건 두번째 코드입니다\n```js\nconst b = '재진'\nconsole.log(b)\n```\n\n모든 코드를 전부 작성하였습니다",
  );
  const [showCodeComment, setShowCodeComment] = useState(false);

  const handleClickOnCodeBlock = (e) => {
    console.log(e.target.textContent);
    setShowCodeComment(true);
  };

  useEffect(() => {
    const codeBlocks = document.querySelectorAll('code');

    codeBlocks.forEach((codeBlock) => {
      codeBlock.addEventListener('click', handleClickOnCodeBlock);
      codeBlock.addEventListener('mouseenter', () => {
        codeBlock.style.border = '1px solid red';
      });
      codeBlock.addEventListener('mouseleave', () => {
        codeBlock.style.border = 'none';
      });
      codeBlock.style.cursor = 'pointer';
    });

    return () => {
      codeBlocks.forEach((codeBlock) => {
        codeBlock.removeEventListener('click', handleClickOnCodeBlock);
        codeBlock.removeEventListener('mouseenter', () => {
          codeBlock.style.border = '1px solid red';
        });
        codeBlock.removeEventListener('mouseleave', () => {
          codeBlock.style.border = 'none';
        });
      });
    };
  }, [show]); // 추후 content로 변경 필요

  return (
    <>
      <TitleWrapper>
        <Text fontSize="lg">제목이 들어갈 자리입니다</Text>
      </TitleWrapper>
      <MarkdownBox>
        <MDEditor.Markdown source={show} />
        {showCodeComment && <ReviewWriteModal />}
      </MarkdownBox>
    </>
  );
};

export default CreateReview;

const MarkdownBox = styled.div`
  box-sizing: border-box;
  height: 100%;
  padding: 0.25rem 0.25rem 0.25rem 3rem;
  background-color: white;
  border: 1px solid black;
  overflow-y: hidden; /* 항상 수직 스크롤바를 감춤 */
  overflow-x: hidden; /* 가로 스크롤바를 감춤 */
  &:hover {
    overflow-y: auto; /* 마우스를 올렸을 때 수직 스크롤바를 표시 */
  }
  position: relative;
`;
const ButtonBox = styled.div`
  display: flex;
  right: 0.25rem;
  margin-top: 0.5rem;
  position: absolute;
`;
const TitleWrapper = styled.div`
  width: calc(100% - 2.5rem);
  padding-left: 3rem;
`;
