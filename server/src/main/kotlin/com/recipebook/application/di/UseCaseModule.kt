package com.recipebook.application.di

import com.recipebook.domain.usecase.comment.*
import com.recipebook.domain.usecase.favorite.*
import com.recipebook.domain.usecase.ingredient.*
import com.recipebook.domain.usecase.mealplan.*
import com.recipebook.domain.usecase.rating.*
import com.recipebook.domain.usecase.recipe.*
import com.recipebook.domain.usecase.tag.*
import com.recipebook.domain.usecase.user.*
import org.koin.dsl.module

val useCaseModule = module {
    single { UpsertUserUseCase(get()) }
    single { GetUserProfileUseCase(get()) }
    single { UpdateUserProfileUseCase(get()) }
    single { GetExcludedIngredientsUseCase(get()) }
    single { AddExcludedIngredientUseCase(get(), get()) }
    single { RemoveExcludedIngredientUseCase(get()) }

    single { GetRecipesUseCase(get()) }
    single { SearchRecipesUseCase(get()) }
    single { GetRecipeByIdUseCase(get()) }
    single { GetMyRecipesUseCase(get()) }
    single { CreateRecipeUseCase(get()) }
    single { UpdateRecipeUseCase(get()) }
    single { DeleteRecipeUseCase(get()) }
    single { PublishRecipeUseCase(get()) }

    single { GetFavoritesUseCase(get()) }
    single { AddFavoriteUseCase(get(), get()) }
    single { RemoveFavoriteUseCase(get()) }

    single { RateRecipeUseCase(get(), get()) }
    single { GetRecipeRatingUseCase(get()) }

    single { GetCommentsUseCase(get()) }
    single { AddCommentUseCase(get(), get()) }
    single { DeleteCommentUseCase(get()) }

    single { GetMealPlanUseCase(get()) }
    single { AddMealPlanEntryUseCase(get(), get()) }
    single { DeleteMealPlanEntryUseCase(get()) }

    single { GetIngredientsUseCase(get()) }
    single { CreateIngredientUseCase(get()) }

    single { GetTagsUseCase(get()) }
}
