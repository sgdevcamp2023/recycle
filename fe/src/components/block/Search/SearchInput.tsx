import styled from "styled-components";
import DefaultInput from "./DefaultInput";
import { useRef } from "react";

const SearchInput = () => {
  const ref = useRef<HTMLInputElement>(null);
  return (
    <SearchInputWrapper>
      <DefaultInput ref={ref} type="text" placeholder="대화 찾기 또는 시작하기" />
    </SearchInputWrapper>
  );
};

export default SearchInput;

const SearchInputWrapper = styled.div`
  width: 100%;
  height: 2rem;
  background-color: ${({ theme }) => theme.backgroundColor.grey300};
`;
