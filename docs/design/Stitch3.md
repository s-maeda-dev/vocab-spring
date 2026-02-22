<!DOCTYPE html>

<html class="light" lang="en"><head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>Add New Word - VocabularySpring</title>
<!-- Tailwind CSS with Plugins -->
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<!-- Google Fonts: Inter -->
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&amp;display=swap" rel="stylesheet"/>
<!-- Material Symbols Outlined -->
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<script id="tailwind-config">
        tailwind.config = {
            darkMode: "class",
            theme: {
                extend: {
                    colors: {
                        "primary": "#a49279",
                        "background-light": "#f7f7f7",
                        "background-dark": "#1b1917",
                        "neumorphic-shadow": "#e0e0e0",
                        "neumorphic-highlight": "#ffffff",
                    },
                    fontFamily: {
                        "display": ["Inter"]
                    },
                    borderRadius: {
                        "DEFAULT": "0.25rem",
                        "lg": "0.5rem",
                        "xl": "0.75rem",
                        "2xl": "1rem",
                        "full": "9999px"
                    },
                },
            },
        }
    </script>
<style>
        /* Neumorphism Custom Utilities */
        .neu-recessed {
            background: #f7f7f7;
            box-shadow: inset 4px 4px 8px #e8e8e8, 
                        inset -4px -4px 8px #ffffff;
            border: none;
        }
        .neu-flat {
            background: #f7f7f7;
            box-shadow: 6px 6px 12px #e8e8e8, 
                        -6px -6px 12px #ffffff;
        }
        .neu-button-primary {
            background: #a49279;
            box-shadow: 5px 5px 10px #d9d9d9, 
                        -5px -5px 10px #ffffff;
        }
        .neu-icon-pill {
            background: #f7f7f7;
            box-shadow: 3px 3px 6px #e8e8e8, 
                        -3px -3px 6px #ffffff;
        }
        
        /* Dark mode overrides (simplified approximation for neumorphism) */
        .dark .neu-recessed {
            background: #1b1917;
            box-shadow: inset 4px 4px 8px #121110, 
                        inset -2px -2px 6px #282522;
        }
        .dark .neu-flat {
            background: #1b1917;
            box-shadow: 6px 6px 12px #121110, 
                        -6px -6px 12px #282522;
        }
        .dark .neu-button-primary {
            box-shadow: 4px 4px 10px #0e0d0c, 
                        -2px -2px 6px #282522;
        }
    </style>
<style>
    body {
      min-height: max(884px, 100dvh);
    }
  </style>
  </head>
<body class="bg-background-light dark:bg-background-dark font-display text-slate-900 dark:text-slate-100 min-h-screen flex flex-col">
<!-- Header Section -->
<header class="flex items-center justify-between p-6 max-w-2xl mx-auto w-full">
<button class="neu-icon-pill p-2 rounded-xl flex items-center justify-center text-primary transition-all active:scale-95">
<span class="material-symbols-outlined">arrow_back</span>
</button>
<h1 class="text-xl font-bold tracking-tight text-slate-800 dark:text-slate-200">Add New Word</h1>
<div class="w-10"></div> <!-- Spacer for centering -->
</header>
<!-- Main Form Content -->
<main class="flex-1 max-w-2xl mx-auto w-full px-6 py-4 space-y-8">
<!-- Illustration/Decorative Element -->
<div class="flex justify-center mb-4">
<div class="neu-flat rounded-full p-6 bg-primary/10">
<span class="material-symbols-outlined text-primary !text-4xl">auto_stories</span>
</div>
</div>
<!-- Form Fields -->
<div class="space-y-6">
<!-- Word Input -->
<div class="space-y-2">
<label class="text-sm font-semibold text-primary px-1 uppercase tracking-wider">Word</label>
<div class="relative">
<input class="neu-recessed w-full py-4 px-5 rounded-2xl focus:ring-2 focus:ring-primary/30 border-none transition-all outline-none text-slate-800 dark:text-slate-200 placeholder:text-slate-400" placeholder="Enter your word..." type="text"/>
</div>
</div>
<!-- Definition Input -->
<div class="space-y-2">
<label class="text-sm font-semibold text-primary px-1 uppercase tracking-wider">Definition</label>
<div class="relative">
<textarea class="neu-recessed w-full py-4 px-5 rounded-2xl focus:ring-2 focus:ring-primary/30 border-none transition-all outline-none text-slate-800 dark:text-slate-200 placeholder:text-slate-400 resize-none" placeholder="What does it mean?" rows="4"></textarea>
</div>
</div>
<!-- Category Select -->
<div class="space-y-2">
<label class="text-sm font-semibold text-primary px-1 uppercase tracking-wider">Category</label>
<div class="relative group">
<select class="neu-recessed w-full py-4 px-5 rounded-2xl focus:ring-2 focus:ring-primary/30 border-none appearance-none transition-all outline-none text-slate-800 dark:text-slate-200">
<option disabled="" selected="" value="">Select a category...</option>
<option value="academic">Academic</option>
<option value="business">Business</option>
<option value="slang">Slang</option>
<option value="verbs">Verbs</option>
<option value="travel">Travel</option>
</select>
<div class="absolute right-5 top-1/2 -translate-y-1/2 pointer-events-none text-slate-400">
<span class="material-symbols-outlined">expand_more</span>
</div>
</div>
</div>
</div>
</main>
<!-- Bottom Navigation / Save Action -->
<footer class="p-6 max-w-2xl mx-auto w-full mb-8">
<button class="neu-button-primary w-full py-5 rounded-2xl text-white font-bold text-lg tracking-wide uppercase active:scale-[0.98] transition-all hover:brightness-110">
            Save Word
        </button>
</footer>
<!-- Simplified Navigation Bar -->
<nav class="border-t border-slate-200 dark:border-slate-800 bg-background-light dark:bg-background-dark px-4 py-2">
<div class="max-w-2xl mx-auto flex justify-between items-center h-16">
<a class="flex flex-col items-center gap-1 text-slate-400 group" href="#">
<span class="material-symbols-outlined">home</span>
<span class="text-[10px] font-medium">Home</span>
</a>
<a class="flex flex-col items-center gap-1 text-slate-400 group" href="#">
<span class="material-symbols-outlined">search</span>
<span class="text-[10px] font-medium">Search</span>
</a>
<a class="flex flex-col items-center gap-1 text-primary relative -top-4" href="#">
<div class="neu-flat p-4 rounded-full bg-background-light dark:bg-background-dark border-4 border-background-light dark:border-background-dark">
<span class="material-symbols-outlined !text-3xl">add</span>
</div>
<span class="text-[10px] font-bold mt-1">Add</span>
</a>
<a class="flex flex-col items-center gap-1 text-slate-400 group" href="#">
<span class="material-symbols-outlined">history</span>
<span class="text-[10px] font-medium">History</span>
</a>
<a class="flex flex-col items-center gap-1 text-slate-400 group" href="#">
<span class="material-symbols-outlined">person</span>
<span class="text-[10px] font-medium">Profile</span>
</a>
</div>
</nav>
</body></html>