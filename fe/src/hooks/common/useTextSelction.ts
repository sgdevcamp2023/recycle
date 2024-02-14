import { useCallback, useLayoutEffect, useState } from 'react';

type ClientRect = Record<keyof Omit<DOMRect, 'toJSON'>, number>;

function roundValues(_rect: ClientRect) {
  const rect = {
    ..._rect,
  };
  for (const key of Object.keys(rect)) {
    // @ts-expect-error
    rect[key] = Math.round(rect[key]);
  }
  return rect;
}

function shallowDiff(prev: any, next: any) {
  if (prev != null && next != null) {
    for (const key of Object.keys(next)) {
      if (prev[key] != next[key]) {
        return true;
      }
    }
  } else if (prev != next) {
    return true;
  }
  return false;
}

type TextSelectionState = {
  clientRect?: ClientRect;
  isCollapsed?: boolean;
  textContent?: string;
};

const defaultState: TextSelectionState = {};

/**
 * useTextSelection(ref)
 *
 * @description
 * hook to get information about the current text selection
 *
 */
export function useTextSelection(target?: HTMLElement) {
  const [{ clientRect, isCollapsed, textContent }, setState] =
    useState<TextSelectionState>(defaultState);

  const reset = useCallback(() => {
    setState(defaultState);
  }, []);

  const handler = useCallback(() => {
    let newRect: ClientRect;
    const selection = window.getSelection();
    let newState: TextSelectionState = {};

    if (selection == null || !selection.rangeCount) {
      setState(newState);
      return;
    }

    const range = selection.getRangeAt(0);

    if (target != null && !target.contains(range.commonAncestorContainer)) {
      setState(newState);
      return;
    }

    if (range == null) {
      setState(newState);
      return;
    }

    const contents = range.cloneContents();

    if (contents.textContent != null) {
      newState.textContent = contents.textContent;
    }

    const rects = range.getClientRects();

    if (rects.length === 0 && range.commonAncestorContainer != null) {
      const el = range.commonAncestorContainer as HTMLElement;
      newRect = roundValues(el.getBoundingClientRect().toJSON());
    } else {
      if (rects.length < 1) return;
      newRect = roundValues(rects[0].toJSON());
    }
    if (shallowDiff(clientRect, newRect)) {
      newState.clientRect = newRect;
    }
    newState.isCollapsed = range.collapsed;

    setState(newState);
  }, [target]);

  useLayoutEffect(() => {
    document.addEventListener('selectionchange', handler);
    document.addEventListener('keydown', handler);
    document.addEventListener('keyup', handler);
    window.addEventListener('resize', handler);

    return () => {
      document.removeEventListener('selectionchange', handler);
      document.removeEventListener('keydown', handler);
      document.removeEventListener('keyup', handler);
      window.removeEventListener('resize', handler);
    };
  }, [target]);

  return {
    clientRect,
    isCollapsed,
    textContent,
  };
}

// import { useEffect, useRef, useState } from 'react';

// const useTextSelection = () => {
//   // we need a reference to the element wrapping the text in order to determine
//   // if the selection is the selection we are after
//   const ref = useRef();

//   // we store info about the current Range here
//   const [range, setRange] = useState(null);
//   const [anchorNode, setAnchorNode] = useState();
//   const [focusNode, setFocuseNode] = useState();
//   const [anchorOffset, setAnchorOffset] = useState();
//   const [focusOffset, setFocusOffset] = useState();

//   // In this effect we're registering for the documents "selectionchange" event
//   useEffect(() => {
//     function handleChange() {
//       // get selection information from the browser
//       const selection = window.getSelection();

//       // we only want to proceed when we have a valid selection
//       if (!selection || selection.isCollapsed || !selection.containsNode(ref.current, true)) {
//         setRange(null);
//         return;
//       }

//       setRange(selection.getRangeAt(0));
//       setAnchorNode(selection.anchorNode);
//       setFocuseNode(selection.focusNode);
//       setAnchorOffset(selection.anchorOffset);
//       setFocusOffset(selection.focusOffset);
//     }

//     document.addEventListener('selectionchange', handleChange);
//     return () => document.removeEventListener('selectionchange', handleChange);
//   }, []);

//   return { range, ref, anchorNode, focusNode, anchorOffset, focusOffset };
// };

// export default useTextSelection;
