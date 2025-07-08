package com.example.mymovieapp


import android.R.attr.text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mymovieapp.addusers.AddUserScreen

@Composable
fun TabScreen(navController: NavController){
    val tabs = listOf("User List", "Add User")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(25.dp)) {
        TabRow(selectedTabIndex=selectedTabIndex,
            modifier = Modifier.height(50.dp)) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index},
                    text = {Text(title)}
                )
            }
        }

        when(selectedTabIndex){
            0 -> UserScreen(onUserClick = {
                navController.navigate("movies/$it")
            })
            1 -> AddUserScreen()
        }

    }
}



