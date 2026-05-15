# Recipe Book Server

REST API backend для мобильного приложения книги рецептов. Kotlin + Ktor, Clean Architecture, Firebase Auth, PostgreSQL.

---

## Технологии

| Слой | Технология |
|---|---|
| Язык | Kotlin 2.1.20 |
| Фреймворк | Ktor 3.1.3 (Netty) |
| База данных | PostgreSQL (Neon.tech) |
| ORM | Exposed 0.60.0 |
| Пул соединений | HikariCP 6.3.0 |
| Миграции | Flyway 11.8.2 |
| Авторизация | Firebase Admin SDK 9.4.3 |
| DI | Koin 4.0.4 |
| Сериализация | kotlinx.serialization |
| Логирование | Logback |
| Тесты | JUnit 5, MockK, H2 |
| Контейнеризация | Docker, Docker Compose |

---

## Архитектура

Проект построен по принципам Clean Architecture и разделён на три слоя:

```
domain/        — сущности, интерфейсы репозиториев, use cases
data/          — реализации репозиториев, Exposed-таблицы, DatabaseFactory
presentation/  — DTO, маппинг, Ktor-маршруты
application/   — DI-модули, Firebase, Ktor-плагины, точка входа
```

---

## API

Базовый префикс: `/api/v1`

Публичные эндпоинты (без токена):

| Метод | Путь | Описание |
|---|---|---|
| GET | `/health` | Проверка состояния сервера |
| GET | `/api/v1/ingredients` | Список всех ингредиентов |
| GET | `/api/v1/tags` | Список всех тегов |

Все остальные эндпоинты требуют заголовок `Authorization: Bearer <Firebase ID Token>`.

### Пользователи

| Метод | Путь | Описание |
|---|---|---|
| GET | `/users/me` | Профиль текущего пользователя |
| PUT | `/users/me` | Обновить displayName |
| GET | `/users/me/excluded-ingredients` | Список исключённых ингредиентов |
| POST | `/users/me/excluded-ingredients/{id}` | Добавить исключённый ингредиент |
| DELETE | `/users/me/excluded-ingredients/{id}` | Удалить исключённый ингредиент |

### Рецепты

| Метод | Путь | Описание |
|---|---|---|
| GET | `/recipes` | Список рецептов (пагинация, фильтр по difficulty и tagId) |
| GET | `/recipes/{id}` | Рецепт по ID |
| GET | `/recipes/my` | Мои рецепты |
| POST | `/recipes` | Создать рецепт |
| PUT | `/recipes/{id}` | Обновить рецепт (только автор) |
| DELETE | `/recipes/{id}` | Удалить рецепт (только автор) |
| POST | `/recipes/{id}/publish` | Опубликовать рецепт (только автор) |

Query-параметры для `GET /recipes`: `page`, `size`, `difficulty` (EASY/MEDIUM/HARD), `tagId` (повторяемый).

### Избранное

| Метод | Путь | Описание |
|---|---|---|
| GET | `/favorites` | Мои избранные рецепты |
| POST | `/favorites/{recipeId}` | Добавить в избранное |
| DELETE | `/favorites/{recipeId}` | Удалить из избранного |

### Комментарии

| Метод | Путь | Описание |
|---|---|---|
| GET | `/recipes/{recipeId}/comments` | Комментарии к рецепту |
| POST | `/recipes/{recipeId}/comments` | Добавить комментарий |
| DELETE | `/comments/{commentId}` | Удалить комментарий (только автор) |

### Оценки

| Метод | Путь | Описание |
|---|---|---|
| GET | `/recipes/{recipeId}/rating` | Средняя оценка рецепта |
| POST | `/recipes/{recipeId}/rating` | Поставить оценку (1–5) |

### Ингредиенты и теги

| Метод | Путь | Описание |
|---|---|---|
| GET | `/ingredients` | Все ингредиенты |
| POST | `/ingredients` | Создать ингредиент |
| GET | `/tags` | Все теги |

### План питания

| Метод | Путь | Описание |
|---|---|---|
| GET | `/meal-plan?from=YYYY-MM-DD&to=YYYY-MM-DD` | План питания за период |
| POST | `/meal-plan` | Добавить запись в план |
| DELETE | `/meal-plan/{id}` | Удалить запись из плана |

---

## Авторизация

Используется Firebase Authentication (Email/Password).

Клиент получает ID-токен через Firebase SDK и передаёт его в заголовке:

```
Authorization: Bearer <Firebase ID Token>
```

Сервер верифицирует токен через Firebase Admin SDK. При первом запросе пользователь автоматически создаётся в базе данных.

---

## Переменные окружения

Скопируй `.env.example` в `.env` и заполни:

```env
DATABASE_URL=jdbc:postgresql://host/dbname?sslmode=require
PORT=8080
DB_POOL_SIZE=5
FIREBASE_CREDENTIALS_PATH=firebase-service-account.json
BASE_URL=http://localhost:8080
```

Файл `firebase-service-account.json` нужно скачать в Firebase Console → Настройки проекта → Сервисные аккаунты.

---

## Запуск

### Локально

```bash
export $(cat .env | xargs)
./gradlew :server:run
```

### Docker Compose

```bash
docker-compose up --build
```

Поднимет приложение на порту `8080` и PostgreSQL на порту `5433`.

### Только сборка

```bash
./gradlew :server:build
```

---

## Проверка

```bash
# Health check
curl http://localhost:8080/health

# С токеном
curl -H "Authorization: Bearer <token>" \
     http://localhost:8080/api/v1/users/me
```

---

## Тесты

```bash
./gradlew :server:test
```

Юнит-тесты use cases — MockK. Интеграционные тесты репозиториев — H2 in-memory (PostgreSQL mode). Тесты маршрутов — Ktor TestApplication.

---

## Миграции базы данных

Flyway запускается автоматически при старте приложения.

| Файл | Содержимое |
|---|---|
| `V1__init.sql` | Полная схема: 12 таблиц |
| `V2__repair_schema.sql` | Патч для существующих баз |
| `V3__seed_data.sql` | Справочные данные: 30 ингредиентов, 15 тегов |
