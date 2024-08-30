import './App.css'
import LoginForm from './components/LoginForm'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { useState, createContext } from 'react'
import RegisterForm from './components/RegisterForm'
import BankDashBoard from './components/BankDashBoard'

export const UserContext = createContext();

function App() {
  const [user, setUser] = useState(null);

  return (
    <>
      <UserContext.Provider value={{ user, setUser }}>
      <BrowserRouter>
        <h1>The Banking Application</h1>
        <h2>Arsen, Joyceline, Quiara, and George</h2>
        <Routes>
          <Route path="/" element={<RegisterForm />} />
          <Route path="/login" element={<LoginForm />} />
          <Route path="/dashboard" element={<BankDashBoard />} />
        </Routes>
      </BrowserRouter>
    </UserContext.Provider>
    </>
  )
}

export default App
