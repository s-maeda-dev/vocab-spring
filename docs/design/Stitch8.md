<!DOCTYPE html>
<html lang="ja"><head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>VocabularySpring - 学習統計状況</title>
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300;400;500;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<script id="tailwind-config">
        tailwind.config = {
            darkMode: "class",
            theme: {
                extend: {
                    colors: {
                        "primary": "#a69383", // Muted dusty beige-brown
                        "primary-dark": "#5d4037",
                        "accent": "#9d8475", // Slightly deeper accent
                        "background-light": "#e8e4df", // Creamy dusty base
                        "background-dark": "#3e2723", 
                        "neu-light": "#ffffff", // Pure white highlight
                        "neu-dark": "#c2beb9",  // Muted shadow for cream base
                        "text-main": "#4a403a", // Dark warm grey/brown
                        "text-sub": "#8c817b" // Muted grey-brown
                    },
                    fontFamily: {
                        "display": ["Lexend", "Noto Sans JP", "sans-serif"],
                        "body": ["Noto Sans JP", "sans-serif"]
                    },
                    borderRadius: {"DEFAULT": "0.5rem", "lg": "1rem", "xl": "1.5rem", "2xl": "2rem", "3xl": "2.5rem", "full": "9999px"},
                    boxShadow: {
                        'neu': '8px 8px 16px #c2beb9, -8px -8px 16px #ffffff',
                        'neu-pressed': 'inset 6px 6px 12px #c2beb9, inset -6px -6px 12px #ffffff',
                        'neu-sm': '4px 4px 8px #c2beb9, -4px -4px 8px #ffffff',
                        'neu-float': '12px 12px 24px #c2beb9, -12px -12px 24px #ffffff',
                        'neu-dark': '6px 6px 12px #281815, -6px -6px 12px #5d4037',
                        'neu-dark-pressed': 'inset 4px 4px 8px #281815, inset -4px -4px 8px #5d4037',
                    }
                },
            },
        }
    </script>
<style>
        .neu-text-shadow {
            text-shadow: 1px 1px 1px rgba(255, 255, 255, 0.6);
        }
        .dark .neu-text-shadow {
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
        }
        .no-scrollbar::-webkit-scrollbar {
            display: none;
        }
        .no-scrollbar {
            -ms-overflow-style: none;
            scrollbar-width: none;
        }
    </style>
<style>
        body {
          min-height: max(884px, 100dvh);
        }
    </style>
<style>
    body {
      min-height: max(884px, 100dvh);
    }
  </style>
<style>
    body {
      min-height: max(884px, 100dvh);
    }
  </style>
  </head>
<body class="bg-background-light dark:bg-background-dark text-text-main dark:text-stone-200 font-body min-h-screen flex flex-col antialiased transition-colors duration-300">
<header class="pt-12 pb-2 px-6 flex justify-between items-center sticky top-0 z-20 bg-background-light/95 dark:bg-background-dark/90 backdrop-blur-sm">
<div>
<h1 class="text-3xl font-bold text-text-main dark:text-primary neu-text-shadow tracking-tight">学習統計状況詳細</h1>
<p class="text-xs text-text-sub dark:text-stone-400 font-medium mt-1 ml-1">カテゴリー別分析データ</p>
</div>
<button class="size-12 rounded-2xl bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark flex items-center justify-center text-text-main dark:text-primary active:shadow-neu-pressed dark:active:shadow-neu-dark-pressed transition-all hover:scale-[1.02]">
<span class="material-symbols-outlined">calendar_month</span>
</button>
</header>
<main class="flex-1 overflow-y-auto px-6 pb-28 space-y-8 mt-4">
<div class="flex gap-4 overflow-x-auto no-scrollbar py-2 px-1">
<button class="flex-shrink-0 px-6 py-2.5 rounded-full bg-background-light dark:bg-background-dark shadow-neu-pressed dark:shadow-neu-dark-pressed text-primary-dark dark:text-primary font-bold text-sm">
            ビジネス
        </button>
