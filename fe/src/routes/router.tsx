import Login from '@page/Login';
import NotFound from '@page/NotFound';
import SignUp from '@page/SignUp';
import Common from '@page/Common';
import { Route, Routes } from 'react-router-dom';
import GridTemplate from '@components/layout/GridTemplate';
import Main from '@page/Main';
import RegisterEmail from '@page/RegisterEmail';
import AccountLinking from '@page/AccountLinking';
import LineComment from '@page/LineComment';
import TestPageThree from '@page/TestPageThree';
import CreateReview from '@components/block/Content/CreateReview';

const Router = () => {
  return (
    <Routes>
      {/* 메인 페이지 */}
      {[
        '/',
        '/Question',
        '/Question/:questionId',
        '/Review',
        '/Review/:reviewId',
        '/Setting',
        '/Request',
        '/CreateQuestion',
        '/CreateReview',
        '/readQuestion',
        '/*',
      ].map((path) => (
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
      <Route path="/linecomment" element={<LineComment />} />
      <Route path="/test" element={<TestPageThree />} />
      <Route path="/test2" element={<CreateReview />} />
    </Routes>
  );
};

export default Router;
