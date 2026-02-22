<!DOCTYPE html>

<html lang="en"><head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>VocabularySpring - Flashcard Test</title>
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<script id="tailwind-config">
        tailwind.config = {
            darkMode: "class",
            theme: {
                extend: {
                    colors: {
                        "primary": "#a49279",
                        "background-light": "#f0f0f0", // Adjusted for better neumorphic contrast
                        "background-dark": "#1b1917",
                        "neumorphic-light": "#ffffff",
                        "neumorphic-shadow": "#d1d1d1",
                    },
                    fontFamily: {
                        "display": ["Inter", "sans-serif"]
                    },
                    borderRadius: {
                        "DEFAULT": "0.25rem",
                        "lg": "0.5rem",
                        "xl": "0.75rem",
                        "2xl": "1.5rem",
                        "full": "9999px"
                    },
                    boxShadow: {
                        'neu-flat': '8px 8px 16px #d1d1d1, -8px -8px 16px #ffffff',
                        'neu-pressed': 'inset 6px 6px 12px #d1d1d1, inset -6px -6px 12px #ffffff',
                        'neu-sm': '4px 4px 8px #d1d1d1, -4px -4px 8px #ffffff',
                    }
                },
            },
        }
    </script>
<style>
        body {
            font-family: 'Inter', sans-serif;
        }
        .neumorphic-card {
            background: #f0f0f0;
            box-shadow: 20px 20px 60px #cccccc, -20px -20px 60px #ffffff;
        }
        .neumorphic-button-raised {
            background: #f0f0f0;
            box-shadow: 6px 6px 12px #cccccc, -6px -6px 12px #ffffff;
        }
        .neumorphic-button-inset {
            background: #f0f0f0;
            box-shadow: inset 6px 6px 12px #cccccc, inset -6px -6px 12px #ffffff;
        }
        .neumorphic-progress-container {
            background: #f0f0f0;
            box-shadow: inset 4px 4px 8px #cccccc, inset -4px -4px 8px #ffffff;
        }
    </style>
<style>
    body {
      min-height: max(884px, 100dvh);
    }
  </style>
  </head>
<body class="bg-background-light dark:bg-background-dark min-h-screen flex flex-col font-display">
<!-- Header Navigation -->
<header class="flex items-center justify-between p-6">
<div class="flex items-center gap-4">
<button class="neumorphic-button-raised w-10 h-10 rounded-lg flex items-center justify-center text-slate-600 dark:text-slate-400">
<span class="material-symbols-outlined">close</span>
</button>
<div>
<h1 class="text-slate-900 dark:text-slate-100 font-bold text-lg leading-none">Daily Vocab</h1>
<p class="text-primary text-xs font-medium uppercase tracking-wider mt-1">VocabularySpring</p>
</div>
</div>
<button class="neumorphic-button-raised w-10 h-10 rounded-lg flex items-center justify-center text-slate-600 dark:text-slate-400">
<span class="material-symbols-outlined">more_vert</span>
</button>
</header>
<!-- Main Content Area -->
<main class="flex-1 flex flex-col px-6 py-4 max-w-md mx-auto w-full">
<!-- Progress Bar -->
<div class="mb-10">
<div class="flex justify-between items-end mb-3 px-1">
<span class="text-slate-500 dark:text-slate-400 text-sm font-medium">Session Progress</span>
<span class="text-primary font-bold text-sm">12 / 25</span>
</div>
<div class="neumorphic-progress-container h-4 w-full rounded-full p-1">
<div class="h-full bg-primary rounded-full" style="width: 48%;"></div>
</div>
</div>
<!-- Flashcard Centerpiece -->
<div class="flex-1 flex items-center justify-center py-4">
<div class="neumorphic-card w-full aspect-[4/5] rounded-2xl flex flex-col items-center justify-center p-8 text-center relative overflow-hidden">
<!-- Abstract Decorative Element -->
<div class="absolute top-0 right-0 w-32 h-32 bg-primary/5 rounded-full -mr-16 -mt-16"></div>
<div class="z-10">
<h2 class="text-slate-900 dark:text-slate-100 text-5xl font-bold tracking-tight mb-4">Aesthetic</h2>
<p class="text-primary text-xl font-light italic opacity-80">/esˈθet.ɪk/</p>
</div>
<!-- Visual Hint Placeholder (Subtle Neumorphic Frame) -->
<div class="mt-12 w-full aspect-video rounded-xl neumorphic-button-inset flex items-center justify-center overflow-hidden grayscale opacity-40">
<img alt="Minimalist interior design" class="object-cover w-full h-full" data-alt="Minimalist aesthetic interior with soft lighting" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBYAYbmxJLikmVyEZ_HesucuS3x07OwbC4txDANNEzoM1UfaTdtdVfiyhVK4UTOGrZtqszgCedSP2D1fyQl3vrypqkzArCJVYnue2nskLua-b7MDYeAze-9SzCKnxUZTHQckPVp_Bid8i_dhqYppHqFqY7SccZ_9YiqOQW8MhYMVJSHfVMJFR46LIp7iBQx0XinHWMguSwUwYm9EPXMjDSgRwAkpblKMOhZ9jX-AE59vsqA0DIgtuT7mD0wQ867ptK8Y28Unb5nPyB0"/>
</div>
</div>
</div>
<!-- Action Buttons -->
<div class="grid grid-cols-2 gap-6 mt-10 mb-8">
<button class="neumorphic-button-inset flex flex-col items-center justify-center py-6 rounded-2xl group transition-all">
<span class="material-symbols-outlined text-slate-400 mb-2">sentiment_dissatisfied</span>
<span class="text-slate-500 font-semibold text-sm">Don't Know</span>
</button>
<button class="neumorphic-button-raised flex flex-col items-center justify-center py-6 rounded-2xl group transition-all active:shadow-neu-pressed">
<span class="material-symbols-outlined text-primary mb-2">sentiment_satisfied</span>
<span class="text-primary font-bold text-sm">I Know</span>
</button>
</div>
</main>
<!-- Bottom Navigation (Simplified for Test Screen) -->
<nav class="mt-auto border-t border-slate-200/50 bg-background-light px-8 py-4">
<div class="flex justify-between items-center max-w-md mx-auto">
<a class="text-primary" href="#">
<span class="material-symbols-outlined text-3xl">home</span>
</a>
<a class="text-slate-400" href="#">
<span class="material-symbols-outlined text-3xl">menu_book</span>
</a>
<a class="text-slate-400" href="#">
<span class="material-symbols-outlined text-3xl">leaderboard</span>
</a>
<a class="text-slate-400" href="#">
<span class="material-symbols-outlined text-3xl">person</span>
</a>
</div>
</nav>
</body></html>