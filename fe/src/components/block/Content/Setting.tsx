import { DefaultTabType } from '@store/useTabStore';
import DefaultTab from '../navbar/DefaultTab';
import styled from 'styled-components';

const Setting = () => {
  const items: Record<string, DefaultTabType> = {
    '작성한 질문 목록': 'myQuestion',
    '임시 저장된 질문': 'questionDrafts',
  };
  return (
    <BoxWrapper>
      <DefaultTab items={items} />
    </BoxWrapper>
  );
};

export default Setting;

const BoxWrapper = styled.div`
  width: 100%;
`;
