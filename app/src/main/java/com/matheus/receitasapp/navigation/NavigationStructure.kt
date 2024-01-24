package com.matheus.receitasapp.navigation

object AuthGraph {
    const val ROOT = "auth_graph"
    const val LOGIN = "login"
    const val SIGN_UP = "sign_up"
    const val FORGOT_PASSWORD = "forgot_password"
}

object RecipeDetailsGraph {
    const val ROOT = "recipe_details_graph"
    const val DETAILS = "details"
}

object HomeGraph {
    const val ROOT = "home_graph"
}

object RootGraph {
    const val ROOT = "root_graph"
}

object AppGraph {
    val initial = RootGraph
    val auth = AuthGraph
    val home = HomeGraph
    val recipe_details = RecipeDetailsGraph
}

/************************/
object NavDestinations {

    const val MAIN_APP = "MAIN_APP"

    object Splash {
        const val SPLASH_MAIN = "splash_main"
        const val SPLASH = "splash"
    }

    object Auth {
        const val ONBOARDING = "onboarding"
        const val SIGN_UP = "sign_up"
        const val AUTH_MAIN = "auth_main"
        const val AUTH = "auth"
        const val AUTHENTICATION = "authentication"
        const val REGISTRATION_MAIN = "registration_main"
        const val REGISTRATION = "registration"
    }

    object Root {
        const val ROOT = "root"
    }

    object Main {
        const val MAIN = "main"
    }

    object RecipeDetails {
        const val RECIPE_DETAILS_MAIN = "recipe_details_main"
        const val RECIPE_DETAILS = "recipe_details"
    }

    object Filters {
        const val FILTERS_MAIN = "filters_main"
        const val FILTERS = "filters"
    }

    object SearchRecipes {
        const val SEARCH_RECIPES_MAIN = "search_recipes_main"
        const val SEARCH_RECIPES = "search_recipes"
    }

    object TopRecipes {
        const val TOP_RECIPES_MAIN = "top_recipes_main"
        const val TOP_RECIPES = "top_recipes"
    }

    object Settings {
        const val SETTINGS_MAIN = "settings_main"
        const val SETTINGS = "settings"
    }

}
