import React from "react";

export default function DisplayData ( {data} ) { //IMPORTANT TO HAVE CURLY BRACES
    if (!data) { return null; }

    return (
        <div className="card shadow my-5">
            <div className="card-body">
                <h5 className="card-title">Submitted Data</h5>
                <p><strong>Name:</strong> {data.name}</p>
                <p><strong>Email:</strong> {data.email}</p>
                <p><strong>Password:</strong> {data.password}</p>
                <p><strong>Phone:</strong> {data.phone}</p>

            </div>
        </div>
    );
}