import Login from "@page/Login";
import NotFound from "@page/NotFound";
import SignUp from "@page/SignUp";
import Index from "@page/Index";
import { Route, Routes } from "react-router-dom";
import TestPage from "@page/TestPage";
import TestPageTwo from "@page/TestPageTwo";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<Index />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/*" element={<NotFound />} />
      <Route path="/test" element={<TestPage />} />
      <Route path="/test2" element={<TestPageTwo />} />
    </Routes>
  );
};

export default Router;
