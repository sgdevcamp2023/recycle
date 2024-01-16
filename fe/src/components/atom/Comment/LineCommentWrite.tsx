import styled from 'styled-components';

const UploadButton = styled.button`
  width: 1.25rem;
  height: 1.25rem;
  background-color: #d9d9d9;
  color: white;
  border-radius: 1.25rem;
  cursor: pointer;
  transition: all 0.5s;
  font-size: 0.6rem;
  padding: 0.2rem;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  bottom: 0.25rem;
  right: 0.25rem;

  &:hover {
    background-color: #939393;
    border: 0.063rem solid #939393;
    color: white;
    font-weight: 600;
  }
`;

const LineCommentViewBox = styled.div`
  width: 17.25rem;
  min-height: 6rem;
  height: auto; /* 높이를 자동으로 조정 */
  padding: 0.25rem;
  background-color: white;
  color: black;
  border: 0.0625rem solid #aaaaaa;
  border-radius: 0.625rem;
  position: relative;
  box-shadow: 4px 4px 2px 2px rgba(0, 0, 0, 0.2);
  display: flex;
  align-items: center; /* 세로 중앙 정렬 */
  justify-content: center; /* 가로 중앙 정렬 */
`;

const CommentWriteContainer = styled.textarea`
  width: 90%;
  max-height: 15rem; /* 최대 높이 설정 */
  min-height: 3.5rem;
  overflow-y: auto; /* 내용이 넘칠 경우 세로 스크롤 표시 */
  border: none; /* 보더 제거 */
  outline: none; /* 포커스 효과 제거 */
  padding: 0.5rem;
  font-size: 0.625rem;
  color: #000; /* 입력 텍스트 색상 */
  position: absolute;
  top: 0.25rem;

  &::placeholder {
    color: #939393; /* 플레이스홀더 색상 */
  }
`;

const LineCommentWrite = () => {
  return (
    <div>
      <LineCommentViewBox>
        <CommentWriteContainer placeholder='댓글 추가' />
        <UploadButton>▶</UploadButton>
      </LineCommentViewBox>
    </div>
  );
};

export default LineCommentWrite;
