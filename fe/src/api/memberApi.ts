import axios from 'axios';
import clientApi from './axios';
import { v4 as uuidv4 } from 'uuid';

const baseURL = import.meta.env.VITE_API_URL;
const headers = {
  'X-ZZAUG-ID': 'X-ZZAUG-ID',
  Authorization: 'Bearer accessToken',
  // Referer: 'referer',
};
export interface userProps {
  certification?: string;
  password?: string;
}

const memberApi = {
  //!아래 API까지는 accessToken - null인 상태로 날아가게 됩니다!
  //회원가입 [post]
  signUp: async ({ certification, password }: userProps) => {
    return await axios.post(
      `${baseURL}/api/v1/members/`,
      { certification, password },
      {
        headers: headers,
      },
    );
  },
  //로그인 [post]
  login: async ({ certification, password }: userProps) => {
    return await axios.post(
      `${baseURL}/api/v1/members/login`,
      { certification, password },
      {
        headers: headers,
      },
    );
  },
  // 아이디 중복검사 [get]
  checkIdDuplicate: async ({ certification }: userProps) => {
    return await axios.get(`${baseURL}/api/v1/members/check?${certification}`, {
      headers: headers,
    });
  },

  //!여기 아래 부터는 로그인 하고 난 후에 사용하게 되는 API
  // 증명(아이디)를 조회합니다. [get]
  checkId: async ({ certification }: userProps) => {
    return await clientApi.member.get(`members?${certification}`);
  },
  // 멤버 정보 수정 [put]
  updateMemberInfo: async ({ certification }: userProps) => {
    return await clientApi.member.put(`/members?${certification}`);
  },
  //로그아웃 [post]
  logout: async () => {
    return await clientApi.member.post('/members/logout');
  },
  //토큰 갱신 [post]
  refreshToken: async () => {
    return await clientApi.member.post('/members/token');
  },
  // 멤버 정보 조회하기 [get]
  //! 파라미터 Interface 만들어주기
  getMemberInfo: async ({ id }: any) => {
    return await clientApi.member.get(`/members/token?${id}`);
  },
  // 이메일 요청 하기[get]
  //! 파라미터 Interface 만들어주기
  requestEmailApprove: async ({ email, nonce }: any) => {
    return await clientApi.member.get(`/members/check/email?${email}?${nonce}`);
  },
  // 이메일 인증 [get]
  //! 파라미터 Interface 만들어주기
  checkEmailApprove: async ({ email, nonce, code }: any) => {
    return await clientApi.member.post('/members/check/email', {
      email,
      nonce,
      code,
    });
  },
};

export default memberApi;
