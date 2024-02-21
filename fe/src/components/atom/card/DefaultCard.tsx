import styled from 'styled-components';
import Text from '../Text';
import { MouseEventHandler, ReactNode } from 'react';
import MDEditor from '@uiw/react-md-editor';

export type DefaultCardType = 'question' | 'review' | 'add' | null;

export interface DefaultCardProps {
  type: DefaultCardType;
  commentCount?: number;
  title?: string;
  width?: number;
  height?: number;
  author?: string;
  author_id?: number;
  content?: string;
  created_at?: string;
  question_id?: number;
  review_cnt?: number;
  updated_at?: string;
  children?: ReactNode;
  isLoading?: boolean;
  onClick?: MouseEventHandler<HTMLDivElement>;
}

interface CardWrapperProps {
  width?: number;
  height?: number;
}

interface ContentContainerProps {
  type: DefaultCardType;
}

const DefaultCard = ({
  type,
  commentCount,
  title,
  width,
  height,
  content,
  children,
  isLoading,
  onClick,
}: DefaultCardProps) => {
  return (
    <CardWrapper height={height} width={width} onClick={onClick}>
      <HeaderContainer>
        <Text fontSize="xl" fontWeight="bold">
          {title && title}
        </Text>
      </HeaderContainer>
      <ContentContainer type={type}>
        {type == 'add' && (
          <Text fontSize="xl" fontWeight="bold">
            new
          </Text>
        )}
        {/* {type == 'question' && <>{content}</>}
        {type == 'review' && <>{content}</>} */}
        {content && (
          <MarkdownBox>
            <MDEditor.Markdown source={content} />
          </MarkdownBox>
        )}
        {children}
      </ContentContainer>
      <Divider />
      <FooterContainer>
        {type == 'add' && (
          <Text fontSize="lg" fontWeight="bold">
            새로운 질문하기
          </Text>
        )}
        {type == 'question' && !isLoading && (
          <TextBox>
            <Text color="green" fontSize="lg">
              {commentCount}
            </Text>
            <Text>개의 코멘트가 달렸습니다</Text>
          </TextBox>
        )}
        {type == 'review' && !isLoading && (
          <TextBox>
            <Text color="green" fontSize="lg">
              {commentCount}
            </Text>
            <Text>개의 코멘트를 남기셨습니다</Text>
          </TextBox>
        )}
      </FooterContainer>
    </CardWrapper>
  );
};

export default DefaultCard;

const CardWrapper = styled.div<CardWrapperProps>`
  cursor: pointer;
  width: 100%;
  min-height: 16rem;
  background-color: ${({ theme }) => theme.backgroundColor.grey300};
  width: ${({ width }) => width}rem;
  height: ${({ height }) => height}rem;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Divider = styled.div`
  width: 99%;
  height: 0px;
  border: 0.5px solid ${({ theme }) => theme.backgroundColor.grey400};
`;

const HeaderContainer = styled.div`
  flex: 1;
  padding: 1rem 0.5rem;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const ContentContainer = styled.div<ContentContainerProps>`
  flex: 4;
  width: 90%;
  height: 100%;
  padding: 1rem 0.5rem;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const FooterContainer = styled.div`
  display: flex;
  flex: 1;
  text-align: center;
  justify-content: center;
  align-items: center;
  padding: 0.5rem;
`;

const TextBox = styled.div`
  display: flex;
  align-items: flex-end;
`;

const MarkdownBox = styled.div`
  box-sizing: border-box;
  height: 100%;
  width: 100%;
  border-radius: 10px;
  padding: 0.25rem;
  background-color: white;
  /* border: 1px solid black; */
  overflow-y: hidden; /* 항상 수직 스크롤바를 감춤 */
  overflow-x: hidden; /* 가로 스크롤바를 감춤 */
  &:hover {
    overflow-y: auto; /* 마우스를 올렸을 때 수직 스크롤바를 표시 */
  }
  position: relative;
`;
