import styled from 'styled-components';
import { BackgroundColorType, ColorType, FontSizeType, borderColorType } from '@styles/theme';
import { MouseEventHandler } from 'react';

interface TabButtonProps {
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
  $isActive?: boolean | null;
  isTabButton?: boolean;
}

const TabButton = ({
  width = 'default',
  height = 'default',
  fontSize = 'base',
  color = 'white',
  $borderColor = 'green',
  $backgroundColor = 'green100',
  padding = '1rem',
  $isActive = null,
  isTabButton = false,
  onClick,
  children,
}: TabButtonProps) => {
  return (
    <TabButtonBox
      width={width}
      height={height}
      fontSize={fontSize}
      color={color}
      $borderColor={$borderColor}
      $backgroundColor={$backgroundColor}
      padding={padding}
      onClick={onClick}
      $isActive={isTabButton && $isActive}
    >
      {children}
    </TabButtonBox>
  );
};

const TabButtonBox = styled.button<
  Pick<
    TabButtonProps,
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
  font-weight: 500;
  font-size: ${({ theme, fontSize }) =>
    fontSize ? theme.fontSize[fontSize] : theme.fontSize.base};
  cursor: pointer;
  transition: all 0.5s;
  display: flex;
  align-items: center;
  justify-content: center;
  color: ${({ $isActive }) => ($isActive ? 'white' : '#888')};
  background-color: ${({ theme, $isActive }) =>
    $isActive ? theme.backgroundColor.green100 : theme.backgroundColor.white};
  border-radius: 8px;
  &:hover {
    background-color: ${({ theme }) => theme.backgroundColor.green100};
    color: white;
  }
`;

export default TabButton;
