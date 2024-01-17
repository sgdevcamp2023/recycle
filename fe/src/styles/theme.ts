import { DefaultTheme } from 'styled-components';

export type FontSizeType =
  | 'xxs'
  | 'xs'
  | 'sm'
  | 'base'
  | 'lg'
  | 'xl'
  | 'xxl'
  | 'xxxl';

export type BackgroundColorType =
  | 'green1'
  | 'green2'
  | 'green3'
  | 'grey1'
  | 'grey2'
  | 'grey3'
  | 'black'
  | 'white';

export type borderColorType = 'black' | 'grey' | 'green';

export type ColorType = 'black' | 'white' | 'inherit';

const theme: DefaultTheme = {
  fontSize: {
    xxs: '0.625rem',
    xs: '0.75rem',
    sm: '0.875rem',
    base: '1rem',
    lg: '1.25rem',
    xl: '2rem',
    xxl: '2.5rem',
  },
  backgroundColor: {
    white: '#FFFFFF',
    black: '#000000',
    green1: '#1EB649',
    green2: '#26DE81',
    green3: '#AEE5BE',
    grey1: '#8A8A8A',
    grey2: '#939393',
    grey3: '#F5F5F5',
  },
  color: {
    white: '#FFFFFF',
    black: '#000000',
    inherit: 'inherit',
  },
  borderColor: {
    black: '#000',
    white: '#fff',
    green: '#78C37C',
  },
};

export default theme;
