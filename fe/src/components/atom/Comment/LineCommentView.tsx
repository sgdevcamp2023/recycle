import styled from 'styled-components';
import GreyButton from '../Button/GreyButton';
import Text from '../Text';

const LineCommentViewBox = styled.div`
  width: 15.5rem;
  height: auto; /* 높이를 자동으로 조정 */
  padding: 0.25rem 1rem;
  background-color: white;
  color: black;
  border: 0.0625rem solid #aaaaaa;
  border-radius: 0.625rem;
  position: relative;
  box-shadow: 4px 4px 2px 2px rgba(0, 0, 0, 0.2);
  margin-bottom: 1rem;
`;

const CommentContainer = styled.div`
  max-height: 15rem; /* 최대 높이 설정 */
  overflow: auto; /* 내용이 넘칠 경우 스크롤 표시 */
`;

const UserInfoWrapper = styled.div`
  display: flex;
  width: 15rem;
  height: 1.5rem;
  justify-content: space-between;
  align-items: flex-end;
  margin-top: 0.5rem;
`;

const Name = styled.div`
  width: 3.25rem;
  height: 1.5rem;
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
`;

const Date = styled.div`
  width: 9.375rem;
  height: 1.5rem;
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
`;

const LineCommentContent = styled.p`
  font-size: 0.625rem;
  margin-top: 0.5rem;
`;

const LineCommentView = () => {
  return (
    <div>
      <LineCommentViewBox>
        <UserInfoWrapper>
          <Name>
            <Text fontSize="base">김현우</Text>
          </Name>
          <Date>
            <Text fontSize="xxs">2024년 1월 3일</Text>
          </Date>
          <GreyButton
            width={2.5}
            height={1.5}
            padding={0.2}
            fontSize="xxs"
            $backgroundColor={'grey100'}
            color={'white'}
          >
            수정
          </GreyButton>
        </UserInfoWrapper>
        <CommentContainer>
          <LineCommentContent>
            아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을
            모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까
            차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니
            그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을
            모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까
            차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니
            그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을
            모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까
            차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니
            그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을
            모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까 차이점을 모르죠...아니 그러니까
            차이점을 모르죠...아니
          </LineCommentContent>
        </CommentContainer>
      </LineCommentViewBox>
    </div>
  );
};

export default LineCommentView;
