import styled from 'styled-components';

const StorageStyleButton = styled.button`
  width: 4rem;
  height: 1.25rem;
  padding: 0.25rem;
  background-color: white;
  color: #1eb649;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 0.7rem;
  font-weight: 600;
  padding: 0.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;

  &:hover {
    background-color: #1eb649;
    border: none;
    color: white;
    font-weight: 600;
  }
`;

const TemporaryStorageButton = () => {
  return (
    <div>
      <StorageStyleButton>임시저장</StorageStyleButton>
    </div>
  );
};

export default TemporaryStorageButton;
