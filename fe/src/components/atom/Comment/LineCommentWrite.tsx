import { MouseEventHandler } from 'react';
import styled from 'styled-components';

const UploadButton = styled.button`
  width: 20px;
  height: 20px;
  background-color: #d9d9d9;
  color: white;
  border-radius: 20px;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 9.6px;
  padding: 3.2px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  bottom: 4px;
  right: 4px;

  &:hover {
    background-color: #939393;
    border: 1.008px solid #939393;
    color: white;
    font-weight: 600;
  }
`;

const LineCommentViewBox = styled.div`
  width: 276px;
  min-height: 96px;
  height: auto; /* 높이를 자동으로 조정 */
  padding: 4px;
  background-color: white;
  color: black;
  border: 1px solid #aaaaaa;
  border-radius: 10px;
  position: relative;
  box-shadow: 0.25rem 0.25rem 0.125rem 0.125rem rgba(0, 0, 0, 0.2);
  display: flex;
  align-items: center; /* 세로 중앙 정렬 */
  justify-content: center; /* 가로 중앙 정렬 */
`;

const CommentWriteContainer = styled.textarea`
  width: 90%;
  max-height: 240px; /* 최대 높이 설정 */
  min-height: 56px;
  overflow-y: auto; /* 내용이 넘칠 경우 세로 스크롤 표시 */
  border: none; /* 보더 제거 */
  outline: none; /* 포커스 효과 제거 */
  padding: 8px;
  font-size: 10px;
  color: #000; /* 입력 텍스트 색상 */
  position: absolute;
  top: 4px;

  &::placeholder {
    color: #939393; /* 플레이스홀더 색상 */
  }
`;

interface LineCommentWriteProps {
  uploadOnClick?: MouseEventHandler<HTMLButtonElement>;
  cancelOnClick?: MouseEventHandler<HTMLButtonElement>;
}

const LineCommentWrite = ({ cancelOnClick, uploadOnClick }: LineCommentWriteProps) => {
  return (
    <div>
      <LineCommentViewBox>
        <CommentWriteContainer placeholder="댓글 추가" />
        <UploadButton>▶</UploadButton>
      </LineCommentViewBox>
      <br />
      <button onClick={cancelOnClick}>취소</button>
    </div>
  );
};

export default LineCommentWrite;
