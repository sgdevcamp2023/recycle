import { MouseEventHandler, ReactNode } from 'react';
import DefaultCard, { DefaultCardType } from './DefaultCard';
import ReviewList from './ReviewList';

export interface ReviewCardProps {
  reviews: string[];
  type: DefaultCardType;
  title?: string;
  commentCount?: number;
  onClick?: MouseEventHandler<HTMLDivElement>;
}

const ReviewCard = ({ reviews, type, title, commentCount, onClick }: ReviewCardProps) => {
  const Content: ReactNode = (
    <>
      <ReviewList reviews={reviews} reviewsPerPage={3} />
    </>
  );
  return (
    <DefaultCard
      onClick={onClick}
      type={type}
      height={20}
      commentCount={commentCount}
      title={title}
    >
      {Content}
    </DefaultCard>
  );
};

export default ReviewCard;
