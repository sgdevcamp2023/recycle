import { BlockNoteEditor } from '@blocknote/core';
import { BlockNoteView, useBlockNote } from '@blocknote/react';
import '@blocknote/react/style.css';

const BlockNote = () => {
  const editor: BlockNoteEditor = useBlockNote({});

  return (
    <>
      <BlockNoteView editor={editor} />
    </>
  );
};

export default BlockNote;
