import styled from 'styled-components';

const ReturnStyleButton = styled.button`
  width: 4rem;
  height: 1.25rem;
  padding: 0.25rem;
  background-color: white;
  color: #939393;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 0.7rem;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;

  &:hover {
    background-color: #939393;
    border: 0.063rem solid #939393;
    color: white;
    font-weight: 600;
  }
`;

const ReturnButton = () => {
  return (
    <div>
      <ReturnStyleButton>◀️ 돌아가기</ReturnStyleButton>
    </div>
  );
};

export default ReturnButton;
