import Login from "@page/Login";
import NotFound from "@page/NotFound";
import SignUp from "@page/SignUp";
import Index from "@page/Index";
import { Route, Routes } from "react-router-dom";
import TestPage from "@page/TestPage";
import TestPageTwo from "@page/TestPageTwo";
import TestPageThree from "@page/TestPageThree";
import GridTemplate from "@components/layout/GridTemplate";
import BlackNoteTest from "@page/BlackNoteTest";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<Index />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/*" element={<NotFound />} />
      <Route path="/test" element={<TestPage />} />
      <Route path="/test2" element={<TestPageTwo />} />
      <Route path="/test3" element={<TestPageThree />} />
      <Route path="/grid" element={<GridTemplate />} />
      <Route path="/Black" element={<BlackNoteTest />} />
    </Routes>
  );
};

export default Router;
