console.log("Script loaded");

// Initialize current theme
let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
  changeTheme(); // Apply the current theme on page load
});

function changeTheme() {
  // Apply the current theme to the page
  changePageTheme(currentTheme, "");

  // Set up the listener for the theme change button
  const changeThemeButton = document.querySelector("#theme_change_button");

  changeThemeButton.addEventListener("click", () => {
    let oldTheme = currentTheme;
    console.log("Change theme button clicked");

    // Toggle theme between light and dark
    currentTheme = currentTheme === "dark" ? "light" : "dark";

    console.log("New theme:", currentTheme);

    // Apply the new theme to the page
    changePageTheme(currentTheme, oldTheme);
  });
}

// Save the theme to localStorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// Retrieve the theme from localStorage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light"; // Default to "light" if no theme is set
}

// Apply the theme to the page and update the button text
function changePageTheme(theme, oldTheme) {
  // Update the theme in localStorage
  setTheme(theme);

  // Remove the old theme class from the HTML element, if applicable
  if (oldTheme) {
    document.querySelector("html").classList.remove(oldTheme);
  }

  // Add the new theme class to the HTML element
  document.querySelector("html").classList.add(theme);

  // Update the button text to indicate the current theme
  document.querySelector("#theme_change_button span").textContent =
    theme === "light" ? " Dark" : " Light";
}
