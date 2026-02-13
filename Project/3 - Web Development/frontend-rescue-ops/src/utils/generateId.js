let idCounter = 0;

export function generateId() {
    return `id_${idCounter++}`;
}
