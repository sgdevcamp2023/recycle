import styled from 'styled-components';

const ReviewStyleButton = styled.button`
  width: 8rem;
  height: 3rem;
  padding: 1rem;
  background-color: #1eb649;
  color: white;
  border: none;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;

  &:hover {
    background-color: white;
    border: 0.063rem solid #1eb649;
    color: #1eb649;
  }
`;

const ReviewButton = () => {
  return (
    <div>
      <ReviewStyleButton>리뷰하기</ReviewStyleButton>
    </div>
  );
};

export default ReviewButton;
