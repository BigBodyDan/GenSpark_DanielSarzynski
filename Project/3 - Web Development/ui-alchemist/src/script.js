const body = document.getElementById("pageBody");
const previewPanel = document.getElementById("previewPanel");
const detailsList = document.getElementById("detailsList");

const cards = document.querySelectorAll(".card");

const darkModeBtn = document.getElementById("darkModeBtn");
const decreaseFontBtn = document.getElementById("decreaseFont");
const increaseFontBtn = document.getElementById("increaseFont");
const highlightBtn = document.getElementById("highlightBtn");
const toggleDetailsBtn = document.getElementById("toggleDetailsBtn");
const resetBtn = document.getElementById("resetBtn");

const statusText = document.getElementById("statusText");

let isDarkMode = false;
let isHighlighted = false;
let detailsVisible = true;
let currentFontSize = 16; // default px

previewPanel.style.fontSize = currentFontSize + "px";

darkModeBtn.addEventListener("click", function () {
    isDarkMode = !isDarkMode;

    body.classList.toggle("bg-dark");
    body.classList.toggle("text-light");

    cards.forEach(function(card) {
        card.classList.toggle("bg-dark");
        card.classList.toggle("text-light");
    });

    statusText.textContent = "Dark Mode: " + (isDarkMode ? "ON" : "OFF");
});

decreaseFontBtn.addEventListener("click", function () {
    if (currentFontSize > 12) {
        currentFontSize -= 2;
        previewPanel.style.fontSize = currentFontSize + "px";
        statusText.textContent = "Font Size: " + currentFontSize + "px";
    }
});

increaseFontBtn.addEventListener("click", function () {
    if (currentFontSize < 30) {
        currentFontSize += 2;
        previewPanel.style.fontSize = currentFontSize + "px";
        statusText.textContent = "Font Size: " + currentFontSize + "px";
    }
});

highlightBtn.addEventListener("click", function () {
    isHighlighted = !isHighlighted;

    previewPanel.classList.toggle("border");
    previewPanel.classList.toggle("border-warning");
    previewPanel.classList.toggle("border-4");
    previewPanel.classList.toggle("shadow-lg");

    statusText.textContent = "Highlight: " + (isHighlighted ? "ACTIVE" : "OFF");
});

toggleDetailsBtn.addEventListener("click", function () {
    detailsVisible = !detailsVisible;

    detailsList.classList.toggle("d-none");

    statusText.textContent = "Details: " + (detailsVisible ? "Visible" : "Hidden");
});

resetBtn.addEventListener("click", function () {

    // Reset state values
    isDarkMode = false;
    isHighlighted = false;
    detailsVisible = true;
    currentFontSize = 16;

    // Reset classes
    cards.forEach(function(card) {
        card.classList.remove("bg-dark", "text-light");
    });
    body.classList.remove("bg-dark");
    body.classList.remove("text-light");
    previewPanel.classList.remove(
        "bg-dark",
        "text-light",
        "border",
        "border-warning",
        "border-4",
        "shadow-lg"
    );

    detailsList.classList.remove("d-none");

    previewPanel.style.fontSize = currentFontSize + "px";

    statusText.textContent = "UI reset to default state.";
});