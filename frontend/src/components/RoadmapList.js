import React from 'react';

const RoadmapList = ({ users }) => {
  return (
    <div>
      {users.map((user) => {
        return <div key={user.id}>{user.name}</div>;
      })}
    </div>
  );
};

export default RoadmapList;
