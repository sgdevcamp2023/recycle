import styled from 'styled-components';
import BlockNote from '../Editor/BlockNote';
import DefaultInput from '../Search/DefaultInput';
import { useEffect, useRef } from 'react';

const CreateQuestion = () => {
  const ref = useRef<HTMLInputElement>(null);

  useEffect(() => {
    console.log(ref.current && ref.current.value);
  }, [ref?.current?.value]);
  return (
    <>
      <TitleWrapper>
        <DefaultInput
          type="text"
          height={40}
          ref={ref}
          fontSize="xl"
          $backgroundColor="white"
          placeholder="제목을 입력해주세요"
          padding="0 0.5rem 0.5rem 0.5rem"
        />
      </TitleWrapper>
      <BlockNote />
    </>
  );
};

export default CreateQuestion;

const TitleWrapper = styled.div`
  width: calc(100% - 2.5rem);
  padding-left: 3rem;
`;
