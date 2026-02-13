import {useEffect, useState} from 'react'
import StatusPanel from "./components/StatusPanel.jsx";
import ControlsPanel from "./components/ControlsPanel.jsx";
import DataDisplay from "./components/DataDisplay.jsx";
import {fetchData} from "./utils/fetchData.jsx";

function App() {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [data, setData] = useState([]);
    const [filter, setFilter] = useState("All");
    const [beacon, setBeacon] = useState(false);

    useEffect(() => {
        fetchData({setLoading,setError, setData});
    }, []);

    useEffect(() => {
        const id = setInterval(() => {
        setBeacon((prev) => !prev);
    }, 1000);

    return () => clearInterval(id);}, []);

    return (
        <div className="container-fluid py-4">
            <h2 className="mb-4 fw-bold">Lifecycle Lighthouse</h2>

            <StatusPanel loading={loading} error={error} beacon={beacon} />

            <ControlsPanel onReload={() => fetchData({setLoading,setError,setData})}
                       filter={filter} setFilter={setFilter}/>

            <DataDisplay data={data} filter={filter} />
        </div>
    );
}

export default App
