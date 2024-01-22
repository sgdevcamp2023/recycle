import styled from 'styled-components';

const BelowStyleButton = styled.button`
  width: 5rem;
  height: 2rem;
  background-color: #1eb649;
  color: white;
  border: none;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 0.75rem;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    background-color: white;
    border: 0.063rem solid #1eb649;
    color: #1eb649;
  }
`;

const EmailEnrollButton = () => {
  return (
    <div>
      <BelowStyleButton>등록하기</BelowStyleButton>
    </div>
  );
};

export default EmailEnrollButton;
