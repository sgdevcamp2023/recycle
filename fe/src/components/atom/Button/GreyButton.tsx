import styled from 'styled-components';
import { BackgroundColorType, ColorType, FontSizeType, borderColorType } from '@styles/theme';
import { MouseEventHandler } from 'react';

interface GreyButtonProps {
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
  $isActive?: boolean;
}

const GreyButton = ({
  width = 'default',
  height = 'default',
  fontSize = 'xs',
  color = 'grey',
  $borderColor = 'green',
  $backgroundColor = 'white',
  padding = '0.25rem',
  $isActive = false,
  onClick,
  children,
}: GreyButtonProps) => {
  return (
    <GreyButtonBox
      width={width}
      height={height}
      fontSize={fontSize}
      color={color}
      $borderColor={$borderColor}
      $backgroundColor={$backgroundColor}
      padding={padding}
      onClick={onClick}
      $isActive={$isActive}
    >
      {children}
    </GreyButtonBox>
  );
};

const GreyButtonBox = styled.button<
  Pick<
    GreyButtonProps,
    | 'width'
    | 'height'
    | 'fontSize'
    | 'color'
    | '$backgroundColor'
    | '$borderColor'
    | 'padding'
    | 'cursor'
    | '$isActive'
  >
>`
  width: ${({ width }) => (width === 'default' ? '100%' : `${width}rem`)};
  height: ${({ height }) => (height === 'default' ? '100%' : `${height}rem`)};
  padding: ${({ padding }) => (padding === 'default' ? '1rem' : `${padding}`)};
  border: none;
  border-radius: 0.25rem;
  color: ${({ theme, color }) => (color ? theme.color[color] : theme.color.white)};
  font-weight: 500;
  font-size: ${({ theme, fontSize }) =>
    fontSize ? theme.fontSize[fontSize] : theme.fontSize.base};
  background-color: ${({ theme, $backgroundColor }) =>
    $backgroundColor ? theme.backgroundColor[$backgroundColor] : theme.backgroundcolor.white};
  cursor: pointer;
  transition: all 0.5s;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    background-color: ${({ theme, $backgroundColor }) =>
      $backgroundColor ? theme.backgroundColor.grey200 : theme.backgroundColor.grey200};
    border: 0.0625rem solid #939393;
    color: ${({ theme, color }) => (color ? theme.color.white : theme.color.black)};
  }
`;

export default GreyButton;
