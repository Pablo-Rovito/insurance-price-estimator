import React from 'react';
import './App.css';
import QuoteForm from './components/QuoteForm.tsx';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>insurance price calc</p>
      </header>
      <div className="App-form">
        <QuoteForm />
      </div>
    </div>
  );
}

export default App;
