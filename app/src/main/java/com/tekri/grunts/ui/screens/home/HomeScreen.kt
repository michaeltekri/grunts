package com.tekri.grunts.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tekri.grunts.ui.theme.brown
import com.tekri.grunts.ui.theme.oceanblue
import com.tekri.grunts.R
import com.tekri.grunts.navigation.ROUT_CART
import com.tekri.grunts.navigation.ROUT_HOME
import com.tekri.grunts.navigation.ROUT_PICTURE
import com.tekri.grunts.navigation.ROUT_PROFILE
import com.tekri.grunts.ui.theme.cream


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    //Scaffold

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back/nav */ }) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Back")
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
                containerColor =oceanblue
            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home", color = cream) },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                         navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites", color = cream) },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                         navController.navigate(ROUT_CART)
                    }

                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile", color = cream) },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                          navController.navigate(ROUT_PICTURE)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Profile") },
                    label = { Text("search", color = cream) },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
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


                //Main Contents of the page
                Text(text = "Welcome to Grunt", fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic)
                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.width(20.dp))

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "The app consists of the following:"
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "CLEANERS",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Image(
                        painter = painterResource(R.drawable.cleaners),
                        contentDescription = "product",
                        modifier = Modifier.size(400.dp).padding(start = 10.dp, end = 10.dp)
                    )
                    Text(
                        text = "HANDYMEN",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = painterResource(R.drawable.main),
                        contentDescription = "product",
                        modifier = Modifier.size(400.dp).padding(start = 10.dp, end = 10.dp)
                    )
                    Text(
                        text = "EMPLOYER",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                    Image(
                        painter = painterResource(R.drawable.parent),
                        contentDescription = "product",
                        modifier = Modifier.size(400.dp).padding(start = 10.dp, end = 10.dp)
                    )




                }

            }


        }
    )

    //End of scaffold


    }




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController= rememberNavController())
}