import { DefaultTheme } from 'styled-components';

export type FontSizeType = 'xxs' | 'xs' | 'sm' | 'base' | 'lg' | 'xl' | 'xxl' | 'xxxl';

export type BackgroundColorType =
  | 'green100'
  | 'green200'
  | 'green300'
  | 'grey100'
  | 'grey200'
  | 'grey300'
  | 'grey400'
  | 'black'
  | 'white';

export type borderColorType = 'black' | 'grey' | 'grey500' | 'green';

export type ColorType = 'black' | 'white' | 'inherit' | 'green' | 'grey';

const theme: DefaultTheme = {
  fontSize: {
    xxs: '0.625rem',
    xs: '0.75rem',
    sm: '0.875rem',
    base: '1rem',
    lg: '1.25rem',
    xl: '2rem',
    xxl: '2.5rem',
    xxxl: '4rem',
  },
  backgroundColor: {
    white: '#FFFFFF',
    black: '#000000',
    green100: '#1EB649',
    green200: '#26DE81',
    green300: '#AEE5BE',
    grey100: '#8A8A8A',
    grey200: '#939393',
    grey300: '#F5F5F5',
    grey400: '#DCDADA',
  },
  color: {
    white: '#FFFFFF',
    black: '#000000',
    inherit: 'inherit',
    green: '#1EB649',
    grey: '#858585',
  },
  borderColor: {
    black: '#000',
    white: '#fff',
    green: '#78C37C',
    grey500: '#aaa',
  },
};

export default theme;
