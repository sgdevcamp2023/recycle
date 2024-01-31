import styled from 'styled-components';
import Text from '../Text';
import { MouseEventHandler } from 'react';

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
  onClick,
}: DefaultCardProps) => {
  return (
    <CardWrapper height={height} width={width} onClick={onClick}>
      <ContentContainer type={type}>
        {type == 'add' && (
          <Text fontSize="xl" fontWeight="bold">
            new
          </Text>
        )}
        {type == 'question' && (
          <>
            <Text fontSize="xl" fontWeight="bold">
              {title}
            </Text>
            {content}
          </>
        )}
        {type == 'review' && (
          <>
            <Text fontSize="xl" fontWeight="bold">
              {title}
            </Text>
            {content}
          </>
        )}
      </ContentContainer>
      <Divider />
      <FooterContainer>
        {type == 'add' && (
          <Text fontSize="lg" fontWeight="bold">
            새로운 질문하기
          </Text>
        )}
        {type == 'question' && (
          <TextBox>
            <Text color="green" fontSize="lg">
              {commentCount}
            </Text>
            <Text>개의 코멘트가 달렸습니다</Text>
          </TextBox>
        )}
        {type == 'review' && (
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
`;

const Divider = styled.div`
  width: 100%;
  height: 0px;
  border: 0.5px solid ${({ theme }) => theme.backgroundColor.grey400};
`;

const ContentContainer = styled.div<ContentContainerProps>`
  flex: 4;
  padding: 1rem 0.5rem;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
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
