import { BackgroundColorType, ColorType, FontSizeType } from '@styles/theme';
import { RefObject, forwardRef } from 'react';
import styled from 'styled-components';

interface BelowCommentInputProps {
  // type: string; // password, normal
  width?: number | string;
  height?: number | string;
  maxLength?: number;
  initValue?: string;
  placeholder?: string;
  fontSize?: FontSizeType;
  color?: ColorType;
  backgroundColor?: BackgroundColorType;
  padding?: number | string;
}

const BelowCommentWrite = forwardRef<HTMLTextAreaElement, BelowCommentInputProps>(
  (
    {
      // type = 'text',
      initValue = '',
      maxLength = 524288,
      width = '100%',
      height = '100%',
      placeholder = '',
      fontSize = 'base',
      color = 'black',
      backgroundColor = 'white',
      padding = '0.5rem',
    },
    ref,
  ) => {
    if (initValue && ref) {
      (ref as RefObject<HTMLTextAreaElement>)!.current!.value = initValue;
    }

    return (
      <StyledInput
        ref={ref}
        // type={type}
        maxLength={maxLength}
        width={width}
        height={height}
        placeholder={placeholder}
        fontSize={fontSize}
        color={color}
        backgroundColor={backgroundColor}
        padding={padding}
      />
    );
  },
);

const StyledInput = styled.textarea<
  Pick<
    BelowCommentInputProps,
    'padding' | 'width' | 'height' | 'fontSize' | 'color' | 'backgroundColor'
  >
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
  overflow-y: auto; /* 내용이 넘칠 경우 수직 스크롤 표시 */
  resize: none; /* 사용자가 크기를 조정할 수 없도록 함 */

  ::placeholder {
    color: ${({ theme }) => theme.color.grey};
  }
`;

export default BelowCommentWrite;
