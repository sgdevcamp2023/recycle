import LineCommentView from '@components/atom/Comment/LineCommentView';
import LineCommentWrite from '@components/atom/Comment/LineCommentWrite';
import Text from '@components/atom/Text';
import { ChangeEvent, useState } from 'react';

const TestPage = () => {
  const [inputValue, setInputValue] = useState('');

  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  return (
    <>
      <>테스트페이지임여</>
      <div>
        <DefaultInput
          placeholder="이름 입력"
          value={inputValue}
          onChange={handleInputChange}
          fontSize="1rem"
        ></DefaultInput>
      </div>
      <div>
        <DefaultInput
          width="44.25rem"
          height="6.25rem"
          placeholder="댓글을 작성하세요"
          value={inputValue}
          onChange={handleInputChange}
          fontSize="0.75rem"
          border="0.063rem solid black"
        ></DefaultInput>
      </div>
      <div>
        <DefaultInput
          width="49.5rem"
          height="2rem"
          placeholder="제목이나 내용을 입력하여 주세요!"
          value={inputValue}
          onChange={handleInputChange}
          fontSize="0.875rem"
          backgroundColor="#f4f4f4"
          color="9a9999"
          borderRadius="0.625rem"
          border="none"
          fontWeight="700"
        ></DefaultInput>
      </div>
      <div>
        <Text>Hello</Text>
      </div>
      <div>
        <LineCommentView></LineCommentView>
      </div>
      <div>
        <LineCommentWrite></LineCommentWrite>
      </div>
    </>
  );
};

export default TestPage;
