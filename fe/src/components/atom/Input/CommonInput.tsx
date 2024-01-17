import DefaultInput from './DefaultInput'; // DefaultInput 컴포넌트를 가져옵니다.

interface CommonInputProps {
  value: string;
  placeholder: string;
  color?: string;
  border?: number | string;
  width?: number | string;
  height?: number | string;
  marginBottom?: number | string;
}

const CommonInput: React.FC<CommonInputProps> = ({
  value,
  placeholder,
  color,
  border,
  marginBottom,
  width,
  height,
}) => {
  return (
    <DefaultInput
      height={height}
      placeholder={placeholder}
      fontSize='1rem'
      color={color}
      backgroundColor='white'
      value={value}
      border={border}
      marginBottom={marginBottom}
      width={width}
    />
  );
};

export default CommonInput;
