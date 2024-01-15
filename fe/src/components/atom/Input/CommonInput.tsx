import React, { ChangeEventHandler } from 'react';
import DefaultInput from './DefaultInput'; // DefaultInput 컴포넌트를 가져옵니다.

interface CommonInputProps {
  value: string;
  onChange: ChangeEventHandler<HTMLInputElement>;
}

const CommonInput: React.FC<CommonInputProps> = ({ value, onChange }) => {
  return (
    <DefaultInput
      width='22.25rem'
      height='3rem'
      placeholder='아무거나 입력하세요'
      fontSize='1rem'
      color='#939393'
      backgroundColor='#f0f0f0'
      value={value}
      onChange={onChange}
    />
  );
};

export default CommonInput;
