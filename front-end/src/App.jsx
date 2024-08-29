import './App.css'
import LoginForm from './components/LoginForm'
import { BrowserRouter, Route } from 'react-router-dom'
import RegisterForm from './components/RegisterForm'

function App() {
  // <BrowserRouter>
  //       <Routes>
  //         <Route path='/register' element={<LoginForm/>}/>
  //         {/* <Route path='/register' element={<LoginForm/>}/>
  //         <Route path='/register' element={<LoginForm/>}/> */}
  //       </Routes>
  //     </BrowserRouter>
  return (
    <>
      <h1>The Banking Application</h1>
      <h2>Arsen, Joyceline, Quiara and George</h2>
      <RegisterForm />
    </>
  )
}

export default App
