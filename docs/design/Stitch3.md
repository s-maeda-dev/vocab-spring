<!DOCTYPE html>
<html lang="ja"><head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>VocabularySpring - 単語帳一覧</title>
<link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;600;700&amp;family=Noto+Sans+JP:wght@300;400;500;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<script>
    tailwind.config = {
        darkMode: "class",
        theme: {
            extend: {
                colors: {
                    "primary": "#A68B77", 
                    "primary-dark": "#8a7362",
                    "background-light": "#F5F3F0", 
                    "background-dark": "#2d2a26",
                    "surface-light": "#F5F3F0", 
                    "surface-dark": "#2d2a26",
                    "text-main": "#4A4A4A", 
                    "text-muted": "#9CA3AF", 
                },
                fontFamily: {
                    "display": ["Lexend", "Noto Sans JP", "sans-serif"],
                    "body": ["Noto Sans JP", "sans-serif"]
                },
                boxShadow: {
                    'neu-flat': '5px 5px 10px #d1cfcc, -5px -5px 10px #ffffff',
                    'neu-flat-soft': '4px 4px 8px #d6d4d1, -4px -4px 8px #ffffff', 
                    'neu-pressed': 'inset 3px 3px 6px #d1cfcc, inset -3px -3px 6px #ffffff',
                    'neu-pressed-deep': 'inset 5px 5px 10px #d1cfcc, inset -5px -5px 10px #ffffff',
                    'neu-btn': '4px 4px 8px #d1cfcc, -4px -4px 8px #ffffff',
                    'neu-flat-dark': '6px 6px 12px #1e1c19, -6px -6px 12px #3c3833',
                    'neu-pressed-dark': 'inset 4px 4px 8px #1e1c19, inset -4px -4px 8px #3c3833',
                },
                borderRadius: {
                    'xl': '1rem',
                    '2xl': '1.25rem',
                    '3xl': '1.75rem',
                }
            },
        },
    }
</script>
<style>
    .hide-scrollbar::-webkit-scrollbar {
        display: none;
    }
    .hide-scrollbar {
        -ms-overflow-style: none;
        scrollbar-width: none;
    }
    body {
        min-height: max(884px, 100dvh);
        background-color: #F5F3F0;
    }
</style>
<style>
    body {
      min-height: max(884px, 100dvh);
    }
  </style>
  </head>
<body class="bg-background-light dark:bg-background-dark font-body text-text-main dark:text-gray-100 antialiased overflow-hidden">
<div class="relative flex h-screen w-full flex-col max-w-md mx-auto bg-background-light dark:bg-background-dark overflow-hidden shadow-2xl">
<header class="flex items-start justify-between px-6 pt-12 pb-4 z-10 bg-background-light dark:bg-background-dark">
<div class="flex flex-col">
<h1 class="text-2xl font-bold tracking-tight text-text-main dark:text-white font-display">単語帳</h1>
<p class="text-xs text-text-muted mt-1 font-medium">全 1,240 単語</p>
</div>
<div class="flex items-center gap-3">
<button class="flex items-center justify-center w-10 h-10 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-btn dark:shadow-neu-flat-dark text-text-main active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all duration-200 active:scale-95">
<span class="material-symbols-outlined font-medium text-[20px]">tune</span>
</button>
<button class="flex items-center justify-center w-10 h-10 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-btn dark:shadow-neu-flat-dark text-text-main active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all duration-200 active:scale-95">
<span class="material-symbols-outlined font-bold text-[20px]">add</span>
</button>
</div>
</header>
<div class="px-6 py-2">
<div class="flex items-center w-full h-12 rounded-xl bg-surface-light dark:bg-surface-dark shadow-neu-pressed dark:shadow-neu-pressed-dark px-4 transition-all">
<span class="material-symbols-outlined text-text-muted mr-3 text-[20px]">search</span>
<input class="w-full bg-transparent border-none focus:ring-0 text-text-main dark:text-gray-100 placeholder-text-muted text-sm font-medium tracking-wide" placeholder="単語を検索..." type="text"/>
</div>
</div>
<div class="px-6 py-4">
<div class="flex space-x-3 overflow-x-auto hide-scrollbar pb-2 pt-1 px-1">
<button class="flex-shrink-0 px-5 py-2 rounded-full bg-primary text-white shadow-neu-flat-soft dark:shadow-neu-flat-dark font-bold text-xs border border-transparent transition-transform active:scale-95">
                すべて
            </button>
