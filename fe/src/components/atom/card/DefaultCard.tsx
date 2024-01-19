import styled from "styled-components";
import Text from "../Text";
import { ReactNode } from "react";

export type TabType = "question" | "review" | "add" | null;

interface DefaultCardProps {
  type: TabType;
  commentCount?: number;
  title?: string;
  width?: number;
  height?: number;
  content?: ReactNode;
}

interface CardWrapperProps {
  width?: number;
  height?: number;
}

interface ContentContainerProps {
  type: TabType;
}

const DefaultCard = ({ type, commentCount, title, width, height, content }: DefaultCardProps) => {
  return (
    <CardWrapper height={height} width={width}>
      <ContentContainer type={type}>
        {type == "add" && (
          <Text fontSize="xl" fontWeight="bold">
            new
          </Text>
        )}
        {type == "question" && (
          <>
            <Text fontSize="xl" fontWeight="bold">
              {title}
            </Text>
            {content}
          </>
        )}
        {type == "review" && (
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
        {type == "add" && (
          <Text fontSize="lg" fontWeight="bold">
            새로운 질문하기
          </Text>
        )}
        {type == "question" && (
          <TextBox>
            <Text color="green" fontSize="lg">
              {commentCount}
            </Text>
            <Text>개의 코멘트가 달렸습니다</Text>
          </TextBox>
        )}
        {type == "review" && (
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
  min-width: 14rem;
  min-height: 16rem;
  background-color: ${({ theme }) => theme.backgroundColor.grey300};
  width: ${({ width }) => width}rem;
  height: ${({ height }) => height}rem;
  border-radius: 10px;
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
