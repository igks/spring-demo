import React, { useState } from "react";
import { useSubscription } from "react-stomp-hooks";

const SocketConsumer = () => {
  const [lastMessage, setLastMessage] = useState("No message received yet");

  useSubscription("/topic/message", (message) => setLastMessage(message.body));

  return <div>Last Message: {lastMessage}</div>;
};

export default SocketConsumer;
