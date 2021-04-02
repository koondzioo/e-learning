import React from "react";

class UploadComponent extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            selectedFile: ''
        }
    }

    onFileChangeHandler = (e) => {
        e.preventDefault();
        this.setState({
            selectedFile: e.target.files[0]
        });
    };

    handleSubmit = () => {
        const formData = new FormData();
        formData.append('file', this.state.selectedFile);
        fetch('http://localhost:8200/upload', {
            method: 'post',
            body: formData
        }).then(res => {
            if (res.ok) {
                console.log(res.data);
                alert("File uploaded successfully.")
            }
        });
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        <div className="form-group files color">
                            <label>Upload Your File </label>
                            <input type="file" className="form-control" name="file" onChange={this.onFileChangeHandler}/>
                        </div>
                        <button onClick={this.handleSubmit}>Submit</button>
                    </div>
                </div>
            </div>
        )
    }

}

export default UploadComponent;