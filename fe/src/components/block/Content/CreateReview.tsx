import Text from '@components/atom/Text';
import useQuestionStore from '@store/useQuestionStore';
import MDEditor from '@uiw/react-md-editor';
import { useEffect, useRef, useState } from 'react';
import styled from 'styled-components';
import ReviewWriteModal from '../modal/ReviewWriteModal';
import { useMarkdownStore } from '@store/useMarkdownStore';
import useReviewStore, { reviewData } from '@store/useReviewStore';
import { Popover } from '@page/PopOver';

const CreateReview = () => {
  const { content } = useQuestionStore((state) => state);
  const [show, setShow] = useState(
    "## 대충 코드임\n안녕하세요\n\n저는 이규민입니다\n\n- 이건 코드입니다\n```js\nconst a = '규민'\nconsole.log(a)\n```\n\n- 이건 두번째 코드입니다\n```js\nconst b = '재진'\nconsole.log(b)\n```\n\n모든 코드를 전부 작성하였습니다",
  );
  const { showCodeComment, setShowCodeComment } = useMarkdownStore();
  const { setId, setReviewList, reviewList } = useReviewStore();
  // const { id, setId } = useReviewStore();
  const [target, setTarget] = useState<HTMLElement | null>(null);
  const [selectedIndices, setSelectedIndices] = useState<number[]>([]);
  const [temp, setTemp] = useState<string[]>([]);
  // const [type, setType] = useState<NodeType>();
  const [refId, setRefId] = useState<string | null>();
  const testRef = useRef<HTMLDivElement>(null);
  useEffect(() => {
    setTarget(document.getElementById('wrapper'));
  }, []);

  const handleShareMeClick = () => {
    const { anchorNode, focusNode, anchorOffset, focusOffset } = window.getSelection() as Selection;
    const startNode = anchorNode?.parentElement;
    const endNode = focusNode?.parentElement;
    if (startNode?.parentElement?.className == 'code-line') {
      alert('code는 라인코멘트를 달 수 없습니다!');
    } else if (startNode == endNode) {
      console.log('same');
      const text = anchorNode?.textContent || '';
      const start = anchorOffset;
      const end = focusOffset;
      setTemp([...temp, text.substring(start, end)]);
      const newData: reviewData = {
        startIdx: start,
        endIdx: end,
        reviewText: text.substring(start, end),
        reviewId: refId,
      };
      setReviewList([...reviewList, newData]);
    } else if (startNode && endNode) {
      const startIndex = Array.from(startNode.childNodes).indexOf(anchorNode);
      const endIndex = Array.from(endNode.childNodes).indexOf(focusNode);
      setSelectedIndices([startIndex + anchorOffset, endIndex + focusOffset]);
      const wrapperElement = testRef.current?.childNodes[0];
      console.log(startNode.nodeName, endNode.nodeName);
      setRefId(
        startNode.nodeName !== anchorNode.nodeName ? anchorNode?.parentNode?.id : anchorNode.id,
      );

      if (wrapperElement && refId) {
        console.log(Array.from(wrapperElement.childNodes));
        const textNodes = Array.from(wrapperElement.childNodes).filter((node) => node.id === refId);
        console.log(textNodes);
        textNodes.map((node) => {
          const text = node.textContent || '';
          const start = anchorOffset;
          const end = focusOffset;
          setTemp([...temp, text.substring(start, end)]);
          const newData: reviewData = {
            startIdx: start,
            endIdx: end,
            reviewText: text.substring(start, end),
            reviewId: refId,
          };
          setReviewList([...reviewList, newData]);
        });
      }
    }
  };

  useEffect(() => {
    console.log('ID updated:', refId);
    const { anchorOffset, focusOffset } = window.getSelection() as Selection;
    if (refId) {
      const wrapperElement = testRef.current;
      // setType(wrapperElement?.nodeType);
      if (wrapperElement) {
        const textNodes = Array.from(wrapperElement.childNodes).filter((node) => node.id === refId);
        textNodes.map((node) => {
          const text = node.textContent || '';
          const start = anchorOffset;
          const end = focusOffset;
          setTemp([...temp, text.substring(start, end)]);
          const newData: reviewData = {
            startIdx: start,
            endIdx: end,
            reviewText: text.substring(start, end),
            reviewId: refId,
          };
          setReviewList([...reviewList, newData]);
        });
      }
    }
  }, [refId]);

  const handleItemClicked = (id: string) => {
    const node = document.getElementById(refId);
    if (node) {
      node.scrollIntoView({ behavior: 'smooth' });
    }
  };
  const ref = useRef<HTMLInputElement>(null);
  const str = `
### Preview Markdown

[![Open in CodeSandbox](https://img.shields.io/badge/Open%20in-CodeSandbox-blue?logo=codesandbox)](https://codesandbox.io/embed/react-md-editor-preview-markdown-vrucl?fontsize=14&hidenavigation=1&theme=dark)

### hello world!

#### 안녕하세요

## 찬수님

### “커리어 화성 갈거니까: 개발자 커리어의 억까와 행운”

- 일은 받는 것이 아니다 → 스스로 찾고 알아가는 거? 쉽지 않음
    - 일을 달라고 하는 것도 쉽지가 않음
    - 인정 받고 싶은 욕심도 어떻게 보면 영향이 있음
    - 이런 내용들을 잘 알지만, 한번 인간 관계가 영향이 생기면 뒤집기가 쉽지 않음
- 이직 ㄱ
    - 면접을 보고 솔직하게 매니저님께 얘기
    - 찬수님이 좋아하는 일 하기
    - 연봉 상승
    
    → 크레딧을 충분히 쌓았을 때 할 것. (크레딧 : 신뢰?)
    
- 나한테 필요한 일들을 하다 보면 다른 일도 되는?
    - 자연스럽게 문제들이 해결(알빠 정신..?)
- 너만 오면 ㄱ
    - 친구 스타트업 놀러갔다가 이직 제의
    - 스타트업 희망편 = 스톡옵션 + 린 스타트업 → 파멸편 = 2주만의 피벗 + 야근 넘치는 문화 + MVP 뽑아야됨
    - 테크리드가 되어보니 과거의 매니저님을 대단하게 생각하게 됨 😆
    - 인생은 새옹지마(타이밍이 언제 올 지 모른다 😅)
    ### “커리어 화성 갈거니까: 개발자 커리어의 억까와 행운”

- 일은 받는 것이 아니다 → 스스로 찾고 알아가는 거? 쉽지 않음
    - 일을 달라고 하는 것도 쉽지가 않음
    - 인정 받고 싶은 욕심도 어떻게 보면 영향이 있음
    - 이런 내용들을 잘 알지만, 한번 인간 관계가 영향이 생기면 뒤집기가 쉽지 않음
- 이직 ㄱ
    - 면접을 보고 솔직하게 매니저님께 얘기
    - 찬수님이 좋아하는 일 하기
    - 연봉 상승
    
    → 크레딧을 충분히 쌓았을 때 할 것. (크레딧 : 신뢰?)
    
- 나한테 필요한 일들을 하다 보면 다른 일도 되는?
    - 자연스럽게 문제들이 해결(알빠 정신..?)
- 너만 오면 ㄱ
    - 친구 스타트업 놀러갔다가 이직 제의
    - 스타트업 희망편 = 스톡옵션 + 린 스타트업 → 파멸편 = 2주만의 피벗 + 야근 넘치는 문화 + MVP 뽑아야됨
    - 테크리드가 되어보니 과거의 매니저님을 대단하게 생각하게 됨 😆
    - 인생은 새옹지마(타이밍이 언제 올 지 모른다 😅)
    ### “커리어 화성 갈거니까: 개발자 커리어의 억까와 행운”

- 일은 받는 것이 아니다 → 스스로 찾고 알아가는 거? 쉽지 않음
    - 일을 달라고 하는 것도 쉽지가 않음
    - 인정 받고 싶은 욕심도 어떻게 보면 영향이 있음
    - 이런 내용들을 잘 알지만, 한번 인간 관계가 영향이 생기면 뒤집기가 쉽지 않음
- 이직 ㄱ
    - 면접을 보고 솔직하게 매니저님께 얘기
    - 찬수님이 좋아하는 일 하기
    - 연봉 상승
    
    → 크레딧을 충분히 쌓았을 때 할 것. (크레딧 : 신뢰?)
    
- 나한테 필요한 일들을 하다 보면 다른 일도 되는?
    - 자연스럽게 문제들이 해결(알빠 정신..?)
- 너만 오면 ㄱ
    - 친구 스타트업 놀러갔다가 이직 제의
    - 스타트업 희망편 = 스톡옵션 + 린 스타트업 → 파멸편 = 2주만의 피벗 + 야근 넘치는 문화 + MVP 뽑아야됨
    - 테크리드가 되어보니 과거의 매니저님을 대단하게 생각하게 됨 😆
    - 인생은 새옹지마(타이밍이 언제 올 지 모른다 😅)
`;
  const [markdown, setMarkdown] = useState(str);

  const handleMarkdownChange = (value: string | undefined) => {
    if (value) {
      setMarkdown(value);
      console.log(value);
    }
  };

  useEffect(() => {}, [ref?.current?.value]);

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
      <MarkdownBox ref={testRef} id="wrapper">
        <MDEditor.Markdown source={show} />
      </MarkdownBox>
      <Popover target={target} onClick={handleShareMeClick} />
    </>
  );
};

export default CreateReview;

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
