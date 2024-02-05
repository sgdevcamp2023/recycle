import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import './index.css';
import { BrowserRouter } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';
import theme from '@styles/theme.ts';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { QueryCache, QueryClient, QueryClientProvider } from '@tanstack/react-query';
import toast from 'react-hot-toast';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      refetchOnMount: false,
      refetchOnReconnect: false,
    },
  },
  queryCache: new QueryCache({
    onError: (error, query) => {
      if (query.meta.errorMessage) toast(() => query.meta.errorMessage);
    },
  }),
});

ReactDOM.createRoot(document.getElementById('root')!).render(
  <QueryClientProvider client={queryClient}>
    <BrowserRouter>
      <ThemeProvider theme={theme}>
        <React.StrictMode>
          <App />
          <ReactQueryDevtools initialIsOpen={false} />
        </React.StrictMode>
      </ThemeProvider>
    </BrowserRouter>
  </QueryClientProvider>,
);
