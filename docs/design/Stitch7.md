<!DOCTYPE html>
<html lang="ja"><head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>クイズ結果サマリー - VocabularySpring</title>
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;500;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Zen+Kaku+Gothic+New:wght@400;500;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<script id="tailwind-config">
    tailwind.config = {
        darkMode: "class",
        theme: {
            extend: {
                colors: {
                    "primary": "#a1887f", 
                    "primary-dark": "#8d6e63",
                    "accent": "#d7ccc8",
                    "background-base": "#f5f4f0", 
                    "surface-light": "#fbfaf9", 
                    "text-main": "#4e342e", 
                    "text-sub": "#8d817b", 
                    "success": "#4caf50", 
                    "error": "#ef5350", 
                    "bronze": "#a1887f",
                },
                fontFamily: {
                    "display": ["Lexend", "sans-serif"],
                    "japanese": ["Zen Kaku Gothic New", "Noto Sans JP", "sans-serif"],
                },
                borderRadius: {"DEFAULT": "0.5rem", "lg": "1rem", "xl": "1.5rem", "2xl": "2rem", "3xl": "2.5rem", "full": "9999px"},
                boxShadow: {
                    'soft-neumorphic': '6px 6px 12px #dcdbd8, -6px -6px 12px #ffffff',
                    'soft-neumorphic-sm': '4px 4px 8px #dcdbd8, -4px -4px 8px #ffffff',
                    'soft-neumorphic-btn': '5px 5px 10px #dcdbd8, -5px -5px 10px #ffffff',
                    'soft-pressed': 'inset 4px 4px 8px #dcdbd8, inset -4px -4px 8px #ffffff',
                    'float': '0 10px 30px -10px rgba(78, 52, 46, 0.15)',
                    'inner-card': 'inset 2px 2px 5px #dcdbd8, inset -2px -2px 5px #ffffff',
                }
            },
        },
    }
