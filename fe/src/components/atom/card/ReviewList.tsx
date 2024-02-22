import { useState, useEffect } from 'react';
import styled from 'styled-components';

interface ReviewListProps {
  reviews: string[];
  reviewsPerPage: number;
}

const ReviewListContainer = styled.div`
  ul {
    list-style: none;
    padding: 0;
  }

  li {
    margin-bottom: 10px;
  }

  div {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 20px;
  }

  button {
    padding: 10px;
    cursor: pointer;
    border: 1px solid ${({ theme }) => theme.backgroundColor.grey400};
    background-color: ${({ theme }) => theme.backgroundColor.grey300};
    color: black;
    margin: 0px 10px;
    border-radius: 8px;

    &:hover {
      background-color: ${({ theme }) => theme.backgroundColor.green100}; /* 초록색 */
      color: white;
    }

    &:disabled {
      opacity: 0.5;
      cursor: not-allowed;
    }
  }
`;

const ArrowButton = styled.button`
  padding: 10px;
  cursor: pointer;
  border: 1px solid black;
  background-color: white;
  color: black;

  &:hover {
    background-color: ${({ theme }) => theme.backgroundColor.green100}; /* 초록색 */
    color: white;
  }

  &:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }
`;

const ReviewList = ({ reviews, reviewsPerPage }: ReviewListProps) => {
  const [currentPage, setCurrentPage] = useState(1);
  const [displayedReviews, setDisplayedReviews] = useState<string[]>([]);

  useEffect(() => {
    const startIndex = (currentPage - 1) * reviewsPerPage;
    const endIndex = startIndex + reviewsPerPage;
    setDisplayedReviews(reviews.slice(startIndex, endIndex));
  }, [reviews, currentPage, reviewsPerPage]);

  const handleNextPage = () => {
    if (currentPage < Math.ceil(reviews.length / reviewsPerPage)) {
      setCurrentPage(currentPage + 1);
    }
  };

  const handlePrevPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  return (
    <ReviewListContainer>
      {/* 리뷰 목록 출력 */}
      <ul>
        {displayedReviews.map((review, index) => (
          <li key={index}>{review}</li>
        ))}
      </ul>

      {/* 페이지 이동 버튼 */}
      <div>
        <ArrowButton
          onClick={(event) => {
            event.stopPropagation();
            handlePrevPage();
          }}
          disabled={currentPage === 1}
        >
          before
        </ArrowButton>
        <span> {currentPage} page </span>
        <ArrowButton
          onClick={(event) => {
            event.stopPropagation();
            handleNextPage();
          }}
          disabled={currentPage === Math.ceil(reviews.length / reviewsPerPage)}
        >
          next
        </ArrowButton>
      </div>
    </ReviewListContainer>
  );
};

export default ReviewList;
