import styled from 'styled-components';

interface CustomInputProps {
  width?: number | string;
  height?: number | string;
  placeholder?: string;
  fontSize?: string;
  fontWeight?: string; // 추가된 부분
  color?: string;
  backgroundColor?: string;
  value: string;
  borderRadius?: string;
  padding?: number | string;
  border?: number | string;
  marginBottom?: number | string;
}

const DefaultInput = ({
  width = '22rem',
  height = '3rem',
  placeholder = '',
  fontSize = '1rem',
  fontWeight = 'normal',
  color = 'black',
  backgroundColor = 'white',
  value,
  borderRadius = '0.25rem',
  border = '0.063rem solid #AAAAAA',
  padding = '',
  marginBottom = '',
}: CustomInputProps) => {
  return (
    <StyledInput
      type='text'
      value={value}
      width={width}
      height={height}
      placeholder={placeholder}
      fontSize={fontSize}
      fontWeight={fontWeight}
      color={color}
      backgroundColor={backgroundColor}
      borderRadius={borderRadius}
      border={border}
      padding={padding}
      marginBottom={marginBottom}
    />
  );
};

const StyledInput = styled.input<CustomInputProps>`
  width: ${({ width }) => (typeof width === 'number' ? `${width}px` : width)};
  height: ${({ height }) =>
    typeof height === 'number' ? `${height}px` : height};
  padding: ${({ padding }) =>
    typeof padding === 'number' ? `${padding}px` : padding};
  border: ${({ border }) => border};
  border-radius: ${({ borderRadius }) => borderRadius};
  color: ${({ color }) => color};
  font-size: ${({ fontSize }) => fontSize};
  font-weight: ${({ fontWeight }) => fontWeight}; // 추가된 부분
  background-color: ${({ backgroundColor }) => backgroundColor};
  ::placeholder {
    color: #aaa;
    text-align: left;
  }
  &:focus {
    outline: none;
  }
`;

export default DefaultInput;
