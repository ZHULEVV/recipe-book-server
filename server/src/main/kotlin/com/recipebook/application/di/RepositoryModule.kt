package com.recipebook.application.di

import com.recipebook.data.repository.*
import com.recipebook.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single { RecipeRepositoryImpl() }
    single<RecipeRepository> { get<RecipeRepositoryImpl>() }
    single<UserRepository> { UserRepositoryImpl() }
    single<IngredientRepository> { IngredientRepositoryImpl() }
    single<TagRepository> { TagRepositoryImpl() }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get<RecipeRepositoryImpl>()) }
    single<RatingRepository> { RatingRepositoryImpl() }
    single<CommentRepository> { CommentRepositoryImpl() }
    single<MealPlanRepository> { MealPlanRepositoryImpl() }
}
