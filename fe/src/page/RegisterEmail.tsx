import RegisterEmailModal from '@components/block/modal/RegisterEmailModal';
import styled from 'styled-components';

const CenterModalBox = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const RegisterEmail = () => {
  return (
    <CenterModalBox>
      <RegisterEmailModal />
    </CenterModalBox>
  );
};

export default RegisterEmail;