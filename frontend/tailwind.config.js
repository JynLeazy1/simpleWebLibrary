/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        primary: "#E59F71",
        secondary: "#BA5A31",
        dark: "#0C0C0C",
        success: "#69DC9E",
        light: "#FFFFFF",
      }
    },
  },
  plugins: [],
};
