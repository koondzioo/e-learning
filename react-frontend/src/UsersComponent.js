import React, {useEffect, useState} from "react";
import axios from "axios";


const fetchUsers = () => {
    return axios.get(`http://localhost:8090/user/all`).then(data => {
        return data;
    }).catch(error => {
        console.log(error);
    })
}

const UsersComponent = () => {

    const [users, setUsers] = useState([])


    useEffect(() => {
        fetchUsers().then((randomData) => {
            setUsers(users => [...users, randomData.data])
        })
    }, [])


    const refresh = () =>{
        fetchUsers().then((randomData) => {
            setUsers(users => [...users, randomData.data])
        })
    }
    return (
        <div>
            {
                users.map((User, index) => {
                    return (
                        <p key={`${User[index].firstName}`}>
                            {User[index].firstName}  -  {User[index].lastName}
                        </p>

                    );
                })
            }
            <button onClick={null}>Refresh</button>
            <div>Hello</div>
        </div>
    );

}

export default UsersComponent;

