import styled from 'styled-components';
import GreyButton from '../Button/GreyButton';
import Text from '../Text';
import { reviewDataProps } from '@store/useReviewStore';

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
    console.log(elementId);
    console.log(element);
    if (!element) return '';

    let extractedText = '';

    // 텍스트 노드일 경우
    if (element.nodeType === Node.TEXT_NODE) {
      const nodeText = element.textContent || '';
      extractedText = nodeText.substring(startIdx, endIdx);
    } else {
      // 자식 노드 중에서 텍스트 노드만 선택
      const childTextNodes = Array.from(element.childNodes).filter(
        (childNode) => childNode.nodeType === Node.TEXT_NODE,
      );

      childTextNodes.forEach((childNode) => {
        const nodeText = childNode.textContent || '';
        extractedText += nodeText;
      });
    }

    return extractedText;
  }
  return (
    <div>
      <LineCommentViewBox>
        <UserInfoWrapper>
          <Name>
            <Text fontSize="base">김현우</Text>
          </Name>
          <Date>
            <Text fontSize="xxs">2024년 1월 3일</Text>
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
          <LineCommentContent>{item.reviewText}</LineCommentContent>
          <LineCommentContent>
            {extractTextByIdAndIndices({
              elementId: item.reviewId,
              startIdx: item.startIdx,
              endIdx: item.endIdx,
            })}
          </LineCommentContent>
          <LineCommentContent>
            {item.reviewId + ' ' + item.startIdx + ' ' + item.endIdx}
          </LineCommentContent>
        </CommentContainer>
      </LineCommentViewBox>
    </div>
  );
};

export default LineCommentView;
