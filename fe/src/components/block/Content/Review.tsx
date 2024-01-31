import useTabStore, { DefaultTabType } from '@store/useTabStore';
import DefaultTab from '../navbar/DefaultTab';
import styled from 'styled-components';
import SearchInput from '../Search/SearchInput';
import { useEffect } from 'react';
import ReviewCard, { ReviewCardProps } from '@components/atom/card/ReviewCard';

const Review = () => {
  const items: Record<string, DefaultTabType> = {
    '내가 작성한 리뷰': 'myReview',
    '임시 저장된 리뷰': 'reviewDrafts',
  };
  const { setDefaultTabType } = useTabStore();
  useEffect(() => {
    setDefaultTabType('myReview');
  }, [setDefaultTabType]);
  const mockDataArray: ReviewCardProps[] = [
    {
      type: 'review',
      commentCount: 8,
      title: 'Title 2',
      reviews: ['리뷰 1', '리뷰 2', '리뷰 3', '리뷰 4', '리뷰 5'],
    },
    {
      type: 'review',
      commentCount: 8,
      title: 'Title 2',
      reviews: ['리뷰 1', '리뷰 2', '리뷰 3', '리뷰 4', '리뷰 5'],
    },
    {
      type: 'review',
      commentCount: 8,
      title: 'Title 2',
      reviews: ['리뷰 1', '리뷰 2', '리뷰 3', '리뷰 4', '리뷰 5'],
    },
    {
      type: 'review',
      commentCount: 8,
      title: 'Title 2',
      reviews: ['리뷰 1', '리뷰 2', '리뷰 3', '리뷰 4', '리뷰 5'],
    },
  ];
  return (
    <BoxWrapper>
      <DefaultTab items={items} />
      <SearchInput />
      <ReviewWrapper>
        {mockDataArray &&
          mockDataArray.map((item, idx) => {
            return (
              <ReviewCard
                key={idx}
                reviews={item.reviews}
                type={item.type}
                commentCount={item.commentCount}
                title={item.title}
              />
            );
          })}
      </ReviewWrapper>
    </BoxWrapper>
  );
};

export default Review;

const BoxWrapper = styled.div`
  width: 100%;
`;

const ReviewWrapper = styled.div`
  width: 100%;
  margin-top: 1.5rem;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 0.5rem;
`;
