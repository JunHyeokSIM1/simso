import React from 'react';
import Users from './User';
import { createGlobalStyle } from 'styled-components';
import RoadmapTemplate from './components/RoadmapTemplate';
import RoadmapHead from './components/RoadmapHead';

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
        <Users />
      </RoadmapTemplate>
    </>
  );
}

export default App;
