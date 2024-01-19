import styled from 'styled-components';

const CommonStyleButton = styled.button`
  width: 22.375rem;
  height: 3rem;
  padding: 1rem;
  background-color: #1eb649;
  color: white;
  border: none;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1rem;
  font-weight: 600;

  &:hover {
    background-color: white;
    border: 0.063rem solid #1eb649;
    color: #1eb649;
  }
`;

const RegisterButton = () => {
  return (
    <div>
      <CommonStyleButton>인증하기</CommonStyleButton>
    </div>
  );
};

export default RegisterButton;
