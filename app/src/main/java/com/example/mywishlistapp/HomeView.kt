package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mywishlistapp.data.DummyWish
import com.example.mywishlistapp.data.DummyWish.wishList
//import com.example.mywishlistapp.data.DummyWish
import com.example.mywishlistapp.data.Wish

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
){

    val context = LocalContext.current
    Scaffold(
        topBar = {AppBarView(title = "WishList",{

            Toast.makeText(context,"Button Clicked",Toast.LENGTH_LONG).show()
        })},
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                //contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = {
                     Toast.makeText(context,"FAButton Clicked",Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.Addscreen.route + "/0L")

                 }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null )
            }
        }
    ){
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            items(wishlist.value){
             wish ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if(it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )
                SwipeToDismiss(state = dismissState , background = {}, directions = setOf(DismissDirection.StartToEnd,DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold (0.25f) } ,
                    dismissContent = {
                        WishItem(wish = wish)  {
                             val id = wish.id
                            navController.navigate(Screen.Addscreen.route+"/$id")
                        }
                    }
                )

                WishItem(wish = wish) {
                  val id = wish.id
                   navController.navigate(Screen.Addscreen.route +"/$id")
               }
            }
        }
    }
}

@Composable
fun WishItem(wish: Wish, onclick:() -> Unit){
    Card (modifier = Modifier.fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onclick()
        },
        elevation = 10.dp,
        backgroundColor = Color.White,
    ){
        Column (modifier = Modifier.padding (16.dp)){
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold )
            Text(text = wish.description  )

        }
    }

}