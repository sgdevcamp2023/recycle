import React, { useEffect, useState } from 'react';
import ReactMarkdown from 'react-markdown';
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter';
import { solarizedlight } from 'react-syntax-highlighter/dist/esm/styles/prism';

const MarkdownEditor = () => {
  const [markdown, setMarkdown] = useState('');
  const [reviewMarkdown, setReviewMarkdown] = useState('');
  const [selectedLine, setSelectedLine] = useState<number>();
  const [editorContent, setEditorContent] = useState('');
  const [reviewEditorContent, setReviewEditorContent] = useState('');
  const [originLines, setOriginLines] = useState<string[]>([]);
  const [changedLines, setChangedLines] = useState<number[]>([]);

  const handleLineClick = (lineNumber) => {
    console.log(`Clicked on line ${lineNumber}`);

    // 현재 Markdown 텍스트에서 코드 블록을 추출하고 각 라인을 배열로 저장
    const codeBlock = markdown.split('```')[1].trim();
    setOriginLines(codeBlock.split('\n'));

    // 선택된 라인 번호를 상태에 저장하여 나중에 사용할 수 있도록 함
    setSelectedLine(lineNumber);

    // 코드 라인을 클릭하면 code_review div를 보이도록 설정
    document.getElementById('code_review').style.display = 'block';
  };

  useEffect(() => {
    // selectedLine, originLines중 하나라도 변경될 때마다 실행
    if (selectedLine !== undefined) {
      // 현재 선택된 라인에 해당하는 내용을 originLines 배열에서 가져와 editorContent 상태를 업데이트
      setEditorContent(originLines[selectedLine]);
    }
  }, [selectedLine, originLines]);

  // 에디터를 닫을 때 선택라인을 초기화
  const handleEditorClose = () => {
    setSelectedLine(undefined);
    // 에디터를 닫을 때 code_review div를 숨기도록 설정
    document.getElementById('code_review').style.display = 'none';
  };

  // 에디터 콘텐츠를 현재 에디터의 내용으로 업데이트
  const handleEditorChange = (e) => {
    setEditorContent(e.target.value);
    // 리뷰 에디터 업데이트하기
    setReviewEditorContent(e.target.value);
  };

  const handleEditorSave = () => {
    // 기존 코드 블록의 시작과 끝 위치를 찾음
    const codeBlockStart = markdown.indexOf('```');
    const codeBlockEnd = markdown.lastIndexOf('```');
    // 코드 블록 앞과 뒤의 내용을 추출
    const start = markdown.substring(0, codeBlockStart + 3);
    const end = markdown.substring(codeBlockEnd);
    // 코드 블록을 줄별로 분리하여 배열로 만듦
    const lines = editorContent.split('\n');
    // 각 줄을 문자열로 가공
    const updatedCodeBlock = lines.map((line) => `${line}`).join('\n');
    // 기존 코드내용 복사하여, 수정된 코드 블록으로 업데이트
    const copiedLines = [...originLines];
    copiedLines[selectedLine!] = updatedCodeBlock;

    const reviewCodeBlock = '```' + `${copiedLines.join('\n')}\n` + '```';
    // 기존 Markdown의 코드 블록 부분을 수정된 내용으로 업데이트
    console.log(reviewCodeBlock);

    setReviewMarkdown(reviewCodeBlock);

    handleEditorClose();
  };

  return (
    <div>
      <textarea
        id="origin-editor"
        value={markdown}
        onChange={(e) => setMarkdown(e.target.value)}
        rows={10}
        style={{ width: '900px', height: '250px' }}
        placeholder="사용자가 질문 작성시 사용할 입력창입니다"
      />
      <textarea
        id="review-editor"
        value={reviewMarkdown}
        onChange={(e) => setReviewMarkdown(e.target.value)}
        rows={10}
        style={{ width: '900px', height: '250px', display: 'none' }}
        placeholder="나중에 사라질 녀석입니다"
      />
      <div id="origin_code" style={{ width: '900px' }}>
        <ReactMarkdown
          components={{
            code: ({ node, inline, className, children, ...props }) => {
              const match = /language-(\w+)/.exec(className || '');
              return !inline && match ? (
                <SyntaxHighlighter
                  style={solarizedlight}
                  language={match[1]}
                  PreTag="div"
                  children={children.replace(/\n$/, '')}
                  showLineNumbers
                  wrapLines
                  lineProps={(lineNumber) => ({
                    onClick: () => handleLineClick(lineNumber),
                    style: {
                      cursor: 'pointer',
                      border: selectedLine === lineNumber ? '1px solid #007bff' : 'none',
                      padding: '0.2em',
                      display: 'block',
                    },
                  })}
                />
              ) : (
                <code className={className} {...props}>
                  {markdown}
                </code>
              );
            },
          }}
        >
          {markdown}
        </ReactMarkdown>
      </div>

      <div id="review_code" style={{ width: '900px' }}>
        <ReactMarkdown
          components={{
            code: ({ node, inline, className, children, ...props }) => {
              const match = /language-(\w+)/.exec(className || '');
              if (!inline && match) {
                return (
                  <SyntaxHighlighter
                    style={solarizedlight}
                    language={match[1]}
                    PreTag="div"
                    children={String(children).replace(/\n$/, '')}
                    showLineNumbers
                    wrapLines
                    lineProps={(lineNumber) => ({})}
                  />
                );
              }
              return (
                <code className={className} {...props}>
                  {children}
                </code>
              );
            },
          }}
        >
          {reviewMarkdown}
        </ReactMarkdown>
      </div>
      {selectedLine !== null && (
        <div id="code_review" style={{ display: 'none' }}>
          <h4>Selected Line Editor</h4>

          <textarea
            value={editorContent}
            onChange={handleEditorChange}
            rows={3}
            style={{ width: '900px', height: '250px', display: 'block' }}
          />
          <button onClick={handleEditorClose} style={{ width: '70px', padding: '5px' }}>
            닫기
          </button>
          <button onClick={handleEditorSave} style={{ width: '70px', padding: '5px' }}>
            저장하기
          </button>
        </div>
      )}
    </div>
  );
};

export default MarkdownEditor;
