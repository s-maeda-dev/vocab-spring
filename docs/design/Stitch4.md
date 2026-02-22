<!DOCTYPE html>
<html lang="ja"><head>
<meta charset="utf-8"/>
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<title>VocabularySpring - 単語の新規登録</title>
<link href="https://fonts.googleapis.com/css2?family=Lexend:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300;400;500;700&amp;display=swap" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&amp;display=swap" rel="stylesheet"/>
<script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
<script id="tailwind-config">
        tailwind.config = {
            darkMode: "class",
            theme: {
                extend: {
                    colors: {
                        "primary": "#A18E78", // Dusty muted brown
                        "primary-hover": "#8C7B68",
                        "accent": "#BFA588",
                        "background-light": "#F5F2F0", // Slightly warmer/lighter than original for soft neumorphism
                        "background-dark": "#2d2624", 
                        "text-main": "#5D544F", // Softer dark brown-grey
                        "text-muted": "#9C948F",
                        // Neumorphic shadows customized for dusty theme
                        "shadow-light": "#FFFFFF",
                        "shadow-dark": "#D6D1CC", 
                    },
                    fontFamily: {
                        "display": ["Noto Sans JP", "sans-serif"],
                        "english": ["Lexend", "sans-serif"]
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
                        'neumorph': '8px 8px 16px #D6D1CC, -8px -8px 16px #FFFFFF',
                        'neumorph-sm': '4px 4px 8px #D6D1CC, -4px -4px 8px #FFFFFF',
                        'neumorph-btn': '6px 6px 14px #D6D1CC, -6px -6px 14px #FFFFFF',
                        'neumorph-inset': 'inset 4px 4px 8px #D6D1CC, inset -4px -4px 8px #FFFFFF',
                        'neumorph-inset-deep': 'inset 6px 6px 12px #Ccc8c2, inset -6px -6px 12px #FFFFFF',
                        'btn-solid': '6px 6px 12px rgba(161, 142, 120, 0.35), -6px -6px 12px rgba(255, 255, 255, 0.9)',
                        'float': '0 10px 30px rgba(161, 142, 120, 0.15)',
                    }
                },
            },
        }
    </script>
<style>
        ::-webkit-scrollbar {
            width: 6px;
        }
        ::-webkit-scrollbar-track {
            background: transparent; 
        }
        ::-webkit-scrollbar-thumb {
            background: #D6D1CC; 
            border-radius: 10px;
        }
        ::-webkit-scrollbar-thumb:hover {
            background: #A18E78; 
        }
        .no-scrollbar::-webkit-scrollbar {
            display: none;
        }
        .no-scrollbar {
            -ms-overflow-style: none;
            scrollbar-width: none;
        }
        body {
            min-height: max(884px, 100dvh);
        }select {
            background-image: none !important;
        }
    </style>
<style>
    body {
      min-height: max(884px, 100dvh);
    }
  </style>
  </head>
