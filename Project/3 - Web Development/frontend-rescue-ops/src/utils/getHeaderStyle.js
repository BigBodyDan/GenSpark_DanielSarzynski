export function getHeaderStyle(title) {
    switch (title) {
        case "To Do":
            return "bg-secondary text-white";
        case "Doing":
            return "bg-warning text-dark";
        case "Done":
            return "bg-success text-white";
        default:
            return "bg-light";
    }
}