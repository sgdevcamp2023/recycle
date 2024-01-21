import styled from 'styled-components';
import gitLogo from '../../../assets/icons/github-mark.png';
import Text from '@components/atom/Text';
import mainLogo from '../../../assets/logos/ZzaugLogo.png';
import ExperienceButton from '@components/atom/Button/ExperienceButton';
import LinkToGitButton from '@components/atom/Button/LinkToGitButton';

const LoginBox = styled.div`
  box-sizing: border-box;
  width: 38.75rem;
  height: 12.5rem;
  padding: 0.75rem 0.625rem;
  background-color: white;
  color: black;
  border: 1px solid black;
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const GithubLogo = styled.img`
  width: 11.25rem;
  height: 11.25rem;
`;

const RightBox = styled.div`
  width: 20rem;
  height: 11.25rem;
  /* border: 1px solid black; */
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  position: relative;
`;

const Title = styled.div`
  font-size: 1.5rem;
  font-weight: 700;
`;

const Content = styled.div`
  width: 20rem;
  height: 3.375rem;
  /* border: 1px solid black; */
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  /* gap: 0.25rem; */
  justify-content: space-between;
  position: absolute;
  top: 3.25rem;
`;

const ButtonBox = styled.div`
  width: 20rem;
  height: 3.375rem;
  /* border: 1px solid black; */
  display: flex;
  align-items: flex-end;
  justify-content: flex-end;
  position: absolute;
  bottom: 0;
  gap: 0.25rem;
`;

const Logo = styled.img`
  /* width: 3rem; */
  height: 1.2rem;
  display: inline;
  float: left;
`;

const ReviewToGitModal = () => {
  return (
    <div>
      <LoginBox>
        <GithubLogo src={gitLogo} alt='github Icon' />
        <RightBox>
          <Title>내 리뷰를 깃허브에 올려봐요</Title>
          <Content>
            <Text fontSize='xs'>열심히 작성한 리뷰들</Text>
            <Text fontSize='xs'>여기에만 남겨놓긴 아쉽지 않나요?</Text>
            <Text
              fontSize='xs'
              style={{ display: 'flex', alignItems: 'center' }}
            >
              <Logo src={mainLogo} alt='zzaug main logo' />
              에서는 GitHub에 리뷰를 업로드 해줘요!
            </Text>
          </Content>
          <ButtonBox>
            <LinkToGitButton />
            <ExperienceButton />
          </ButtonBox>
        </RightBox>
      </LoginBox>
    </div>
  );
};

export default ReviewToGitModal;
