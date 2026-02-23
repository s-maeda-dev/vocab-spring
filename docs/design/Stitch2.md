<html lang="ja"><head><style>
    body {
      min-height: max(884px, 100dvh);
    }
  </style>
  </head><body class="font-sans text-text-main antialiased selection:bg-primary selection:text-white">```html


<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>ホームダッシュボード</title>
<link href="https://fonts.googleapis.com" rel="preconnect"/>
<link crossorigin="" href="https://fonts.gstatic.com" rel="preconnect"/>
<link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;600;700&amp;family=Noto+Sans+JP:wght@300;400;500;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<script id="tailwind-config">
        tailwind.config = {
            darkMode: "class",
            theme: {
                extend: {
                    colors: {
                        "primary": "#a18d7f", // Muted dusty brown
                        "primary-dark": "#8a7566",
                        "primary-light": "#d7ccc8",
                        "background-light": "#f5f2f0", // Warm grey/beige
                        "surface": "#f5f2f0",
                        "text-main": "#4a403a", // Softer dark brown
                        "text-muted": "#9d948e",
                    },
                    fontFamily: {
                        "display": ["Lexend", "Noto Sans JP", "sans-serif"],
                        "sans": ["Noto Sans JP", "sans-serif"]
                    },
                    borderRadius: {
                        "DEFAULT": "0.5rem",
                        "lg": "1rem",
                        "xl": "1.5rem",
                        "2xl": "2rem",
                        "3xl": "2.5rem",
                        "full": "9999px"
                    },
                    boxShadow: {
                        'neu-flat': '8px 8px 16px #dcd9d6, -8px -8px 16px #ffffff',
                        'neu-pressed': 'inset 4px 4px 8px #dcd9d6, inset -4px -4px 8px #ffffff',
                        'neu-floating': '12px 12px 24px #d1ccc9, -12px -12px 24px #ffffff',
                        'neu-btn': '6px 6px 12px #dcd9d6, -6px -6px 12px #ffffff',
                        'nav-shadow': '0 -4px 20px rgba(0,0,0,0.03)',
                    }
                },
            },
        }
    </script>
<style type="text/tailwindcss">
    @layer utilities {
        .neu-flat {
            @apply bg-background-light shadow-neu-flat border border-white/60;
        }
        .neu-pressed {
            @apply bg-background-light shadow-neu-pressed border-none;
        }
        .neu-icon-btn {
            @apply flex items-center justify-center rounded-full transition-all duration-300 active:shadow-neu-pressed active:scale-95 bg-background-light shadow-neu-flat border border-white/60;
        }
        .nav-item-active {
            @apply shadow-neu-pressed text-primary;
        }.no-scrollbar::-webkit-scrollbar {
            display: none;
        }
        .no-scrollbar {
            -ms-overflow-style: none;
            scrollbar-width: none;
        }
    }
    body {
        background-color: #f5f2f0;
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


<div class="relative flex min-h-screen w-full flex-col overflow-x-hidden bg-background-light">
<header class="flex items-center justify-between px-6 pt-12 pb-6">
<div class="flex items-center gap-4">
<div class="flex h-14 w-14 items-center justify-center rounded-full neu-flat text-primary">
<span class="material-symbols-outlined text-[28px]">spa</span>
</div>
<div>
<p class="text-[11px] font-bold tracking-wider text-text-muted uppercase mb-1">WELCOME,</p>
<h1 class="text-2xl font-bold tracking-tight text-text-main font-display">Alexさん</h1>
</div>
</div>
<button aria-label="ログアウト" class="neu-icon-btn h-12 w-12 text-text-muted hover:text-primary transition-colors">
<span class="material-symbols-outlined text-[22px]">logout</span>
</button>
</header>
<main class="flex flex-1 flex-col px-6 pb-32">
<div class="mb-8">
<div class="flex items-center justify-between mb-4 px-1">
<h2 class="text-lg font-bold text-text-main">カテゴリー</h2>
</div>
<div class="grid grid-cols-2 gap-4">
<button class="neu-flat rounded-[1.5rem] p-5 flex flex-col items-center justify-center gap-3 transition-transform active:scale-95 group hover:shadow-neu-floating">
<div class="h-12 w-12 rounded-full neu-pressed flex items-center justify-center text-primary mb-1">
<span class="material-symbols-outlined text-2xl">work</span>
</div>
<div class="text-center">
<div class="text-sm font-bold text-text-main">Business</div>
<div class="text-xs font-bold text-text-muted mt-1">315 語</div>
</div>
</button>
<button class="neu-flat rounded-[1.5rem] p-5 flex flex-col items-center justify-center gap-3 transition-transform active:scale-95 group hover:shadow-neu-floating">
<div class="h-12 w-12 rounded-full neu-pressed flex items-center justify-center text-primary mb-1">
<span class="material-symbols-outlined text-2xl">computer</span>
</div>
<div class="text-center">
<div class="text-sm font-bold text-text-main">IT</div>
<div class="text-xs font-bold text-text-muted mt-1">210 語</div>
</div>
</button>
<button class="neu-flat rounded-[1.5rem] p-5 flex flex-col items-center justify-center gap-3 transition-transform active:scale-95 group hover:shadow-neu-floating">
<div class="h-12 w-12 rounded-full neu-pressed flex items-center justify-center text-primary mb-1">
<span class="material-symbols-outlined text-2xl">verified</span>
</div>
<div class="text-center">
<div class="text-sm font-bold text-text-main">Qualification</div>
<div class="text-xs font-bold text-text-muted mt-1">150 語</div>
</div>
</button>
<button class="neu-flat rounded-[1.5rem] p-5 flex flex-col items-center justify-center gap-3 transition-transform active:scale-95 group hover:shadow-neu-floating">
<div class="h-12 w-12 rounded-full neu-pressed flex items-center justify-center text-primary mb-1">
<span class="material-symbols-outlined text-2xl">restaurant</span>
</div>
<div class="text-center">
<div class="text-sm font-bold text-text-main">Daily</div>
<div class="text-xs font-bold text-text-muted mt-1">317 語</div>
</div>
</button>
</div>
</div>
<div class="mb-8">
<a class="block neu-flat rounded-[2rem] p-6 group transition-all hover:bg-white/40 active:scale-[0.98]" href="#">
<div class="flex items-center justify-between">
<div class="flex items-center gap-4">
<div class="h-14 w-14 rounded-2xl neu-pressed flex items-center justify-center text-primary bg-primary/5">
<span class="material-symbols-outlined text-3xl">analytics</span>
</div>
<div class="flex flex-col">
<span class="text-lg font-bold text-text-main">学習統計</span>
<span class="text-xs text-text-muted font-medium mt-1">進捗状況を確認する</span>
</div>
</div>
<div class="h-10 w-10 rounded-full neu-flat flex items-center justify-center text-primary group-hover:text-primary-dark transition-colors">
<span class="material-symbols-outlined">arrow_forward</span>
</div>
</div>
</a>
</div>
<button class="group flex w-full items-center justify-center gap-3 rounded-full py-4 transition-all active:scale-[0.98] bg-primary hover:bg-primary-dark shadow-neu-btn text-white mt-auto">
<span class="material-symbols-outlined text-xl filled">play_circle</span>
<span class="font-bold tracking-wide text-lg">本日の学習を開始</span>
</button>
</main>
<nav class="fixed bottom-0 left-0 right-0 z-50 bg-background-light/90 backdrop-blur-md pb-8 pt-4 border-t border-white/50">
<div class="flex items-center justify-around px-4">
<a class="flex flex-col items-center gap-1 w-16" href="#">
<div class="h-12 w-12 flex items-center justify-center rounded-2xl bg-background-light shadow-neu-pressed text-primary transition-all duration-300">
<span class="material-symbols-outlined fill-1">home</span>
</div>
<span class="text-[10px] font-bold text-text-main mt-1">ホーム</span>
</a>
<a class="flex flex-col items-center gap-1 w-16 group" href="#">
<div class="h-12 w-12 flex items-center justify-center rounded-2xl text-text-muted hover:text-primary transition-all duration-300 hover:shadow-neu-flat">
<span class="material-symbols-outlined">menu_book</span>
</div>
<span class="text-[10px] font-bold text-text-muted group-hover:text-primary transition-colors mt-1">単語帳</span>
</a>
<a class="flex flex-col items-center gap-1 w-16 group" href="#">
<div class="h-12 w-12 flex items-center justify-center rounded-2xl text-text-muted hover:text-primary transition-all duration-300 hover:shadow-neu-flat">
<span class="material-symbols-outlined">add_circle</span>
</div>
<span class="text-[10px] font-bold text-text-muted group-hover:text-primary transition-colors mt-1">単語追加</span>
</a>
<a class="flex flex-col items-center gap-1 w-16 group" href="#">
<div class="h-12 w-12 flex items-center justify-center rounded-2xl text-text-muted hover:text-primary transition-all duration-300 hover:shadow-neu-flat">
<span class="material-symbols-outlined">help</span>
</div>
<span class="text-[10px] font-bold text-text-muted group-hover:text-primary transition-colors mt-1">クイズ</span>
</a>
<a class="flex flex-col items-center gap-1 w-16 group" href="#">
<div class="h-12 w-12 flex items-center justify-center rounded-2xl text-text-muted hover:text-primary transition-all duration-300 hover:shadow-neu-flat">
<span class="material-symbols-outlined">bar_chart</span>
</div>
<span class="text-[10px] font-bold text-text-muted group-hover:text-primary transition-colors mt-1">統計状況</span>
</a>
</div>
</nav>
</div>
</body></html>