<button class="flex-shrink-0 px-6 py-2.5 rounded-full bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark text-text-sub dark:text-stone-400 font-medium text-sm active:shadow-neu-pressed dark:active:shadow-neu-dark-pressed transition-all hover:-translate-y-0.5">
            学術
        </button>
<button class="flex-shrink-0 px-6 py-2.5 rounded-full bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark text-text-sub dark:text-stone-400 font-medium text-sm active:shadow-neu-pressed dark:active:shadow-neu-dark-pressed transition-all hover:-translate-y-0.5">
            日常会話
        </button>
<button class="flex-shrink-0 px-6 py-2.5 rounded-full bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark text-text-sub dark:text-stone-400 font-medium text-sm active:shadow-neu-pressed dark:active:shadow-neu-dark-pressed transition-all hover:-translate-y-0.5">
            旅行
        </button>
</div>
<section class="space-y-6">
<div class="flex items-center gap-3 px-1">
<div class="size-10 rounded-xl bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark flex items-center justify-center text-primary-dark">
<span class="material-symbols-outlined">business_center</span>
</div>
<h2 class="text-xl font-bold text-text-main dark:text-stone-100">ビジネス</h2>
</div>
<div class="rounded-3xl bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark p-6 relative overflow-hidden">
<div class="flex flex-col md:flex-row gap-6">
<div class="flex-1 flex flex-col items-center justify-center relative min-h-[140px]">
<div class="relative size-32 rounded-full shadow-neu-pressed dark:shadow-neu-dark-pressed flex items-center justify-center bg-background-light dark:bg-background-dark">
<svg class="size-full -rotate-90 p-2" viewBox="0 0 36 36">
<path class="text-transparent" d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" stroke="currentColor" stroke-width="3"></path>
<path class="text-[#8d6e63] drop-shadow-sm" d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" stroke="currentColor" stroke-dasharray="85, 100" stroke-linecap="round" stroke-width="3"></path>
</svg>
<div class="absolute flex flex-col items-center">
<span class="text-3xl font-display font-bold text-text-main dark:text-stone-200">85<span class="text-sm align-top">%</span></span>
<span class="text-[10px] text-text-sub font-medium">正解率</span>
</div>
</div>
</div>
<div class="flex-1 grid grid-cols-2 gap-4">
<div class="flex flex-col justify-center p-3 rounded-2xl bg-background-light dark:bg-background-dark shadow-neu-sm dark:shadow-neu-dark">
<span class="text-[10px] text-text-sub font-medium mb-1">累積回答数</span>
<span class="text-lg font-bold text-text-main dark:text-stone-200">1,420</span>
</div>
<div class="flex flex-col justify-center p-3 rounded-2xl bg-background-light dark:bg-background-dark shadow-neu-sm dark:shadow-neu-dark">
<span class="text-[10px] text-text-sub font-medium mb-1">登録単語数</span>
<span class="text-lg font-bold text-text-main dark:text-stone-200">350</span>
</div>
<div class="col-span-2 flex items-center justify-between p-3 rounded-2xl bg-background-light dark:bg-background-dark shadow-neu-sm dark:shadow-neu-dark">
<div class="flex flex-col">
<span class="text-[10px] text-text-sub font-medium mb-1">苦手単語数</span>
<span class="text-lg font-bold text-rose-800/70 dark:text-rose-400">12 <span class="text-xs font-normal text-text-sub">Words</span></span>
</div>
<span class="material-symbols-outlined text-rose-800/40 dark:text-rose-400/40">warning</span>
</div>
</div>
</div>
</div>
<div class="space-y-4">
<div class="flex justify-between items-end px-2">
<h3 class="text-sm font-bold text-text-sub dark:text-stone-400">苦手単語リスト (ビジネス)</h3>
<a class="text-xs text-primary-dark dark:text-primary font-medium hover:underline" href="#">すべて表示</a>
</div>
<div class="group rounded-3xl bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark p-4 pl-5 flex items-center justify-between transition-transform active:scale-[0.99]">
<div class="flex items-center gap-4">
<div class="size-10 rounded-full bg-background-light dark:bg-background-dark shadow-neu-pressed dark:shadow-neu-dark-pressed flex items-center justify-center text-xs font-bold text-rose-800/70 dark:text-rose-400">
                    42%
                </div>
