INSERT INTO ingredients (id, name, default_unit) VALUES
    (gen_random_uuid(), 'Свёкла', 'г'),
    (gen_random_uuid(), 'Уксус столовый', 'мл'),
    (gen_random_uuid(), 'Хлеб белый', 'г'),
    (gen_random_uuid(), 'Панировочные сухари', 'г'),
    (gen_random_uuid(), 'Колбаса варёная', 'г'),
    (gen_random_uuid(), 'Горошек зелёный консервированный', 'г'),
    (gen_random_uuid(), 'Майонез', 'г'),
    (gen_random_uuid(), 'Бекон', 'г'),
    (gen_random_uuid(), 'Перец чёрный молотый', 'ч.л.'),
    (gen_random_uuid(), 'Маслины', 'г'),
    (gen_random_uuid(), 'Сыр фета', 'г'),
    (gen_random_uuid(), 'Лук красный', 'г'),
    (gen_random_uuid(), 'Масло оливковое', 'мл'),
    (gen_random_uuid(), 'Орегано', 'ч.л.'),
    (gen_random_uuid(), 'Соевый соус', 'мл'),
    (gen_random_uuid(), 'Мёд', 'г'),
    (gen_random_uuid(), 'Имбирь', 'г'),
    (gen_random_uuid(), 'Грибы шампиньоны', 'г'),
    (gen_random_uuid(), 'Бульон куриный', 'мл'),
    (gen_random_uuid(), 'Вино белое сухое', 'мл'),
    (gen_random_uuid(), 'Дрожжи сухие', 'г'),
    (gen_random_uuid(), 'Сыр моцарелла', 'г'),
    (gen_random_uuid(), 'Базилик свежий', 'г'),
    (gen_random_uuid(), 'Лосось филе', 'г'),
    (gen_random_uuid(), 'Лимон', 'шт'),
    (gen_random_uuid(), 'Редис', 'г'),
    (gen_random_uuid(), 'Кокосовое молоко', 'мл'),
    (gen_random_uuid(), 'Лимонная трава', 'г'),
    (gen_random_uuid(), 'Лайм', 'шт'),
    (gen_random_uuid(), 'Рыбный соус', 'мл'),
    (gen_random_uuid(), 'Перец чили', 'шт'),
    (gen_random_uuid(), 'Баранина', 'г'),
    (gen_random_uuid(), 'Зира', 'ч.л.'),
    (gen_random_uuid(), 'Кабачок', 'г'),
    (gen_random_uuid(), 'Сельдерей', 'г'),
    (gen_random_uuid(), 'Фасоль белая консервированная', 'г'),
    (gen_random_uuid(), 'Авокадо', 'шт'),
    (gen_random_uuid(), 'Кинза', 'г'),
    (gen_random_uuid(), 'Лапша рамен', 'г'),
    (gen_random_uuid(), 'Зелёный лук', 'г'),
    (gen_random_uuid(), 'Масло кунжутное', 'мл'),
    (gen_random_uuid(), 'Маскарпоне', 'г'),
    (gen_random_uuid(), 'Печенье савоярди', 'г'),
    (gen_random_uuid(), 'Кофе эспрессо', 'мл'),
    (gen_random_uuid(), 'Какао порошок', 'г'),
    (gen_random_uuid(), 'Ванилин', 'ч.л.')
