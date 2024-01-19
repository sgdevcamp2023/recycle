import { ReactNode } from "react";
import DefaultCard from "./DefaultCard";
import ReviewList from "./ReviewList";

const ReviewCard = () => {
  const reviews: string[] = [
    "리뷰 1",
    "리뷰 2",
    "리뷰 3",
    "리뷰 4",
    "리뷰 5",
    // 추가적인 리뷰들...
  ];
  const Content: ReactNode = <ReviewList reviews={reviews} reviewsPerPage={3} />;
  return <DefaultCard type="review" width={40} height={20} commentCount={32} title="hello" content={Content}></DefaultCard>;
};

export default ReviewCard;
