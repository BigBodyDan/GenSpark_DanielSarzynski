export default function ProfileCard(props) {
    const today = new Date().toLocaleDateString();

    return (
        <div>
            <h1 className="text-center mb-3">Hello React - The First Render</h1>
            <p className="lead">
                Hello, {props.name}.
                Today is {today}.
            </p>
            <p className="lead">
                This is a simple React app that renders a clean UI using JSX.
                It shows what React is, how JSX renders to the page, and how to organize a basic layout.
            </p>
        </div>
    );
}