</script>
<style>
    body {
        background-color: #f5f4f0;
        color: #4e342e;
        min-height: max(884px, 100dvh);
    }
    .neu-card {
        background-color: #f5f4f0;
        border-radius: 1.5rem;
        box-shadow: 8px 8px 16px #dcdbd8, -8px -8px 16px #ffffff;
        border: 1px solid rgba(255,255,255,0.6);
    }
    .neu-inset {
        background-color: #f0efeb;
        border-radius: 1.5rem;
        box-shadow: inset 5px 5px 10px #dcdbd8, inset -5px -5px 10px #ffffff;
    }
    .neu-btn-round {
        background-color: #f5f4f0;
        border-radius: 9999px;
        box-shadow: 5px 5px 10px #dcdbd8, -5px -5px 10px #ffffff;
        transition: all 0.2s ease;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .neu-btn-round:active {
        box-shadow: inset 3px 3px 6px #dcdbd8, inset -3px -3px 6px #ffffff;
        transform: scale(0.95);
    }
    .neu-badge-ring {
        background: linear-gradient(145deg, #ffffff, #e6e5e1);
        box-shadow: 7px 7px 14px #d1d0cd, -7px -7px 14px #ffffff;
        border-radius: 9999px;
    }
    .text-shadow-sm {
        text-shadow: 1px 1px 2px rgba(255,255,255,0.8);
    }.progress-ring__circle {
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
<style>
    body {
      min-height: max(884px, 100dvh);
    }
  </style>
  </head>
<body class="font-japanese text-text-main antialiased selection:bg-primary/20">
<div class="relative mx-auto flex h-full min-h-screen w-full max-w-md flex-col overflow-hidden pb-24">
<header class="sticky top-0 z-20 flex items-center justify-between px-6 py-5 bg-background-base/90 backdrop-blur-md">
<button class="neu-btn-round size-10 text-text-main/70">
<span class="material-symbols-outlined text-xl">close</span>
</button>
<h1 class="text-lg font-bold tracking-tight text-text-main/90">学習サマリー</h1>
<div class="size-10"></div>
</header>
<main class="flex flex-col items-center px-6 pt-2 pb-8 gap-8">
<div class="flex flex-col items-center text-center w-full mt-4">
<div class="relative mb-6">
<div class="neu-badge-ring p-6 size-28 flex items-center justify-center relative z-10">
<span class="material-symbols-outlined text-5xl text-[#a1887f]" style="font-variation-settings: 'FILL' 1, 'wght' 400, 'GRAD' 0, 'opsz' 48;">military_tech</span>
</div>
<div class="absolute -top-1 -right-1 size-9 rounded-full bg-[#a1887f] text-white flex items-center justify-center shadow-md z-20 border-2 border-[#f5f4f0]">
<span class="material-symbols-outlined text-sm">star</span>
</div>
</div>
<h2 class="text-2xl font-bold text-text-main mb-3 tracking-wide">お疲れ様でした！</h2>
<div class="flex flex-col items-center max-w-[90%] gap-1">
<p class="text-sm font-medium italic text-text-sub/90 font-display leading-relaxed">
                        "天才とは、1％のひらめきと99％の努力である"
                    </p>
<p class="text-xs font-bold text-[#a1887f] mt-1">— トーマス・エジソン</p>
</div>
</div>
<div class="neu-card w-full p-6 flex flex-col gap-6">
<div class="flex items-center justify-between">
<div class="flex flex-col">
<span class="text-xs font-bold text-text-main/60 mb-0.5">正解率</span>
<div class="flex items-baseline gap-1">
<span class="text-4xl font-bold text-text-main font-display">8</span>
<span class="text-xl font-medium text-text-sub font-display">/10</span>
</div>
</div>
<div class="relative size-16">
<div class="absolute inset-0 rounded-full shadow-[inset_2px_2px_5px_#dcdbd8,inset_-2px_-2px_5px_#ffffff] bg-[#f0efeb]"></div>
<svg class="relative size-full -rotate-90" viewBox="0 0 36 36">
<path class="text-transparent" d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" stroke="currentColor" stroke-width="3"></path>
<path class="text-[#a1887f] drop-shadow-sm" d="M18 2.0845 a 15.9155 15.9155 0 0 1 0 31.831 a 15.9155 15.9155 0 0 1 0 -31.831" fill="none" stroke="currentColor" stroke-dasharray="80, 100" stroke-linecap="round" stroke-width="3"></path>
</svg>
<div class="absolute inset-0 flex items-center justify-center">
<span class="text-xs font-bold text-text-main/70">80%</span>
</div>
</div>
</div>
<div class="h-2 w-full rounded-full bg-gray-200 shadow-inner overflow-hidden">
<div class="h-full rounded-full bg-[#a1887f]" style="width: 80%"></div>
</div>
<hr class="border-t border-black/5 mx-2"/>
<div class="flex flex-col gap-3">
<div class="flex items-center justify-between py-1 px-1">
<div class="flex items-center gap-3">
<span class="material-symbols-outlined text-success text-lg bg-success/10 rounded-full p-0.5">check_circle</span>
<span class="text-sm font-medium text-text-main font-display">Architecture</span>
</div>
<span class="text-xs font-bold text-text-sub/50">建築</span>
</div>
<div class="flex items-center justify-between py-1 px-1">
<div class="flex items-center gap-3">
<span class="material-symbols-outlined text-success text-lg bg-success/10 rounded-full p-0.5">check_circle</span>
<span class="text-sm font-medium text-text-main font-display">Inspiration</span>
</div>
<span class="text-xs font-bold text-text-sub/50">ひらめき</span>
</div>
<div class="flex items-center justify-between py-1 px-1">
<div class="flex items-center gap-3">
<span class="material-symbols-outlined text-error text-lg bg-error/10 rounded-full p-0.5">cancel</span>
<span class="text-sm font-medium text-text-main font-display">Perspiration</span>
</div>
<span class="text-xs font-bold text-text-sub/50">努力</span>
</div>
</div>
</div>
<div class="w-full flex flex-col gap-3">
<div class="flex items-center gap-2 pl-1">
<div class="size-8 rounded-full bg-[#d7ccc8] flex items-center justify-center shadow-sm text-white">
<span class="material-symbols-outlined text-lg">smart_toy</span>
</div>
<h3 class="font-bold text-text-main text-sm">AIからの応援メッセージ</h3>
</div>
<div class="neu-inset p-5 relative">
<p class="text-sm text-text-main leading-relaxed tracking-wide">
                        素晴らしいですね！ <span class="font-bold text-[#8d6e63]">"Architecture"</span> の理解が深まっています。次は実際の設計事例を調べるとより定着しますよ。
                    </p>
</div>
</div>
</main>
<div class="fixed bottom-0 left-0 right-0 z-30 flex items-center justify-center p-6 bg-gradient-to-t from-[#f5f4f0] via-[#f5f4f0] to-transparent pt-12">
<div class="flex w-full max-w-md gap-4">
<button class="neu-btn-round flex-1 py-4 text-sm font-bold text-text-main/70 hover:text-text-main active:scale-[0.98]">
<span class="material-symbols-outlined text-lg mr-2">refresh</span>
                    再挑戦
                </button>
<button class="flex-1 rounded-full bg-[#a1887f] py-4 text-sm font-bold text-white shadow-[4px_4px_10px_rgba(161,136,127,0.4),-4px_-4px_10px_rgba(255,255,255,0.8)] active:shadow-inner active:translate-y-0.5 transition-all flex items-center justify-center gap-2 hover:bg-[#8d6e63]">
<span class="material-symbols-outlined text-lg">home</span>
                    ホーム
                </button>
</div>
</div>
</div>
</body></html>