import styled from "styled-components";
import DefaultTab from "../navbar/DefaultTab";
import useTabStore, { DefaultTabType } from "@store/useTabStore";
import { useEffect } from "react";
import SearchInput from "../Search/SearchInput";
import DefaultCard, { DefaultCardProps, DefaultCardType } from "@components/atom/card/DefaultCard";
import { useNavigate } from "react-router-dom";

const Question = () => {
  const items: Record<string, DefaultTabType> = { "내가 작성한 질문": "myQuestion", "임시 저장된 질문": "questionDrafts" };
  const { defaultTabType, setDefaultTabType } = useTabStore();
  const navigate = useNavigate();
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

  interface handleCardClickProps {
    type: DefaultCardType;
  }
  //  "question" | "review" | "add"
  const handleCardClick = ({ type }: handleCardClickProps) => {
    if (type == "add") {
      navigate("/newQuestion");
    }
  };
  return (
    <BoxWrapper>
      <DefaultTab items={items} />
      <SearchInput />
      <QuestionWrapper>
        {defaultTabType == "myQuestion" &&
          mockDataArray.map((item, idx) => {
            return (
              <DefaultCard
                type={item.type}
                key={idx}
                commentCount={item.commentCount}
                title={item.title}
                onClick={() => handleCardClick({ type: item.type })}
              />
            );
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
  margin-top: 0.5rem;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 0.5rem;
`;
