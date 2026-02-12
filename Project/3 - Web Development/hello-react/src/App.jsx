import { useState } from 'react'

import ProfileCard from "./components/ProfileCard.jsx";
import GoalsList from "./components/GoalsList.jsx";

function App() {
    const name = "Daniel";

  return (
      <div className="container m-5">
          <div className="card shadow p-4">
              <ProfileCard name={name} />
              <GoalsList />
          </div>
      </div>
  )
}

export default App
