package com.example.musicappui.ui.theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun SubscriptionView() {
    var email by remember { mutableStateOf("") }
    var selectedPlan by remember { mutableStateOf("") }
    var selectedGateway by remember { mutableStateOf("") }

    val plans = listOf(
        Plan("Basic Plan", "$9.99/month"),
        Plan("Premium Plan", "$19.99/month")
    )

    val gateways = listOf(
        "Credit Card",
        "PayPal",
        "Google Pay",
        "Apple Pay"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter your email") },
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Text(
                text = "Choose a Plan",
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            plans.forEach { plan ->
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedPlan == plan.name,
                        onClick = { selectedPlan = plan.name },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "${plan.name} - ${plan.price}")
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Text(
                text = "Choose Payment Gateway",
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            gateways.forEach { gateway ->
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedGateway == gateway,
                        onClick = { selectedGateway = gateway },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = gateway)
                }
            }
        }

        Button(
            onClick = {
                // Handle subscribe button click
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(
                text = "Subscribe",
                style = MaterialTheme.typography.button.copy(color = Color.White)
            )
        }
    }
}

data class Plan(val name: String, val price: String)
