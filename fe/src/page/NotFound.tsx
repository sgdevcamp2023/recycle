import ErrorModal from '@components/block/modal/ErrorModal';
import styled from 'styled-components';

const NotFound = () => {
  return (
    <NotFoundWrapper>
      <ErrorModal />
    </NotFoundWrapper>
  );
};

export default NotFound;

const NotFoundWrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;
