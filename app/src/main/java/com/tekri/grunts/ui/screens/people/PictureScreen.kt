package com.tekri.grunts.ui.screens.people

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tekri.grunts.R
import com.tekri.grunts.model.Profile
import com.tekri.grunts.navigation.ROUT_CART
import com.tekri.grunts.navigation.ROUT_EDIT_PROFILE
import com.tekri.grunts.navigation.ROUT_HOME
import com.tekri.grunts.navigation.ROUT_PICTURE
import com.tekri.grunts.navigation.ROUT_PROFILE
import com.tekri.grunts.ui.theme.cream
import com.tekri.grunts.ui.theme.oceanblue
import com.tekri.grunts.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PictureScreen(
    navController: NavController,
    userId: Int?,
    profileViewModel: ProfileViewModel = viewModel()
) {

    // State to hold the fetched profile
    var userProfile by remember { mutableStateOf<Profile?>(null) }

    LaunchedEffect(userId) {
        if (userId != null) {
            // Fetch the profile using the ViewModel
            userProfile = profileViewModel.getProfileByUserId(userId)
        }
    }
    //Scaffold

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("CONTACT") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back/nav */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = oceanblue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        //BottomBar
        bottomBar = {
            NavigationBar(
                containerColor = oceanblue
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home", color = cream) },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites", color = cream) },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(ROUT_CART)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile", color = cream) },
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        navController.navigate(ROUT_PROFILE)
                    }


                )


            }
        },

        //FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = oceanblue
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        //content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())

            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                    colors = CardDefaults.cardColors(cream)


                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        userProfile?.let { profile ->
                            Image(
                                painter = rememberAsyncImagePainter(model = profile.imagePath),
                                contentDescription = "profile",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(androidx.compose.foundation.shape.CircleShape), // Clip to Circle
                                contentScale = ContentScale.Crop, // Ensures image is cropped to fit the circle
                                alignment = Alignment.Center
                            )


                            Text(text = profile.name, fontSize = 15.sp, textAlign = TextAlign.Left)
                            Text(
                                text = profile.description,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Left
                            )
                            Text(text = profile.phone, fontSize = 15.sp, textAlign = TextAlign.Left)
                        }



                        IconButton(
                            onClick = {
                                navController.navigate(
                                    "$ROUT_EDIT_PROFILE/${userProfile?.id}"
                                )
                            },
                            modifier = Modifier.align(alignment = Alignment.Start)
                        ) {
                            Icon(
                                Icons.Default.Edit, contentDescription = "Profile"
                            )

                        }


                    }
                }


            }
        }
    )

    //End of scaffold


}

@Preview(showBackground = true)
@Composable
fun PictureScreenPreview() {
    PictureScreen(
        navController = rememberNavController(),
        userId = 1,
        profileViewModel = ProfileViewModel(LocalContext.current)
    )
}