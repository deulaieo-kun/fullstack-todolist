import { configureStore } from "@reduxjs/toolkit";
import promise from "redux-promise";
import thunk from "redux-thunk";
import todoReducer from './reducers/todoReducer';
import updateReducer from './reducers/updateReducer';
import promiseMiddleware from 'redux-promise-middleware';
import logger from "redux-logger";

export const store = configureStore({
    reducer: {
        todos: todoReducer,
        update: updateReducer
    },
    middleware: [
        thunk,
        promiseMiddleware,
        promise,
        logger,
    ]
});