<button class="flex-shrink-0 px-5 py-2 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark text-text-muted font-medium text-xs active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all border border-transparent hover:text-text-main">
                ビジネス
            </button>
<button class="flex-shrink-0 px-5 py-2 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark text-text-muted font-medium text-xs active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all border border-transparent hover:text-text-main">
                IT用語
            </button>
<button class="flex-shrink-0 px-5 py-2 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark text-text-muted font-medium text-xs active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all border border-transparent hover:text-text-main">
                資格試験
            </button>
</div>
</div>
<div class="flex-1 overflow-y-auto px-6 pb-32 hide-scrollbar space-y-4 pt-2">
<div class="group flex items-start justify-between p-5 rounded-2xl bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all cursor-pointer border border-transparent hover:scale-[1.01]">
<div class="flex flex-col w-full">
<div class="flex items-baseline gap-3 w-full mb-1">
<h3 class="text-text-main dark:text-white text-base font-bold font-display tracking-tight group-hover:text-primary transition-colors">Paradigm</h3>
<span class="text-text-muted text-xs font-medium">パラダイム</span>
</div>
<p class="text-[11px] text-text-muted/80 leading-snug w-[90%] truncate">The discovery of DNA created a new scientific paradigm.</p>
</div>
<span class="material-symbols-outlined text-text-muted/30 text-[18px] mt-1 group-hover:text-primary transition-colors">chevron_right</span>
</div>
<div class="group flex items-start justify-between p-5 rounded-2xl bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all cursor-pointer border border-transparent hover:scale-[1.01]">
<div class="flex flex-col w-full">
<div class="flex items-baseline gap-3 w-full mb-1">
<h3 class="text-text-main dark:text-white text-base font-bold font-display tracking-tight group-hover:text-primary transition-colors">Implement</h3>
<span class="text-text-muted text-xs font-medium">実行する</span>
</div>
<p class="text-[11px] text-text-muted/80 leading-snug w-[90%] truncate">We need to implement the new security protocols.</p>
</div>
<span class="material-symbols-outlined text-text-muted/30 text-[18px] mt-1 group-hover:text-primary transition-colors">chevron_right</span>
</div>
<div class="group flex items-start justify-between p-5 rounded-2xl bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all cursor-pointer border border-transparent hover:scale-[1.01]">
<div class="flex flex-col w-full">
<div class="flex items-baseline gap-3 w-full mb-1">
<h3 class="text-text-main dark:text-white text-base font-bold font-display tracking-tight group-hover:text-primary transition-colors">Revenue</h3>
<span class="text-text-muted text-xs font-medium">収益</span>
</div>
<p class="text-[11px] text-text-muted/80 leading-snug w-[90%] truncate">The company's annual revenue exceeded expectations.</p>
</div>
<span class="material-symbols-outlined text-text-muted/30 text-[18px] mt-1 group-hover:text-primary transition-colors">chevron_right</span>
</div>
<div class="group flex items-start justify-between p-5 rounded-2xl bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all cursor-pointer border border-transparent hover:scale-[1.01]">
<div class="flex flex-col w-full">
<div class="flex items-baseline gap-3 w-full mb-1">
<h3 class="text-text-main dark:text-white text-base font-bold font-display tracking-tight group-hover:text-primary transition-colors">Algorithm</h3>
<span class="text-text-muted text-xs font-medium">アルゴリズム</span>
</div>
<p class="text-[11px] text-text-muted/80 leading-snug w-[90%] truncate">The search algorithm prioritizes relevant content.</p>
</div>
<span class="material-symbols-outlined text-text-muted/30 text-[18px] mt-1 group-hover:text-primary transition-colors">chevron_right</span>
</div>
<div class="group flex items-start justify-between p-5 rounded-2xl bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all cursor-pointer border border-transparent hover:scale-[1.01]">
<div class="flex flex-col w-full">
<div class="flex items-baseline gap-3 w-full mb-1">
<h3 class="text-text-main dark:text-white text-base font-bold font-display tracking-tight group-hover:text-primary transition-colors">Certification</h3>
<span class="text-text-muted text-xs font-medium">証明・資格</span>
</div>
<p class="text-[11px] text-text-muted/80 leading-snug w-[90%] truncate">She received her teaching certification last month.</p>
</div>
<span class="material-symbols-outlined text-text-muted/30 text-[18px] mt-1 group-hover:text-primary transition-colors">chevron_right</span>
</div>
<div class="group flex items-start justify-between p-5 rounded-2xl bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all cursor-pointer border border-transparent hover:scale-[1.01]">
<div class="flex flex-col w-full">
<div class="flex items-baseline gap-3 w-full mb-1">
<h3 class="text-text-main dark:text-white text-base font-bold font-display tracking-tight group-hover:text-primary transition-colors">Strategy</h3>
<span class="text-text-muted text-xs font-medium">戦略</span>
</div>
<p class="text-[11px] text-text-muted/80 leading-snug w-[90%] truncate">Our marketing strategy needs to be updated for 2024.</p>
</div>
<span class="material-symbols-outlined text-text-muted/30 text-[18px] mt-1 group-hover:text-primary transition-colors">chevron_right</span>
</div>
<div class="group flex items-start justify-between p-5 rounded-2xl bg-surface-light dark:bg-surface-dark shadow-neu-flat-soft dark:shadow-neu-flat-dark active:shadow-neu-pressed dark:active:shadow-neu-pressed-dark transition-all cursor-pointer border border-transparent hover:scale-[1.01]">
<div class="flex flex-col w-full">
<div class="flex items-baseline gap-3 w-full mb-1">
<h3 class="text-text-main dark:text-white text-base font-bold font-display tracking-tight group-hover:text-primary transition-colors">Innovation</h3>
<span class="text-text-muted text-xs font-medium">革新</span>
</div>
<p class="text-[11px] text-text-muted/80 leading-snug w-[90%] truncate">Innovation is key to our future success.</p>
</div>
<span class="material-symbols-outlined text-text-muted/30 text-[18px] mt-1 group-hover:text-primary transition-colors">chevron_right</span>
</div>
</div>
<div class="absolute bottom-0 left-0 w-full bg-background-light/90 backdrop-blur-md dark:bg-background-dark/90 px-4 pb-8 pt-4 z-20 shadow-[0_-4px_16px_rgba(0,0,0,0.05)]">
<div class="flex justify-between items-center w-full px-8">
<button class="flex flex-col items-center gap-1 group w-12 h-12 justify-center rounded-xl active:bg-black/5 transition-all">
<span class="material-symbols-outlined text-[28px] text-text-muted group-hover:text-primary transition-colors">home</span>
</button>
<button class="flex flex-col items-center gap-1 group w-12 h-12 justify-center rounded-xl bg-surface-light dark:bg-surface-dark shadow-neu-pressed dark:shadow-neu-pressed-dark relative">
<span class="material-symbols-outlined text-[26px] text-primary fill-current">menu_book</span>
</button>
<button class="flex flex-col items-center gap-1 group w-12 h-12 justify-center rounded-xl active:bg-black/5 transition-all">
<span class="material-symbols-outlined text-[28px] text-text-muted group-hover:text-primary transition-colors">search</span>
</button>
<button class="flex flex-col items-center gap-1 group w-12 h-12 justify-center rounded-xl active:bg-black/5 transition-all">
<span class="material-symbols-outlined text-[28px] text-text-muted group-hover:text-primary transition-colors">person</span>
</button>
</div>
</div>
</div>

</body></html>