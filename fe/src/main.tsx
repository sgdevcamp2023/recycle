import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import './index.css';
import { BrowserRouter } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';
import theme from '@styles/theme.ts';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { HelmetProvider } from 'react-helmet-async';

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <QueryClientProvider client={queryClient}>
    <BrowserRouter>
      <ThemeProvider theme={theme}>
        <HelmetProvider>
          <React.StrictMode>
            <App />
            <ReactQueryDevtools initialIsOpen={false} />
          </React.StrictMode>
        </HelmetProvider>
      </ThemeProvider>
    </BrowserRouter>
  </QueryClientProvider>,
);
