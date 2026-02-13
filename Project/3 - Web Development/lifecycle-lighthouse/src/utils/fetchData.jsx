export function fetchData  ( {setLoading, setError, setData } ) {
    setLoading(true);
    setError(null);
    setData([]);

    setTimeout(() => {
        const shouldFail = Math.random() < 0.3;

        if (shouldFail) {
            setError("Failed to fetch data. Please try again.");
            setData([]);
            setLoading(false);
        } else {
            const items = [
                { id: 1, title: "Server Health Check", priority: "High" },
                { id: 2, title: "Log File Archiving", priority: "Low" },
                { id: 3, title: "Security Update", priority: "High" },
                { id: 4, title: "Bug Fix", priority: "Low" },
                { id: 5, title: "Database Cleanup", priority: "Low" },
                { id: 6, title: "Performance Test", priority: "High" },
                { id: 7, title: "Client Meeting", priority: "High" },
                { id: 8, title: "Team Training", priority: "Low" }
            ];

            setData(items);
            setLoading(false);
        }
    }, 1500);
}