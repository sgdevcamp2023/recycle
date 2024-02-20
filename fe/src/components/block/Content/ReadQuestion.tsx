import Text from '@components/atom/Text';
import useQuestionStore from '@store/useQuestionStore';
import MDEditor from '@uiw/react-md-editor';
import { useEffect, useRef, useState } from 'react';
import styled from 'styled-components';
import { useMarkdownStore } from '@store/useMarkdownStore';
import useReviewStore, { reviewData } from '@store/useReviewStore';
import DefaultBadge from '@components/atom/Mui/DefaultBadge';

const ReadQuestion = () => {
  const { content } = useQuestionStore((state) => state);
  const [show, setShow] = useState(
    "## 대충 코드임\n안녕하세요\n\n저는 이규민입니다\n\n- 이건 코드입니다\n```js\nconst a = '규민'\nconsole.log(a)\n```\n\n- 이건 두번째 코드입니다\n```js\nconst b = '재진'\nconsole.log(b)\n```\n\n모든 코드를 전부 작성하였습니다",
  );
  const { showCodeComment, setShowCodeComment } = useMarkdownStore();
  const { setId } = useReviewStore();
  // const { id, setId } = useReviewStore();

  useEffect(() => {
    const codeBlocks = document.querySelectorAll('code');

    codeBlocks.forEach((codeBlock, index) => {
      const id = index + 1;
      codeBlock.id = id;

      codeBlock.addEventListener('click', (e) => handleClickOnCodeBlock(e, id));
      codeBlock.addEventListener('mouseenter', () => {
        codeBlock.style.border = '1px solid red';
      });
      codeBlock.addEventListener('mouseleave', () => {
        codeBlock.style.border = 'none';
      });
      codeBlock.style.cursor = 'pointer';
    });

    return () => {
      codeBlocks.forEach((codeBlock, index) => {
        const id = index + 1;
        codeBlock.id = id;

        codeBlock.removeEventListener('click', () => handleClickOnCodeBlock(id));
        codeBlock.removeEventListener('mouseenter', () => {
          codeBlock.style.border = '1px solid red';
        });
        codeBlock.removeEventListener('mouseleave', () => {
          codeBlock.style.border = 'none';
        });
      });
    };
  }, []); // 추후 content로 변경 필요

  const handleClickOnCodeBlock = (e, id) => {
    const parentDiv = e.currentTarget.parentElement;
    const parentBorderTop = parentDiv.getBoundingClientRect().top + window.scrollY;
    const modalTop = parentBorderTop;

    setId(id);
    setShowCodeComment({
      top: modalTop,
    });
  };

  return (
    <>
      <TitleWrapper>
        <Text fontSize="xl" fontWeight="bold">
          제목이 들어갈 자리입니다
        </Text>
      </TitleWrapper>
      <MarkdownBox>
        <MDEditor.Markdown source={show} />
      </MarkdownBox>
    </>
  );
};

export default ReadQuestion;

const MarkdownBox = styled.div`
  box-sizing: border-box;
  height: 100%;
  padding: 0.25rem 0.25rem 0.25rem 3rem;
  background-color: white;
  /* border: 1px solid black; */
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
