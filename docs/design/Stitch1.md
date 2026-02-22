<!DOCTYPE html>

<html class="light" lang="en"><head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>VocabularySpring Dashboard</title>
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
                        "background-light": "#f5f4f2",
                        "background-dark": "#1b1917",
                        "neu-light": "#ffffff",
                        "neu-dark": "#d1ccc2",
                    },
                    fontFamily: {
                        "display": ["Inter"]
                    },
                    borderRadius: {
                        "DEFAULT": "0.25rem",
                        "lg": "0.5rem",
                        "xl": "1rem",
                        "2xl": "1.5rem",
                        "full": "9999px"
                    },
                    boxShadow: {
                        'neu-raised': '5px 5px 10px #d1ccc2, -5px -5px 10px #ffffff',
                        'neu-pressed': 'inset 5px 5px 10px #d1ccc2, inset -5px -5px 10px #ffffff',
                        'neu-raised-sm': '3px 3px 6px #d1ccc2, -3px -3px 6px #ffffff',
                    }
                },
            },
        }
    </script>
<style>
        .neu-raised { box-shadow: 5px 5px 10px #d1ccc2, -5px -5px 10px #ffffff; }
        .neu-pressed { box-shadow: inset 4px 4px 8px #d1ccc2, inset -4px -4px 8px #ffffff; }
        .neu-raised-sm { box-shadow: 3px 3px 6px #d1ccc2, -3px -3px 6px #ffffff; }
        
        .progress-ring-circle {
            transition: stroke-dashoffset 0.35s;
            transform: rotate(-90deg);
            transform-origin: 50% 50%;
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
<header class="p-6 flex items-center justify-between">
<div class="flex items-center gap-4">
<div class="w-12 h-12 rounded-full neu-raised p-1">
<img alt="Profile" class="w-full h-full rounded-full object-cover" data-alt="Professional male portrait for profile avatar" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDyd_ZnJEbFL4bSHcwLo2I9esw1Dpun6OZLq6ohfj0_2pWCqCRuK1DBbt_AeHonesigrlhBMyec0olwm3VdEkc6-rEjF0Of8nRko6pDBUMixdxUCG49W6Sgya_rliwuVlsXN_CdLQGO0bvY2_u3Hil3ZH-5LhbYQ-QJB-PK6l8xYwTIp2FCGQQtckiO5ptR-3-PG3D4YorqRejuFdS1TivcA4Bod72_Ht4QhLT3Tbbq7w6uOOhP9EgzCDPR1BnjTbvnUC1Dqz1C7Oue"/>
</div>
<div>
<p class="text-xs text-primary font-semibold uppercase tracking-wider">Welcome back</p>
<h1 class="text-xl font-bold">Hello, Alex</h1>
</div>
</div>
<button class="w-10 h-10 rounded-full neu-raised flex items-center justify-center text-primary">
<span class="material-symbols-outlined">notifications</span>
</button>
</header>
<main class="flex-1 px-6 pb-24 space-y-8 overflow-y-auto">
<!-- Streak Info -->
<div class="neu-pressed rounded-2xl p-4 flex items-center justify-between">
<div class="flex items-center gap-3">
<div class="w-10 h-10 rounded-xl neu-raised flex items-center justify-center text-orange-500 bg-background-light">
<span class="material-symbols-outlined fill-1">local_fire_department</span>
</div>
<div>
<p class="text-sm font-bold">12 Day Streak</p>
<p class="text-xs text-slate-500">You're on fire! Keep it up.</p>
</div>
</div>
<div class="text-primary font-bold text-sm">+2%</div>
</div>
<!-- Mastery Gauge -->
<section class="flex flex-col items-center py-4">
<div class="relative w-64 h-64 flex items-center justify-center rounded-full neu-raised">
<!-- Outer Ring (Pressed Effect) -->
<div class="absolute inset-4 rounded-full neu-pressed"></div>
<!-- Circular Progress -->
<svg class="w-48 h-48 transform -rotate-90">
<circle class="text-slate-200" cx="96" cy="96" fill="transparent" r="80" stroke="currentColor" stroke-width="12"></circle>
<circle class="text-primary" cx="96" cy="96" fill="transparent" r="80" stroke="currentColor" stroke-dasharray="502.6" stroke-dashoffset="75.4" stroke-linecap="round" stroke-width="12"></circle>
</svg>
<!-- Text Center -->
<div class="absolute flex flex-col items-center">
<span class="text-4xl font-black text-slate-800">85%</span>
<span class="text-[10px] uppercase font-bold tracking-widest text-primary">Mastered</span>
</div>
</div>
<h2 class="mt-8 text-lg font-bold text-center">Overall Vocabulary Strength</h2>
<p class="text-sm text-slate-500">Goal: 100% Mastered</p>
</section>
<!-- Stats Grid -->
<div class="grid grid-cols-2 gap-4">
<div class="neu-raised rounded-xl p-4 flex flex-col items-center text-center">
<span class="material-symbols-outlined text-primary mb-2">menu_book</span>
<span class="text-xl font-bold">1,240</span>
<span class="text-[10px] uppercase text-slate-500 font-bold">Words Learned</span>
</div>
<div class="neu-raised rounded-xl p-4 flex flex-col items-center text-center">
<span class="material-symbols-outlined text-primary mb-2">schedule</span>
<span class="text-xl font-bold">45m</span>
<span class="text-[10px] uppercase text-slate-500 font-bold">Minutes Today</span>
</div>
</div>
<!-- Recent Decks -->
<section class="space-y-4">
<div class="flex justify-between items-end">
<h3 class="text-lg font-bold">Recent Decks</h3>
<a class="text-primary text-sm font-semibold" href="#">View All</a>
</div>
<div class="space-y-4">
<!-- Deck Card 1 -->
<div class="neu-raised rounded-2xl p-4 flex items-center gap-4 group cursor-pointer active:shadow-neu-pressed transition-all">
<div class="w-16 h-16 rounded-xl overflow-hidden neu-raised-sm flex-shrink-0">
<img alt="Business" class="w-full h-full object-cover" data-alt="Modern glass skyscraper reflecting the sky" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDhbNS101486XW9oIgD1Cb46C2DtTsG90of1BMK6vdSmFdAe3zhp5oEBUK3_3O-JIAzwsZofmDwWy6sbBIWPp-M82UV5xCkbQJ7ahJ04WZWDO3d5vbLvwfSmjT46nJ1J7tlAKHi6kj9wMv7Q3h8X0k8WCJdnVxnvXF30xqE0oKuS7rM7wwi5VdIwrZP3ippX2qa5RDKFjWX6gOOmMvdDfDGVxHXFTAuI_P84mWRuXIvVmqIW3k0PxQ0ZJ0L6KfoEumWckQP9GOsTPH0"/>
</div>
<div class="flex-1">
<h4 class="font-bold text-slate-800">Business English</h4>
<p class="text-xs text-slate-500 mb-2">42 / 150 words</p>
<div class="w-full h-1.5 rounded-full bg-slate-200 overflow-hidden">
<div class="bg-primary h-full rounded-full" style="width: 28%;"></div>
</div>
</div>
<span class="material-symbols-outlined text-slate-400">chevron_right</span>
</div>
<!-- Deck Card 2 -->
<div class="neu-raised rounded-2xl p-4 flex items-center gap-4 group cursor-pointer active:shadow-neu-pressed transition-all">
<div class="w-16 h-16 rounded-xl overflow-hidden neu-raised-sm flex-shrink-0">
<img alt="Academic" class="w-full h-full object-cover" data-alt="Close up of an open classic book with glasses" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBl4SCtzi8W-UipqHACS5yD5riAwd4aKI90a_DtKHbKW8lq2fNI3buDUr4NWQmKVq6xH7nIEL6uzDtte1UXS-X4z-Y8J-vxYzBT-lmRtdCHzMqL3bkoG0Uusvjw1N4N4FzJqioREIxjyl_3DPYh3ya5Aj5finSslJkrzlsXFm77LiVIvR9y9-lJyo23qy9jCLyWIIUGIdZ4kduAK1EldxoP9Y5bKE2wKMuISgwupSMirw_t42MxK5PLP7Gy6n1Rtr-uDX1Y7Y9uOBFw"/>
</div>
<div class="flex-1">
<h4 class="font-bold text-slate-800">Academic Word List</h4>
<p class="text-xs text-slate-500 mb-2">89 / 100 words</p>
<div class="w-full h-1.5 rounded-full bg-slate-200 overflow-hidden">
<div class="bg-primary h-full rounded-full" style="width: 89%;"></div>
</div>
</div>
<span class="material-symbols-outlined text-slate-400">chevron_right</span>
</div>
</div>
</section>
<!-- CTA Button -->
<div class="pt-4">
<button class="w-full py-4 neu-raised bg-primary text-white font-bold rounded-2xl active:shadow-neu-pressed transition-all flex items-center justify-center gap-2">
<span class="material-symbols-outlined">play_circle</span>
                Start Today's Session
            </button>
</div>
</main>
<!-- Bottom Navigation -->
<nav class="fixed bottom-0 left-0 right-0 bg-background-light p-4 border-t border-slate-200/50">
<div class="max-w-md mx-auto flex items-center justify-between">
<a class="flex flex-col items-center gap-1 group" href="#">
<div class="w-12 h-12 rounded-xl neu-pressed flex items-center justify-center text-primary">
<span class="material-symbols-outlined fill-1">home</span>
</div>
<span class="text-[10px] font-bold text-primary">Home</span>
</a>
<a class="flex flex-col items-center gap-1 group" href="#">
<div class="w-12 h-12 rounded-xl flex items-center justify-center text-slate-400 group-active:shadow-neu-pressed transition-all">
<span class="material-symbols-outlined">library_books</span>
</div>
<span class="text-[10px] font-bold text-slate-400">Library</span>
</a>
<a class="flex flex-col items-center gap-1 group" href="#">
<div class="w-12 h-12 rounded-xl flex items-center justify-center text-slate-400 group-active:shadow-neu-pressed transition-all">
<span class="material-symbols-outlined">search</span>
</div>
<span class="text-[10px] font-bold text-slate-400">Search</span>
</a>
<a class="flex flex-col items-center gap-1 group" href="#">
<div class="w-12 h-12 rounded-xl flex items-center justify-center text-slate-400 group-active:shadow-neu-pressed transition-all">
<span class="material-symbols-outlined">person</span>
</div>
<span class="text-[10px] font-bold text-slate-400">Profile</span>
</a>
</div>
</nav>
</body></html>