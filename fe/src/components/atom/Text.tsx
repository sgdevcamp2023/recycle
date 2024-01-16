import { ColorType, FontSizeType } from "@styles/theme";
import styled from "styled-components";

type FontWeightTpye = "normal" | "bold";

interface TextProps {
  // 있어야 되는 요소들: fontSize, fontWeight, Color, mb, mr, center
  fontSize?: FontSizeType;
  fontWeight?: FontWeightTpye;
  color?: ColorType;
  mb?: number;
  mr?: number;
  center?: boolean;
}

const Text = styled.p<Omit<TextProps, "text">>`
  color: ${({ theme, color }) => (color ? theme.color[color] : theme.color.inherit)};
  font-size: ${({ theme, fontSize }) => (fontSize ? theme.fontSize[fontSize] : theme.fontSize.base)};
  font-weight: ${({ fontWeight }) => (fontWeight === "bold" ? 500 : fontWeight)};
  margin-top: 0px;
  margin-left: 0px;
  margin-bottom: ${({ mb }) => mb}px;
  margin-right: ${({ mr }) => mr}px;
  text-align: ${({ center }) => (center ? "center" : "left")};
`;

Text.defaultProps = {
  fontSize: "base",
  fontWeight: "normal",
  color: "inherit",
  mb: 0,
  mr: 0,
  center: false,
};

export default Text;
