import initialState from "./initialState";
import React, { createContext } from "react";

const Store = createContext(initialState);

export default Store;