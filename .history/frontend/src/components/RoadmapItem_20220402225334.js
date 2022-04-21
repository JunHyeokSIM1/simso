import axios from 'axios';

async function getUsers() {
  const response = await axios.get(
    'https://jsonplaceholder.typicode.com/users'
  );
  return response.data;
}

function RoadmapItem() {
  console.log(getUsers);

  return (
    <>
      <ul>
        {getUsers.map((user) => (
          <li key={user.id} style={{ cursor: 'pointer' }}>
            {user.username} ({user.name})
          </li>
        ))}
      </ul>
    </>
  );
}

export default RoadmapItem;
