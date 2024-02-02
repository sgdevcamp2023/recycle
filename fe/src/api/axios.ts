import axios, { AxiosInstance } from 'axios';
import { v4 as uuidv4 } from 'uuid';

const baseURL = import.meta.env.VITE_API_URL;

interface ServiceEndpoints {
  [key: string]: number;
}

const CreateServiceApi = (service: string): AxiosInstance => {
  const portMap: ServiceEndpoints = {
    review: 8082,
    member: 8081,
    notification: 8084,
    // 다른 서비스들의 포트 번호 추가
  };

  const servicePort = portMap[service];
  const serviceEndpoint = `${baseURL}/api/v1`;
  const api = axios.create({
    baseURL: serviceEndpoint,
    headers: {
      'X-ZZAUG-ID': uuidv4(),
      //받는 시점에서 생성하는 방법도 있지 않을까?
    },
  });

  // Interceptor를 사용하여 accessToken을 요청에 추가
  // const { authToken } = useAuthStore();
  // const accessToken = localStorage.getItem('accessToken');
  api.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      const {
        config,
        response: { status },
      } = error;

      const originalRequest = config;

      if (status === 403) {
        const accessToken = localStorage.getItem('access');
        const refreshToken = localStorage.getItem('refresh');

        try {
          const { data } = await axios({
            method: 'post',
            url: '/members/reissue',
            data: { accessToken, refreshToken },
          });
          const newAccessToken = data.data.accessToken;
          const newRefreshToken = data.data.refreshToken;
          originalRequest.headers = {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + newAccessToken,
          };
          localStorage.setItem('access', newAccessToken);
          localStorage.setItem('refresh', newRefreshToken);
          return await axios(originalRequest);
        } catch (error) {
          console.error(error);
        }
      }
    },
  );

  api.interceptors.request.use((config) => {
    const accessToken = localStorage.getItem('access');
    config.headers.Authorization = `Bearer ${accessToken}`;
    return config;
  });
  return api;
};

interface ClientAPI {
  [key: string]: AxiosInstance;
}

const clientApi: ClientAPI = {
  review: CreateServiceApi('review'),
  member: CreateServiceApi('member'),
  notification: CreateServiceApi('notification'),
};

export default clientApi;
