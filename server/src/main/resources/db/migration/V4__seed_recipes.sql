INSERT INTO users (id, firebase_uid, email, display_name, is_subscriber, created_at)
VALUES ('00000000-0000-0000-0000-000000000001', 'system_seed', 'system@recipebook.app', 'Recipe Book', false, now())
ON CONFLICT (firebase_uid) DO NOTHING;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (
        gen_random_uuid(), 'Борщ классический',
        'Наваристый украинский борщ со свёклой, капустой и мясом. Блюдо, которое согревает в любое время года.',
        120, 45, 'MEDIUM', 6, 42.0, 2.8, 1.5, 5.0,
        '00000000-0000-0000-0000-000000000001', true, now()
    ) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Сварить бульон из говядины на медленном огне 1.5 часа. Снимать пену.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Свёклу натереть на крупной тёрке, потушить с томатной пастой и уксусом 15 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Картофель нарезать кубиками, добавить в бульон. Варить 10 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Добавить нашинкованную капусту, морковь и лук. Варить ещё 10 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Добавить тушёную свёклу, варить 5 минут. Посолить, поперчить.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 6, 'Выключить огонь, дать настояться 20 минут. Подавать со сметаной и зеленью.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (
        gen_random_uuid(), 'Котлеты домашние',
        'Сочные домашние котлеты из смешанного фарша. Идеально к картофельному пюре.',
        40, 25, 'EASY', 4, 220.0, 14.0, 16.0, 6.0,
        '00000000-0000-0000-0000-000000000001', true, now()
    ) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Смешать фарш свинины и говядины в равных частях.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Добавить мелко нарезанный лук, яйцо, соль и перец. Хорошо вымесить.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Сформировать котлеты, обвалять в панировочных сухарях.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Обжарить на среднем огне по 5–7 минут с каждой стороны до золотистой корочки.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Довести до готовности под крышкой на медленном огне 10 минут.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (
        gen_random_uuid(), 'Блины классические',
        'Тонкие ажурные блины на молоке. Подходят для любой начинки или просто со сметаной.',
        30, 20, 'EASY', 4, 185.0, 6.0, 7.0, 24.0,
        '00000000-0000-0000-0000-000000000001', true, now()
    ) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Смешать яйца с сахаром и солью, добавить молоко, постепенно ввести муку.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Добавить растопленное сливочное масло, перемешать до однородности.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Дать тесту постоять 15 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Жарить на разогретой сковороде по 1–2 минуты с каждой стороны.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (
        gen_random_uuid(), 'Пельмени домашние',
        'Настоящие домашние пельмени с сочной начинкой из смешанного фарша.',
        90, 60, 'HARD', 6, 275.0, 12.0, 12.0, 28.0,
        '00000000-0000-0000-0000-000000000001', true, now()
    ) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Замесить крутое тесто из муки, яйца, воды и соли. Дать отдохнуть 30 минут.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Смешать фарш свинины и говядины, добавить мелко нарубленный лук, соль, перец.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Раскатать тесто тонко, вырезать кружки стаканом.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'На каждый кружок положить фарш, слепить пельмени.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 5, 'Варить в подсолённой воде 8–10 минут после всплытия.' FROM r;

WITH r AS (
    INSERT INTO recipes (id, title, description, cooking_time_min, active_time_min,
        difficulty, base_servings, calories_per_100g, protein_per_100g, fat_per_100g,
        carbs_per_100g, author_id, is_published, created_at)
    VALUES (
        gen_random_uuid(), 'Салат Оливье',
        'Классический новогодний салат с варёными овощами, яйцами и майонезом.',
        40, 30, 'EASY', 6, 198.0, 8.0, 14.0, 10.0,
        '00000000-0000-0000-0000-000000000001', true, now()
    ) RETURNING id
)
INSERT INTO steps (id, recipe_id, step_number, content)
SELECT gen_random_uuid(), r.id, 1, 'Отварить картофель, морковь и яйца до готовности. Остудить.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 2, 'Нарезать все ингредиенты мелкими кубиками одинакового размера.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 3, 'Добавить горошек, мелко нарезанный лук и солёные огурцы.' FROM r
UNION ALL SELECT gen_random_uuid(), r.id, 4, 'Заправить майонезом, посолить по вкусу. Перемешать и охладить.' FROM r;

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id
FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Борщ классический', 'Котлеты домашние', 'Пельмени домашние', 'Салат Оливье')
  AND t.name = 'Русская кухня';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id
FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title IN ('Борщ классический', 'Котлеты домашние', 'Блины классические', 'Пельмени домашние', 'Салат Оливье')
  AND t.name = 'Домашняя кухня';

INSERT INTO recipe_tags (recipe_id, tag_id)
SELECT r.id, t.id
FROM recipes r, tags t
WHERE r.author_id = '00000000-0000-0000-0000-000000000001'
  AND r.title = 'Блины классические'
  AND t.name = 'Вегетарианское';
