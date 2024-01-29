import Text from '@components/atom/Text';
import styled, { keyframes } from 'styled-components';
import logo from '../assets/logos/ZzaugLogo.png';
import ReverseButton from '@components/atom/Button/ReverseButton';
import pencil from '../assets/icons/Pencil.png';
import useMovePage from '@hooks/common/useMovePage';

const CenterModalBox = styled.div`
  box-sizing: border-box;
  width: 100vw;
  height: 100vh;
  padding: 8rem 10rem;
  position: relative;
`;

const TitleBox = styled.div`
  width: 56.25rem;
  height: 18.75rem;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  position: relative;
`;

const TextBox = styled.div`
  width: 56.25rem;
  height: 5rem;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const Logo = styled.img`
  width: 16.5rem;
  margin-right: 0.5rem;
  margin-left: -0.5rem;
`;

const ButtonBox = styled.div`
  width: 56.25rem;
  height: 4rem;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 1rem;
  position: absolute;
  bottom: 0;
`;

const BigLogo = styled.img`
  width: 46.875rem;
  position: absolute;
  bottom: 7rem;
  right: 10rem;
  transform: rotate(-17deg);
`;

const movePenAnimation = keyframes`
  from {
    bottom: 15rem;
    right: 40rem;
  }
  to {
    bottom: 26rem;
    right: -1rem;
  }
`;

const PenLogo = styled.img`
  width: 20rem;
  position: absolute;
  animation: ${movePenAnimation} 2s forwards; /* 1초 동안 선형으로 애니메이션 적용 */
`;

const Main = () => {
  const move = useMovePage();
  return (
    <CenterModalBox>
      <TitleBox>
        <Text fontSize="xxxl" fontWeight="bold">
          코드리뷰의 모든 것
        </Text>
        <TextBox>
          <Logo src={logo} alt="zzaug main logo" />
          <Text fontSize="xxxl" fontWeight="bold">
            에서 쉽고 간편하게
          </Text>
        </TextBox>
        <ButtonBox>
          <ReverseButton
            width={11.25}
            height={3.25}
            borderColor={'green'}
            onClick={() => move('login')}
          >
            로그인
          </ReverseButton>
          <ReverseButton
            width={11.25}
            height={3.25}
            borderColor={'green'}
            onClick={() => move('signup')}
          >
            회원가입
          </ReverseButton>
        </ButtonBox>
      </TitleBox>
      <BigLogo src={logo} alt="zzaug main logo" />
      <PenLogo src={pencil} alt="pen logo" />
    </CenterModalBox>
  );
};

export default Main;
