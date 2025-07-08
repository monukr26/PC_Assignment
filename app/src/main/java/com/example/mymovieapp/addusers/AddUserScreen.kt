package com.example.mymovieapp.addusers


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.util.TableInfo
import com.example.mymovieapp.UserViewModel


@Composable
fun AddUserScreen( viewModel: NewUserViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val users by viewModel.allUsers.collectAsState()

    Column (modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Name") })
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter Email") })


        Button(
            onClick = {
                if (name.isNotBlank() && email.isNotBlank()) {
                    viewModel.addUser(name, email)
                    name = ""
                    email = ""
                }
            },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text("Add New User")

        }
        Divider(modifier = Modifier.padding(vertical = 14.dp))

        LazyColumn {
            items(users.size) { index ->
                val user = users[index]
                Text("${user.name} (${user.email}) - Synced: ${user.isSynced}")

            }
        }

    }

}