<div>
<h3 class="text-base font-bold text-text-main dark:text-stone-100">Leverage</h3>
<p class="text-[11px] text-text-sub dark:text-stone-500">活用する、てこ</p>
</div>
</div>
<button class="size-10 rounded-full bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark flex items-center justify-center text-text-sub dark:text-primary active:shadow-neu-pressed dark:active:shadow-neu-dark-pressed hover:text-text-main transition-colors">
<span class="material-symbols-outlined text-[20px]">refresh</span>
</button>
</div>
<div class="group rounded-3xl bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark p-4 pl-5 flex items-center justify-between transition-transform active:scale-[0.99]">
<div class="flex items-center gap-4">
<div class="size-10 rounded-full bg-background-light dark:bg-background-dark shadow-neu-pressed dark:shadow-neu-dark-pressed flex items-center justify-center text-xs font-bold text-rose-800/70 dark:text-rose-400">
                    55%
                </div>
<div>
<h3 class="text-base font-bold text-text-main dark:text-stone-100">Agenda</h3>
<p class="text-[11px] text-text-sub dark:text-stone-500">協議事項、議題</p>
</div>
</div>
<button class="size-10 rounded-full bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark flex items-center justify-center text-text-sub dark:text-primary active:shadow-neu-pressed dark:active:shadow-neu-dark-pressed hover:text-text-main transition-colors">
<span class="material-symbols-outlined text-[20px]">refresh</span>
</button>
</div>
</div>
</section>
<div class="w-full h-px bg-stone-300/30 dark:bg-stone-700/50 my-2"></div>
<section class="space-y-6">
<div class="flex items-center gap-3 px-1">
<div class="size-10 rounded-xl bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark flex items-center justify-center text-primary-dark">
<span class="material-symbols-outlined">school</span>
</div>
<h2 class="text-xl font-bold text-text-main dark:text-stone-100">学術</h2>
</div>
<div class="rounded-3xl bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark p-6 relative overflow-hidden">
<div class="flex flex-col md:flex-row gap-6">
<div class="flex-1 flex flex-col items-center justify-center relative min-h-[140px]">
<div class="relative size-32 rounded-full shadow-neu-pressed dark:shadow-neu-dark-pressed flex items-center justify-center bg-background-light dark:bg-background-dark">
<svg class="size-full -rotate-90 p-2" viewBox="0 0 36 36">
<path class="text-transparent" d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" stroke="currentColor" stroke-width="3"></path>
<path class="text-[#a1887f] drop-shadow-sm" d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" stroke="currentColor" stroke-dasharray="62, 100" stroke-linecap="round" stroke-width="3"></path>
</svg>
<div class="absolute flex flex-col items-center">
<span class="text-3xl font-display font-bold text-text-main dark:text-stone-200">62<span class="text-sm align-top">%</span></span>
<span class="text-[10px] text-text-sub font-medium">正解率</span>
</div>
</div>
</div>
<div class="flex-1 grid grid-cols-2 gap-4">
<div class="flex flex-col justify-center p-3 rounded-2xl bg-background-light dark:bg-background-dark shadow-neu-sm dark:shadow-neu-dark">
<span class="text-[10px] text-text-sub font-medium mb-1">累積回答数</span>
<span class="text-lg font-bold text-text-main dark:text-stone-200">856</span>
</div>
<div class="flex flex-col justify-center p-3 rounded-2xl bg-background-light dark:bg-background-dark shadow-neu-sm dark:shadow-neu-dark">
<span class="text-[10px] text-text-sub font-medium mb-1">登録単語数</span>
<span class="text-lg font-bold text-text-main dark:text-stone-200">210</span>
</div>
<div class="col-span-2 flex items-center justify-between p-3 rounded-2xl bg-background-light dark:bg-background-dark shadow-neu-sm dark:shadow-neu-dark">
<div class="flex flex-col">
<span class="text-[10px] text-text-sub font-medium mb-1">苦手単語数</span>
<span class="text-lg font-bold text-rose-800/70 dark:text-rose-400">48 <span class="text-xs font-normal text-text-sub">Words</span></span>
</div>
<span class="material-symbols-outlined text-rose-800/40 dark:text-rose-400/40">warning</span>
</div>
</div>
</div>
</div>
<div class="space-y-4">
<div class="flex justify-between items-end px-2">
<h3 class="text-sm font-bold text-text-sub dark:text-stone-400">苦手単語リスト (学術)</h3>
<a class="text-xs text-primary-dark dark:text-primary font-medium hover:underline" href="#">すべて表示</a>
</div>
<div class="group rounded-3xl bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark p-4 pl-5 flex items-center justify-between transition-transform active:scale-[0.99]">
<div class="flex items-center gap-4">
<div class="size-10 rounded-full bg-background-light dark:bg-background-dark shadow-neu-pressed dark:shadow-neu-dark-pressed flex items-center justify-center text-xs font-bold text-rose-800/70 dark:text-rose-400">
                    28%
                </div>
