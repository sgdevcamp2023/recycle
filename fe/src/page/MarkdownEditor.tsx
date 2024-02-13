import React, { useState } from 'react';

const MarkdownEditor = () => {
  const [markdown, setMarkdown] = useState(`# 예시입니다
  \`\`\`javascript
  const hello = "안녕";
  console.log(hello);
  \`\`\`
  `);
  const [selectedLine, setSelectedLine] = useState(null);

  const handleLineClick = (lineNumber) => {
    console.log(`Clicked on line ${lineNumber}`);
    setSelectedLine(lineNumber);
  };

  const handleEditorClose = () => {
    setSelectedLine(null);
  };

  return (
    <div>
      <textarea
        value={markdown}
        onChange={(e) => setMarkdown(e.target.value)}
        rows={10}
        style={{ width: '100%' }}
      />
      {selectedLine !== null && (
        <div>
          <h4>Selected Line Editor</h4>
          <button onClick={handleEditorClose}>Close Editor</button>
          <textarea value={selectedLine} readOnly rows={3} style={{ width: '100%' }} />
        </div>
      )}
    </div>
  );
};

export default MarkdownEditor;
