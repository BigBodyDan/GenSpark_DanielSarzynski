import Card from "./Card.jsx";

export default function MainContent() {
    const cardsData = [
        { id: 1, title: "Project One", text: "This is the first card." },
        { id: 2, title: "Project Two", text: "This is the second card." },
        { id: 3, title: "Project Three", text: "This is the third card." },
        { id: 4, title: "Project Four", text: "This is the fourth card." }
    ];

    return (
        <main className="col-10 p-3">
            <h3>Portfolio: </h3>
            {cardsData.map((item) => (
                <Card key={item.id} title={item.title} text={item.text} />
                ))}
        </main>
    );
}