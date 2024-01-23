import styled from "styled-components";
import DefaultInput from "./DefaultInput";
import { useRef } from "react";
import useTabStore from "@store/useTabStore";

const SearchInput = () => {
  const ref = useRef<HTMLInputElement>(null);
  const { defaultTabType } = useTabStore();
  return (
    <SearchInputWrapper>
      <DefaultInput
        disabled={defaultTabType == "questionDrafts" || defaultTabType == "reviewDrafts"}
        ref={ref}
        type="text"
        placeholder="원하는 글의 제목이나 내용을 입력해주세요"
      />
    </SearchInputWrapper>
  );
};

export default SearchInput;

const SearchInputWrapper = styled.div`
  width: 100%;
  height: 2.5rem;
  margin-top: 0.5rem;
`;
