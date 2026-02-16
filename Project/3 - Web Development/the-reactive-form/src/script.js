const form = document.querySelector("#reactiveForm");
const username = document.querySelector("#username");
const email = document.querySelector("#email");
const password = document.querySelector("#password");
const submitBtn = document.querySelector("#submitBtn");

const usernameFeedback = document.querySelector("#usernameFeedback");
const emailFeedback = document.querySelector("#emailFeedback");
const passwordFeedback = document.querySelector("#passwordFeedback");
const formSummary = document.querySelector("#formSummary");


function validateUsername() {
    const value = username.value.trim();

    if (value === "") {
        setInvalid(username, usernameFeedback, "Username cannot be blank.");
        return false;
    }

    if (value.length < 3) {
        setInvalid(username, usernameFeedback, "Username must be at least 3 characters.");
        return false;
    }

    setValid(username, usernameFeedback, "Looks good.");
    return true;
}

function validateEmail() {
    const value = email.value.trim();

    if (value === "") {
        setInvalid(email, emailFeedback, "Email cannot be blank.");
        return false;
    }

    if (!value.includes("@") || !value.includes(".")) {
        setInvalid(email, emailFeedback, "Enter a valid email address.");
        return false;
    }

    setValid(email, emailFeedback, "Looks good.");
    return true;
}

function validatePassword() {
    const value = password.value.trim();
    const hasNumber = /\d/.test(value);

    if (value === "") {
        setInvalid(password, passwordFeedback, "Password cannot be blank.");
        return false;
    }

    if (value.length < 8 || !hasNumber) {
        setInvalid(password, passwordFeedback, "Password must be 8+ characters and include a number.");
        return false;
    }

    setValid(password, passwordFeedback, "Looks good.");
    return true;
}


function setInvalid(input, feedback, message) {
    input.classList.remove("is-valid");
    input.classList.add("is-invalid");
    feedback.classList.remove("text-success");
    feedback.classList.add("text-danger");
    feedback.textContent = message;
}

function setValid(input, feedback, message) {
    input.classList.remove("is-invalid");
    input.classList.add("is-valid");
    feedback.classList.remove("text-danger");
    feedback.classList.add("text-success");
    feedback.textContent = message;
}

function checkFormValidity() {
    const isValid = validateUsername() && validateEmail() && validatePassword();

    submitBtn.disabled = !isValid;
    return isValid;
}

username.addEventListener("input", checkFormValidity);
email.addEventListener("input", checkFormValidity);
password.addEventListener("input", checkFormValidity);

form.addEventListener("submit", function (event) {
    event.preventDefault();

    const isValid = checkFormValidity();

    if (!isValid) {
        formSummary.classList.remove("text-success");
        formSummary.classList.add("text-danger");
        formSummary.textContent = "Fix the errors before submitting.";
        return;
    }

    formSummary.classList.remove("text-danger");
    formSummary.classList.add("text-success");
    formSummary.textContent = "Form submitted successfully!";

    form.reset();

    clearValidation(username, usernameFeedback);
    clearValidation(email, emailFeedback);
    clearValidation(password, passwordFeedback);

    submitBtn.disabled = true;
});

function clearValidation(input, feedback) {
    input.classList.remove("is-valid", "is-invalid");
    feedback.textContent = "";
}