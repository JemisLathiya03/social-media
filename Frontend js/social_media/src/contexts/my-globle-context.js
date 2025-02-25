import { createContext } from "react";

const GlobleContext = createContext();

const MyGlobleContextProvider = GlobleContext.Provider;
const MyGlobleContextConsumer = GlobleContext.Consumer;

export {GlobleContext,MyGlobleContextProvider,MyGlobleContextConsumer}