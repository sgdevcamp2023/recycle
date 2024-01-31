import { BackgroundColorType, ColorType, FontSizeType } from '@styles/theme';
import {
  ChangeEvent,
  ChangeEventHandler,
  KeyboardEventHandler,
  RefObject,
  forwardRef,
} from 'react';
import styled from 'styled-components';

interface CustomInputProps {
  type: string; // password, normal
  width?: number | string;
  height?: number | string;
  maxLength?: number;
  initValue?: string;
  placeholder?: string;
  fontSize?: FontSizeType;
  color?: ColorType;
  backgroundColor?: BackgroundColorType;
  padding?: number | string;
  onChange?: ChangeEventHandler<HTMLInputElement>;
}

const CustomInput = forwardRef<HTMLInputElement, CustomInputProps>(
  (
    {
      type = 'text',
      initValue = '',
      maxLength = 524288,
      width = '100%',
      height = '100%',
      placeholder = '',
      fontSize = 'base',
      color = 'black',
      backgroundColor = 'white',
      padding = '0.5rem',
      onChange,
    },
    ref,
  ) => {
    if (initValue && ref) {
      (ref as RefObject<HTMLInputElement>)!.current!.value = initValue;
    }

    return (
      <StyledInput
        ref={ref}
        type={type}
        maxLength={maxLength}
        width={width}
        height={height}
        placeholder={placeholder}
        fontSize={fontSize}
        color={color}
        backgroundColor={backgroundColor}
        padding={padding}
        onChange={onChange}
      />
    );
  },
);

CustomInput.displayName = 'CustomInput';

const StyledInput = styled.input<
  Pick<CustomInputProps, 'padding' | 'width' | 'height' | 'fontSize' | 'color' | 'backgroundColor'>
>`
  width: ${({ width }) => (width === '100%' ? 'calc(100%)' : `${width}rem`)};
  height: ${({ height }) => (height === '100%' ? '100%' : `${height}rem`)};
  padding: ${({ padding }) => (padding === '0.5rem' ? '0.5rem' : `${padding}`)};
  border: 0.0625rem solid ${({ theme }) => theme.borderColor.grey500};
  border-radius: 0.25rem;
  color: ${({ theme, color }) => (color ? theme.color[color] : theme.color.black)};
  font-weight: 500;
  font-size: ${({ theme, fontSize }) =>
    fontSize ? theme.fontSize[fontSize] : theme.fontSize.base};
  background-color: ${({ theme, backgroundColor }) =>
    backgroundColor ? theme.backgroundColor[backgroundColor] : theme.backgroundcolor.inherit};

  ::placeholder {
    color: ${({ theme }) => theme.color.grey};
  }
`;

export default CustomInput;
