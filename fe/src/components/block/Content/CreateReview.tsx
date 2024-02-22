import Text from '@components/atom/Text';
import useQuestionStore from '@store/useQuestionStore';
import MDEditor from '@uiw/react-md-editor';
import { useEffect, useRef, useState } from 'react';
import styled from 'styled-components';
import ReviewWriteModal from '../modal/ReviewWriteModal';
import { useMarkdownStore } from '@store/useMarkdownStore';
import useReviewStore, { reviewDataProps } from '@store/useReviewStore';
import { Popover } from '@page/PopOver';
import { useParams } from 'react-router-dom';
import useGetQuestion from '@hooks/query/question/useGetQuestion';
import useSaveReview from '@hooks/query/review/useSaveReview';
import useGetReviewsOnQuestion from '@hooks/query/question/useGetReviewsOnQuestion';

export interface ReviewSubmitProps {
  content: string;
  startPoint: PointProps;
  endPoint: PointProps;
  tag: 'CODE' | 'LINE';
}
interface PointProps {
  point: number;
  index: number;
}

const CreateReview = () => {
  const { content } = useQuestionStore((state) => state);
  const [show, setShow] = useState();

  const { reviewId } = useParams<{ reviewId: string }>();
  const { showCodeComment, setShowCodeComment } = useMarkdownStore();
  const {
    setId,
    setReviewList,
    reviewList,
    data: reviewData,
    setData,
    setReview,
  } = useReviewStore();
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

  const { data } = useGetQuestion({ questionId: parseInt(reviewId) });
  console.log(reviewId);
  const { data: reviewResult } = useGetReviewsOnQuestion({ questionId: parseInt(reviewId) });
  console.log(reviewResult);

  useEffect(() => {
    setShow(data?.data?.data?.content);
  }, [data]);

  // function extractTextByIdAndIndices(elementId, startIdx, endIdx) {
  //   const element = document.getElementById(elementId);
  //   console.log(elementId);
  //   console.log(element);
  //   if (!element) return '';

  //   let extractedText = '';

  //   // 텍스트 노드일 경우
  //   if (element.nodeType === Node.TEXT_NODE) {
  //     const nodeText = element.textContent || '';
  //     extractedText = nodeText.substring(startIdx, endIdx);
  //   } else {
  //     // 자식 노드 중에서 텍스트 노드만 선택
  //     const childTextNodes = Array.from(element.childNodes).filter(
  //       (childNode) => childNode.nodeType === Node.TEXT_NODE,
  //     );

  //     childTextNodes.forEach((childNode) => {
  //       const nodeText = childNode.textContent || '';
  //       extractedText += nodeText;
  //     });
  //   }
  //   return extractedText;
  // }
  function extractTextByIdAndIndices({ elementId, startIdx, endIdx }: any) {
    const element = document.getElementById(elementId);
    if (!element) return '';

    let extractedText = '';

    // 텍스트 노드일 경우
    if (element.nodeType === Node.TEXT_NODE) {
      const nodeText = element.textContent || '';
      extractedText = nodeText.substring(startIdx, endIdx);
    } else {
      // 텍스트 노드가 아닌 경우, 자식 노드 중에서 텍스트를 추출하여 합침
      extractedText = extractTextFromElement(element);
      extractedText = extractedText.substring(startIdx, endIdx);
    }
    console.log(extractedText);
    return extractedText;
  }

  // 엘리먼트 내의 텍스트를 추출하는 함수
  function extractTextFromElement(element) {
    let text = '';
    const treeWalker = document.createTreeWalker(
      element,
      NodeFilter.SHOW_ELEMENT | NodeFilter.SHOW_TEXT,
      null,
    );
    while (treeWalker.nextNode()) {
      const node = treeWalker.currentNode;
      if (node.nodeType === Node.TEXT_NODE) {
        text += node.textContent || '';
      } else if (node.nodeType === Node.ELEMENT_NODE) {
        text += extractTextFromElement(node);
      }
    }
    return text;
  }

  useEffect(() => {
    const lineReviews = (reviewResult?.data?.data || []).filter((review) => review.tag === 'LINE');
    console.log('lineReviews', lineReviews);
    setData(
      lineReviews.map((review) => ({
        reviewId: review.questionId,
        startIdx: review.startPoint.index,
        endIdx: review.endPoint.index,
        reviewText: review.content,
        reviewPoint: review.startPoint.point,
        reviewComment: extractTextByIdAndIndices({
          elementId: review.startPoint.point,
          startIdx: review.startPoint.index,
          endIdx: review.endPoint.index,
        }),
        createdAt: review.createdAt,
      })),
    );
    console.log('reviewData', reviewData);
    const codeReviews = (reviewResult?.data?.data || []).filter((review) => review.tag === 'CODE');
    console.log(codeReviews);
    setReview(
      codeReviews.map((review) => ({
        reviewId: review.reviewId != null ? review.reviewId.toString() : null,
        comment: review.content,
        id: review.startPoint.point,
        createdAt: review.createdAt,
      })),
    );
  }, [reviewResult]);

  const handleShareMeClick = () => {
    const { anchorNode, focusNode, anchorOffset, focusOffset } = window.getSelection() as Selection;
    const startNode = anchorNode?.parentElement;
    const endNode = focusNode?.parentElement;
    if (startNode?.parentElement?.className == 'code-line') {
      alert('code는 라인코멘트를 달 수 없습니다!');
    } else if (startNode == endNode) {
      const text = anchorNode?.textContent || '';
      const start = anchorOffset;
      const end = focusOffset;
      setTemp([...temp, text.substring(start, end)]);
      const newData: reviewDataProps = {
        startIdx: start,
        endIdx: end,
        reviewText: text.substring(start, end),
        reviewId: startNode?.id,
      };
      setReviewList([newData]);
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
          const newData: reviewDataProps = {
            startIdx: start,
            endIdx: end,
            reviewText: text.substring(start, end),
            reviewId: refId,
          };
          setReviewList([newData]);
        });
      }
    }
  };

  useEffect(() => {
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
          const newData: reviewDataProps = {
            startIdx: start,
            endIdx: end,
            reviewText: text.substring(start, end),
            reviewId: refId,
          };
          setReviewList([newData]);
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

  const handleMarkdownChange = (value: string | undefined) => {
    if (value) {
      setMarkdown(value);
      console.log(value);
    }
  };

  // useEffect(() => {
  //   const codeBlocks = document.querySelectorAll('code');

  //   codeBlocks.forEach((codeBlock, index) => {
  //     const id = index + 1;
  //     codeBlock.id = id.toString();

  //     codeBlock.addEventListener('click', (e) => handleClickOnCodeBlock(e, id));
  //     codeBlock.addEventListener('mouseenter', () => {
  //       codeBlock.style.border = '1px solid red';
  //     });
  //     codeBlock.addEventListener('mouseleave', () => {
  //       codeBlock.style.border = 'none';
  //     });
  //     codeBlock.style.cursor = 'pointer';
  //   });

  //   return () => {
  //     codeBlocks.forEach((codeBlock, index) => {
  //       const id = index + 1;
  //       codeBlock.id = id.toString();

  //       codeBlock.removeEventListener('click', () =>
  //         handleClickOnCodeBlock(parseInt(codeBlock.id)),
  //       );
  //       codeBlock.removeEventListener('mouseenter', () => {
  //         codeBlock.style.border = '1px solid red';
  //       });
  //       codeBlock.removeEventListener('mouseleave', () => {
  //         codeBlock.style.border = 'none';
  //       });
  //     });
  //   };
  // }, []); // 추후 content로 변경 필요
  useEffect(() => {
    const markdownElement = testRef.current;
    if (markdownElement) {
      let id = 0;
      const codeBlocks = markdownElement.querySelectorAll('code');
      codeBlocks.forEach((codeBlock, index) => {
        id = index + 1;
        codeBlock.id = `${id}`;
        codeBlock.addEventListener('click', (e) => handleClickOnCodeBlock(e));
        codeBlock.addEventListener('mouseenter', () => {
          codeBlock.style.border = '1px solid red';
        });
        codeBlock.addEventListener('mouseleave', () => {
          codeBlock.style.border = 'none';
        });
        codeBlock.style.cursor = 'pointer';
      });
      const elements = markdownElement.querySelectorAll('*:not(code)'); // 코드 블록이 아닌 모든 요소를 선택합니다.
      elements.forEach((element, index) => {
        id = index + 1;
        element.id = `${id}`;
      });
    }
  }, [show]);

  const handleClickOnCodeBlock = (e) => {
    const parentDiv = e.currentTarget.parentElement;
    const parentBorderTop = parentDiv.getBoundingClientRect().top + window.scrollY;
    const modalTop = parentBorderTop;
    setId(e.currentTarget.id);
    setShowCodeComment({
      top: modalTop,
    });
  };

  const titleParser = (content: string | undefined) => {
    const titleEndIndex = content?.indexOf('\n');
    // const title = content?.substring(0, titleEndIndex).trim();
    const title = content?.substring(content.indexOf('# ') + 2, titleEndIndex).trim();

    // 나머지 내용 추출
    const mainContent = content?.substring(titleEndIndex + 1).trim();

    return { title, mainContent };
  };
  const { mutate } = useSaveReview();

  const handleSubmimtReivew = () => {
    console.log(reviewData);
    const testReview: ReviewSubmitProps = {
      content: reviewData[0].reviewComment,
      startPoint: {
        point: reviewData[0].reviewId,
        index: reviewData[0].startIdx,
      },
      endPoint: {
        point: reviewData[0].reviewId,
        index: reviewData[0].endIdx,
      },
      tag: 'LINE',
    };
    console.log(reviewId);
    mutate({ content: testReview, questionId: reviewId });
  };

  return (
    <>
      {/* <button onClick={handleSubmimtReivew}>리뷰하기</button> */}
      <TitleWrapper>
        <Text fontSize="xl" fontWeight="bold">
          {show && titleParser(show).title}
        </Text>
      </TitleWrapper>
      <MarkdownBox ref={testRef} id="wrapper">
        <MDEditor.Markdown source={show && titleParser(show).mainContent} />
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
