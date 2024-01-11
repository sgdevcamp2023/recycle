import Login from "@page/Login";
import NotFound from "@page/NotFound";
import SignUp from "@page/SignUp";
import Index from "@page/Index";
import { Route, Routes } from "react-router-dom";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<Index />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/*" element={<NotFound />} />
    </Routes>
  );
};

export default Router;
