import styled, { css } from 'styled-components';
import { createPortal } from 'react-dom';
import { FunctionComponent, useCallback, useEffect, useRef, useState } from 'react';
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
`;

export const Popover = ({ target }: { target?: HTMLElement }) => {
  const [selectedIndices, setSelectedIndices] = useState<number[]>([]);

  const textSelection = useTextSelection(target);
  const { isCollapsed, clientRect } = textSelection;

  if (clientRect == null || isCollapsed) return null;

  const buttonLeft = clientRect.left + clientRect.width / 2;
  const buttonTop = clientRect.top - 20;

  const handleShareMeClick = () => {
    console.log('handleShareMeClick');
    const { anchorNode, focusNode, anchorOffset, focusOffset } = window.getSelection() as Selection;
    const startNode = anchorNode.parentElement;
    const endNode = focusNode.parentElement;

    if (startNode && endNode) {
      const startIndex = Array.from(startNode.childNodes).indexOf(anchorNode);
      const endIndex = Array.from(endNode.childNodes).indexOf(focusNode);

      setSelectedIndices([startIndex + anchorOffset, endIndex + focusOffset]);

      const wrapperElement = document.getElementById('wrapper');
      console.log(wrapperElement);
      if (wrapperElement) {
        console.log(wrapperElement.childNodes);
        const textNodes = Array.from(wrapperElement.childNodes).filter(
          (node) => node.nodeType === Node.TEXT_NODE,
        );
        console.log(selectedIndices);
        console.log(textNodes);
        textNodes.forEach((node, index) => {
          if (index >= selectedIndices[0] && index <= selectedIndices[1]) {
            const spanElement = document.createElement('span');
            spanElement.innerHTML = node.textContent;
            node.replaceWith(spanElement);
            console.log('node', node);
          }
        });
      }
    }
  };

  return (
    <Portal>
      <Button left={buttonLeft} top={buttonTop} onClick={handleShareMeClick}>
        share me
      </Button>
    </Portal>
  );
};

const LineComment = () => {
  const [target, setTarget] = useState<HTMLElement>();
  const ref = useRef(null);

  return (
    <Wrapper>
      <div id="wrapper" ref={ref}>
        <h1>
          A printed letter is usually reserved for important professional communications, such as
          recommendation letters, cover letters, resignation letters, business and legal
          correspondence, and company communications.
        </h1>
        Since a letter is a formal mode of communication, you'll want to know how to write one that
        is professional. Correct formatting is especially important if you're sending a hard copy to
        the recipient rather than an email, because the letter needs to fit the page, be clear and
        concise, be easy to read, and look professional. Review information on what you need to
        include when writing a professional letter, examples, and advice on the appropriate font,
        salutation, spacing, closing, and signature for business correspondence.
      </div>
      <Popover target={target} />
    </Wrapper>
  );
};

export default LineComment;

const HighLight = styled.span`
  background: rgba(255, 212, 0, 0.14);
  border-bottom: 2px solid rgb(255, 212, 0);
  padding-bottom: 2px;
`;

const Wrapper = styled.div`
  position: relative;
  padding: 10rem;
`;
