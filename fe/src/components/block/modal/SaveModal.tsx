import DefaultButton from '@components/atom/Button/DefaultButton';
import GreyButton from '@components/atom/Button/GreyButton';
import ReverseButton from '@components/atom/Button/ReverseButton';
import SaveReviewButton from '@components/atom/Button/SaveReviewButton';
import Text from '@components/atom/Text';
import ReturnButton from '@components/atom/button/ReturnButton';
import TemporaryStorageButton from '@components/atom/button/TemporaryStorageButton';
import styled from 'styled-components';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 17.25rem;
  height: 6.5rem;
  padding: 0.25rem 1rem;
  background-color: white;
  color: black;
  border: 0.0625rem solid #1eb649;
  border-radius: 1.25rem;
  overflow: auto;

  &::-webkit-scrollbar {
    display: none;
  }
`;

const UpperBox = styled.div`
  width: 15.25rem;
  height: 2.25rem;
  border-bottom: 0.0625rem solid #aaa;
  display: flex;
  align-items: flex-end;
  justify-content: flex-start;
`;

const MiddleBox = styled.div`
  width: 15.25rem;
  height: 1.125rem;
  display: flex;
  align-items: flex-start; /* 세로 정렬을 flex-end로 설정 */
  justify-content: space-between; /* 가로 정렬을 양 끝으로 설정 */
  font-size: 0.5rem;
`;

const BottomBox = styled.div`
  width: 15.25rem;
  height: 46px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const ButtonWithMargin = styled.div`
  display: flex;
  gap: 0.25rem;
`;

const SaveModal = () => {
  return (
    <div>
      <LoginBox>
        <UpperBox>
          <Text fontSize='base'>Save</Text>
        </UpperBox>
        <MiddleBox>
          <p>마지막 저장시간</p>
          <p>Edited Jan 03. 2024. 16:06</p>
        </MiddleBox>
        <BottomBox>
          {/* <ReturnButton /> */}
          <GreyButton width={4} height={1.25} padding={0.25} fontSize='xxs'>
            ◀️ 돌아가기
          </GreyButton>
          <ButtonWithMargin>
            <ReverseButton
              width={4}
              height={1.25}
              padding={0.25}
              fontSize='xxs'
            >
              임시저장
            </ReverseButton>
            <DefaultButton
              width={4}
              height={1.25}
              padding={0.25}
              fontSize='xxs'
            >
              리뷰 남기기
            </DefaultButton>
          </ButtonWithMargin>
        </BottomBox>
      </LoginBox>
    </div>
  );
};

export default SaveModal;
