import useModalStore from "@store/useModalStore";
import { ReactNode } from "react";
import styled from "styled-components";

interface BackgroundModalProps {
  children: ReactNode;
  onClick?: () => void;
}

const BackgroundModal = ({ children, onClick }: BackgroundModalProps) => {
  const { setShowModal } = useModalStore();

  const closeModal = () => {
    if (onClick) {
      onClick();
    } else {
      setShowModal(false);
    }
  };

  return <BackgroundWrapper onClick={closeModal}>{children}</BackgroundWrapper>;
};

const BackgroundWrapper = styled.div`
  display: flex;
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;
  background-color: rgba(0, 0, 0, 0.1);
`;

export default BackgroundModal;
