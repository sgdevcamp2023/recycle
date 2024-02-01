// vite.config.ts
import { defineConfig } from 'file:///Users/kmlee/recycle/fe/node_modules/vite/dist/node/index.js';
import react from 'file:///Users/kmlee/recycle/fe/node_modules/@vitejs/plugin-react/dist/index.mjs';
import path from 'path';
import svgr from 'file:///Users/kmlee/recycle/fe/node_modules/vite-plugin-svgr/dist/index.js';
var __vite_injected_original_dirname = '/Users/kmlee/recycle/fe';
var vite_config_default = defineConfig({
  plugins: [react(), svgr()],
  resolve: {
    alias: [
      {
        find: '@',
        replacement: path.resolve(__vite_injected_original_dirname, 'src'),
      },
      {
        find: '@page',
        replacement: path.resolve(__vite_injected_original_dirname, 'src/page'),
      },
      {
        find: '@components',
        replacement: path.resolve(__vite_injected_original_dirname, 'src/components'),
      },
      {
        find: '@api',
        replacement: path.resolve(__vite_injected_original_dirname, 'src/api'),
      },
      {
        find: '@hook',
        replacement: path.resolve(__vite_injected_original_dirname, 'src/hook'),
      },
      {
        find: '@styles',
        replacement: path.resolve(__vite_injected_original_dirname, 'src/styles'),
      },
      {
        find: '@template',
        replacement: path.resolve(__vite_injected_original_dirname, 'src/template'),
      },
      {
        find: '@store',
        replacement: path.resolve(__vite_injected_original_dirname, 'src/store'),
      },
      {
        find: '@assets',
        replacement: path.resolve(__vite_injected_original_dirname, 'src/assets'),
      },
    ],
  },
});
export { vite_config_default as default };
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCIvVXNlcnMva21sZWUvcmVjeWNsZS9mZVwiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9maWxlbmFtZSA9IFwiL1VzZXJzL2ttbGVlL3JlY3ljbGUvZmUvdml0ZS5jb25maWcudHNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfaW1wb3J0X21ldGFfdXJsID0gXCJmaWxlOi8vL1VzZXJzL2ttbGVlL3JlY3ljbGUvZmUvdml0ZS5jb25maWcudHNcIjtpbXBvcnQgeyBkZWZpbmVDb25maWcgfSBmcm9tICd2aXRlJztcbmltcG9ydCByZWFjdCBmcm9tICdAdml0ZWpzL3BsdWdpbi1yZWFjdCc7XG5pbXBvcnQgcGF0aCBmcm9tICdwYXRoJztcbmltcG9ydCBzdmdyIGZyb20gJ3ZpdGUtcGx1Z2luLXN2Z3InO1xuXG5leHBvcnQgZGVmYXVsdCBkZWZpbmVDb25maWcoe1xuICBwbHVnaW5zOiBbcmVhY3QoKSwgc3ZncigpXSxcbiAgcmVzb2x2ZToge1xuICAgIGFsaWFzOiBbXG4gICAgICB7XG4gICAgICAgIGZpbmQ6ICdAJyxcbiAgICAgICAgcmVwbGFjZW1lbnQ6IHBhdGgucmVzb2x2ZShfX2Rpcm5hbWUsICdzcmMnKSxcbiAgICAgIH0sXG4gICAgICB7XG4gICAgICAgIGZpbmQ6ICdAcGFnZScsXG4gICAgICAgIHJlcGxhY2VtZW50OiBwYXRoLnJlc29sdmUoX19kaXJuYW1lLCAnc3JjL3BhZ2UnKSxcbiAgICAgIH0sXG4gICAgICB7XG4gICAgICAgIGZpbmQ6ICdAY29tcG9uZW50cycsXG4gICAgICAgIHJlcGxhY2VtZW50OiBwYXRoLnJlc29sdmUoX19kaXJuYW1lLCAnc3JjL2NvbXBvbmVudHMnKSxcbiAgICAgIH0sXG4gICAgICB7XG4gICAgICAgIGZpbmQ6ICdAYXBpJyxcbiAgICAgICAgcmVwbGFjZW1lbnQ6IHBhdGgucmVzb2x2ZShfX2Rpcm5hbWUsICdzcmMvYXBpJyksXG4gICAgICB9LFxuICAgICAge1xuICAgICAgICBmaW5kOiAnQGhvb2snLFxuICAgICAgICByZXBsYWNlbWVudDogcGF0aC5yZXNvbHZlKF9fZGlybmFtZSwgJ3NyYy9ob29rJyksXG4gICAgICB9LFxuICAgICAge1xuICAgICAgICBmaW5kOiAnQHN0eWxlcycsXG4gICAgICAgIHJlcGxhY2VtZW50OiBwYXRoLnJlc29sdmUoX19kaXJuYW1lLCAnc3JjL3N0eWxlcycpLFxuICAgICAgfSxcbiAgICAgIHtcbiAgICAgICAgZmluZDogJ0B0ZW1wbGF0ZScsXG4gICAgICAgIHJlcGxhY2VtZW50OiBwYXRoLnJlc29sdmUoX19kaXJuYW1lLCAnc3JjL3RlbXBsYXRlJyksXG4gICAgICB9LFxuICAgICAge1xuICAgICAgICBmaW5kOiAnQHN0b3JlJyxcbiAgICAgICAgcmVwbGFjZW1lbnQ6IHBhdGgucmVzb2x2ZShfX2Rpcm5hbWUsICdzcmMvc3RvcmUnKSxcbiAgICAgIH0sXG4gICAgICB7XG4gICAgICAgIGZpbmQ6ICdAYXNzZXRzJyxcbiAgICAgICAgcmVwbGFjZW1lbnQ6IHBhdGgucmVzb2x2ZShfX2Rpcm5hbWUsICdzcmMvYXNzZXRzJyksXG4gICAgICB9LFxuICAgIF0sXG4gIH0sXG59KTtcbiJdLAogICJtYXBwaW5ncyI6ICI7QUFBdVAsU0FBUyxvQkFBb0I7QUFDcFIsT0FBTyxXQUFXO0FBQ2xCLE9BQU8sVUFBVTtBQUNqQixPQUFPLFVBQVU7QUFIakIsSUFBTSxtQ0FBbUM7QUFLekMsSUFBTyxzQkFBUSxhQUFhO0FBQUEsRUFDMUIsU0FBUyxDQUFDLE1BQU0sR0FBRyxLQUFLLENBQUM7QUFBQSxFQUN6QixTQUFTO0FBQUEsSUFDUCxPQUFPO0FBQUEsTUFDTDtBQUFBLFFBQ0UsTUFBTTtBQUFBLFFBQ04sYUFBYSxLQUFLLFFBQVEsa0NBQVcsS0FBSztBQUFBLE1BQzVDO0FBQUEsTUFDQTtBQUFBLFFBQ0UsTUFBTTtBQUFBLFFBQ04sYUFBYSxLQUFLLFFBQVEsa0NBQVcsVUFBVTtBQUFBLE1BQ2pEO0FBQUEsTUFDQTtBQUFBLFFBQ0UsTUFBTTtBQUFBLFFBQ04sYUFBYSxLQUFLLFFBQVEsa0NBQVcsZ0JBQWdCO0FBQUEsTUFDdkQ7QUFBQSxNQUNBO0FBQUEsUUFDRSxNQUFNO0FBQUEsUUFDTixhQUFhLEtBQUssUUFBUSxrQ0FBVyxTQUFTO0FBQUEsTUFDaEQ7QUFBQSxNQUNBO0FBQUEsUUFDRSxNQUFNO0FBQUEsUUFDTixhQUFhLEtBQUssUUFBUSxrQ0FBVyxVQUFVO0FBQUEsTUFDakQ7QUFBQSxNQUNBO0FBQUEsUUFDRSxNQUFNO0FBQUEsUUFDTixhQUFhLEtBQUssUUFBUSxrQ0FBVyxZQUFZO0FBQUEsTUFDbkQ7QUFBQSxNQUNBO0FBQUEsUUFDRSxNQUFNO0FBQUEsUUFDTixhQUFhLEtBQUssUUFBUSxrQ0FBVyxjQUFjO0FBQUEsTUFDckQ7QUFBQSxNQUNBO0FBQUEsUUFDRSxNQUFNO0FBQUEsUUFDTixhQUFhLEtBQUssUUFBUSxrQ0FBVyxXQUFXO0FBQUEsTUFDbEQ7QUFBQSxNQUNBO0FBQUEsUUFDRSxNQUFNO0FBQUEsUUFDTixhQUFhLEtBQUssUUFBUSxrQ0FBVyxZQUFZO0FBQUEsTUFDbkQ7QUFBQSxJQUNGO0FBQUEsRUFDRjtBQUNGLENBQUM7IiwKICAibmFtZXMiOiBbXQp9Cg==
