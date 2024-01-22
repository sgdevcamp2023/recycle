import styled from "styled-components";
import DefaultTab from "../navbar/DefaultTab";
import useTabStore, { DefaultTabType } from "@store/useTabStore";
import { useEffect } from "react";
import SearchInput from "../Search/SearchInput";
import DefaultCard, { DefaultCardProps } from "@components/atom/card/DefaultCard";

const Question = () => {
  const items: Record<string, DefaultTabType> = { "내가 작성한 질문": "myQuestion", "임시 저장된 질문": "questionDrafts" };
  const { defaultTabType, setDefaultTabType } = useTabStore();
  useEffect(() => {
    setDefaultTabType("myQuestion");
  }, [setDefaultTabType]);

  const mockDataArray: DefaultCardProps[] = [
    {
      type: "add",
    },
    {
      type: "question",
      commentCount: 8,
      title: "Title 2",
    },
    {
      type: "question",
      commentCount: 8,
      title: "Title 2",
    },
    {
      type: "question",
      commentCount: 8,
      title: "Title 2",
    },
    {
      type: "question",
      commentCount: 8,
      title: "Title 2",
    },
  ];
  return (
    <BoxWrapper>
      <DefaultTab items={items} />
      <SearchInput />
      <QuestionWrapper>
        {defaultTabType == "myQuestion" &&
          mockDataArray.map((item, idx) => {
            return <DefaultCard type={item.type} key={idx} commentCount={item.commentCount} title={item.title} />;
          })}
        {defaultTabType == "questionDrafts" &&
          mockDataArray.map((item, idx) => {
            return <DefaultCard type={item.type} key={idx} commentCount={item.commentCount} title={item.title} />;
          })}
      </QuestionWrapper>
    </BoxWrapper>
  );
};

export default Question;

const BoxWrapper = styled.div`
  width: 100%;
`;

const QuestionWrapper = styled.div`
  width: 100%;
  margin-top: 2rem;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 0.5rem;
`;
