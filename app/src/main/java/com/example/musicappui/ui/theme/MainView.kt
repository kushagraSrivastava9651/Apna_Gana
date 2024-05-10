package com.example.musicappui.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.musicappui.MainViewModel
import com.example.musicappui.R
import com.example.musicappui.Screen
import com.example.musicappui.screensInBottom
import com.example.musicappui.screensInDrawer
import com.example.musicappui.ui.theme.AccountDialog
import com.example.musicappui.ui.theme.AccountView

import com.example.musicappui.ui.theme.BrowseView
import com.example.musicappui.ui.theme.HomeView
import com.example.musicappui.ui.theme.Item
import com.example.musicappui.ui.theme.Library
import com.example.musicappui.ui.theme.SubscriptionView
import com.example.musicappui.ui.theme.TitleDescriptionScreen
import com.example.musicappui.ui.theme.category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView() {

    val scaffoldState :ScaffoldState= rememberScaffoldState()
    val scope: CoroutineScope= rememberCoroutineScope()
    val viewModel:MainViewModel= viewModel( )

    val isSheetFullScreen by remember {
        mutableStateOf(false)
    }
    val modifier=if(isSheetFullScreen)Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    val currentScreen=remember{
        viewModel.current_screen.value
    }

    val dialogOpen= remember {
        mutableStateOf(false)
    }

    val title= remember {
        //change that with current screen title
        mutableStateOf(currentScreen.title)
    }


    val roundedCornerRadius=if(isSheetFullScreen)0.dp else 12.dp

    val modalSheetState= rememberModalBottomSheetState(initialValue= ModalBottomSheetValue.Hidden,
        confirmValueChange = {it!=ModalBottomSheetValue.HalfExpanded}
    )

    //on which view are currently are
    val controller: NavController= rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute =navBackStackEntry?.destination?.route

    val bottomBar :@Composable ()->Unit={
        if(currentScreen is Screen.DrawerScreen||currentScreen ==Screen.BottomScreen.Home){
            BottomNavigation(Modifier.wrapContentSize()) {
                    screensInBottom.forEach{
                        item->
                        val isSelected=currentRoute==item.bRoute
                        val tint =if(isSelected) Color.White
                        else Color.Black
                        BottomNavigationItem(
                            selected = currentRoute==item.bRoute,
                            onClick = {controller.navigate(item.bRoute)
                                      title.value=item.bTitle},
                            icon = {

                                Icon(tint=tint,contentDescription = item.bTitle,
                              painter =   painterResource(id = item.icon)) },
                                                       label = { Text(text = item.bTitle, color = tint)},
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.Black
                            )
                    }
            }
        }
    }


    ModalBottomSheetLayout(
        sheetState=modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent ={

           MoreBottomSheet(modifier=modifier)
    } ) {
        Scaffold(
            bottomBar=bottomBar,
            topBar = {
                TopAppBar(title = { Text(title.value) },
                    actions = {
                              IconButton(onClick = {
                                  scope.launch {
                                      if(modalSheetState.isVisible)
                                          modalSheetState.hide()
                                      else
                                          modalSheetState.show()
                                  }
                              }) {
                                   Icon(imageVector = Icons.Default.MoreVert,contentDescription = null)
                              }
                    },
                    navigationIcon = { IconButton(onClick = {
                        //open the drawer
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
                    }}
                )
            },
            scaffoldState=scaffoldState,
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)){
                    items(screensInDrawer){
                            item->
                        DrawerIem(selected = currentRoute==item.dRoute, item = item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if(item.dRoute=="add_account"){
                                dialogOpen.value=true
                            }
                            else {
                                controller.navigate(item.dRoute)
                                title.value=item.dTitle
                            }
                        }
                    }
                }
            }


        ) {
            Navigation(navController = controller, viewModel = viewModel, pd = it)
            AccountDialog(dialogOpen = dialogOpen)
        }
    }

}






@Composable
fun DrawerIem(
         selected:Boolean,
         item:Screen.DrawerScreen,
         onDrawerItemClicked :() ->Unit
){
    val background = if (selected) Color.DarkGray else Color.White
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemClicked()
            }) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h5,
        )
    }

}

@Composable
fun MoreBottomSheet(modifier: Modifier){
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colors.primarySurface)){
           Column (modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween){
                 Row(modifier = modifier.padding(16.dp)){
                     Icon(modifier = Modifier.padding(8.dp),
                  painter=   painterResource(id = R.drawable.baseline_settings_24), contentDescription = "Settings")
                     Text(text = "Setting", fontSize = 20.sp, color = Color.White)

                 }

               Row(modifier = modifier.padding(16.dp)){
                   Icon(modifier = Modifier.padding(8.dp),
                       painter=   painterResource(id = R.drawable.baseline_share_24), contentDescription = "Settings")
                   Text(text = "Share", fontSize = 20.sp, color = Color.White)

               }

               Row(modifier = modifier.padding(16.dp)){
                   Icon(modifier = Modifier.padding(8.dp),
                       painter=   painterResource(id = R.drawable.baseline_help_center_24), contentDescription = "Help")
                   Text(text = "Help", fontSize = 20.sp, color = Color.White)

               }

           }
    }
}

@Composable
fun Navigation(navController:NavController,viewModel: MainViewModel,pd:PaddingValues){
    NavHost(navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route,
        modifier = Modifier.padding(pd)){



        composable(Screen.BottomScreen.Home.bRoute){
            //TODO home screen
            HomeView(navController)
        }
        composable(
            route = "details/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType; defaultValue = 0 })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
            val item = category.find { it.id == itemId }
            item?.let {
                TitleDescriptionScreen(
                    itemId = it.id,
                    title = it.title,
                    description = it.description
                )
            } ?: run {
                // Handle case where item is not found
                // You can navigate back or show an error message
            }
        }




        composable(Screen.BottomScreen.Browse.bRoute){
            //TODO Browse screen
            BrowseView(navController)
        }

        composable(Screen.BottomScreen.Library.bRoute){
            //TODO Library screen
            Library()
        }

        composable(Screen.DrawerScreen.Account.route){
                     AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route){
          SubscriptionView()
        }

    }
}