WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Паста Карбонара',
        'Классическая итальянская паста с беконом, яйцами и сыром Пармезан. Кремовый соус без сливок.',
        25, 20, 'MEDIUM', 2, 380.0, 18.0, 19.0, 34.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Отварить спагетти до состояния аль денте в подсолённой воде.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Обжарить нарезанный кубиками бекон или гуанчале на сухой сковороде до хрустящей корочки.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Смешать яйца (2 целых и 2 желтка) с натёртым Пармезаном, добавить чёрный перец.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Снять сковороду с огня. Добавить горячие спагетти к бекону, быстро перемешать.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Влить яично-сырную смесь, постоянно помешивая. Добавить немного воды от пасты для кремовости.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Греческий салат',
        'Свежий средиземноморский салат с овощами, маслинами и сыром фета. Лёгкий и полезный.',
        15, 15, 'EASY', 4, 95.0, 4.0, 7.0, 5.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Нарезать помидоры и огурцы крупными кусками, болгарский перец — полосками.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Добавить маслины, нарезанный кольцами красный лук.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Выложить крупные куски феты поверх салата.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Заправить оливковым маслом, орегано, солью и перцем. Не перемешивать.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Курица терияки',
        'Сочная курица в сладко-солёном японском соусе терияки с кунжутом. Подавать с рисом.',
        30, 15, 'EASY', 2, 165.0, 22.0, 5.0, 8.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Смешать соевый соус, мирин, сахар и чеснок для маринада.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Замариновать куриное филе на 20 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Обжарить курицу на раскалённой сковороде по 4–5 минут с каждой стороны.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Влить оставшийся маринад, уварить соус до загустения.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Нарезать курицу, полить соусом, посыпать кунжутом и зелёным луком.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Гречневая каша с грибами',
        'Рассыпчатая гречневая каша, томлённая с лесными грибами и луком. Сытное вегетарианское блюдо.',
        40, 15, 'EASY', 4, 120.0, 5.0, 3.0, 20.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Промыть гречку, обжарить на сухой сковороде до золотистого цвета.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Обжарить нарезанный лук и грибы на масле до готовности.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Смешать гречку с грибами, залить кипятком 1:2, посолить.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Варить на медленном огне под крышкой 20 минут до поглощения воды.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Шашлык из курицы',
        'Сочный куриный шашлык в кефирном маринаде. Получается мягким и нежным даже на сковороде.',
        50, 20, 'EASY', 4, 145.0, 20.0, 6.0, 2.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Нарезать куриное филе кусочками 3–4 см.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Смешать кефир, лук, чеснок, паприку, соль и перец. Замариновать курицу минимум на 2 часа.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Нанизать на шампуры, чередуя с кольцами лука.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Жарить на мангале или в духовке при 200°C 25–30 минут, периодически переворачивая.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Ризотто с грибами',
        'Кремовое итальянское ризотто с шампиньонами и Пармезаном. Требует внимания, но результат стоит усилий.',
        40, 35, 'MEDIUM', 4, 210.0, 7.0, 8.0, 28.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Обжарить нарезанные грибы с чесноком на сливочном масле. Отложить в сторону.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'На той же сковороде обжарить мелко нарезанный лук-шалот до мягкости.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Добавить рис Арборио, обжарить 2 минуты. Влить белое вино, помешивать до впитывания.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Добавлять горячий бульон по одному половнику, постоянно помешивая. Повторять 18–20 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Добавить грибы, сливочное масло и Пармезан. Перемешать, снять с огня.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Сырники творожные',
        'Нежные творожные сырники с хрустящей корочкой. Идеальный завтрак с джемом или сметаной.',
        25, 20, 'EASY', 4, 230.0, 14.0, 10.0, 22.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Смешать творог, яйцо, сахар, ванилин и щепотку соли.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Добавить муку, замесить мягкое тесто. Не переусердствовать с мукой.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Сформировать небольшие лепёшки, обвалять в муке.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Обжарить на среднем огне по 3–4 минуты с каждой стороны до золотистой корочки.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Пицца Маргарита',
        'Классическая итальянская пицца на тонком тесте с томатным соусом и моцареллой.',
        50, 30, 'MEDIUM', 2, 265.0, 11.0, 10.0, 34.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Замесить тесто из муки, дрожжей, воды, соли и оливкового масла. Дать подойти 1 час.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Приготовить соус: пробить томаты с чесноком и базиликом блендером.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Раскатать тесто в тонкий круг, нанести соус тонким слоем.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Разложить кусочки моцареллы, добавить листья свежего базилика.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Выпекать при 250°C на максимуме 8–10 минут до румяной корочки.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Запечённый лосось',
        'Нежный лосось, запечённый с лимоном и зеленью. Быстрый и полезный ужин за 20 минут.',
        25, 10, 'EASY', 2, 180.0, 25.0, 9.0, 0.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Разогреть духовку до 200°C. Выложить стейки лосося на фольгу.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Натереть рыбу солью, перцем, полить оливковым маслом и лимонным соком.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Добавить дольки лимона и веточки розмарина. Завернуть в фольгу.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Запекать 15–18 минут. Раскрыть фольгу за 3 минуты до конца для корочки.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Окрошка на кефире',
        'Освежающий летний суп с колбасой, огурцами и яйцами на кефире. Идеален в жару.',
        20, 20, 'EASY', 4, 65.0, 4.0, 3.0, 5.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Отварить картофель и яйца, остудить и нарезать мелкими кубиками.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Нарезать варёную колбасу, свежие огурцы, редис мелкими кубиками.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Смешать все ингредиенты, добавить мелко нарезанный зелёный лук и укроп.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Залить холодным кефиром, добавить горчицу и соль по вкусу. Подавать охлаждённой.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Том-ям',
        'Острый тайский суп с креветками, грибами и кокосовым молоком. Яркий и ароматный.',
        35, 20, 'MEDIUM', 2, 85.0, 8.0, 4.0, 5.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Довести куриный бульон до кипения, добавить лемонграсс, галангал и листья кафрского лайма.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Добавить пасту том-ям, нарезанные грибы и помидоры черри.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Влить кокосовое молоко, варить 5 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Добавить очищенные креветки, варить ещё 3 минуты.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Приправить рыбным соусом и соком лайма. Подавать с листьями кинзы.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Лазанья болоньезе',
        'Классическая итальянская лазанья с мясным соусом и нежным бешамелем. Блюдо для особого случая.',
        90, 50, 'HARD', 6, 290.0, 16.0, 14.0, 27.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Приготовить болоньезе: обжарить лук, морковь, сельдерей. Добавить фарш, томаты, тушить 40 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Приготовить бешамель: растопить масло, добавить муку, влить молоко. Варить до загустения, добавить мускатный орех.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Листы лазаньи отварить до полуготовности (если требуется по инструкции).' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Чередовать слои: бешамель, листы лазаньи, болоньезе, Пармезан. Повторить 3–4 раза.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Верхний слой — бешамель и тёртый Пармезан. Запекать при 180°C 35 минут.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Плов узбекский',
        'Настоящий рассыпчатый узбекский плов с бараниной, морковью и специями. Готовится в казане.',
        120, 40, 'HARD', 6, 210.0, 9.0, 10.0, 23.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Разогреть масло в казане, обжарить лук до тёмно-золотистого цвета.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Добавить баранину кусками, обжаривать до корочки.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Добавить морковь соломкой, зиру, барбарис. Обжаривать 10 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Залить кипятком, тушить зирвак 30 минут. Посолить.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Промытый рис выложить ровным слоем, не перемешивать. Воткнуть целую головку чеснока.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 6, 'Варить на сильном огне до испарения воды, затем томить под крышкой 20 минут.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Омлет с сыром и овощами',
        'Пышный омлет с болгарским перцем, помидорами и расплавленным сыром. Готовится за 10 минут.',
        15, 10, 'EASY', 2, 175.0, 12.0, 13.0, 3.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Взбить яйца с молоком, солью и перцем до однородности.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Обжарить нарезанный болгарский перец и помидоры на масле 2–3 минуты.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Влить яичную смесь поверх овощей, накрыть крышкой.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Когда омлет почти схватится, посыпать тёртым сыром. Жарить ещё 1 минуту.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Суп минестроне',
        'Густой итальянский овощной суп с пастой и фасолью. Сытный и согревающий.',
        50, 25, 'MEDIUM', 6, 72.0, 3.5, 2.0, 11.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Обжарить лук, чеснок, морковь и сельдерей на оливковом масле до мягкости.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Добавить нарезанные кубиками кабачки, картофель и помидоры.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Залить овощным бульоном, варить 20 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Добавить консервированную фасоль и небольшие макароны. Варить ещё 10 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Приправить базиликом, орегано, солью. Подавать с тёртым Пармезаном.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Гуакамоле',
        'Классический мексиканский соус из авокадо с помидором, лаймом и кинзой. Веганский и без глютена.',
        10, 10, 'EASY', 4, 160.0, 2.0, 15.0, 5.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Разрезать спелые авокадо, удалить косточку, извлечь мякоть.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Размять вилкой до желаемой консистенции — гладкой или с кусочками.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Добавить мелко нарезанный помидор, красный лук, кинзу, чили.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Выжать сок лайма, посолить. Перемешать и подавать сразу.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Рамен с курицей',
        'Японский суп с пшеничной лапшой, яйцом аджицuke и насыщенным бульоном. Согревающий и ароматный.',
        90, 30, 'HARD', 2, 145.0, 10.0, 5.0, 15.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Сварить насыщенный куриный бульон с имбирём, чесноком и соевым соусом — 1.5 часа.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Приготовить маринованные яйца: варить 6.5 минут, остудить, залить смесью соевого соуса и мирина на 4 часа.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Отварить лапшу рамен согласно инструкции.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Обжарить куриное мясо, нарезать тонкими ломтиками.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Собрать рамен: лапша в миску, залить горячим бульоном, выложить курицу, половинку яйца, нори, зелёный лук.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Тирамису',
        'Нежный итальянский десерт с маскарпоне, кофе и печеньем савоярди. Без выпечки.',
        30, 30, 'MEDIUM', 6, 310.0, 6.0, 20.0, 26.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Взбить желтки с сахаром до белой пышной массы.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Добавить маскарпоне к желткам, перемешать до однородности.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Взбить белки до крепких пиков, аккуратно вмешать в крем.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Быстро обмакнуть печенье савоярди в холодный кофе с ликёром.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Выложить слой печенья, слой крема. Повторить. Убрать в холодильник на 4–6 часов.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 6, 'Перед подачей обильно посыпать какао через сито.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Торт Наполеон',
        'Многослойный торт из хрустящих коржей и нежного заварного крема. Классика домашней выпечки.',
        180, 90, 'HARD', 8, 340.0, 5.0, 18.0, 41.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Приготовить тесто: смешать муку, масло, воду и уксус. Разделить на 10 частей, охладить 1 час.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Раскатать каждую часть тонко, выпекать при 200°C 5–7 минут до золотистого цвета.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Приготовить заварной крем: молоко, яйца, сахар, мука. Варить до загустения, охладить, взбить со сливочным маслом.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Смазать каждый корж кремом, собрать торт. Крошку от коржей использовать для посыпки.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Убрать в холодильник на 8–12 часов для пропитки.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Куриный суп с лапшой',
        'Лёгкий домашний куриный суп с домашней лапшой. Согревает и восстанавливает силы.',
        60, 25, 'EASY', 6, 55.0, 5.0, 2.0, 5.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Сварить бульон из курицы с луком, морковью и лавровым листом — 40 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Вынуть курицу, отделить мясо от костей, нарезать.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'В процеженный бульон добавить нарезанную морковь и картофель, варить 10 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Добавить лапшу и мясо, варить ещё 5–7 минут. Посолить, добавить зелень.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (gen_random_uuid(), 'Жареный рис по-азиатски',
        'Рис с яйцом, овощами и соевым соусом в стиле уличной еды. Быстро и вкусно.',
        20, 15, 'EASY', 2, 190.0, 6.0, 6.0, 28.0,
        '00000000-0000-0000-0000-000000000001', true, now()) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Использовать охлаждённый варёный рис (лучше вчерашний).' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Разогреть вок до максимума, добавить масло. Обжарить чеснок и имбирь 30 секунд.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Добавить рис, жарить помешивая 3–4 минуты до лёгкой поджаристости.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Сдвинуть рис в сторону, вбить яйца, перемешать с рисом.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Добавить соевый соус, кунжутное масло, зелёный лук. Подавать горячим.' FROM r;

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Паста Карбонара', 'Ризотто с грибами', 'Пицца Маргарита', 'Лазанья болоньезе', 'Тирамису', 'Суп минестроне')
  AND t.name = 'Итальянская кухня';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Курица терияки', 'Том-ям', 'Рамен с курицей', 'Жареный рис по-азиатски')
  AND t.name = 'Азиатская кухня';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Греческий салат', 'Запечённый лосось')
  AND t.name = 'Средиземноморская кухня';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Гречневая каша с грибами', 'Сырники творожные', 'Шашлык из курицы', 'Окрошка на кефире', 'Куриный суп с лапшой')
  AND t.name = 'Русская кухня';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Омлет с сыром и овощами', 'Куриный суп с лапшой', 'Жареный рис по-азиатски')
  AND t.name = 'Домашняя кухня';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Греческий салат', 'Гречневая каша с грибами', 'Ризотто с грибами', 'Пицца Маргарита', 'Суп минестроне')
  AND t.name = 'Вегетарианское';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title = 'Гуакамоле'
  AND t.name = 'Веганское';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Курица терияки', 'Шашлык из курицы', 'Запечённый лосось')
  AND t.name = 'Высокобелковое';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Греческий салат', 'Окрошка на кефире', 'Гуакамоле')
  AND t.name = 'Низкокалорийное';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Гречневая каша с грибами', 'Шашлык из курицы', 'Запечённый лосось', 'Гуакамоле', 'Плов узбекский')
  AND t.name = 'Без глютена';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Том-ям', 'Рамен с курицей', 'Запечённый лосось')
  AND t.name = 'Содержит рыбу';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Паста Карбонара', 'Тирамису', 'Сырники творожные', 'Омлет с сыром и овощами')
  AND t.name = 'Содержит яйца';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Паста Карбонара', 'Ризотто с грибами', 'Лазанья болоньезе', 'Тирамису', 'Омлет с сыром и овощами', 'Сырники творожные')
  AND t.name = 'Содержит лактозу';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Паста Карбонара', 'Пицца Маргарита', 'Лазанья болоньезе', 'Торт Наполеон', 'Рамен с курицей')
  AND t.name = 'Содержит глютен';
