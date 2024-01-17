import styled from 'styled-components';

const InviteStyleButton = styled.button`
  width: 4.3125rem;
  height: 1.5rem;
  padding: 0.25rem;
  background-color: #1eb649;
  color: white;
  border: none;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 1rem;
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

const InviteButton = () => {
  return (
    <div>
      <InviteStyleButton>Invite</InviteStyleButton>
    </div>
  );
};

export default InviteButton;
