import Login from '@page/Login';
import NotFound from '@page/NotFound';
import SignUp from '@page/SignUp';
import Common from '@page/Common';
import { Route, Routes } from 'react-router-dom';
import TestPage from '@page/TestPage';
import TestPageTwo from '@page/TestPageTwo';
import TestPageThree from '@page/TestPageThree';
import GridTemplate from '@components/layout/GridTemplate';
import BlackNoteTest from '@page/BlackNoteTest';
import Main from '@page/Main';

const Router = () => {
  return (
    <Routes>
      {/* 메인 페이지 */}
      {['/', '/newQuestion', '/newReview', '/*'].map((path) => (
        <Route key={path} element={<GridTemplate />}>
          {path === '/*' ? (
            <Route path="/*" element={<NotFound />} />
          ) : (
            <Route key={path} path={path} element={<Common />} />
          )}
        </Route>
      ))}

      <Route path="/main" element={<Main />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />

      {/* 테스트 페이지 */}
      <Route path="/test" element={<TestPage />} />
      <Route path="/test2" element={<TestPageTwo />} />
      <Route path="/test3" element={<TestPageThree />} />
      <Route path="/grid" element={<GridTemplate />} />
      <Route path="/Black" element={<BlackNoteTest />} />
    </Routes>
  );
};

export default Router;
