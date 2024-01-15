import styled from 'styled-components';

const DoubleCheckStyleButton = styled.button`
  width: 5.5rem;
  height: 3rem;
  background-color: white;
  border: 0.063rem solid #1eb649;
  color: #1eb649;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 1rem;
  padding: 0.5rem;

  &:hover {
    background-color: #1eb649;
    border: none;
    color: white;
  }
`;

const DoubleCheckButton = () => {
  return (
    <div>
      <DoubleCheckStyleButton>중복 확인</DoubleCheckStyleButton>
    </div>
  );
};

export default DoubleCheckButton;
