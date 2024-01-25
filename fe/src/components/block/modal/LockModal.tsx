import Text from '@components/atom/Text';
import styled from 'styled-components';
import logo from '../../../assets/logos/ZzaugLogo.png';
import lock from '../../../assets/icons/Lock.png';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 19.5rem;
  height: 7rem;
  padding: 0.25rem 1rem;
  background-color: white;
  color: black;
  border: 1px solid #1eb649;
  border-radius: 0.625rem;
  padding-right: 3.4375rem;
  padding-left: 3.4375rem;
  position: relative;
  display: flex;
  justify-content: center;
`;

const TitleLogo = styled.img`
  width: 3.9063rem;
  margin: 0 0.125rem;
`;

const LockIcon = styled.img`
  width: 2.25rem;
  height: 2.25rem;
  position: absolute;
  top: 1.3125rem;
`;

const TitleBox = styled.div`
  width: 100%;
  height: 1.25rem;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  bottom: 1.125rem;
`;

const LockModal = () => {
  return (
    <div>
      <LoginBox>
        <LockIcon src={lock} alt="must login lock icon" />
        <TitleBox>
          <Text fontSize="sm" fontWeight="bold">
            로그인 후
          </Text>
          <TitleLogo src={logo} alt="zzaug main logo" />
          <Text fontSize="sm" fontWeight="bold">
            이용이 가능해요!
          </Text>
        </TitleBox>
      </LoginBox>
    </div>
  );
};

export default LockModal;
