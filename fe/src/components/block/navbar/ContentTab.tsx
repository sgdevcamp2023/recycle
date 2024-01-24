import GreyButton from '@components/atom/Button/GreyButton';
import Text from '@components/atom/Text';
import { useState } from 'react';
import styled from 'styled-components';

const ContentTab = () => {
  const [key, setKey] = useState('');
  return (
    <ContentTabWrapper>
      <Text color='grey' fontSize='base'>
        Edited Jan 03, 2024
      </Text>
      {/* <TabButton
        isActive={key == 'review'}
        onClick={() => {
          setKey('review');
        }}
      >
        review
      </TabButton> */}
      <GreyButton
        isActive={key == 'review'}
        onClick={() => {
          setKey('review');
        }}
        width={4}
        height={2}
        color={'black'}
        fontSize='base'
      >
        review
      </GreyButton>
      {/* <TabButton
        isActive={key == 'share'}
        onClick={() => {
          setKey('share');
        }}
      >
        share
      </TabButton> */}
      <GreyButton
        isActive={key == 'share'}
        onClick={() => {
          setKey('share');
        }}
        width={4}
        height={2}
        color={'black'}
        fontSize='base'
      >
        share
      </GreyButton>
      {/* <TabButton
        isActive={key == 'save'}
        onClick={() => {
          setKey('save');
        }}
      >
        save
      </TabButton> */}
      <GreyButton
        isActive={key == 'save'}
        onClick={() => {
          setKey('save');
        }}
        width={4}
        height={2}
        color={'black'}
        fontSize='base'
      >
        save
      </GreyButton>
    </ContentTabWrapper>
  );
};

export default ContentTab;

const ContentTabWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 4px;
  max-height: 2rem;
  padding: 0.5rem;
  margin-top: 0.5rem;
`;

// const TabButton = styled.div<{ isActive: boolean }>`
//   padding: 8px;
//   cursor: pointer;
//   color: black;
//   border-radius: 8px;
//   &:hover {
//     background-color: ${({ theme }) => theme.backgroundColor.grey400};
//   }
// `;
