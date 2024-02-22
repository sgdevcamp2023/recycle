import SideBar from '@components/block/sideBar/SideBar';
import styled from 'styled-components';
import { Outlet, useLocation, useParams } from 'react-router-dom';
import ContentTab from '@components/block/navbar/ContentTab';
import ReviewWriteModal from '@components/block/modal/ReviewWriteModal';
import { useMarkdownStore } from '@store/useMarkdownStore';
import { useEffect, useRef, useState } from 'react';
import useReviewStore, { reviewData } from '@store/useReviewStore';
import LineCommentWrite from '@components/atom/Comment/LineCommentWrite';
import LineCommentView from '@components/atom/Comment/LineCommentView';
import ReviewShowModal from '@components/block/modal/ReviewShowModal';
import value from '../../svg.d';
import { reviewDataProps } from '../../store/useReviewStore';
import useSaveReview from '@hooks/query/review/useSaveReview';
import { ReviewSubmitProps } from '@components/block/Content/CreateReview';
import useGetReviewsOnQuestion from '@hooks/query/question/useGetReviewsOnQuestion';

const GridTemplate = () => {
  const { showCodeComment, setShowCodeComment } = useMarkdownStore();
  const [reviewList, setReviewList] = useState([]);
  const [reviewData, setReviewData] = useState([]);
  const commentRef = useRef(null);
  const { reviewId } = useParams<{ reviewId: string }>();
  // useReviewStore 훅으로부터 리뷰 목록 가져오기
  const {
    reviewList: initialReviewList,
    setReviewList: setInitialReviewList,
    setData,
    data,
    setReview,
  } = useReviewStore();

  // 컴포넌트가 마운트될 때와 리뷰 목록이 변경될 때마다 실행되는 useEffect 설정
  useEffect(() => {
    // 초기 리뷰 목록 설정
    setReviewList(initialReviewList);
  }, [initialReviewList]);

  useEffect(() => {
    setReviewData(data);
  }, [data]);
  const { data: reviewResult } = useGetReviewsOnQuestion({ questionId: parseInt(reviewId) });
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

  const handleCancelLineComment = (indexToDelete) => {
    const updatedReviewList = initialReviewList.filter((item, index) => index !== indexToDelete);
    setInitialReviewList(updatedReviewList);
  };

  const { mutate } = useSaveReview();

  const handleAddLineComment = ({ item, index: indexToDelete }) => {
    console.log(item);
    console.log('addline');
    item.reviewComment = commentRef?.current?.value;
    setData([...data, item]);
    console.log(data);
    console.log(reviewData);
    console.log(commentRef?.current?.value);
    const updatedReviewList = initialReviewList.filter((item, index) => index !== indexToDelete);
    setInitialReviewList(updatedReviewList);
    console.log(reviewData);
    const testReview: ReviewSubmitProps = {
      content: commentRef?.current?.value,
      startPoint: {
        point: item.reviewId,
        index: item.startIdx,
      },
      endPoint: {
        point: item.reviewId,
        index: item.endIdx,
      },
      tag: 'LINE',
    };
    mutate({ content: testReview, questionId: reviewId });
  };

  const location = useLocation();
  return (
    <LayoutWrapper>
      <div>
        <SideBar />
      </div>
      <MainWrapper>
        <TopHeader>
          <ContentTab />
        </TopHeader>
        <MainContent>
          <Outlet />
        </MainContent>
        <RightContent>
          {location.pathname.startsWith('/review/') && showCodeComment && (
            <ReviewWriteModal top={showCodeComment.top} />
          )}
          {location.pathname === '/createReview' && showCodeComment && (
            <ReviewWriteModal top={showCodeComment.top} />
          )}
          {location.pathname === '/readQuestion' && showCodeComment && (
            <ReviewShowModal top={showCodeComment.top} />
          )}
          {location.pathname.startsWith('/review/') && (
            <div>
              {reviewList &&
                reviewList.map((item: reviewData, index) => {
                  return (
                    <>
                      <h3 key={index}>{item.reviewText}</h3>
                      <LineCommentWrite
                        cancelOnClick={() => handleCancelLineComment(index)}
                        uploadOnClick={() => handleAddLineComment({ item, index })}
                        ref={commentRef}
                      />
                    </>
                  );
                })}
              {reviewData &&
                reviewData.map((item, index) => {
                  return (
                    <>
                      <LineCommentView item={item} />
                    </>
                  );
                })}
            </div>
          )}
        </RightContent>
      </MainWrapper>
    </LayoutWrapper>
  );
};

export default GridTemplate;

const LayoutWrapper = styled.div`
  width: 100vw;
  height: 100vh;
  display: grid;
  grid-template-columns: 1fr 10fr;
  grid-template-areas: 'Sidebar';
`;
const MainWrapper = styled.div`
  display: grid;
  grid-template-rows: 1fr 11fr;
  grid-template-columns: 6fr 4fr;
`;

const TopHeader = styled.div`
  grid-column: 1 / span 10;
  grid-row: 1 / span 1;
  display: flex;
  justify-content: end;
`;

const MainContent = styled.div`
  display: inline;
  grid-column: 1 / span 5;
  grid-row: 2 / span 11;
  max-width: calc(60% - 2rem);
  padding: 1rem;
`;

const RightContent = styled.div`
  grid-column: 2 / span 5; /* Updated grid-column */
  grid-row: 2 / span 20; /* Updated grid-row */
  padding: 1rem;
`;
