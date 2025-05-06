package com.tekri.grunts.ui.screens.people

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tekri.grunts.R
import com.tekri.grunts.navigation.ROUT_CART
import com.tekri.grunts.navigation.ROUT_HOME
import com.tekri.grunts.navigation.ROUT_PICTURE
import com.tekri.grunts.navigation.ROUT_PROFILE
import com.tekri.grunts.ui.theme.brown
import com.tekri.grunts.ui.theme.cream
import com.tekri.grunts.ui.theme.oceanblue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){

       //Scaffold

       var selectedIndex by remember { mutableIntStateOf(0) }

       Scaffold(
           //TopBar
           topBar = {
               TopAppBar(
                   title = { Text("SEARCH") },
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
                       label = { Text("info", color = cream) },
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
                   //searchBar
                   var search by remember { mutableStateOf("") }
                   OutlinedTextField(
                       value = search,
                       onValueChange = {search=it},
                       modifier = Modifier.fillMaxWidth().padding(start = 5.dp, end = 5.dp, top = 5.dp),
                       leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                       placeholder = { Text(text="Search...") },
                       shape = RoundedCornerShape(16.dp), // Rounded corners

                   )

                   //End of Searchbar

                   Row(
                       modifier = Modifier.padding(start = 20.dp, top = 30.dp),
                   ) {
                       Card (
                           modifier = Modifier.width(150.dp)
                               .height(220.dp),

                           ){

                           Column(
                               modifier = Modifier.fillMaxSize(),
                               horizontalAlignment = Alignment.CenterHorizontally,
                               verticalArrangement = Arrangement.Center

                           ) {
                               Image(
                                   painter = painterResource(R.drawable.profile),
                                   contentDescription = "profile",
                                   modifier = Modifier.size(100.dp)
                                       .clip(androidx.compose.foundation.shape.CircleShape), // Clip to Circle
                                   contentScale = ContentScale.Crop // Ensures image is cropped to fit the circle



                               )

                               Text(text = "Abel Kamau", fontSize = 15.sp, textAlign = TextAlign.Left)
                               Text(text = "Kisumu", fontSize = 15.sp, textAlign = TextAlign.Left)
                               Text(text = "Technician", fontSize = 15.sp, textAlign = TextAlign.Left)
                               Spacer(modifier = Modifier.height(5.dp))

                               Row(
                               ) {
                                   IconButton(
                                       onClick = {},
                                   ) {Icon(Icons.Default.Info
                                       , contentDescription = "Profile")

                                   }

                                   IconButton(
                                       onClick = {},
                                   ) {Icon(Icons.Default.CheckCircle
                                       , contentDescription = "Profile")

                                   }
                               }



                           }







                       }
                       Spacer(modifier = Modifier.width(50.dp))
                       Card(
                           modifier = Modifier.width(150.dp)
                               .height(220.dp),
                       ) {
                           Column(
                               modifier = Modifier.fillMaxSize(),
                               horizontalAlignment = Alignment.CenterHorizontally,
                               verticalArrangement = Arrangement.Center

                           ) {
                               Image(
                                   painter = painterResource(R.drawable.profile),
                                   contentDescription = "profile",
                                   modifier = Modifier.size(100.dp)
                                       .clip(androidx.compose.foundation.shape.CircleShape), // Clip to Circle
                                   contentScale = ContentScale.Crop // Ensures image is cropped to fit the circle



                               )

                               Text(text = "Keegan Kyle", fontSize = 15.sp, textAlign = TextAlign.Left)
                               Text(text = "Mombasa", fontSize = 15.sp, textAlign = TextAlign.Left)
                               Text(text = "Plumber", fontSize = 15.sp, textAlign = TextAlign.Left)
                               Spacer(modifier = Modifier.height(5.dp))

                               Row(
                               ) {
                                   IconButton(
                                       onClick = {},
                                   ) {Icon(Icons.Default.Info
                                       , contentDescription = "Profile")

                                   }

                                   IconButton(
                                       onClick = {},
                                   ) {Icon(Icons.Default.CheckCircle
                                       , contentDescription = "Profile")

                                   }
                               }

                           }
                       }
                   }















               }
           }

       )

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(navController= rememberNavController())
}