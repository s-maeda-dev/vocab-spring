<!DOCTYPE html>
<html lang="ja"><head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>VocabularySpring - クイズ実行中</title>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com" rel="preconnect"/>
<link crossorigin="" href="https://fonts.gstatic.com" rel="preconnect"/>
<link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;600;700&amp;family=Noto+Sans+JP:wght@300;400;500;700&amp;display=swap" rel="stylesheet"/>
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<script id="tailwind-config">
        tailwind.config = {
            darkMode: "class",
            theme: {
                extend: {
                    colors: {
                        "primary": "#A1887F", // Muted dusty brown from reference
                        "primary-dark": "#8D6E63",
                        "accent": "#BCAAA4",
                        "background-light": "#F2F0EB", // Warmer off-white base
                        "surface": "#F5F3EF", // Slightly lighter surface for cards
                        "text-main": "#4E4440", // Softer dark brown for text
                        "text-sub": "#8D7B75", // Muted secondary text
                        "shadow-light": "#FFFFFF",
                        "shadow-dark": "#D6D2CC", // Adjusted shadow color for the warmer tone
                    },
                    fontFamily: {
                        "display": ["Lexend", "Noto Sans JP", "sans-serif"],
                        "body": ["Noto Sans JP", "sans-serif"]
                    },
                    borderRadius: {"DEFAULT": "0.5rem", "lg": "1rem", "xl": "1.5rem", "2xl": "2rem", "3xl": "2.5rem", "full": "9999px"},
                    boxShadow: {
                        'neumorphic-flat': '8px 8px 16px #D6D2CC, -8px -8px 16px #FFFFFF',
                        'neumorphic-pressed': 'inset 4px 4px 8px #D6D2CC, inset -4px -4px 8px #FFFFFF',
                        'neumorphic-deep': '12px 12px 24px #D6D2CC, -12px -12px 24px #FFFFFF',
                        'neumorphic-sm': '4px 4px 8px #D6D2CC, -4px -4px 8px #FFFFFF',
                        'neumorphic-btn': '6px 6px 12px #C9C5BF, -6px -6px 12px #FFFFFF',
                        'float': '0 10px 30px -10px rgba(161, 136, 127, 0.3)',
                    }
                },
            },
        }
    </script>
<style>
        .neu-input {
            box-shadow: inset 4px 4px 8px #D6D2CC, inset -4px -4px 8px #FFFFFF;
            border: none;
            background-color: #EDEBE6;
            transition: all 0.3s ease;
        }
        .neu-input:focus {
            outline: none;
            box-shadow: inset 6px 6px 12px #D6D2CC, inset -6px -6px 12px #FFFFFF;
            background-color: #EFEFEA;
        }
        .neu-transition {
            transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
        }
        .glass-gradient {
            background: linear-gradient(145deg, rgba(255,255,255,0.6), rgba(255,255,255,0.1));
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
<body class="bg-background-light text-text-main font-body min-h-screen flex flex-col overflow-hidden antialiased">
<div class="px-6 pt-10 pb-4 flex items-center justify-between z-10">
<button class="flex items-center justify-center w-12 h-12 rounded-full bg-background-light shadow-neumorphic-sm hover:shadow-neumorphic-pressed active:shadow-neumorphic-pressed text-text-main neu-transition group">
<span class="material-symbols-outlined font-bold text-[22px] group-hover:text-primary transition-colors">close</span>
</button>
<h1 class="text-text-main font-bold text-lg tracking-wide">クイズ実行中</h1>
<div class="flex items-center gap-2 px-5 h-10 rounded-full bg-background-light shadow-neumorphic-pressed text-primary font-bold text-sm tracking-wide">
<span>12</span>
<span class="text-text-sub font-normal opacity-50">/</span>
<span class="text-text-sub">50</span>
</div>
</div>
<div class="px-8 py-2">
<div class="flex justify-between items-end mb-3">
<span class="text-xs font-semibold text-text-sub tracking-wider pl-1">進捗状況</span>
<span class="text-xs font-bold text-primary">24%</span>
</div>
<div class="h-3 w-full rounded-full bg-background-light shadow-neumorphic-pressed p-[2px]">
<div class="h-full w-[24%] rounded-full bg-primary shadow-[2px_2px_4px_rgba(161,136,127,0.3)]"></div>
</div>
</div>
<div class="flex-1 flex flex-col px-6 pb-6 gap-6 overflow-y-auto">
<div class="w-full relative rounded-3xl bg-surface shadow-neumorphic-deep p-8 flex flex-col items-center text-center gap-2 mt-8 mb-2 neu-transition border border-white/40">
<div class="absolute top-0 left-1/2 -translate-x-1/2 -translate-y-1/2 w-20 h-20 rounded-full bg-surface shadow-neumorphic-flat flex items-center justify-center z-10 border-4 border-background-light">
<span class="material-symbols-outlined text-4xl text-primary drop-shadow-sm">school</span>
</div>
<div class="pt-8 w-full flex flex-col items-center">
<p class="text-text-sub text-xs font-medium tracking-widest mb-3 opacity-80">意味</p>
<h1 class="text-[2.5rem] font-bold text-text-main mb-6 tracking-tight drop-shadow-sm">儚い</h1>
<div class="w-full border-t border-text-sub/10 my-2"></div>
<p class="mt-4 text-text-sub text-sm font-light leading-relaxed max-w-[90%] opacity-90">
            つかの間の、一時的な、または非常に短期間続くこと。
        </p>
</div>
</div>
<div class="flex flex-col gap-4 mt-2">
<label class="text-sm font-semibold text-text-sub ml-2 block mb-1">回答を入力</label>
<div class="relative">
<input class="w-full p-5 rounded-2xl neu-input text-lg text-text-main placeholder-text-sub/50 font-medium tracking-wide focus:ring-0" placeholder="答えを入力..." type="text"/>
<span class="absolute right-5 top-1/2 -translate-y-1/2 material-symbols-outlined text-text-sub/40">edit</span>
</div>
</div>
</div>
<div class="p-6 pt-2 bg-background-light pb-10 sticky bottom-0 z-20 gradient-mask-t">
<button class="w-full py-4 rounded-2xl bg-primary text-white font-bold text-lg tracking-wide shadow-float active:shadow-neumorphic-pressed active:translate-y-[1px] neu-transition flex items-center justify-center gap-2 hover:bg-primary-dark hover:shadow-lg">
<span>回答を確認</span>
<span class="material-symbols-outlined text-[24px]">check_circle</span>
</button>
</div>
</body></html>