import Login from '@page/Login';
import NotFound from '@page/NotFound';
import SignUp from '@page/SignUp';
import Common from '@page/Common';
import { Route, Routes } from 'react-router-dom';
import TestPage from '@page/TestPage';
import TestPageTwo from '@page/TestPageTwo';
import TestPageThree from '@page/TestPageThree';
import GridTemplate from '@components/layout/GridTemplate';
import Main from '@page/Main';
import RegisterEmail from '@page/RegisterEmail';
import AccountLinking from '@page/AccountLinking';
import APITestPage from '@page/APITestPage';

const Router = () => {
  return (
    <Routes>
      {/* 메인 페이지 */}
      {['/', '/newQuestion', '/newReview', '/*'].map((path) => (
        <Route key={path} element={<GridTemplate />}>
          {path === '/*' ? (
            <Route path="/error" element={<NotFound />} />
          ) : (
            <Route key={path} path={path} element={<Common />} />
          )}
        </Route>
      ))}
      {/* 로그인 하기 전 */}
      <Route path="/main" element={<Main />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      {/* 나중에 Common 안에 넣어주기 */}
      <Route path="/registeremail" element={<RegisterEmail />} />
      <Route path="/accountlinking" element={<AccountLinking />} />

      {/* 테스트 페이지 */}
      <Route path="/test" element={<TestPage />} />
      <Route path="/test2" element={<TestPageTwo />} />
      <Route path="/test3" element={<TestPageThree />} />
      <Route path="/grid" element={<GridTemplate />} />
      <Route path="/Api" element={<APITestPage />} />
    </Routes>
  );
};

export default Router;
