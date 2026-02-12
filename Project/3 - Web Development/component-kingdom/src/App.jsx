import { useState } from 'react'
import Header from './components/Header'
import Footer from './components/Footer'
import MainContent from "./components/MainContent.jsx";
import SideBar from './components/SideBar.jsx';


function App() {

  return (
    <div>
        <Header />

        <div className="container-fluid">
            <div className="row">
                <SideBar />
                <MainContent />
            </div>
        </div>

        <Footer />
    </div>
  )
}

export default App
