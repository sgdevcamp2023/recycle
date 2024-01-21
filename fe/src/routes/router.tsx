import Login from "@page/Login";
import NotFound from "@page/NotFound";
import SignUp from "@page/SignUp";
import Common from "@page/Common";
import { Route, Routes } from "react-router-dom";
import TestPage from "@page/TestPage";
import TestPageTwo from "@page/TestPageTwo";
import TestPageThree from "@page/TestPageThree";
import GridTemplate from "@components/layout/GridTemplate";

const Router = () => {
  return (
    <Routes>
      {/* 메인 페이지 */}
      <Route element={<GridTemplate />}>
        <Route path="/" element={<Common />} />
      </Route>
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/*" element={<NotFound />} />

      {/* 테스트 페이지 */}
      <Route path="/test" element={<TestPage />} />
      <Route path="/test2" element={<TestPageTwo />} />
      <Route path="/test3" element={<TestPageThree />} />
      <Route path="/grid" element={<GridTemplate />} />
    </Routes>
  );
};

export default Router;