<body class="bg-background-light dark:bg-background-dark font-display min-h-screen flex flex-col items-center justify-center text-text-main dark:text-slate-200 antialiased selection:bg-primary/20">
<div class="relative flex h-full min-h-screen w-full max-w-[480px] flex-col overflow-hidden bg-background-light dark:bg-background-dark shadow-2xl">
<header class="sticky top-0 z-10 flex items-center justify-between px-6 pt-12 pb-6 bg-background-light/90 backdrop-blur-sm dark:bg-background-dark">
<button class="flex items-center justify-center w-12 h-12 rounded-full text-text-muted hover:text-text-main shadow-neumorph-btn active:shadow-neumorph-inset transition-all duration-200 group">
<span class="material-symbols-outlined text-[24px] group-active:scale-95 transition-transform">arrow_back</span>
</button>
<h1 class="text-[18px] font-bold tracking-tight text-text-main">単語の新規登録</h1>
<div class="w-12"></div> 
</header>
<main class="flex-1 overflow-y-auto px-8 pb-32 no-scrollbar">
<div class="mt-2 mb-8 group">
<label class="block text-sm font-bold text-text-muted mb-3 ml-2 tracking-wide" for="term">用語名</label>
<div class="relative rounded-3xl shadow-neumorph-inset-deep bg-background-light overflow-hidden transition-all focus-within:shadow-neumorph-inset ring-1 ring-white/50">
<input class="w-full h-16 bg-transparent border-none px-6 text-xl font-bold text-text-main placeholder:text-text-muted/40 focus:ring-0 font-english" id="term" placeholder="例: Serendipity" type="text"/>
</div>
</div>
<div class="mb-8">
<label class="block text-sm font-bold text-text-muted mb-3 ml-2" for="meaning">意味</label>
<div class="relative rounded-3xl shadow-neumorph-inset-deep bg-background-light overflow-hidden transition-all focus-within:shadow-neumorph-inset ring-1 ring-white/50">
<textarea class="w-full min-h-[140px] bg-transparent border-none p-6 text-base text-text-main placeholder:text-text-muted/40 focus:ring-0 resize-none leading-relaxed" id="meaning" placeholder="この言葉の意味を入力してください"></textarea>
</div>
</div>
<div class="mb-8">
<label class="block text-sm font-bold text-text-muted mb-3 ml-2" for="category">カテゴリー</label>
<div class="relative group">
<select class="appearance-none w-full h-14 rounded-full shadow-neumorph bg-background-light px-6 text-base text-text-main focus:outline-none focus:ring-2 focus:ring-primary/10 active:shadow-neumorph-inset transition-all cursor-pointer border-none ring-1 ring-white/60" id="category">
<option class="text-text-muted" disabled="" selected="" value="">カテゴリーを選択...</option>
<option value="academic">学術・アカデミック</option>
<option value="casual">日常会話</option>
<option value="business">ビジネス・技術</option>
<option value="literature">文学・芸術</option>
</select>
<div class="pointer-events-none absolute inset-y-0 right-4 flex items-center px-2 text-text-muted group-hover:text-primary transition-colors">
<span class="material-symbols-outlined">expand_more</span>
</div>
</div>
</div>
<div class="mb-8">
<label class="block text-sm font-bold text-text-muted mb-3 ml-2" for="notes">メモ</label>
<div class="relative rounded-3xl shadow-neumorph-inset-deep bg-background-light overflow-hidden transition-all focus-within:shadow-neumorph-inset ring-1 ring-white/50">
<textarea class="w-full min-h-[100px] bg-transparent border-none p-6 text-base text-text-main placeholder:text-text-muted/40 focus:ring-0 resize-none leading-relaxed" id="notes" placeholder="覚え書き、例文、個人的なメモなど..."></textarea>
</div>
</div>
<div class="h-6"></div>
</main>
<div class="absolute bottom-0 left-0 right-0 p-6 pt-0 bg-gradient-to-t from-background-light via-background-light/95 to-transparent z-20 pb-10">
<button class="w-full h-14 rounded-full bg-primary text-white shadow-btn-solid active:scale-[0.98] active:shadow-inner transition-all duration-200 font-bold text-base tracking-wide flex items-center justify-center gap-2 hover:bg-primary-hover ring-1 ring-white/20">
<span class="material-symbols-outlined text-[20px]">save</span>
<span>保存する</span>
</button>
</div>
<div class="absolute top-0 left-0 w-full h-32 bg-gradient-to-b from-white/30 to-transparent pointer-events-none z-0"></div>
</div>
<div class="hidden lg:block fixed -z-10 inset-0 bg-[#E8E6E3]">
<div class="absolute inset-0 opacity-[0.03]" style="background-image: url('data:image/svg+xml,%3Csvg width=\'60\' height=\'60\' viewBox=\'0 0 60 60\' xmlns=\'http://www.w3.org/2000/svg\'%3E%3Cg fill=\'none\' fill-rule=\'evenodd\'%3E%3Cg fill=\'%23000000\' fill-opacity=\'1\'%3E%3Cpath d=\'M36 34v-4h-2v4h-4v2h4v4h2v-4h4v-2h-4zm0-30V0h-2v4h-4v2h4v4h2V6h4V4h-4zM6 34v-4H4v4H0v2h4v4h2v-4h4v-2H6zM6 4V0H4v4H0v2h4v4h2V6h4V4H6z\'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E');"></div>
</div>

</body></html>