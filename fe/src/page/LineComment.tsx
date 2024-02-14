import styled from 'styled-components';
import { useEffect, useRef, useState } from 'react';
import { Popover } from './PopOver';

interface LineCommentProps {}

const LineComment: React.FC<LineCommentProps> = () => {
  const [target, setTarget] = useState<HTMLElement | null>(null);
  const [selectedIndices, setSelectedIndices] = useState<number[]>([]);
  const [temp, setTemp] = useState<string[]>([]);
  const [type, setType] = useState<NodeType>();
  const [id, setId] = useState<string | null>(null);
  const ref = useRef<HTMLDivElement>(null);

  useEffect(() => {
    setTarget(document.getElementById('wrapper'));
  }, []);

  const handleShareMeClick = () => {
    const { anchorNode, focusNode, anchorOffset, focusOffset } = window.getSelection() as Selection;
    const startNode = anchorNode.parentElement;
    const endNode = focusNode.parentElement;

    if (startNode && endNode) {
      const startIndex = Array.from(startNode.childNodes).indexOf(anchorNode);
      const endIndex = Array.from(endNode.childNodes).indexOf(focusNode);
      setSelectedIndices([startIndex + anchorOffset, endIndex + focusOffset]);

      const wrapperElement = ref.current;

      setType(
        startNode.nodeType === anchorNode.nodeType ? anchorNode.nodeType : startNode.nodeType,
      );
      setId(
        startNode.nodeName !== anchorNode.nodeName ? anchorNode?.parentNode?.id : anchorNode.id,
      );

      if (wrapperElement && id) {
        const textNodes = Array.from(wrapperElement.childNodes).filter((node) => node.id === id);
        setTemp(
          textNodes.map((node) => {
            const text = node.textContent || '';
            const start = anchorOffset;
            const end = focusOffset;
            return text.substring(start, end);
          }),
        );
      }
    }
  };

  useEffect(() => {
    console.log('ID updated:', id);
    const { anchorOffset, focusOffset } = window.getSelection() as Selection;
    if (id) {
      const wrapperElement = ref.current;
      setType(wrapperElement?.nodeType);
      if (wrapperElement) {
        const textNodes = Array.from(wrapperElement.childNodes).filter((node) => node.id === id);
        setTemp(
          textNodes.map((node) => {
            const text = node.textContent || '';
            const start = anchorOffset;
            const end = focusOffset;
            return text.substring(start, end);
          }),
        );
      }
    }
  }, [id]);

  const handleItemClicked = (id: string) => {
    const node = document.getElementById(id);
    if (node) {
      node.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <Container>
      <Wrapper>
        <div id="wrapper" ref={ref}>
          <h1 id="1">
            A printed letter is usually reserved for important professional communications, such as
            recommendation letters, cover letters, resignation letters, business and legal
            correspondence, and company communications.
          </h1>
          <span id="3">
            you'll want to know how to write one that is professional. Correct formatting is
            especially important if you're sending a hard copy to the recipient rather than an
            email, because the letter needs to fit the page, be clear and concise, be easy to read,
            and look professional. Review information on what you need to include when writing a
            professional letter, examples, and advice on the appropriate font, salutation, spacing,
            closing, and signature for business correspondence.
          </span>
          <h3 id="4">
            you'll want to know how to write one that is professional. Correct formatting is
            especially important if you're sending a hard copy to the recipient rather than an
            email, because the letter needs to fit the page, be clear and concise, be easy to read,
            and look professional. Review information on what you need to include when writing a
            professional letter, examples, and advice on the appropriate font, salutation, spacing,
            closing, and signature for business correspondence.
          </h3>
          <h3 id="5">
            you'll want to know how to write one that is professional. Correct formatting is
            especially important if you're sending a hard copy to the recipient rather than an
            email, because the letter needs to fit the page, be clear and concise, be easy to read,
            and look professional. Review information on what you need to include when writing a
            professional letter, examples, and advice on the appropriate font, salutation, spacing,
            closing, and signature for business correspondence.
          </h3>
          <h3 id="6">
            you'll want to know how to write one that is professional. Correct formatting is
            especially important if you're sending a hard copy to the recipient rather than an
            email, because the letter needs to fit the page, be clear and concise, be easy to read,
            and look professional. Review information on what you need to include when writing a
            professional letter, examples, and advice on the appropriate font, salutation, spacing,
            closing, and signature for business correspondence.
          </h3>
          <h3 id="7">
            you'll want to know how to write one that is professional. Correct formatting is
            especially important if you're sending a hard copy to the recipient rather than an
            email, because the letter needs to fit the page, be clear and concise, be easy to read,
            and look professional. Review information on what you need to include when writing a
            professional letter, examples, and advice on the appropriate font, salutation, spacing,
            closing, and signature for business correspondence.
          </h3>
          <h3 id="8">
            you'll want to know how to write one that is professional. Correct formatting is
            especially important if you're sending a hard copy to the recipient rather than an
            email, because the letter needs to fit the page, be clear and concise, be easy to read,
            and look professional. Review information on what you need to include when writing a
            professional letter, examples, and advice on the appropriate font, salutation, spacing,
            closing, and signature for business correspondence.
          </h3>

          <h3 id="9">
            you'll want to know how to write one that is professional. Correct formatting is
            especially important if you're sending a hard copy to the recipient rather than an
            email, because the letter needs to fit the page, be clear and concise, be easy to read,
            and look professional. Review information on what you need to include when writing a
            professional letter, examples, and advice on the appropriate font, salutation, spacing,
            closing, and signature for business correspondence.
          </h3>
        </div>
      </Wrapper>
      <Popover target={target} onClick={handleShareMeClick} />
      <SideBar>
        {temp.map((text, index) => (
          <h1
            key={index}
            onClick={() => {
              console.log('move');
              handleItemClicked(id!);
            }}
          >
            {text}
          </h1>
        ))}
      </SideBar>
    </Container>
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
  padding: 5rem;
  width: calc(70% - 10rem);
`;

const Container = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
`;

const SideBar = styled.div`
  width: calc(30% - 2rem);
  padding: 1rem;
  height: 100%;
`;
