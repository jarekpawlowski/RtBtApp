package com.developerjp.rtbtapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import com.developerjp.rtbtapp.presentation.NavGraphs
import com.developerjp.rtbtapp.presentation.rtbt_listings.RtBtListingsScreen
import com.developerjp.rtbtapp.presentation.rtbt_listings.RtBtListingsViewModel
import com.developerjp.rtbtapp.ui.theme.RtBtAppTheme

import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.spec.NavGraphSpec

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RtBtAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   DestinationsNavHost(navGraph = NavGraphs.root)
                    //RtBtListingsScreen( navigator, RtBtListingsViewModel)
                }
            }
        }
    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RtBtAppTheme {
        Greeting("Android")
    }
}*/