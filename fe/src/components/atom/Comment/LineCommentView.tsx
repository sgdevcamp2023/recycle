import styled from 'styled-components';
import GreyButton from '../Button/GreyButton';
import Text from '../Text';
import { reviewDataProps } from '@store/useReviewStore';
import { useState } from 'react';

const LineCommentViewBox = styled.div`
  width: 15.5rem;
  height: auto; /* 높이를 자동으로 조정 */
  padding: 0.25rem 1rem;
  background-color: white;
  color: black;
  border: 0.0625rem solid #aaaaaa;
  border-radius: 0.625rem;
  position: relative;
  box-shadow: 4px 4px 2px 2px rgba(0, 0, 0, 0.2);
  margin-bottom: 1rem;
`;

const CommentContainer = styled.div`
  max-height: 15rem; /* 최대 높이 설정 */
  overflow: auto; /* 내용이 넘칠 경우 스크롤 표시 */
`;

const UserInfoWrapper = styled.div`
  display: flex;
  width: 15rem;
  height: 1.5rem;
  justify-content: space-between;
  align-items: flex-end;
  margin-top: 0.5rem;
`;

const Name = styled.div`
  width: 3.25rem;
  height: 1.5rem;
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
`;

const Date = styled.div`
  width: 9.375rem;
  height: 1.5rem;
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
`;

const LineCommentContent = styled.p`
  font-size: 0.625rem;
  margin-top: 0.5rem;
`;

interface LineCommentViewProps {
  item: reviewDataProps;
}

const LineCommentView = ({ item }: LineCommentViewProps) => {
  console.log(item);

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

  const [sliceComment, setSliceComment] = useState(
    extractTextByIdAndIndices({
      elementId: item.reviewPoint,
      startIdx: item.startIdx,
      endIdx: item.endIdx,
    }),
  );
  return (
    <div>
      <LineCommentViewBox>
        <UserInfoWrapper>
          <Name>
            <Text fontSize="base">김현우</Text>
          </Name>
          <Date>
            <Text fontSize="xxs">{item.createdAt}</Text>
          </Date>
          <GreyButton
            width={2.5}
            height={1.5}
            padding={0.2}
            fontSize="xxs"
            $backgroundColor={'grey100'}
            color={'white'}
          >
            수정
          </GreyButton>
        </UserInfoWrapper>
        <CommentContainer>
          <LineCommentContent>
            {item.reviewComment?.substring(item.startIdx, item.endIdx)}
          </LineCommentContent>
          <LineCommentContent>{item.reviewText}</LineCommentContent>
        </CommentContainer>
      </LineCommentViewBox>
    </div>
  );
};

export default LineCommentView;
