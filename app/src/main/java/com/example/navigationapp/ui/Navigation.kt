package com.example.navigationapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationapp.ui.theme.NavigationAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = Screens.find { it.route == currentDestination?.route} ?: Screen1

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavigationAppTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerMenu(
                    onItemClick = {
                        navController.navigateSingleTopTo(it)
                        scope.launch { drawerState.close() }
                    }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    PokemonTopBar(
                        //currentScreen = currentScreen,
                        drawerButtonOnClick = { scope.launch { drawerState.open() } },
                    )
                },
                bottomBar = {
                    PokemonBottomBar(
                        bottomBarButtonOnClick = {
                            navController.navigateSingleTopTo(it)
                        }
                        //currentScreen = currentScreen,
                        //navController = navController,
                        //tabs
                    )
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen1.route,
                    modifier = Modifier.padding(it)
                ) {
                    composable(Screen1.route) {
                        Screen1 { navController.navigateSingleTopTo(Screen2.route) }
                    }
                    composable(Screen2.route) {
                        Screen2 { navController.navigateSingleTopTo(Screen3.route) }
                    }
                    composable(Screen3.route) {
                        Screen3 { navController.navigateSingleTopTo(Screen1.route) }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopBar(
    //currentScreen: PokemonDestination,
    drawerButtonOnClick: () -> Unit,
) {
    TopAppBar(
        title = {
            //Text(text = currentScreen.route)
        },
        navigationIcon = {
            IconButton(onClick = drawerButtonOnClick) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Open Drawer"
                )
            }
        }
    )
}

@Composable
fun PokemonBottomBar(
    //currentScreen: PokemonDestination,
    //navController: NavController,
    bottomBarButtonOnClick: (String) -> Unit
    //tabs: List<PokemonDestination> = PokemonScreens
) {
    BottomAppBar(
        actions = {
            //tabs.forEach {
            IconButton(onClick = { bottomBarButtonOnClick(Screen1.route) }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = { bottomBarButtonOnClick(Screen2.route) }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Localized description"

                )
            }
            IconButton(onClick = { bottomBarButtonOnClick(Screen3.route) }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Localized description"
                )
            }
            //}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Favorite, "Localized description")
            }
        }
    )
}

@Composable
fun DrawerMenu(
    onItemClick: (String) -> Unit
) {
    Column (
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxHeight()
            .fillMaxWidth(0.7f)
    ) {
        DrawerMenuItem(
            icon = Icons.Filled.Add,
            onClick = { onItemClick(Screen1.route) },
            text = "Screen1"
        )
        DrawerMenuItem(
            icon = Icons.Filled.Add,
            onClick = { onItemClick(Screen2.route) },
            text = "Screen2"
        )
        DrawerMenuItem(
            icon = Icons.Filled.Add,
            onClick = { onItemClick(Screen3.route) },
            text = "Screen3"
        )
    }
}

@Composable
fun DrawerMenuItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}

@Composable
fun Screen1(onNextClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 1", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNextClicked) {
            Text("Next")
        }
    }
}

@Composable
fun Screen2(onNextClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 2", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNextClicked) {
            Text("Next")
        }
    }
}

@Composable
fun Screen3(onNextClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen 3", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNextClicked) {
            Text("Back to Start")
        }
    }
}

        fun NavHostController.navigateSingleTopTo(route: String) =
            this.navigate(route) { launchSingleTop = true }