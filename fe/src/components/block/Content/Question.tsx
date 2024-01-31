import styled from 'styled-components';
import DefaultTab from '../navbar/DefaultTab';
import useTabStore, { DefaultTabType } from '@store/useTabStore';
import { useEffect, useState } from 'react';
import SearchInput from '../Search/SearchInput';
import DefaultCard, { DefaultCardProps, DefaultCardType } from '@components/atom/card/DefaultCard';
import useGetQuestions from '@hooks/query/question/useGetQuestions';
import useGetQuestionDrafts from '@hooks/query/question/useGetQuestionDrafts';

const Question = () => {
  const items: Record<string, DefaultTabType> = {
    '내가 작성한 질문': 'myQuestion',
    '임시 저장된 질문': 'questionDrafts',
  };
  const { defaultTabType, setDefaultTabType } = useTabStore();
  useEffect(() => {
    setDefaultTabType('myQuestion');
  }, [setDefaultTabType]);

  const [questionsArray, setQuestionsArray] = useState<DefaultCardProps[]>([]);
  const [questionDraftsArray, setQuestionDraftsArray] = useState<DefaultCardProps[]>([]);
  const tId = null;

  const { data: questionData, isLoading: isQuestionsLoading } = useGetQuestions();
  const { data: questionDraftsData, isLoading: isQuestionDraftsLoading } = useGetQuestionDrafts({
    tId,
  });

  useEffect(() => {
    setQuestionsArray(questionData?.data.data);
    console.log(isQuestionsLoading);
  }, [isQuestionsLoading]);

  useEffect(() => {
    setQuestionDraftsArray(questionDraftsData?.data.data);
    console.log(isQuestionDraftsLoading);
  }, [isQuestionDraftsLoading]);

  interface handleCardClickProps {
    type: DefaultCardType;
  }
  //  "question" | "review" | "add"
  const handleCardClick = ({ type }: handleCardClickProps) => {
    if (type == 'add') {
      alert('새로운 질문 추가하기');
    }
  };

  function parseTitleFromContent(content: string | undefined): string {
    const match = content ? content.match(/<h1>(.*?)<\/h1>/i) : '';
    return match ? match[1] : '';
  }

  function removeH1Tag(content: string): string {
    return content.replace(/<h1>.*?<\/h1>/i, ''); // Remove the <h1> tag and its content
  }

  return (
    <BoxWrapper>
      <DefaultTab items={items} />
      <SearchInput />
      <QuestionWrapper>
        {defaultTabType == 'myQuestion' && (
          <DefaultCard
            type="add"
            onClick={() =>
              handleCardClick({
                type: 'add',
              })
            }
          />
        )}
        {(isQuestionDraftsLoading || isQuestionsLoading) && (
          <DefaultCard type="question" content="질문 목록을 불러오고 있습니다!" />
        )}
        {defaultTabType === 'myQuestion' &&
          questionsArray &&
          questionsArray.map((item, idx) => {
            return (
              <DefaultCard
                type="question"
                key={idx}
                commentCount={item.review_cnt}
                title={parseTitleFromContent(item.content)}
                content={removeH1Tag(item.content ? item.content : '')}
                onClick={() =>
                  handleCardClick({
                    type: item.type,
                  })
                }
              />
            );
          })}
        {defaultTabType === 'questionDrafts' &&
          questionDraftsArray &&
          questionDraftsArray.map((item, idx) => {
            return (
              <DefaultCard
                type="question"
                key={idx}
                commentCount={item.review_cnt}
                title={parseTitleFromContent(item.content)}
                content={removeH1Tag(item.content ? item.content : '')}
                onClick={() =>
                  handleCardClick({
                    type: item.type,
                  })
                }
              />
            );
          })}
      </QuestionWrapper>
    </BoxWrapper>
  );
};

export default Question;

const BoxWrapper = styled.div`
  width: 100%;
`;

const QuestionWrapper = styled.div`
  width: 100%;
  margin-top: 1.5rem;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 0.5rem;
`;
