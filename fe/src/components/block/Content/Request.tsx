import useTabStore, { DefaultTabType } from "@store/useTabStore";
import DefaultTab from "../navbar/DefaultTab";
import styled from "styled-components";
// import SearchInput from "../Search/SearchInput";
import DefaultCard, { DefaultCardProps } from "@components/atom/card/DefaultCard";
import { useEffect } from "react";

const Request = () => {
  const items: Record<string, DefaultTabType> = { "리뷰 요청 받은 질문": "reviewRequest" };
  const { setDefaultTabType } = useTabStore();
  useEffect(() => {
    setDefaultTabType("reviewRequest");
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
      {/* <SearchInput /> */}
      <RequestWrapper>
        {mockDataArray.map((item, idx) => {
          return <DefaultCard type={item.type} key={idx} commentCount={item.commentCount} title={item.title} />;
        })}
      </RequestWrapper>
    </BoxWrapper>
  );
};

export default Request;

const BoxWrapper = styled.div`
  width: 100%;
`;

const RequestWrapper = styled.div`
  width: 100%;
  margin-top: 0.5rem;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 0.5rem;
`;