ON CONFLICT (name) DO NOTHING;

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Борщ' AND i.name = 'Говядина'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Борщ' AND i.name = 'Свёкла'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Борщ' AND i.name = 'Капуста белокочанная'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Борщ' AND i.name = 'Картофель'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Борщ' AND i.name = 'Морковь'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Борщ' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 2, 'ст.л.' FROM recipes r, ingredients i WHERE r.title = 'Борщ' AND i.name = 'Томатная паста'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Борщ' AND i.name = 'Сметана'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Борщ' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Котлеты по-домашнему' AND i.name = 'Говядина'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Котлеты по-домашнему' AND i.name = 'Свинина'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Котлеты по-домашнему' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 2, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Котлеты по-домашнему' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Котлеты по-домашнему' AND i.name = 'Хлеб белый'
UNION ALL
SELECT r.id, i.id, 100, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Котлеты по-домашнему' AND i.name = 'Молоко'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Котлеты по-домашнему' AND i.name = 'Панировочные сухари'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Котлеты по-домашнему' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Блины' AND i.name = 'Мука пшеничная'
UNION ALL
SELECT r.id, i.id, 500, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Блины' AND i.name = 'Молоко'
UNION ALL
SELECT r.id, i.id, 2, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Блины' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 30, 'г' FROM recipes r, ingredients i WHERE r.title = 'Блины' AND i.name = 'Масло сливочное'
UNION ALL
SELECT r.id, i.id, 1, 'ст.л.' FROM recipes r, ingredients i WHERE r.title = 'Блины' AND i.name = 'Сахар'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Блины' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Пельмени' AND i.name = 'Мука пшеничная'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Пельмени' AND i.name = 'Говядина'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Пельмени' AND i.name = 'Свинина'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Пельмени' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 2, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Пельмени' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Пельмени' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Салат Оливье' AND i.name = 'Картофель'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Салат Оливье' AND i.name = 'Морковь'
UNION ALL
SELECT r.id, i.id, 4, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Салат Оливье' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Салат Оливье' AND i.name = 'Колбаса варёная'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Салат Оливье' AND i.name = 'Огурцы'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Салат Оливье' AND i.name = 'Горошек зелёный консервированный'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Салат Оливье' AND i.name = 'Майонез'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Салат Оливье' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Паста Карбонара' AND i.name = 'Макароны'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Паста Карбонара' AND i.name = 'Бекон'
UNION ALL
SELECT r.id, i.id, 4, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Паста Карбонара' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Паста Карбонара' AND i.name = 'Сыр твёрдый'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Паста Карбонара' AND i.name = 'Перец чёрный молотый'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Паста Карбонара' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Греческий салат' AND i.name = 'Помидоры'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Греческий салат' AND i.name = 'Огурцы'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Греческий салат' AND i.name = 'Перец болгарский'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Греческий салат' AND i.name = 'Маслины'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Греческий салат' AND i.name = 'Сыр фета'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Греческий салат' AND i.name = 'Лук красный'
UNION ALL
SELECT r.id, i.id, 50, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Греческий салат' AND i.name = 'Масло оливковое'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Греческий салат' AND i.name = 'Орегано';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 600, 'г' FROM recipes r, ingredients i WHERE r.title = 'Курица терияки' AND i.name = 'Курица филе'
UNION ALL
SELECT r.id, i.id, 60, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Курица терияки' AND i.name = 'Соевый соус'
UNION ALL
SELECT r.id, i.id, 30, 'г' FROM recipes r, ingredients i WHERE r.title = 'Курица терияки' AND i.name = 'Мёд'
UNION ALL
SELECT r.id, i.id, 3, 'зуб' FROM recipes r, ingredients i WHERE r.title = 'Курица терияки' AND i.name = 'Чеснок'
UNION ALL
SELECT r.id, i.id, 20, 'г' FROM recipes r, ingredients i WHERE r.title = 'Курица терияки' AND i.name = 'Имбирь'
UNION ALL
SELECT r.id, i.id, 30, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Курица терияки' AND i.name = 'Масло растительное';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Гречневая каша с грибами' AND i.name = 'Гречка'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Гречневая каша с грибами' AND i.name = 'Грибы шампиньоны'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Гречневая каша с грибами' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 50, 'г' FROM recipes r, ingredients i WHERE r.title = 'Гречневая каша с грибами' AND i.name = 'Масло сливочное'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Гречневая каша с грибами' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 800, 'г' FROM recipes r, ingredients i WHERE r.title = 'Шашлык из курицы' AND i.name = 'Курица филе'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Шашлык из курицы' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Шашлык из курицы' AND i.name = 'Майонез'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Шашлык из курицы' AND i.name = 'Перец чёрный молотый'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Шашлык из курицы' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Ризотто с грибами' AND i.name = 'Рис'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Ризотто с грибами' AND i.name = 'Грибы шампиньоны'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Ризотто с грибами' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 50, 'г' FROM recipes r, ingredients i WHERE r.title = 'Ризотто с грибами' AND i.name = 'Масло сливочное'
UNION ALL
SELECT r.id, i.id, 80, 'г' FROM recipes r, ingredients i WHERE r.title = 'Ризотто с грибами' AND i.name = 'Сыр твёрдый'
UNION ALL
SELECT r.id, i.id, 800, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Ризотто с грибами' AND i.name = 'Бульон куриный'
UNION ALL
SELECT r.id, i.id, 100, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Ризотто с грибами' AND i.name = 'Вино белое сухое'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Ризотто с грибами' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 500, 'г' FROM recipes r, ingredients i WHERE r.title = 'Сырники' AND i.name = 'Творог'
UNION ALL
SELECT r.id, i.id, 2, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Сырники' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Сырники' AND i.name = 'Мука пшеничная'
UNION ALL
SELECT r.id, i.id, 50, 'г' FROM recipes r, ingredients i WHERE r.title = 'Сырники' AND i.name = 'Сахар'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Сырники' AND i.name = 'Соль'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Сырники' AND i.name = 'Сметана';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Пицца Маргарита' AND i.name = 'Мука пшеничная'
UNION ALL
SELECT r.id, i.id, 7, 'г' FROM recipes r, ingredients i WHERE r.title = 'Пицца Маргарита' AND i.name = 'Дрожжи сухие'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Пицца Маргарита' AND i.name = 'Помидоры'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Пицца Маргарита' AND i.name = 'Сыр моцарелла'
UNION ALL
SELECT r.id, i.id, 20, 'г' FROM recipes r, ingredients i WHERE r.title = 'Пицца Маргарита' AND i.name = 'Базилик свежий'
UNION ALL
SELECT r.id, i.id, 30, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Пицца Маргарита' AND i.name = 'Масло оливковое'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Пицца Маргарита' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 600, 'г' FROM recipes r, ingredients i WHERE r.title = 'Запечённый лосось' AND i.name = 'Лосось филе'
UNION ALL
SELECT r.id, i.id, 1, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Запечённый лосось' AND i.name = 'Лимон'
UNION ALL
SELECT r.id, i.id, 3, 'зуб' FROM recipes r, ingredients i WHERE r.title = 'Запечённый лосось' AND i.name = 'Чеснок'
UNION ALL
SELECT r.id, i.id, 30, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Запечённый лосось' AND i.name = 'Масло оливковое'
UNION ALL
SELECT r.id, i.id, 20, 'г' FROM recipes r, ingredients i WHERE r.title = 'Запечённый лосось' AND i.name = 'Зелень укропа'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Запечённый лосось' AND i.name = 'Соль'
UNION ALL
SELECT r.id, i.id, 0.5, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Запечённый лосось' AND i.name = 'Перец чёрный молотый';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Окрошка' AND i.name = 'Картофель'
UNION ALL
SELECT r.id, i.id, 3, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Окрошка' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Окрошка' AND i.name = 'Огурцы'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Окрошка' AND i.name = 'Колбаса варёная'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Окрошка' AND i.name = 'Редис'
UNION ALL
SELECT r.id, i.id, 1000, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Окрошка' AND i.name = 'Кефир'
UNION ALL
SELECT r.id, i.id, 30, 'г' FROM recipes r, ingredients i WHERE r.title = 'Окрошка' AND i.name = 'Зелень укропа'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Окрошка' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Том-ям' AND i.name = 'Курица филе'
UNION ALL
SELECT r.id, i.id, 400, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Том-ям' AND i.name = 'Кокосовое молоко'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Том-ям' AND i.name = 'Грибы шампиньоны'
UNION ALL
SELECT r.id, i.id, 30, 'г' FROM recipes r, ingredients i WHERE r.title = 'Том-ям' AND i.name = 'Лимонная трава'
UNION ALL
SELECT r.id, i.id, 1, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Том-ям' AND i.name = 'Лайм'
UNION ALL
SELECT r.id, i.id, 30, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Том-ям' AND i.name = 'Рыбный соус'
UNION ALL
SELECT r.id, i.id, 2, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Том-ям' AND i.name = 'Перец чили';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Макароны'
UNION ALL
SELECT r.id, i.id, 500, 'г' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Говядина'
UNION ALL
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Помидоры'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 3, 'зуб' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Чеснок'
UNION ALL
SELECT r.id, i.id, 500, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Молоко'
UNION ALL
SELECT r.id, i.id, 60, 'г' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Мука пшеничная'
UNION ALL
SELECT r.id, i.id, 60, 'г' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Масло сливочное'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Сыр твёрдый'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Лазанья болоньезе' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 500, 'г' FROM recipes r, ingredients i WHERE r.title = 'Плов узбекский' AND i.name = 'Рис'
UNION ALL
SELECT r.id, i.id, 600, 'г' FROM recipes r, ingredients i WHERE r.title = 'Плов узбекский' AND i.name = 'Баранина'
UNION ALL
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Плов узбекский' AND i.name = 'Морковь'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Плов узбекский' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 1, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Плов узбекский' AND i.name = 'Чеснок'
UNION ALL
SELECT r.id, i.id, 100, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Плов узбекский' AND i.name = 'Масло растительное'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Плов узбекский' AND i.name = 'Зира'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Плов узбекский' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 4, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Омлет' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 100, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Омлет' AND i.name = 'Молоко'
UNION ALL
SELECT r.id, i.id, 20, 'г' FROM recipes r, ingredients i WHERE r.title = 'Омлет' AND i.name = 'Масло сливочное'
UNION ALL
SELECT r.id, i.id, 0.5, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Омлет' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Помидоры'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Морковь'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 3, 'зуб' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Чеснок'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Кабачок'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Сельдерей'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Фасоль белая консервированная'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Макароны'
UNION ALL
SELECT r.id, i.id, 40, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Масло оливковое'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Суп минестроне' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 2, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Гуакамоле' AND i.name = 'Авокадо'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Гуакамоле' AND i.name = 'Помидоры'
UNION ALL
SELECT r.id, i.id, 80, 'г' FROM recipes r, ingredients i WHERE r.title = 'Гуакамоле' AND i.name = 'Лук красный'
UNION ALL
SELECT r.id, i.id, 1, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Гуакамоле' AND i.name = 'Лайм'
UNION ALL
SELECT r.id, i.id, 20, 'г' FROM recipes r, ingredients i WHERE r.title = 'Гуакамоле' AND i.name = 'Кинза'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Гуакамоле' AND i.name = 'Соль'
UNION ALL
SELECT r.id, i.id, 1, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Гуакамоле' AND i.name = 'Перец чили';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Рамен с курицей' AND i.name = 'Курица филе'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Рамен с курицей' AND i.name = 'Лапша рамен'
UNION ALL
SELECT r.id, i.id, 2, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Рамен с курицей' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 60, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Рамен с курицей' AND i.name = 'Соевый соус'
UNION ALL
SELECT r.id, i.id, 20, 'г' FROM recipes r, ingredients i WHERE r.title = 'Рамен с курицей' AND i.name = 'Имбирь'
UNION ALL
SELECT r.id, i.id, 3, 'зуб' FROM recipes r, ingredients i WHERE r.title = 'Рамен с курицей' AND i.name = 'Чеснок'
UNION ALL
SELECT r.id, i.id, 50, 'г' FROM recipes r, ingredients i WHERE r.title = 'Рамен с курицей' AND i.name = 'Зелёный лук'
UNION ALL
SELECT r.id, i.id, 15, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Рамен с курицей' AND i.name = 'Масло кунжутное';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 500, 'г' FROM recipes r, ingredients i WHERE r.title = 'Тирамису' AND i.name = 'Маскарпоне'
UNION ALL
SELECT r.id, i.id, 4, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Тирамису' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Тирамису' AND i.name = 'Сахар'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Тирамису' AND i.name = 'Печенье савоярди'
UNION ALL
SELECT r.id, i.id, 200, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Тирамису' AND i.name = 'Кофе эспрессо'
UNION ALL
SELECT r.id, i.id, 30, 'г' FROM recipes r, ingredients i WHERE r.title = 'Тирамису' AND i.name = 'Какао порошок';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 500, 'г' FROM recipes r, ingredients i WHERE r.title = 'Торт Наполеон' AND i.name = 'Мука пшеничная'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Торт Наполеон' AND i.name = 'Масло сливочное'
UNION ALL
SELECT r.id, i.id, 2, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Торт Наполеон' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 1000, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Торт Наполеон' AND i.name = 'Молоко'
UNION ALL
SELECT r.id, i.id, 200, 'г' FROM recipes r, ingredients i WHERE r.title = 'Торт Наполеон' AND i.name = 'Сахар'
UNION ALL
SELECT r.id, i.id, 0.5, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Торт Наполеон' AND i.name = 'Соль'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Торт Наполеон' AND i.name = 'Ванилин';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 500, 'г' FROM recipes r, ingredients i WHERE r.title = 'Куриный суп' AND i.name = 'Курица филе'
UNION ALL
SELECT r.id, i.id, 300, 'г' FROM recipes r, ingredients i WHERE r.title = 'Куриный суп' AND i.name = 'Картофель'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Куриный суп' AND i.name = 'Морковь'
UNION ALL
SELECT r.id, i.id, 150, 'г' FROM recipes r, ingredients i WHERE r.title = 'Куриный суп' AND i.name = 'Лук репчатый'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Куриный суп' AND i.name = 'Макароны'
UNION ALL
SELECT r.id, i.id, 30, 'г' FROM recipes r, ingredients i WHERE r.title = 'Куриный суп' AND i.name = 'Зелень петрушки'
UNION ALL
SELECT r.id, i.id, 1, 'ч.л.' FROM recipes r, ingredients i WHERE r.title = 'Куриный суп' AND i.name = 'Соль';

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit)
SELECT r.id, i.id, 400, 'г' FROM recipes r, ingredients i WHERE r.title = 'Жареный рис по-азиатски' AND i.name = 'Рис'
UNION ALL
SELECT r.id, i.id, 3, 'шт' FROM recipes r, ingredients i WHERE r.title = 'Жареный рис по-азиатски' AND i.name = 'Яйца куриные'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Жареный рис по-азиатски' AND i.name = 'Морковь'
UNION ALL
SELECT r.id, i.id, 100, 'г' FROM recipes r, ingredients i WHERE r.title = 'Жареный рис по-азиатски' AND i.name = 'Горошек зелёный консервированный'
UNION ALL
SELECT r.id, i.id, 60, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Жареный рис по-азиатски' AND i.name = 'Соевый соус'
UNION ALL
SELECT r.id, i.id, 15, 'мл' FROM recipes r, ingredients i WHERE r.title = 'Жареный рис по-азиатски' AND i.name = 'Масло кунжутное'
UNION ALL
SELECT r.id, i.id, 50, 'г' FROM recipes r, ingredients i WHERE r.title = 'Жареный рис по-азиатски' AND i.name = 'Зелёный лук'
UNION ALL
SELECT r.id, i.id, 3, 'зуб' FROM recipes r, ingredients i WHERE r.title = 'Жареный рис по-азиатски' AND i.name = 'Чеснок';
