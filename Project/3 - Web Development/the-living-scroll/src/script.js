const entryInput = document.getElementById("entryInput");
const addBtn = document.getElementById("addBtn");
const scrollContainer = document.getElementById("scrollContainer");
const entryCount = document.getElementById("entryCount");
const message = document.getElementById("message");
const emptyMessage = document.getElementById("emptyMessage");

let count = 0;

function updateCounter() {
    entryCount.textContent = count;
}

function checkEmpty() {
    if (count === 0) {
        emptyMessage.style.display = "block";
    } else {
        emptyMessage.style.display = "none";
    }
}

addBtn.addEventListener("click", function () {
    const text = entryInput.value.trim();

    if (text === "") {
        message.textContent = "Entry cannot be blank.";
        return;
    }

    message.textContent = "";

    const entryDiv = document.createElement("div");
    entryDiv.className = "list-group-item d-flex justify-content-between align-items-center";

    const entryText = document.createElement("span");
    entryText.textContent = text;

    const btnGroup = document.createElement("div");

    const editBtn = document.createElement("button");
    editBtn.className = "btn btn-sm btn-info text-white me-2";
    editBtn.textContent = "Edit";

    editBtn.addEventListener("click", function () {
        const updatedText = prompt("Edit your entry:", entryText.textContent);

        if (updatedText === null) return;

        if (updatedText.trim() === "") {
            alert("Entry cannot be blank.");
            return;
        }

        entryText.textContent = updatedText.trim();
    });

    const deleteBtn = document.createElement("button");
    deleteBtn.className = "btn btn-sm btn-danger";
    deleteBtn.textContent = "Delete";

    deleteBtn.addEventListener("click", function () {
        scrollContainer.removeChild(entryDiv);
        count--;
        updateCounter();
        checkEmpty();
    });

    btnGroup.appendChild(editBtn);
    btnGroup.appendChild(deleteBtn);

    entryDiv.appendChild(entryText);
    entryDiv.appendChild(btnGroup);

    scrollContainer.appendChild(entryDiv);

    count++;
    updateCounter();
    checkEmpty();

    entryInput.value = "";
});

updateCounter();
checkEmpty();