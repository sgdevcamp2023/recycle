import styled from 'styled-components';
import { BackgroundColorType, ColorType, FontSizeType, borderColorType } from '@styles/theme';
import { MouseEventHandler } from 'react';

interface ReverseButtonProps {
  children?: React.ReactNode;
  width?: number | string;
  height?: number | string;
  color?: ColorType;
  fontSize?: FontSizeType;
  $backgroundColor?: BackgroundColorType;
  $borderColor?: borderColorType;
  onClick?: MouseEventHandler<HTMLButtonElement>;
  padding?: number | string;
  cursor?: string;
  border?: number | string;
}

const ReverseButton = ({
  width = 'default',
  height = 'default',
  fontSize = 'base',
  color = 'green',
  $borderColor = 'green',
  $backgroundColor = 'white',
  padding = '1rem',
  border = '1px solid #1EB649',
  onClick,
  children,
}: ReverseButtonProps) => {
  return (
    <ReverseButtonProps
      width={width}
      height={height}
      fontSize={fontSize}
      color={color}
      $borderColor={$borderColor}
      $backgroundColor={$backgroundColor}
      padding={padding}
      onClick={onClick}
      border={border}
    >
      {children}
    </ReverseButtonProps>
  );
};

const ReverseButtonProps = styled.button<
  Pick<
    ReverseButtonProps,
    | 'width'
    | 'height'
    | 'fontSize'
    | 'color'
    | '$backgroundColor'
    | '$borderColor'
    | 'padding'
    | 'cursor'
    | 'border'
  >
>`
  width: ${({ width }) => (width === 'default' ? '100%' : `${width}rem`)};
  height: ${({ height }) => (height === 'default' ? '100%' : `${height}rem`)};
  padding: ${({ padding }) => (padding === 'default' ? '1rem' : `${padding}`)};
  border-radius: 0.25rem;
  border: ${({ border }) => border};
  color: ${({ theme, color }) => (color ? theme.color[color] : theme.color.green)};
  font-weight: 500;
  font-size: ${({ theme, fontSize }) =>
    fontSize ? theme.fontSize[fontSize] : theme.fontSize.base};
  background-color: ${({ theme, $backgroundColor }) =>
    $backgroundColor ? theme.$backgroundColor[$backgroundColor] : theme.backgroundcolor.white};
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;

  transition: all 0.5s;

  &:hover {
    background-color: ${({ theme, $backgroundColor }) =>
      $backgroundColor ? theme.backgroundColor.green100 : theme.backgroundColor.white};
    border: none;
    color: ${({ theme, color }) => (color ? theme.color.white : theme.color.green)};
  }
`;

export default ReverseButton;
