import { useRef, useEffect } from "react";
import logo from "./logo.svg";
import "./App.css";
import { StompSessionProvider } from "react-stomp-hooks";
import SocketConsumer from "./SocketConsumer";

function App() {
  return (
    <div className="App">
      <StompSessionProvider
        url={"http://localhost:8080/ws"}
        //All options supported by @stomp/stompjs can be used here
      >
        <SocketConsumer />
      </StompSessionProvider>
    </div>
  );
}

export default App;
