import styled from 'styled-components';

const ModifyStyleButton = styled.button`
  width: 2rem;
  height: 1.5rem;
  background-color: #d9d9d9;
  color: white;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 0.6rem;
  padding: 0.2rem;

  &:hover {
    background-color: #939393;
    border: 0.063rem solid #939393;
    color: white;
    font-weight: 600;
  }
`;

const ModifyButton = () => {
  return (
    <div>
      <ModifyStyleButton>수정</ModifyStyleButton>
    </div>
  );
};

export default ModifyButton;
