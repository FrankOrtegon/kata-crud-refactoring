import React, { useContext, useRef, useState } from "react";
import Store from "../Store";
import HOST_API from "../API_URL";


const Form = () => {
    const formRef = useRef(null);
    const { dispatch, state: { todo } } = useContext(Store);
    const item = todo.item;
    const [state, setState] = useState(item);

    const onAdd = (event) => {
        event.preventDefault();

        const request = {
            name: state.name,
            id: null,
            completed: false
        };


        fetch(HOST_API + "/todo", {
            method: "POST",
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then((todo) => {
                dispatch({ type: "add-item", item: todo });
                setState({ name: "" });
                formRef.current.reset();
            });
    }

    const onEdit = (event) => {
        event.preventDefault();

        const request = {
            name: state.name,
            id: item.id,
            isCompleted: item.isCompleted
        };


        fetch(HOST_API + "/todo", {
            method: "POST",
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then((todo) => {
                dispatch({ type: "update-item", item: todo });
                setState({ name: "" });
                formRef.current.reset();
            });
    }

    return <div className="form-header">
        <form ref={formRef}>
            <input
                type="text"
                name="name"
                placeholder="¿Qué piensas hacer hoy?"
                className="text"
                defaultValue={item.name}
                autoComplete="off"
                onChange={(event) => {
                    setState({ ...state, name: event.target.value })
                }}  ></input>
            {item.id && <button onClick={onEdit} className="btn btn-dark ms-3">Actualizar</button>}
            {!item.id && <button onClick={onAdd} className="btn btn-dark ms-3">Crear</button>}
        </form>
    </div>

}

export default Form;