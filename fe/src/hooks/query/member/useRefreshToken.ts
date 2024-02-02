// import memberApi from '@api/memberApi';
// import { useMutation } from '@tanstack/react-query';
// import axios from 'axios';

// const useRefreshToken = () => {
//   return useMutation({
//     mutationFn: memberApi.refreshToken,
//     onSuccess: async (data) => {
//       console.log('요청 성공');
//       console.log(data);

//       const tokenData = data?.data?.data;

//       const access = tokenData.accessToken;
//       const refresh = tokenData.refreshToken;
//       console.log(access, refresh);
//       if (access && refresh) {
//         localStorage.setItem('access', access);

//         const lastApiEndpoint = localStorage.getItem('lastApiEndpoint');

//         if (lastApiEndpoint !== null) {
//           const response = await axios.get(lastApiEndpoint, {
//             headers: {
//               Authorization: `Bearer ${access}`,
//             },
//           });

//           // 이후 response를 처리하거나 필요에 따라 상태 업데이트 등을 수행
//           console.log('API 다시 요청 결과:', response.data);
//         } else {
//           console.error('마지막 API 엔드포인트가 없습니다.');
//         }
//       }
//     },
//     onError: (err) => {
//       console.error('에러 발생');
//       console.log(err);
//     },
//     onSettled: (data) => {
//       console.log('결과에 관계 없이 무언가 실행됨');
//       console.log(data);
//     },
//   });
// };

// export default useRefreshToken;
