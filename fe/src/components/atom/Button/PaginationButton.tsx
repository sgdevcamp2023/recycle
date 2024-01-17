import styled from 'styled-components';

const PaginationStyleButton = styled.button`
  width: 2rem;
  height: 1.75rem;
  color: black;
  border-radius: 0.625rem;
  border: 0.063rem solid black;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 1rem;
  font-weight: 600;
  padding: 0.2rem;

  &:hover {
    background-color: black;
    color: white;
    font-weight: 600;
  }
`;

const CurrentPageButton = styled.button`
  width: 2rem;
  height: 1.75rem;
  background-color: black;
  color: white;
  border-radius: 0.625rem;
  cursor: pointer;
  font-size: 1rem;
  font-weight: 600;
  padding: 0.2rem;
`;

const PaginationButton = () => {
  return (
    <div>
      <PaginationStyleButton>1</PaginationStyleButton>
      <CurrentPageButton>1</CurrentPageButton>
    </div>
  );
};

export default PaginationButton;
