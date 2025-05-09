package com.tekri.grunts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tekri.grunts.data.ChatDatabase
import com.tekri.grunts.data.UserDatabase
import com.tekri.grunts.repository.ChatRepository
import com.tekri.grunts.repository.UserRepository
import com.tekri.grunts.ui.screens.about.AboutScreen
import com.tekri.grunts.ui.screens.auth.LoginScreen
import com.tekri.grunts.ui.screens.auth.RegisterScreen
import com.tekri.grunts.ui.screens.cart.CartScreen
import com.tekri.grunts.ui.screens.communicate.CommuneScreen
import com.tekri.grunts.ui.screens.contact.ContactScreen
import com.tekri.grunts.ui.screens.home.HomeScreen
import com.tekri.grunts.ui.screens.people.PictureScreen
import com.tekri.grunts.ui.screens.people.ProfileScreen
import com.tekri.grunts.ui.screens.splash.SplashScreen
import com.tekri.grunts.viewmodel.AuthViewModel
import com.tekri.grunts.viewmodel.ChatViewModel
import com.tekri.grunts.viewmodel.ChatViewModelFactory

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_COMMUNE
) {
    val context= LocalContext.current


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
        composable(ROUT_PROFILE) {
            ProfileScreen(navController)
        }
        composable(ROUT_SPLASH) {
           SplashScreen(navController)
        }
        composable(ROUT_PICTURE) {
            PictureScreen(navController)
        }
        composable(ROUT_CART) {
            CartScreen(navController)
        }

        composable(ROUT_ADDPROFILE) {
            CartScreen(navController)
        }



        composable(ROUT_COMMUNE) {
            val chatDatabase = ChatDatabase.getDatabase(LocalContext.current)
            val chatRepository = ChatRepository(chatDatabase.messageDao())
            val chatViewModel: ChatViewModel = viewModel(factory = ChatViewModelFactory(chatRepository))

            CommuneScreen(chatViewModel = chatViewModel) // ✅ Pass the instance
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