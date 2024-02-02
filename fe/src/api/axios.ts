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
            url: '/members/token',
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
          // locate 사용해보기 (이것도 관점!) window.locate.을 이용해서 홈화면으로 에러시 보내버려라!
          // 토큰 발급된거 삭제하기 -> 로컬에 들어간거 따라서 저장해놨으니까, 빼놓는것도 추가를 해야 순수한 함수이고, 에러처리가 된다!
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
