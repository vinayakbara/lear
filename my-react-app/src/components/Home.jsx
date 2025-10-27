import React from 'react';

const Home = () => {

    function list() {
        listEmployees().then((response) => {
        setEmployee(response.data);
    }).catch(error => {
        console.error(error);
    })
    }
    return(
        <div className='container'>
            <br/>
            <div className='card col-md-16'>
                <h2>
                    Dashboard
                </h2>
                <button className='btn btn-primary w-auto mb-2' onClick={List}>List</button>
            </div>
        </div>
    );
};
export default Home;