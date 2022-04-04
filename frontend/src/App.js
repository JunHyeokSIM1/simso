import React from 'react';
import { createGlobalStyle } from 'styled-components';
import RoadmapTemplate from './components/RoadmapTemplate';
import RoadmapHead from './components/RoadmapHead';
import RoadmapItem from './components/RoadmapItem';
import RoadmapItemRegister from './components/RoadmaRegister';

const GlobalStyle = createGlobalStyle`
  body {
    background: #e9ecef;
  }
`;

function App() {
  return (
    <>
      <GlobalStyle></GlobalStyle>
      <RoadmapTemplate>
        <RoadmapHead></RoadmapHead>
        <RoadmapItem></RoadmapItem>
        <RoadmapItemRegister></RoadmapItemRegister>
      </RoadmapTemplate>
    </>
  );
}

export default App;
