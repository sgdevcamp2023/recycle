import CommonInput from '@components/atom/Input/CommonInput';
import CustomInput from '@components/atom/Input/CustomInput';
import DefaultInput from '@components/atom/Input/CustomInput';
import LoginModal from '@components/block/modal/LoginModal';
import RegisterEmailModal from '@components/block/modal/RegisterEmailModal';
import SignInModal from '@components/block/modal/SignInModal';

import styled from 'styled-components';

const CenterModalBox = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
`;

const TestPageThree = () => {
  return (
    <>
      <CenterModalBox>
        {/* <CustomInput width={22} height={3} type='email' /> */}
        {/* <LoginModal /> */}
        <SignInModal />
        {/* <RegisterEmailModal /> */}
      </CenterModalBox>
    </>
  );
};

export default TestPageThree;
