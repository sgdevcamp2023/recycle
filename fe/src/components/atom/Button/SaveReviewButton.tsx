import styled from 'styled-components';

const SaveReviewStyleButton = styled.button`
  width: 4rem;
  height: 1.25rem;
  padding: 0.25rem;
  background-color: #1eb649;
  color: white;
  border: none;
  border-radius: 0.3125rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 0.7rem;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;

  &:hover {
    background-color: white;
    border: 0.063rem solid #1eb649;
    color: #1eb649;
  }
`;

const SaveReviewButton = () => {
  return (
    <div>
      <SaveReviewStyleButton>리뷰 남기기</SaveReviewStyleButton>
    </div>
  );
};

export default SaveReviewButton;
