import styled from 'styled-components';
import mainLogo from '../../../assets/logos/ZzaugLogo.png';
import Text from '@components/atom/Text';
import DefaultButton from '@components/atom/Button/DefaultButton';

const ErrorModal = () => {
  return (
    <ModalBox>
      <Logo src={mainLogo} alt="zzaug main logo" />
      <Text center={true} fontSize="xl" fontWeight="bold">
        새로고침 해주세요~
      </Text>
      <Text
        center={true}
        fontSize="base"
        fontWeight="bold"
        color="grey"
        style={{ marginTop: '0.5rem', marginBottom: '0.5rem' }}
      >
        에러가 발생했어요. 새로고침이 안되면, 메인으로 돌아가주세요
      </Text>
      <DefaultButton height="2.5">이전 페이지로 돌아가기</DefaultButton>
    </ModalBox>
  );
};

export default ErrorModal;

const ModalBox = styled.div`
  box-sizing: border-box;
  width: 24rem;
  height: 22rem;
  padding: 0.25rem 1rem;
  margin-bottom: 12rem;
  background-color: white;
  color: black;
  border: 1px solid #1eb649;
  border-radius: 0.625rem;
  padding-right: 3.4375rem;
  padding-left: 3.4375rem;
  position: relative;
  display: flex;
  flex-direction: column;
  text-align: center;
`;

const Logo = styled.img`
  /* width: 3rem; */
  height: 10rem;
  display: inline;
  float: left;
`;
