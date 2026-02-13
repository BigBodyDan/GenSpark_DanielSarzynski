export function validateForm( { name, email, password, confirmPassword, phone } ) {

    // Name validation
    if (name.length < 3) {
        return "Name must be at least 3 characters long.";
    }

    // Email validation
    const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    if (!emailPattern.test(email)) {
        return "Please enter a valid email address.";
    }

    // Password validation
    if (password.length < 6) {
        return "Password must be at least 6 characters long.";
    }

    // Confirm password validation
    if (password !== confirmPassword) {
        return "Passwords do not match.";
    }

    // Phone validation (10 digits only)
    const cleanPhone = phone.trim().replace(/\D/g, "");
    const phonePattern = /^[0-9]{10}$/;
    if (!phonePattern.test(cleanPhone)) {
        return "Phone number must be exactly 10 digits.";
    }

    return null;
}