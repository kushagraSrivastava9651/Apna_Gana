package com.example.musicappui.ui.theme

 import android.util.Log
 import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
 import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
 import androidx.compose.material3.CircularProgressIndicator

 import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Surface
 import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
 import androidx.compose.runtime.livedata.observeAsState

 import androidx.compose.ui.Modifier
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.res.painterResource
 import androidx.compose.ui.tooling.preview.Preview
 import androidx.compose.ui.unit.dp

 import com.example.musicappui.Login_data.AuthViewModel


 import com.example.musicappui.R

@Composable
fun AccountView(viewModel: AuthViewModel) {
    val userDetails by viewModel.userDetails.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getCurrentUser()?.let {
            viewModel.fetchUserDetails(it.uid)
        }
    }

    when (val result = userDetails) {
        is com.example.musicappui.Login_data.Result.Success -> {
            val userMap = result.data
            val firstName = userMap["firstName"] as? String ?: ""
            val lastName = userMap["lastName"] as? String ?: ""
            val email = userMap["email"] as? String ?: ""

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Account",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Column {
                            Text("$firstName $lastName")
                            Text(text = extractUserHandleFromEmail(email))
                        }
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                }

                Row(modifier = Modifier.padding(top = 16.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_library_music_24),
                        contentDescription = "Quick News",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Quick News")
                }
                Divider()
            }
        }
        is com.example.musicappui.Login_data.Result.Error -> {
            Text("Error fetching user details: ${result.exception.message}")
        }
        null -> {
            // Display a loading indicator
            CircularProgressIndicator()
        }
    }
}
fun extractUserHandleFromEmail(email: String): String {
    return email.substringBefore("@")
}