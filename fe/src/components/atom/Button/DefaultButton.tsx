import styled from 'styled-components';
import {
  BackgroundColorType,
  ColorType,
  FontSizeType,
  borderColorType,
} from '@styles/theme';
import { MouseEventHandler } from 'react';

interface DefaultButtonProps {
  children?: React.ReactNode;
  width?: number | string;
  height?: number | string;
  color?: ColorType;
  fontSize?: FontSizeType;
  backgroundColor?: BackgroundColorType;
  borderColor?: borderColorType;
  onClick?: MouseEventHandler<HTMLButtonElement>;
  padding?: number | string;
  cursor?: string;
}

const DefaultButton = ({
  width = 'default',
  height = 'default',
  fontSize = 'base',
  color = 'white',
  borderColor = 'green',
  backgroundColor = 'green100',
  padding = '1rem',
  onClick,
  children,
}: DefaultButtonProps) => {
  return (
    <DefaultButtonBox
      width={width}
      height={height}
      fontSize={fontSize}
      color={color}
      borderColor={borderColor}
      backgroundColor={backgroundColor}
      padding={padding}
      onClick={onClick}
    >
      {children}
    </DefaultButtonBox>
  );
};

const DefaultButtonBox = styled.button<
  Pick<
    DefaultButtonProps,
    | 'width'
    | 'height'
    | 'fontSize'
    | 'color'
    | 'backgroundColor'
    | 'borderColor'
    | 'padding'
    | 'cursor'
  >
>`
  width: ${({ width }) => (width === 'default' ? '100%' : `${width}rem`)};
  height: ${({ height }) => (height === 'default' ? '100%' : `${height}rem`)};
  padding: ${({ padding }) => (padding === 'default' ? '1rem' : `${padding}`)};
  border: none;
  border-radius: 0.25rem;
  color: ${({ theme, color }) =>
    color ? theme.color[color] : theme.color.white};
  font-weight: 500;
  font-size: ${({ theme, fontSize }) =>
    fontSize ? theme.fontSize[fontSize] : theme.fontSize.base};
  background-color: ${({ theme, backgroundColor }) =>
    backgroundColor
      ? theme.backgroundColor[backgroundColor]
      : theme.backgroundColor.white};
  cursor: pointer;
  transition: all 0.5s;

  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    background-color: ${({ theme, backgroundColor }) =>
      backgroundColor
        ? theme.backgroundColor.white
        : theme.backgroundColor.green100};
    border: 0.063rem solid #1eb649;
    color: ${({ theme, color }) =>
      color ? theme.color.green : theme.color.white};
  }
`;

export default DefaultButton;
