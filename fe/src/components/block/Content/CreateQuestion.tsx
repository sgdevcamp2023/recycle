import styled from 'styled-components';
import DefaultInput from '../Search/DefaultInput';
import { useEffect, useRef, useState } from 'react';
import MarkdownEditor from '@uiw/react-md-editor';
import MDEditor from '@uiw/react-md-editor';
import { Popover } from '@page/PopOver';

const CreateQuestion = () => {
  const [target, setTarget] = useState<HTMLElement | null>(null);
  const [selectedIndices, setSelectedIndices] = useState<number[]>([]);
  const [temp, setTemp] = useState<string[]>([]);
  const [type, setType] = useState<NodeType>();
  const [id, setId] = useState<string | null>(null);
  const testRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    setTarget(document.getElementById('wrapper'));
  }, []);

  const handleShareMeClick = () => {
    console.log('hello');
    const { anchorNode, focusNode, anchorOffset, focusOffset } = window.getSelection() as Selection;
    const startNode = anchorNode.parentElement;
    const endNode = focusNode.parentElement;

    if (startNode && endNode) {
      const startIndex = Array.from(startNode.childNodes).indexOf(anchorNode);
      const endIndex = Array.from(endNode.childNodes).indexOf(focusNode);
      setSelectedIndices([startIndex + anchorOffset, endIndex + focusOffset]);

      const wrapperElement = testRef.current;

      setType(
        startNode.nodeType === anchorNode.nodeType ? anchorNode.nodeType : startNode.nodeType,
      );
      setId(
        startNode.nodeName !== anchorNode.nodeName
          ? anchorNode?.parentNode?.nodeName
          : anchorNode.nodeName,
      );

      if (wrapperElement && id) {
        const textNodes = Array.from(wrapperElement.childNodes).filter(
          (node) => node.nodeName === id,
        );
        setTemp(
          textNodes.map((node) => {
            const text = node.textContent || '';
            const start = anchorOffset;
            const end = focusOffset;
            console.log(start, end);
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
      const wrapperElement = testRef.current;
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
  const ref = useRef<HTMLInputElement>(null);
  const str = `
### Preview Markdown

[![Open in CodeSandbox](https://img.shields.io/badge/Open%20in-CodeSandbox-blue?logo=codesandbox)](https://codesandbox.io/embed/react-md-editor-preview-markdown-vrucl?fontsize=14&hidenavigation=1&theme=dark)

import React from "react";
import ReactDOM from "react-dom";
import MDEditor from '@uiw/react-md-editor';

export default function App() {
  return (
    <div className="container">
      <MDEditor.Markdown source="Hello Markdown!" />
    </div>
  );
}
`;
  const [markdown, setMarkdown] = useState(str);

  const handleMarkdownChange = (value: string | undefined) => {
    if (value) {
      setMarkdown(value);
      console.log(value);
    }
  };

  useEffect(() => {}, [ref?.current?.value]);

  return (
    <>
      <TitleWrapper ref={testRef} id="wrapper">
        <DefaultInput
          type="text"
          height={40}
          ref={ref}
          fontSize="xl"
          $backgroundColor="white"
          placeholder="제목을 입력해주세요"
          padding="0 0.5rem 0.5rem 0.5rem"
        />
        {/* <MarkdownEditor height={'90%'} value={markdown} onChange={handleMarkdownChange} /> */}
        <MDEditor.Markdown source={markdown} />
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
        <Popover target={target} onClick={handleShareMeClick} />
      </TitleWrapper>
    </>
  );
};

export default CreateQuestion;

const TitleWrapper = styled.div`
  width: calc(100% - 2.5rem);
  padding-left: 3rem;
`;
