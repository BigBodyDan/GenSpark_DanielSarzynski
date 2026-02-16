const fateDisplay = document.querySelector("#fateDisplay");
const fateMeter = document.querySelector("#fateMeter");
const prophecy = document.querySelector("#prophecy");
const buttons = document.querySelectorAll(".fate-btn");

let fateValue = 0;

buttons.forEach(button => {
    button.addEventListener("click", handleClick);
});

function handleClick(event) {
    const action = event.target.dataset.action;

    buttons.forEach(btn => btn.classList.remove("active"));

    event.target.classList.add("active");

    if (action === "blessing") {
        addBlessing();
    } else if (action === "curse") {
        addCurse();
    } else if (action === "reveal") {
        toggleReveal();
    } else if (action === "reset") {
        resetFate();
    }

    updateUI();
}

function addBlessing() {
    fateValue += 10;
    fateDisplay.textContent = "A blessing lifts your path.";
}

function addCurse() {
    fateValue -= 10;
    fateDisplay.textContent = "A curse weighs on your soul.";
}

function toggleReveal() {
    prophecy.classList.toggle("d-none");
    fateDisplay.textContent = "The prophecy has shifted.";
}

function resetFate() {
    fateValue = 0;
    fateDisplay.textContent = "Your fate has been reset.";
    prophecy.classList.add("d-none");
    fateDisplay.classList.remove("bg-success", "bg-danger");
    fateDisplay.classList.add("bg-info");
}

function updateUI() {
    fateMeter.textContent = fateValue;

    fateDisplay.classList.remove("bg-success", "bg-danger", "bg-info");

    if (fateValue >= 50) {
        fateDisplay.textContent = "Fate favors you.";
        fateDisplay.classList.add("bg-success");
    } else if (fateValue <= -50) {
        fateDisplay.textContent = "Darkness closes in.";
        fateDisplay.classList.add("bg-danger");
    } else {
        fateDisplay.classList.add("bg-info");
    }
}