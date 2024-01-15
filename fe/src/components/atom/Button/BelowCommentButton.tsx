import styled from 'styled-components';

const BelowStyleButton = styled.button`
  width: 5.875rem;
  height: 2rem;
  background-color: #1eb649;
  color: white;
  border: none;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 0.75rem;

  &:hover {
    background-color: white;
    border: 0.063rem solid #1eb649;
    color: #1eb649;
  }
`;

const BelowLongStyleButton = styled.button`
  width: 42rem;
  height: 2rem;
  background-color: #1eb649;
  color: white;
  border: none;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 0.75rem;

  &:hover {
    background-color: white;
    border: 0.063rem solid #1eb649;
    color: #1eb649;
  }
`;

const BelowCommentButton = () => {
  return (
    <div>
      <BelowStyleButton>댓글 작성</BelowStyleButton>
      <BelowLongStyleButton>댓글 작성</BelowLongStyleButton>
    </div>
  );
};

export default BelowCommentButton;
