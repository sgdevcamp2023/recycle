import styled from 'styled-components';

const ToggleStyleButton = styled.label`
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  cursor: pointer;

  [type='checkbox'] {
    appearance: none;
    position: relative;
    border: max(2px, 0.1em) solid gray;
    border-radius: 1.25em;
    width: 2.25em;
    height: 1.25em;
  }

  [type='checkbox']::before {
    content: '';
    position: absolute;
    left: 0;
    width: 1em;
    height: 1em;
    border-radius: 50%;
    transform: scale(0.8);
    background-color: gray;
    transition: left 250ms linear;
  }

  [type='checkbox']:checked::before {
    background-color: white;
    left: 1em;
  }

  [type='checkbox']:checked {
    background-color: #1eb649;
    border-color: #1eb649;
  }

  [type='checkbox']:focus-visible {
    outline-offset: max(2px, 0.1em);
    outline: max(2px, 0.1em) solid #1eb649;
  }

  [type='checkbox']:enabled:hover {
    box-shadow: 0 0 0 max(4px, 0.2em) lightgray;
  }
`;

const ToggleButton = () => {
  return (
    <div>
      <ToggleStyleButton>
        <input role='switch' type='checkbox'></input>
      </ToggleStyleButton>
    </div>
  );
};

export default ToggleButton;
