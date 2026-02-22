<!DOCTYPE html>
<html lang="ja"><head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>VocabularySpring - クイズ設定</title>
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
                        "primary": "#A5907E", // Muted dusty brown from reference
                        "primary-dark": "#8C7A6B",
                        "background-light": "#F5F4F0", // Warm off-white background
                        "background-dark": "#2d241c",
                        "surface-light": "#F5F4F0", // Same as background for neumorphism
                        "surface-dark": "#2d241c",
                        "text-main": "#4a3b32",
                        "text-light": "#9CA3AF",
                        "accent": "#CD8D7A", // Slightly warmer accent if needed
                    },
                    fontFamily: {
                        "display": ["Lexend", "Noto Sans JP", "sans-serif"],
                        "jp": ["Noto Sans JP", "sans-serif"]
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
                        'neu-flat': '8px 8px 16px #D1D1CE, -8px -8px 16px #FFFFFF', // Softer, warmer shadows
                        'neu-pressed': 'inset 6px 6px 12px #D1D1CE, inset -6px -6px 12px #FFFFFF',
                        'neu-button': '5px 5px 10px #8C7A6B, -5px -5px 10px #BEA691', // Shadow for primary button
                        'neu-float': '10px 10px 20px #D1D1CE, -10px -10px 20px #FFFFFF',
                        'dark-flat': '6px 6px 12px #1f1913, -6px -6px 12px #3b2f25',
                        'dark-pressed': 'inset 4px 4px 8px #1f1913, inset -4px -4px 8px #3b2f25',
                    }
                },
            },
        }
    </script>
