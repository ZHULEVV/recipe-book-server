CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS users (
    id            UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    firebase_uid  VARCHAR(128) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    display_name  VARCHAR(255),
    is_subscriber BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CONSTRAINT users_firebase_uid_unique UNIQUE (firebase_uid)
);

CREATE INDEX IF NOT EXISTS idx_users_firebase_uid ON users (firebase_uid);

CREATE TABLE IF NOT EXISTS ingredients (
    id           UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    name         VARCHAR(255) NOT NULL,
    default_unit VARCHAR(50)  NOT NULL,
    CONSTRAINT ingredients_name_unique UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS tags (
    id       UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    name     VARCHAR(100) NOT NULL,
    category VARCHAR(20)  NOT NULL CHECK (category IN ('DIET', 'CUISINE', 'ALLERGEN')),
    CONSTRAINT tags_name_unique UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS recipes (
    id                UUID             PRIMARY KEY DEFAULT gen_random_uuid(),
    title             VARCHAR(255)     NOT NULL,
    description       TEXT             NOT NULL,
    cooking_time_min  INTEGER          NOT NULL CHECK (cooking_time_min > 0),
    active_time_min   INTEGER          NOT NULL CHECK (active_time_min > 0),
    difficulty        VARCHAR(10)      NOT NULL CHECK (difficulty IN ('EASY', 'MEDIUM', 'HARD')),
    base_servings     INTEGER          NOT NULL DEFAULT 4 CHECK (base_servings > 0),
    calories_per_100g DOUBLE PRECISION,
    protein_per_100g  DOUBLE PRECISION,
    fat_per_100g      DOUBLE PRECISION,
    carbs_per_100g    DOUBLE PRECISION,
    image_url         VARCHAR(512),
    author_id         UUID             NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    is_published      BOOLEAN          NOT NULL DEFAULT FALSE,
    created_at        TIMESTAMPTZ      NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_recipes_title        ON recipes (title);
CREATE INDEX IF NOT EXISTS idx_recipes_author_id    ON recipes (author_id);
CREATE INDEX IF NOT EXISTS idx_recipes_is_published ON recipes (is_published);
CREATE INDEX IF NOT EXISTS idx_recipes_difficulty   ON recipes (difficulty);

CREATE TABLE IF NOT EXISTS recipe_ingredients (
    recipe_id     UUID             NOT NULL REFERENCES recipes (id) ON DELETE CASCADE,
    ingredient_id UUID             NOT NULL REFERENCES ingredients (id) ON DELETE CASCADE,
    amount        DOUBLE PRECISION NOT NULL CHECK (amount > 0),
    unit          VARCHAR(50)      NOT NULL,
    PRIMARY KEY (recipe_id, ingredient_id)
);

CREATE INDEX IF NOT EXISTS idx_recipe_ingredients_ingredient_id ON recipe_ingredients (ingredient_id);

CREATE TABLE IF NOT EXISTS recipe_tags (
    recipe_id UUID NOT NULL REFERENCES recipes (id) ON DELETE CASCADE,
    tag_id    UUID NOT NULL REFERENCES tags (id) ON DELETE CASCADE,
    PRIMARY KEY (recipe_id, tag_id)
);

CREATE TABLE IF NOT EXISTS steps (
    id          UUID    PRIMARY KEY DEFAULT gen_random_uuid(),
    recipe_id   UUID    NOT NULL REFERENCES recipes (id) ON DELETE CASCADE,
    step_number INTEGER NOT NULL CHECK (step_number > 0),
    content     TEXT    NOT NULL,
    CONSTRAINT steps_recipe_step_unique UNIQUE (recipe_id, step_number)
);

CREATE INDEX IF NOT EXISTS idx_steps_recipe_id ON steps (recipe_id);

CREATE TABLE IF NOT EXISTS favorites (
    user_id   UUID        NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    recipe_id UUID        NOT NULL REFERENCES recipes (id) ON DELETE CASCADE,
    added_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    PRIMARY KEY (user_id, recipe_id)
);

CREATE TABLE IF NOT EXISTS ratings (
    id        UUID    PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id   UUID    NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    recipe_id UUID    NOT NULL REFERENCES recipes (id) ON DELETE CASCADE,
    score     INTEGER NOT NULL CHECK (score BETWEEN 1 AND 5),
    CONSTRAINT ratings_user_recipe_unique UNIQUE (user_id, recipe_id)
);

CREATE INDEX IF NOT EXISTS idx_ratings_recipe_id ON ratings (recipe_id);

CREATE TABLE IF NOT EXISTS comments (
    id         UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id    UUID        NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    recipe_id  UUID        NOT NULL REFERENCES recipes (id) ON DELETE CASCADE,
    text       TEXT        NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_comments_recipe_id ON comments (recipe_id);

CREATE TABLE IF NOT EXISTS meal_plan_entries (
    id        UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id   UUID        NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    recipe_id UUID        NOT NULL REFERENCES recipes (id) ON DELETE CASCADE,
    plan_date DATE        NOT NULL,
    meal_type VARCHAR(20) NOT NULL CHECK (meal_type IN ('BREAKFAST', 'LUNCH', 'DINNER', 'SNACK'))
);

CREATE INDEX IF NOT EXISTS idx_meal_plan_user_date ON meal_plan_entries (user_id, plan_date);

CREATE TABLE IF NOT EXISTS user_excluded_ingredients (
    user_id       UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    ingredient_id UUID NOT NULL REFERENCES ingredients (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, ingredient_id)
);
