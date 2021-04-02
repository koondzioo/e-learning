import React from "react";
import UsersComponent from "./UsersComponent";
import UploadComponent from "./UploadComponent";

export default class FirstComponent extends React.Component {

    render() {
        return (
            <div>
                <UsersComponent/>

                <br/>

                <UploadComponent/>
            </div>
        );
    }

}

