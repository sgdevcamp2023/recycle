import SideBar from '@components/block/sideBar/SideBar';
import styled from 'styled-components';
import { Outlet } from 'react-router-dom';
import ContentTab from '@components/block/navbar/ContentTab';
import ReviewWriteModal from '@components/block/modal/ReviewWriteModal';
import { useMarkdownStore } from '@store/useMarkdownStore';
import { useEffect, useState } from 'react';
import useReviewStore, { reviewData } from '@store/useReviewStore';
import LineCommentWrite from '@components/atom/Comment/LineCommentWrite';
import LineCommentView from '@components/atom/Comment/LineCommentView';

const GridTemplate = () => {
  const { showCodeComment, setShowCodeComment } = useMarkdownStore();
  const [reviewList, setReviewList] = useState([]);
  const [reviewData, setReviewData] = useState([]);

  // useReviewStore 훅으로부터 리뷰 목록 가져오기
  const {
    reviewList: initialReviewList,
    setReviewList: setInitialReviewList,
    setData,
    data,
  } = useReviewStore();

  // 컴포넌트가 마운트될 때와 리뷰 목록이 변경될 때마다 실행되는 useEffect 설정
  useEffect(() => {
    // 초기 리뷰 목록 설정
    setReviewList(initialReviewList);
  }, [initialReviewList]);

  useEffect(() => {
    setReviewData(data);
  }, [data]);

  const handleCancelLineComment = (indexToDelete) => {
    const updatedReviewList = initialReviewList.filter((item, index) => index !== indexToDelete);
    setInitialReviewList(updatedReviewList);
  };

  const handleAddLineComment = ({ item, index: indexToDelete }) => {
    console.log(item);
    console.log('addline');
    setData([...data, item]);
    console.log(data);
    console.log(reviewData);
    const updatedReviewList = initialReviewList.filter((item, index) => index !== indexToDelete);
    setInitialReviewList(updatedReviewList);
  };
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
          {showCodeComment && <ReviewWriteModal top={showCodeComment.top} />}
          <div>
            {reviewList &&
              reviewList.map((item: reviewData, index) => {
                return (
                  <>
                    <h3 key={index}>{item.reviewText}</h3>
                    <LineCommentWrite
                      cancelOnClick={() => handleCancelLineComment(index)}
                      uploadOnClick={() => handleAddLineComment(item)}
                    />
                  </>
                );
              })}
            {reviewData &&
              reviewData.map((item: reviewData, index) => {
                return (
                  <>
                    <LineCommentView />
                  </>
                );
              })}
          </div>
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
