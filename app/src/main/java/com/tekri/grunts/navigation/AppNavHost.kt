package com.tekri.grunts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tekri.grunts.data.UserDatabase
import com.tekri.grunts.repository.UserRepository
import com.tekri.grunts.ui.screens.about.AboutScreen
import com.tekri.grunts.ui.screens.auth.LoginScreen
import com.tekri.grunts.ui.screens.auth.RegisterScreen
import com.tekri.grunts.ui.screens.cart.CartScreen
import com.tekri.grunts.ui.screens.contact.ContactScreen
import com.tekri.grunts.ui.screens.home.HomeScreen
import com.tekri.grunts.ui.screens.people.PictureScreen
import com.tekri.grunts.ui.screens.people.ProfileScreen
import com.tekri.grunts.ui.screens.profile.AddProfileScreen
import com.tekri.grunts.ui.screens.profile.EditProfileScreen
import com.tekri.grunts.ui.screens.splash.SplashScreen
import com.tekri.grunts.viewmodel.AuthViewModel
import com.tekri.grunts.viewmodel.ProfileViewModel
import com.tekri.grunts.viewmodel.ProfileViewModelFactory

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,
    profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(context = LocalContext.current))

) {
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_CONTACT) {
            ContactScreen(navController)
        }
        composable(route = ROUT_PROFILE) {
            ProfileScreen(navController = navController, profileViewModel)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(
            route = "$ROUT_PICTURE/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") // Retrieve the userId
            PictureScreen(navController, userId, profileViewModel)
        }
        composable(ROUT_CART) {
            CartScreen(navController)
        }

        composable(ROUT_ADDPROFILE) {
            AddProfileScreen(navController, profileViewModel)
        }
        composable(
            route = ROUT_EDIT_PROFILE,
            arguments = listOf(navArgument("profileId") { type = NavType.IntType })
        ) { backStackEntry ->
            val profileId = backStackEntry.arguments?.getInt("profileId")
            if (profileId != null) {
                EditProfileScreen(profileId = profileId, navController, profileViewModel)
            }
        }


        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }

    }
}