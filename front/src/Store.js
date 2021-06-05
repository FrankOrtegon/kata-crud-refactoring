import initialState from "./initialState";
import { createContext } from "react";

const Store = createContext(initialState);

export default Store;