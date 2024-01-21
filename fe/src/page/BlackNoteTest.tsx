import { BlockNoteEditor } from "@blocknote/core";
import { BlockNoteView, useBlockNote } from "@blocknote/react";
import "@blocknote/react/style.css";

const BlackNoteTest = () => {
  const editor: BlockNoteEditor = useBlockNote({});

  return (
    <>
      <BlockNoteView editor={editor} />
    </>
  );
};

export default BlackNoteTest;
