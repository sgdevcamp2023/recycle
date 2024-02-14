import styled, { css } from 'styled-components';
import { createPortal } from 'react-dom';
import {
  FunctionComponent,
  MouseEventHandler,
  useCallback,
  useEffect,
  useRef,
  useState,
} from 'react';
import { useTextSelection } from '@hooks/common/useTextSelction';

const Portal: FunctionComponent = ({ children }) => {
  return createPortal(children, document.body);
};

const Button = styled.button`
  position: absolute;
  left: ${(props) => props.left}px;
  top: ${(props) => props.top}px;
  margin-left: -50px;
  width: 100px;
  background: blue;
  border: none;
  text-align: center;
  color: white;
  border-radius: 3px;
  transition: top 0.3s ease; /* top 속성에 transition 효과 추가 */
`;

interface PopoverProps {
  target?: HTMLElement;
  onClick?: MouseEventHandler<HTMLButtonElement>;
}

export const Popover = ({ target, onClick }: PopoverProps) => {
  const textSelection = useTextSelection(target);
  const { isCollapsed, clientRect } = textSelection;

  // 조건문 밖에서 Hooks 사용
  useEffect(() => {
    if (clientRect == null || isCollapsed) return;

    const buttonTop = clientRect.top - 20;
    let buttonLeft = clientRect.left + clientRect.width / 2;

    // 버튼이 윈도우의 너비를 넘어가면 가운데 정렬되도록 조정
    if (buttonLeft < 0) {
      buttonLeft = 0;
    } else if (buttonLeft > window.innerWidth) {
      buttonLeft = window.innerWidth - 100; // 버튼 너비의 절반만큼 빼줌
    }

    // 버튼이 윈도우의 높이를 넘어가면 위로 올리도록 조정
    let adjustedButtonTop = buttonTop;
    if (clientRect.top + clientRect.height > window.innerHeight) {
      adjustedButtonTop = window.innerHeight + clientRect.height - 20;
    }

    // 버튼 위치 업데이트
    setButtonPosition({ left: buttonLeft, top: adjustedButtonTop + window.scrollY });
  }, [clientRect, isCollapsed]);

  // 버튼 위치를 저장할 상태
  const [buttonPosition, setButtonPosition] = useState({ left: 0, top: 0 });

  if (clientRect == null || isCollapsed) return null;

  return (
    <Portal>
      <Button left={buttonPosition.left} top={buttonPosition.top} onClick={onClick}>
        share me
      </Button>
    </Portal>
  );
};
