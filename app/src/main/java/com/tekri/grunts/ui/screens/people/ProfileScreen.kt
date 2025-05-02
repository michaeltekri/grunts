package com.tekri.grunts.ui.screens.people

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tekri.grunts.R
import com.tekri.grunts.ui.theme.brown
import com.tekri.grunts.ui.theme.oceanblue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())


    ) {
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
                        containerColor = brown,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            },

            //BottomBar
            bottomBar = {
                NavigationBar(
                    containerColor = brown
                ){
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = selectedIndex == 0,
                        onClick = { selectedIndex = 0
                            // navController.navigate(ROUT_HOME)
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                        label = { Text("Favorites") },
                        selected = selectedIndex == 1,
                        onClick = { selectedIndex = 1
                            // navController.navigate(ROUT_HOME)
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = selectedIndex == 2,
                        onClick = { selectedIndex = 2
                            //  navController.navigate(ROUT_HOME)
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Info, contentDescription = "Profile") },
                        label = { Text("info") },
                        selected = selectedIndex == 2,
                        onClick = { selectedIndex = 2
                            //  navController.navigate(ROUT_HOME)
                        }
                    )

                }
            },

            //FloatingActionButton
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* Add action */ },
                    containerColor = brown
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
                ) {
                    //searchBar
                    var search by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = search,
                        onValueChange = {search=it},
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                        placeholder = { Text(text="Search...") }
                    )

                    //End of Searchbar
                    Card(
                        modifier = Modifier.fillMaxWidth().height(180.dp)
                            .padding(start = 20.dp,end=20.dp).offset(y=90.dp)
                    ) {
                        //CONTENTS OF CARD
                        Row {
                            //Image
                            Image(
                                painter = painterResource(R.drawable.profile),
                                contentDescription = "birds",
                                modifier = Modifier.size(100.dp),





                            )
                            Spacer(modifier = Modifier.width(20.dp))

                            Text(text = "a very large building or buildings containing a lot of stores and often restaurants, and usually with space around it outside for parking: Judy likes to hang out at the mall with her friends. A mall is also a street in a city or town with a lot of stores and that is closed to traffic."
                                , fontSize = 15.sp)

                        }
                    }















                }
            }
        )

        //End of scaffold
    }


}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(navController= rememberNavController())
}