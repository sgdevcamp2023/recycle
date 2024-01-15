import BelowCommentButton from '@components/atom/Button/BelowCommentButton';
import CommonButton from '@components/atom/Button/CommonButton';
import DoubleCheckButton from '@components/atom/Button/DoubleCheckButton';
import ModifyButton from '@components/atom/Button/ModifyButton';
import PaginationButton from '@components/atom/Button/PaginationButton';
import ReplyOfCommentButton from '@components/atom/Button/ReplyOfCommentButton';
import ReturnButton from '@components/atom/Button/ReturnButton';
import ReviewButton from '@components/atom/Button/ReviewButton';
import TemporaryStorageButton from '@components/atom/Button/TemporaryStorageButton';
import ToggleButton from '@components/atom/Button/ToggleButton';
import DefaultInput from '@components/atom/Input/DefaultInput';
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
        <CommonButton></CommonButton>
        <ReviewButton></ReviewButton>
        <BelowCommentButton></BelowCommentButton>
        <DoubleCheckButton></DoubleCheckButton>
        <ReturnButton></ReturnButton>
        <TemporaryStorageButton></TemporaryStorageButton>
        <ModifyButton></ModifyButton>
        <ReplyOfCommentButton></ReplyOfCommentButton>
        <ToggleButton></ToggleButton>
        <PaginationButton></PaginationButton>
      </div>
      <div>
        <DefaultInput
          placeholder='이름 입력'
          value={inputValue}
          onChange={handleInputChange}
          fontSize='1rem'
        ></DefaultInput>
      </div>
      <div>
        <DefaultInput
          width='44.25rem'
          height='6.25rem'
          placeholder='댓글을 작성하세요'
          value={inputValue}
          onChange={handleInputChange}
          fontSize='0.75rem'
          border='0.063rem solid black'
        ></DefaultInput>
      </div>
      <div>
        <DefaultInput
          width='49.5rem'
          height='2rem'
          placeholder='제목이나 내용을 입력하여 주세요!'
          value={inputValue}
          onChange={handleInputChange}
          fontSize='0.875rem'
          backgroundColor='#f4f4f4'
          color='9a9999'
          borderRadius='0.625rem'
          border='none'
          fontWeight='700'
        ></DefaultInput>
      </div>
    </>
  );
};

export default TestPage;
