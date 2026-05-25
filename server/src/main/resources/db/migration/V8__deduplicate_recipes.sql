DELETE FROM recipes
WHERE image_url IS NULL
  AND title IN (
    SELECT title FROM recipes WHERE image_url LIKE 'local://%'
  );

UPDATE recipes SET image_url = 'local://dish_borscht' WHERE title = 'Борщ' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_kotlety' WHERE title = 'Котлеты по-домашнему' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_bliny' WHERE title = 'Блины' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_pelmeni' WHERE title = 'Пельмени' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_olivier' WHERE title = 'Салат Оливье' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_carbonara' WHERE title = 'Паста Карбонара' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_greek_salad' WHERE title = 'Греческий салат' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_teriyaki' WHERE title = 'Курица терияки' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_buckwheat' WHERE title = 'Гречневая каша с грибами' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_shashlik' WHERE title = 'Шашлык из курицы' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_risotto' WHERE title = 'Ризотто с грибами' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_syrniki' WHERE title = 'Сырники' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_pizza' WHERE title = 'Пицца Маргарита' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_salmon' WHERE title = 'Запечённый лосось' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_okroshka' WHERE title = 'Окрошка' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_tomyam' WHERE title = 'Том-ям' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_lasagna' WHERE title = 'Лазанья болоньезе' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_plov' WHERE title = 'Плов узбекский' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_omlet' WHERE title = 'Омлет' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_minestrone' WHERE title = 'Суп минестроне' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_guacamole' WHERE title = 'Гуакамоле' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_ramen' WHERE title = 'Рамен с курицей' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_tiramisu' WHERE title = 'Тирамису' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_napoleon' WHERE title = 'Торт Наполеон' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_chicken_soup' WHERE title = 'Куриный суп' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
UPDATE recipes SET image_url = 'local://dish_fried_rice' WHERE title = 'Жареный рис по-азиатски' AND (image_url IS NULL OR image_url NOT LIKE 'local://%');
