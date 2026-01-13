const studentIdInput = document.getElementById("studentId");
const searchBtn = document.getElementById("searchBtn");
const resultDiv = document.getElementById("result");

searchBtn.addEventListener("click", async () => {
  const id = studentIdInput.value;

  if (!id) {
    showError("Please enter a student ID.");
    return;
  }

  try {
    const response = await fetch(`http://localhost:8080/students/${id}`);
    
    if (!response.ok) {
      showError("Student not found.");
      return;
    }

    const student = await response.json();
    showSuccess(`Hello ${student.name} (ID: ${student.id})`);
  } catch (error) {
    showError("Could not connect to backend. Is Spring Boot running?");
  }
});

function showSuccess(message) {
  resultDiv.classList.remove("hidden", "error");
  resultDiv.classList.add("success");
  resultDiv.innerText = message;
}

function showError(message) {
  resultDiv.classList.remove("hidden", "success");
  resultDiv.classList.add("error");
  resultDiv.innerText = message;
}
