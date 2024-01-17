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

  &:hover {
    background-color: white;
    border: 0.063rem solid #1eb649;
    color: #1eb649;
  }
`;

const CommonButton = () => {
  return (
    <div>
      <CommonStyleButton>로그인</CommonStyleButton>
    </div>
  );
};

export default CommonButton;
