import React from 'react';
import Form from "./components/Form";
import List from "./components/List"
import StoreProvider from "./components/StoreProvider";



function App() {
  return <div>
    <StoreProvider >
      <div className="header">
        <h3>To-Do List</h3>
        <Form />
      </div>
      
      <div className="tareas card border-info mb-3">
        <List />
      </div>
    </StoreProvider>
  </div>

}

export default App;