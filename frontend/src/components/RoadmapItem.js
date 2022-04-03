import React, { useEffect, useState } from 'react';
import axios from 'axios';
import RoadmapList from './RoadmapList';

const RoadmapItem = () => {
  const [users, setUser] = useState([]);

  useEffect(() => {
    axios.get('https://jsonplaceholder.typicode.com/users').then((response) => {
      setUser(response.data);
    });
  }, []);

  return (
    <>
      <RoadmapList users={users}></RoadmapList>
    </>
  );
};

export default RoadmapItem;
