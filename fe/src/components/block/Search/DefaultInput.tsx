import { forwardRef, RefObject } from "react";
import styled from "styled-components";
import { BackgroundColorType, ColorType, FontSizeType } from "@styles/theme";

interface DefaultInputProps {
  type: string;
  initValue?: string;
  maxLength?: number;
  width?: number | string;
  height?: number | string;
  placeholder?: string;
  color?: ColorType;
  placeholderColor?: ColorType;
  fontSize?: FontSizeType;
  backgroundColor?: BackgroundColorType;
  disabled?: boolean;
}

const DefaultInput = forwardRef<HTMLInputElement, DefaultInputProps>(
  (
    {
      type = "text",
      initValue = "",
      maxLength = 524288,
      width = "100%",
      height = "100%",
      placeholder = "",
      fontSize = "base",
      color = "black",
      backgroundColor = "grey300",
      disabled,
    },
    ref
  ) => {
    if (initValue && ref) {
      (ref as RefObject<HTMLInputElement>)!.current!.value = initValue;
    }

    return (
      <DefaultInputContainer
        ref={ref}
        type={type}
        maxLength={maxLength}
        width={width}
        height={height}
        placeholder={placeholder}
        fontSize={fontSize}
        color={color}
        backgroundColor={backgroundColor}
        disabled={disabled}
      />
    );
  }
);

const DefaultInputContainer = styled.input<Pick<DefaultInputProps, "width" | "height" | "fontSize" | "color" | "backgroundColor">>`
  width: ${({ width }) => (width === "100%" ? "calc(100% - 0.5rem)" : `${width}px`)};
  height: ${({ height }) => (height === "100%" ? "100%" : `${height}px`)};
  padding: 0.5rem 0rem 0.5rem 0.5rem;
  border: none;
  border-radius: 4px;
  color: ${({ theme, color }) => (color ? theme.color[color] : theme.color.black)};
  font-weight: 500;
  font-size: ${({ theme, fontSize }) => (fontSize ? theme.fontSize[fontSize] : theme.fontSize.base)};
  background-color: ${({ theme, backgroundColor }) => (backgroundColor ? theme.backgroundColor[backgroundColor] : theme.backgroundcolor.inherit)};

  ::placeholder {
    color: ${({ theme }) => theme.color.grey};
  }

  &:focus {
    outline: none;
  }
  &:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }
`;

export default DefaultInput;
