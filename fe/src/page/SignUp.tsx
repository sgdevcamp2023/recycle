import SignInModal from '@components/block/modal/SignInModal';
import styled from 'styled-components';

const CenterModalBox = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const SignUp = () => {
  return (
    <CenterModalBox>
      <SignInModal />
    </CenterModalBox>
  );
};

export default SignUp;
