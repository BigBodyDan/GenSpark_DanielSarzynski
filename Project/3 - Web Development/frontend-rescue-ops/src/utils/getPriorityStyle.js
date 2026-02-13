export function getPriorityClass(priority) {
    switch (priority) {
        case "High":
            return "bg-danger";
        case "Med":
            return "bg-warning text-dark";
        case "Low":
            return "bg-success text-white";
        default:
            return "bg-light";
    }
}