<div>
<h3 class="text-base font-bold text-text-main dark:text-stone-100">Hypothesis</h3>
<p class="text-[11px] text-text-sub dark:text-stone-500">仮説</p>
</div>
</div>
<button class="size-10 rounded-full bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark flex items-center justify-center text-text-sub dark:text-primary active:shadow-neu-pressed dark:active:shadow-neu-dark-pressed hover:text-text-main transition-colors">
<span class="material-symbols-outlined text-[20px]">refresh</span>
</button>
</div>
</div>
</section>
<div class="h-16"></div>
</main>
<div class="fixed bottom-24 right-6 z-30">
<button class="h-16 px-8 rounded-full bg-[#5d4037] text-[#efebe9] shadow-neu-dark flex items-center gap-2 font-bold active:scale-95 transition-transform hover:bg-[#4e342e]">
<span class="material-symbols-outlined">play_arrow</span>
<span class="text-lg">復習を開始</span>
</button>
</div>
<div class="fixed bottom-6 left-6 right-6 z-40">
<nav class="w-full h-20 rounded-3xl bg-background-light/95 dark:bg-background-dark/95 backdrop-blur-xl shadow-neu-float dark:shadow-neu-dark px-2 flex justify-around items-center border border-white/20">
<a class="flex flex-1 flex-col items-center justify-center gap-1 h-full rounded-2xl text-text-sub dark:text-stone-400 hover:text-text-main dark:hover:text-primary transition-colors" href="#">
<span class="material-symbols-outlined text-[26px]">home</span>
<span class="text-[10px] font-bold">ホーム</span>
</a>
<a class="flex flex-1 flex-col items-center justify-center gap-1 h-full rounded-2xl text-text-sub dark:text-stone-400 hover:text-text-main dark:hover:text-primary transition-colors" href="#">
<span class="material-symbols-outlined text-[26px]">search</span>
<span class="text-[10px] font-bold">検索</span>
</a>
<a class="flex flex-1 flex-col items-center justify-center gap-1 h-full rounded-2xl relative -mt-8" href="#">
<div class="size-14 rounded-full bg-background-light dark:bg-background-dark shadow-neu dark:shadow-neu-dark flex items-center justify-center border-4 border-background-light dark:border-background-dark">
<span class="material-symbols-outlined text-[28px] text-text-main dark:text-primary">bar_chart</span>
</div>
<span class="text-[10px] font-bold text-text-main dark:text-primary mt-1">統計</span>
</a>
<a class="flex flex-1 flex-col items-center justify-center gap-1 h-full rounded-2xl text-text-sub dark:text-stone-400 hover:text-text-main dark:hover:text-primary transition-colors" href="#">
<span class="material-symbols-outlined text-[26px]">quiz</span>
<span class="text-[10px] font-bold">クイズ</span>
</a>
<a class="flex flex-1 flex-col items-center justify-center gap-1 h-full rounded-2xl text-text-sub dark:text-stone-400 hover:text-text-main dark:hover:text-primary transition-colors" href="#">
<span class="material-symbols-outlined text-[26px]">person</span>
<span class="text-[10px] font-bold">マイページ</span>
</a>
</nav>
</div>
</body></html>