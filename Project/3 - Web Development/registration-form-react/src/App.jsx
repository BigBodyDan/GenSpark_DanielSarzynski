import { useState } from 'react'

import UserForm from "./components/UserForm.jsx";
import DisplayData from './components/DisplayData'

function App() {
  const [formData, setFormData] = useState(null);

  const handleFormSubmit = (data) => {
      console.log("Form submitted:", data);
      setFormData(data);
  }

  return (
    <div className="container mt-5">
        <h2 className="text-center mb-4">Registration Form</h2>

        <UserForm onSubmit={handleFormSubmit} />

        {formData && <DisplayData data={formData} />}
    </div>
  )
}

export default App
