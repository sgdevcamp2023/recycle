import styled from 'styled-components';
import gitLogo from '../../../assets/icons/github-mark.png';
import Text from '@components/atom/Text';

const BelowStyleButton = styled.button`
  width: 5.5rem;
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
  gap: 0.25rem;

  &:hover {
    background-color: white;
    border: 0.063rem solid #1eb649;
    color: #1eb649;
  }
`;

const Logo = styled.img`
  /* width: 3rem; */
  height: 1.25rem;
  display: inline;
  float: left;
`;

const LinkToGitButton = () => {
  return (
    <div>
      <BelowStyleButton>
        <Logo src={gitLogo} alt='github logo' />
        <Text fontSize='xs'>연결하기</Text>
      </BelowStyleButton>
    </div>
  );
};

export default LinkToGitButton;
