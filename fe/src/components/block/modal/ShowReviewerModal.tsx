import Text from '@components/atom/Text';
import styled from 'styled-components';
import reviewerIcon from '../../../assets/icons/reviewerIcon.png';
import ToggleButton from '@components/atom/Button/ToggleButton';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 25.25rem;
  max-height: 23.375rem; /* 최대 높이 설정 */
  min-height: 23.375rem; /* 최대 높이 설정 */
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
  width: 23rem;
  height: 2.875rem;
  border-bottom: 0.0625rem solid #aaa;
  display: flex;
  align-items: flex-end;
  justify-content: flex-start;
`;

const MiddleBox = styled.div`
  width: 23rem;
  height: 1.6875rem;
  display: flex;
  align-items: flex-end; /* 세로 정렬을 flex-end로 설정 */
  justify-content: space-between; /* 가로 정렬을 양 끝으로 설정 */
  color: #858585;
`;

const UserBox = styled.div`
  width: 23rem;
  height: 3.125rem;
  display: flex;
  justify-content: space-between;
  margin-top: 0.5rem;
`;

const UserLeftBox = styled.div`
  width: 14.375rem;
  height: 3.125rem;
  display: flex;
  justify-content: flex-start;
  gap: 0.25rem;
`;

const ReviewerIcon = styled.img`
  width: 1.25rem;
  height: 1.25rem;
  color: #858585;
`;

const UserInfoBox = styled.div`
  width: 12.125rem;
  height: 3.125rem;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
`;

const ReviewToggleBox = styled.div`
  width: 6rem;
  height: 3.125rem;
  display: flex;
  gap: 0.25rem;
  justify-content: space-between;
  align-items: center;
`;

const ShowReviewerModal = () => {
  return (
    <div>
      <LoginBox>
        <UpperBox>
          <Text fontSize="lg" fontWeight="bold">
            Reviewers
          </Text>
        </UpperBox>
        <MiddleBox>
          <Text fontSize="base">두 SQL문의 차이가 뭘까요? 글의 리뷰어 목록</Text>
        </MiddleBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
        <UserBox>
          <UserLeftBox>
            <ReviewerIcon src={reviewerIcon} />
            <UserInfoBox>
              <Text fontSize="base" fontWeight="bold">
                김현우
              </Text>
              <Text fontSize="sm">총 12개의 리뷰를 남겨주었습니다.</Text>
            </UserInfoBox>
          </UserLeftBox>
          <ReviewToggleBox>
            <Text fontSize="sm">리뷰 끄기</Text>
            <ToggleButton />
          </ReviewToggleBox>
        </UserBox>
      </LoginBox>
    </div>
  );
};

export default ShowReviewerModal;
