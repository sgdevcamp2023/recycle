import LoginModal from '@components/block/modal/LoginModal';
import styled from 'styled-components';

const CenterModalBox = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Login = () => {
  return (
    <CenterModalBox>
      <LoginModal />
    </CenterModalBox>
  );
};

export default Login;
