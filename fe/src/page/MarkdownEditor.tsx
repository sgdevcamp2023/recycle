import React, { useState } from 'react';
import ReactMarkdown from 'react-markdown';
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter';
import { solarizedlight } from 'react-syntax-highlighter/dist/esm/styles/prism';

const MarkdownEditor = () => {
  const [markdown, setMarkdown] = useState(`# 예시입니다
  \`\`\`javascript
  const hello = "안녕";
  console.log(hello);
  \`\`\`
  `);
  const [selectedLine, setSelectedLine] = useState(null);
  const [editorContent, setEditorContent] = useState('');

  const handleLineClick = (lineNumber) => {
    console.log(`Clicked on line ${lineNumber}`);
    const codeBlock = markdown.split('```')[1].trim();
    const lines = codeBlock.split('\n');
    setSelectedLine(lineNumber);
    setEditorContent(lines[lineNumber]);
  };

  const handleEditorClose = () => {
    setSelectedLine(null);
  };

  const handleEditorChange = (e) => {
    setEditorContent(e.target.value);
  };

  const handleEditorSave = () => {
    const codeBlockStart = markdown.indexOf('```');
    const codeBlockEnd = markdown.lastIndexOf('```');
    const start = markdown.substring(0, codeBlockStart + 3);
    const end = markdown.substring(codeBlockEnd);

    const lines = editorContent.split('\n');
    const updatedCodeBlock = lines.map((line) => `  ${line}`).join('\n');
    console.log(lines, updatedCodeBlock);

    setMarkdown(`${start}\n${updatedCodeBlock}\n${end}`);
    handleEditorClose();
  };

  return (
    <div>
      <textarea
        value={markdown}
        onChange={(e) => setMarkdown(e.target.value)}
        rows={10}
        style={{ width: '100%' }}
      />
      <div>
        <ReactMarkdown
          components={{
            code: ({ node, inline, className, children, ...props }) => {
              const match = /language-(\w+)/.exec(className || '');
              return !inline && match ? (
                <SyntaxHighlighter
                  style={solarizedlight}
                  language={match[1]}
                  PreTag="div"
                  children={String(children).replace(/\n$/, '')}
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
                  {children}
                </code>
              );
            },
          }}
        >
          {markdown}
        </ReactMarkdown>
      </div>
      {selectedLine !== null && (
        <div>
          <h4>Selected Line Editor</h4>
          <button onClick={handleEditorClose}>Close Editor</button>
          <textarea
            value={editorContent}
            onChange={handleEditorChange}
            rows={3}
            style={{ width: '100%' }}
          />
          <button onClick={handleEditorSave}>Save Changes</button>
          <div>
            <ReactMarkdown>{editorContent}</ReactMarkdown>
          </div>
        </div>
      )}
    </div>
  );
};

export default MarkdownEditor;
