import { ReactNode } from "react";
import DefaultCard, { DefaultCardType } from "./DefaultCard";
import ReviewList from "./ReviewList";

export interface ReviewCardProps {
  reviews: string[];
  type: DefaultCardType;
  title?: string;
  commentCount?: number;
}

const ReviewCard = ({ reviews, type, title, commentCount }: ReviewCardProps) => {
  const Content: ReactNode = <ReviewList reviews={reviews} reviewsPerPage={3} />;
  return <DefaultCard type={type} height={20} commentCount={commentCount} title={title} content={Content} />;
};

export default ReviewCard;
