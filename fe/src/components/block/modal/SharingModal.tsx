import Text from '@components/atom/Text';
import styled from 'styled-components';
import searchIcon from '../../../assets/icons/searchIcon.png';
import linkIcon from '../../../assets/icons/linkIcon.png';
import reviewerIcon from '../../../assets/icons/reviewerIcon.png';
import DefaultButton from '@components/atom/Button/DefaultButton';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 25.25rem;
  max-height: 23.375rem; /* 최대 높이 설정 */
  min-height: 23.375rem; /* 최대 높이 설정 */
  overflow: auto; /* 스크롤이 필요한 경우 활성화 */
  padding: 0.25rem 1rem;
  background-color: white;
  color: black;
  border: 0.0625rem solid #1eb649;
  border-radius: 1.25rem;

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

const LinkBox = styled.div`
  width: 23rem;
  height: 3.25rem;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  margin-bottom: 1.375rem;
`;

const InsertLingBox = styled.div`
  width: 23rem;
  height: 1.625rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const BorderlineBox = styled.div`
  width: 23rem;
  height: 1.5rem;
  border: 0.0625rem solid black;
  border-radius: 0.3125rem;
  overflow: hidden;
`;

const LinkInput = styled.input`
  width: 21.25rem;
  height: 1.5rem;
  background-color: #eee;
  border: none;
`;

const IconBox = styled.div`
  width: 1.5rem;
  height: 1.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #d4f1dd;
`;

const IconBtn = styled.img`
  width: 1rem;
  height: 1rem;
  cursor: pointer;
`;

const UserInviteBox = styled.div`
  width: 23rem;
  height: 1.5rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 0.5rem;
`;

const UserInfoBox = styled.div`
  width: 4.25rem;
  height: 1.5rem;
  display: flex;
  align-items: center;
  gap: 0.25rem;
`;

const ReviewerIcon = styled.img`
  width: 1.25rem;
  height: 1.25rem;
  color: #858585;
`;

interface UserInviteItemProps {
  username: string;
}

const UserInviteItem: React.FC<UserInviteItemProps> = ({ username }) => (
  <UserInviteBox>
    <UserInfoBox>
      <ReviewerIcon src={reviewerIcon} />
      <Text fontSize="base" fontWeight="bold">
        {username}
      </Text>
    </UserInfoBox>
    <DefaultButton width={4.3125} height={1.5} padding={0.25} fontSize="base">
      Invite
    </DefaultButton>
  </UserInviteBox>
);

const SharingModal = () => {
  const usernames = [
    '김현우',
    '이규민',
    '신승용',
    '김종준',
    '김영현',
    '안재진',
    '김선재',
    '계동원' /* ... */,
  ];

  return (
    <div>
      <LoginBox>
        <UpperBox>
          <Text fontSize="lg" fontWeight="bold">
            Share
          </Text>
        </UpperBox>
        <LinkBox>
          <Text fontSize="xxs">Direct share link</Text>
          <BorderlineBox>
            <InsertLingBox>
              <LinkInput />
              <IconBox>
                <IconBtn src={linkIcon} />
              </IconBox>
            </InsertLingBox>
          </BorderlineBox>
        </LinkBox>
        <LinkBox>
          <Text fontSize="xxs">아이디로 초대하기</Text>
          <BorderlineBox>
            <InsertLingBox>
              <LinkInput />
              <IconBox>
                <IconBtn src={searchIcon} />
              </IconBox>
            </InsertLingBox>
          </BorderlineBox>
        </LinkBox>
        {usernames.map((username, index) => (
          <UserInviteItem key={index} username={username} />
        ))}
      </LoginBox>
    </div>
  );
};

export default SharingModal;