<style>
        .scrollbar-hide::-webkit-scrollbar {
            display: none;
        }.scrollbar-hide {
            -ms-overflow-style: none;scrollbar-width: none;}
        body {
            min-height: max(844px, 100dvh);
        }.category-scroll::-webkit-scrollbar {
            display: none;
        }
        .category-scroll {
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
<body class="bg-background-light dark:bg-background-dark font-display min-h-screen flex flex-col items-center justify-center text-text-main dark:text-slate-100 antialiased selection:bg-primary/30">
<div class="relative flex h-full min-h-screen w-full max-w-md flex-col overflow-hidden bg-background-light dark:bg-background-dark shadow-2xl">
<header class="flex items-center justify-between px-6 py-6 pt-14 z-10">
<button class="flex h-12 w-12 items-center justify-center rounded-2xl bg-surface-light dark:bg-surface-dark text-text-light shadow-neu-flat dark:shadow-dark-flat active:shadow-neu-pressed dark:active:shadow-dark-pressed transition-all duration-200">
<span class="material-symbols-outlined">arrow_back</span>
</button>
<h1 class="text-lg font-bold text-text-main dark:text-slate-100 tracking-wider font-jp">クイズ設定</h1>
<div class="w-12"></div>
</header>
<main class="flex-1 overflow-y-auto px-6 pb-40 pt-6 scrollbar-hide">
<section class="mb-10">
<h2 class="mb-5 text-base font-bold text-text-main dark:text-slate-100 font-jp pl-1">カテゴリー</h2>
<div class="flex gap-3 overflow-x-auto category-scroll pb-4 -mx-6 px-6">
<label class="cursor-pointer flex-shrink-0">
<input checked="" class="peer sr-only" name="category" type="radio" value="all"/>
<div class="px-5 py-2.5 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-flat dark:shadow-dark-flat text-sm font-bold text-text-light peer-checked:text-primary peer-checked:shadow-neu-pressed dark:peer-checked:shadow-dark-pressed transition-all duration-200 border-2 border-transparent peer-checked:border-primary/5">
                すべて
            </div>
</label>
<label class="cursor-pointer flex-shrink-0">
<input class="peer sr-only" name="category" type="radio" value="business"/>
<div class="px-5 py-2.5 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-flat dark:shadow-dark-flat text-sm font-bold text-text-light peer-checked:text-primary peer-checked:shadow-neu-pressed dark:peer-checked:shadow-dark-pressed transition-all duration-200 border-2 border-transparent peer-checked:border-primary/5">
                ビジネス
            </div>
</label>
<label class="cursor-pointer flex-shrink-0">
<input class="peer sr-only" name="category" type="radio" value="it"/>
<div class="px-5 py-2.5 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-flat dark:shadow-dark-flat text-sm font-bold text-text-light peer-checked:text-primary peer-checked:shadow-neu-pressed dark:peer-checked:shadow-dark-pressed transition-all duration-200 border-2 border-transparent peer-checked:border-primary/5">
                IT用語
            </div>
</label>
<label class="cursor-pointer flex-shrink-0">
<input class="peer sr-only" name="category" type="radio" value="certification"/>
<div class="px-5 py-2.5 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-flat dark:shadow-dark-flat text-sm font-bold text-text-light peer-checked:text-primary peer-checked:shadow-neu-pressed dark:peer-checked:shadow-dark-pressed transition-all duration-200 border-2 border-transparent peer-checked:border-primary/5">
                資格試験
            </div>
</label>
<label class="cursor-pointer flex-shrink-0">
<input class="peer sr-only" name="category" type="radio" value="daily"/>
<div class="px-5 py-2.5 rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-flat dark:shadow-dark-flat text-sm font-bold text-text-light peer-checked:text-primary peer-checked:shadow-neu-pressed dark:peer-checked:shadow-dark-pressed transition-all duration-200 border-2 border-transparent peer-checked:border-primary/5">
                日常会話
            </div>
</label>
</div>
</section>
<section class="mb-12">
<h2 class="mb-5 text-base font-bold text-text-main dark:text-slate-100 font-jp pl-1">出題範囲</h2>
<div class="relative flex h-16 w-full rounded-full bg-surface-light dark:bg-surface-dark p-1.5 shadow-neu-pressed dark:shadow-dark-pressed">
<div class="absolute left-1.5 top-1.5 h-[calc(100%-12px)] w-[calc(50%-6px)] rounded-full bg-surface-light dark:bg-surface-dark shadow-neu-flat dark:shadow-dark-flat transition-transform duration-300 ease-out" id="toggle-bg"></div>
<label class="relative z-10 flex flex-1 cursor-pointer items-center justify-center text-sm font-bold transition-colors" onclick="document.getElementById('toggle-bg').style.transform = 'translateX(0)'">
<input checked="" class="peer sr-only" name="study-source" type="radio" value="all"/>
<span class="text-primary peer-checked:text-primary dark:text-slate-400 dark:peer-checked:text-primary transition-colors flex items-center gap-2 font-jp">
<span class="material-symbols-outlined text-[20px]">library_books</span>
                            すべての単語
                        </span>
</label>
<label class="relative z-10 flex flex-1 cursor-pointer items-center justify-center text-sm font-bold transition-colors" onclick="document.getElementById('toggle-bg').style.transform = 'translateX(100%)'">
<input class="peer sr-only" name="study-source" type="radio" value="weak"/>
<span class="text-text-light peer-checked:text-primary hover:text-text-main dark:text-slate-400 dark:peer-checked:text-primary transition-colors flex items-center gap-2 font-jp">
<span class="material-symbols-outlined text-[20px]">warning</span>
                            苦手な単語
                        </span>
</label>
</div>
<p class="mt-4 text-[11px] text-text-light/90 px-2 font-jp leading-relaxed text-center">
                    「すべての単語」は全範囲から出題されます。「苦手な単語」は過去に間違えた単語を重点的に学習します。
                </p>
</section>
<section class="mb-12">
<h2 class="mb-5 text-base font-bold text-text-main dark:text-slate-100 font-jp pl-1">問題数</h2>
<div class="flex gap-4">
<label class="flex-1 cursor-pointer group">
<input checked="" class="peer sr-only" name="question_count" type="radio" value="5"/>
<div class="flex flex-col items-center justify-center py-6 rounded-2xl bg-surface-light dark:bg-surface-dark shadow-neu-flat dark:shadow-dark-flat peer-checked:shadow-neu-pressed dark:peer-checked:shadow-dark-pressed transition-all duration-200 border-2 border-transparent peer-checked:border-primary/10">
<span class="text-2xl font-bold text-text-light peer-checked:text-primary dark:text-slate-400 dark:peer-checked:text-primary font-display mb-1 transition-colors">5</span>
<span class="text-[10px] font-bold text-text-light/70 peer-checked:text-primary/70 dark:text-slate-500 font-jp transition-colors">問</span>
</div>
</label>
<label class="flex-1 cursor-pointer group">
<input class="peer sr-only" name="question_count" type="radio" value="10"/>
<div class="flex flex-col items-center justify-center py-6 rounded-2xl bg-surface-light dark:bg-surface-dark shadow-neu-flat dark:shadow-dark-flat peer-checked:shadow-neu-pressed dark:peer-checked:shadow-dark-pressed transition-all duration-200 border-2 border-transparent peer-checked:border-primary/10">
<span class="text-2xl font-bold text-text-light peer-checked:text-primary dark:text-slate-400 dark:peer-checked:text-primary font-display mb-1 transition-colors">10</span>
<span class="text-[10px] font-bold text-text-light/70 peer-checked:text-primary/70 dark:text-slate-500 font-jp transition-colors">問</span>
</div>
</label>
</div>
</section>
</main>
<div class="absolute bottom-0 w-full pointer-events-none z-20">
<div class="absolute inset-0 bg-gradient-to-t from-background-light via-background-light/95 to-transparent dark:from-background-dark dark:via-background-dark/95 h-full"></div>
<div class="relative px-8 pb-28 pt-12 pointer-events-auto flex justify-center">
<button class="group relative flex w-full items-center justify-center gap-3 overflow-hidden rounded-full bg-primary dark:bg-primary-dark px-8 py-4 text-white font-bold shadow-lg shadow-primary/30 active:scale-[0.98] transition-all duration-150">
<span class="material-symbols-outlined text-[24px]">play_circle</span>
<span class="text-base tracking-widest font-jp">クイズを開始</span>
</button>
</div>
</div>
<div class="absolute bottom-0 w-full flex items-end justify-between bg-background-light dark:bg-background-dark px-6 pb-8 pt-4 z-30">
<a class="flex flex-1 flex-col items-center justify-end gap-1.5 text-text-light/70 hover:text-primary transition-colors group" href="#">
<div class="flex h-10 w-10 items-center justify-center rounded-2xl group-hover:bg-surface-light group-hover:shadow-neu-flat transition-all">
<span class="material-symbols-outlined text-[24px]">home</span>
</div>
<p class="text-[10px] font-bold tracking-wide font-jp">ホーム</p>
</a>
<a class="flex flex-1 flex-col items-center justify-end gap-1.5 text-text-light/70 hover:text-primary transition-colors group" href="#">
<div class="flex h-10 w-10 items-center justify-center rounded-2xl group-hover:bg-surface-light group-hover:shadow-neu-flat transition-all">
<span class="material-symbols-outlined text-[24px]">search</span>
</div>
<p class="text-[10px] font-bold tracking-wide font-jp">検索</p>
</a>
<a class="flex flex-1 flex-col items-center justify-end gap-1.5 text-text-light/70 hover:text-primary transition-colors group" href="#">
<div class="flex h-10 w-10 items-center justify-center rounded-2xl group-hover:bg-surface-light group-hover:shadow-neu-flat transition-all">
<span class="material-symbols-outlined text-[24px]">add_box</span>
</div>
<p class="text-[10px] font-bold tracking-wide font-jp">単語追加</p>
</a>
<a class="flex flex-1 flex-col items-center justify-end gap-1.5 text-primary" href="#">
<div class="flex h-10 w-10 items-center justify-center rounded-2xl bg-surface-light shadow-neu-pressed">
<span class="material-symbols-outlined text-[24px] font-variation-settings-'FILL'1">quiz</span>
</div>
<p class="text-[10px] font-bold tracking-wide font-jp">クイズ</p>
</a>
<a class="flex flex-1 flex-col items-center justify-end gap-1.5 text-text-light/70 hover:text-primary transition-colors group" href="#">
<div class="flex h-10 w-10 items-center justify-center rounded-2xl group-hover:bg-surface-light group-hover:shadow-neu-flat transition-all">
<span class="material-symbols-outlined text-[24px]">bar_chart</span>
</div>
<p class="text-[10px] font-bold tracking-wide font-jp">統計状況</p>
</a>
</div>
</div>
</body></html>