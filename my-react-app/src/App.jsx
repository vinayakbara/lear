import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
      <HeaderComponent>
        <Routes>
          <Route path='/' element = { <Home />}>
          </Route></Routes></HeaderComponent></BrowserRouter>
    </>
  )
}

export default App
