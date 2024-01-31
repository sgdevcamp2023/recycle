import { DefaultTabType } from '@store/useTabStore';
import DefaultTab from '../navbar/DefaultTab';
import styled from 'styled-components';
import useGetMemberInfo from '@hooks/query/member/useGetMemberInfo';
import { useState } from 'react';
import Text from '@components/atom/Text';

const Setting = () => {
  const items: Record<string, DefaultTabType> = {
    '내 정보': 'myMemberInfo',
  };

  const [id, setId] = useState<number>(1);

  const { data: memberInfo, isLoading } = useGetMemberInfo({ id });
  console.log(memberInfo);
  console.log(isLoading);
  return (
    <BoxWrapper>
      <DefaultTab items={items} />
      {!isLoading ? (
        <>
          <Text>{memberInfo?.data?.data.id}</Text>
          <Text>{memberInfo?.data?.data.email}</Text>
          <Text>{memberInfo?.data?.data.github}</Text>
        </>
      ) : (
        <>Loading 중입니다.</>
      )}
    </BoxWrapper>
  );
};

export default Setting;

const BoxWrapper = styled.div`
  width: 100%;
`;
