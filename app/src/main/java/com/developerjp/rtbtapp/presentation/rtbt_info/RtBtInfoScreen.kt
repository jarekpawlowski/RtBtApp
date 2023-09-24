package com.developerjp.rtbtapp.presentation.rtbt_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun RtBtInfoScreen(
    navigator: DestinationsNavigator,
    name: String,
    viewModel: RtBtInfoViewModel = hiltViewModel()
){
    val state = viewModel.state
    if (state.error == null){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
                .padding(16.dp)
        ){
            state.rtbt?.let{rtbt ->
                Text(
                    text = rtbt.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = rtbt.department,
                    fontStyle = FontStyle.Italic,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = rtbt.line,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = rtbt.machine,
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = rtbt.description,
                    fontStyle = FontStyle.Normal,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

            }
        }

    }
   Box(
       modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
       ){
       if (state.isLoading){
           CircularProgressIndicator()
       }else if (state.error != null){
           Text(
               text = state.error,
               color = MaterialTheme.colorScheme.error
           )
       }
   }
}