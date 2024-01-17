import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: [
      { find: '@', replacement: path.resolve(__dirname, 'src') },
      { find: '@page', replacement: path.resolve(__dirname, 'src/page') },
      {
        find: '@components',
        replacement: path.resolve(__dirname, 'src/components'),
      },
      { find: '@api', replacement: path.resolve(__dirname, 'src/api') },
      { find: '@hook', replacement: path.resolve(__dirname, 'src/hook') },
      { find: '@styles', replacement: path.resolve(__dirname, 'src/styles') },
      {
        find: '@template',
        replacement: path.resolve(__dirname, 'src/template'),
      },
      { find: '@store', replacement: path.resolve(__dirname, 'src/store') },
    ],
  },
});
