package com.example.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mywishlistapp.data.Wish
import com.example.mywishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRrepository:WishRepository = Graph.wishRepository
):ViewModel () {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")


    fun onWishTitleChanged(newString:String){
        wishTitleState = newString
    }

    fun onWishDescriptionChanged(newString:String){
        wishDescriptionState = newString
    }
//lateinit means "late initialization".
//“I’ll initialize this non-null variable later, trust me.”
//It’s useful when you can’t initialize immediately, especially in Android.

    lateinit var getAllWishes : Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRrepository.getWishes()
        }
    }
    //Dispatchers means
    fun addWish(wish: Wish){
        viewModelScope.launch (Dispatchers.IO){
            wishRrepository.addAWish(wish = wish)
        }
    }

    fun getAWishById(id:Long): Flow<Wish>{
        return wishRrepository.getAWishById(id)
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch (Dispatchers.IO){
            wishRrepository.updateAWish(wish = wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch (Dispatchers.IO){
            wishRrepository.deleteAWish(wish = wish)
        }
    }

}