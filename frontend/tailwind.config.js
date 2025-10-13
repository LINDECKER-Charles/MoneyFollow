/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class',
  content: [
    "./src/**/*.{html,ts,scss}",
  ],
  theme: {
    extend: {
      colors: {
        // 🎨 Couleurs principales inspirées du logo
        primary: {
          50:  '#F4F3FF',
          100: '#E5E3FF',
          200: '#C9C4FF',
          300: '#A3A0FF',
          400: '#7C7BFF',
          500: '#5B7CFF', // bleu-violet clair
          600: '#4B5FE8',
          700: '#3D4BC2',
          800: '#2F3B97',
          900: '#232B6E',
        },
        secondary: {
          50:  '#F7F3FF',
          100: '#EDE1FF',
          200: '#D6BFFF',
          300: '#B890FF',
          400: '#A06CFA',
          500: '#8A46E8', // violet principal du dégradé
          600: '#7538C4',
          700: '#602DA1',
          800: '#4B237E',
          900: '#35185A',
        },
        accent: {
          100: '#99E6FF',
          200: '#00C2FF',
          300: '#009ACF',
        },
        neutral: {
          50:  '#F8FAFC',
          100: '#F1F5F9',
          200: '#E2E8F0',
          300: '#CBD5E1',
          400: '#94A3B8',
          500: '#64748B',
          600: '#475569',
          700: '#334155',
          800: '#1E293B',
          900: '#0F172A',
        },
        success: '#34D399',
        warning: '#FBBF24',
        danger: '#EF4444',
        background: {
          light: '#F9FAFB',
          dark: '#1C1F26',
        },
      },

      fontFamily: {
        sans: ['Inter', 'Roboto', 'sans-serif'],
        mono: ['Share Tech Mono', 'monospace'],
        display: ['Bebas Neue', 'sans-serif'],
      },

      boxShadow: {
        soft: '0 4px 20px rgba(0,0,0,0.08)',
        hard: '0 2px 10px rgba(0,0,0,0.25)',
        glow: '0 0 20px rgba(91, 124, 255, 0.5)', // halo bleu doux
      },

      borderRadius: {
        xl: '1rem',
        '2xl': '1.5rem',
      },

      animation: {
        fadeIn: 'fadeIn 0.4s ease-in-out',
        slideUp: 'slideUp 0.5s ease-in-out',
      },

      keyframes: {
        fadeIn: {
          '0%': { opacity: 0 },
          '100%': { opacity: 1 },
        },
        slideUp: {
          '0%': { transform: 'translateY(10px)', opacity: 0 },
          '100%': { transform: 'translateY(0)', opacity: 1 },
        },
      },
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
    require('@tailwindcss/typography'),
    require('@tailwindcss/aspect-ratio'),
  ],
};
