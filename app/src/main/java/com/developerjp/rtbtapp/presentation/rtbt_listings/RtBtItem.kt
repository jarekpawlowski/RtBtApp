package com.developerjp.rtbtapp.presentation.rtbt_listings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//
import com.developerjp.rtbtapp.domain.model.RtBtListing
import com.developerjp.rtbtapp.ui.theme.RtBtAppTheme

@Composable
fun RtBtItem(
    rtbt: RtBtListing,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier.weight(1f)
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = rtbt.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = rtbt.status,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onBackground

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "(${rtbt.department})",
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RtBtItem(
    //rtbt: RtBtListing ,
    modifier: Modifier = Modifier
){
    var rt: RtBtListing = RtBtListing("AAA", "OK", "BAH", "RA5")
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier.weight(1f)
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = rt.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = rt.status,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onBackground

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "(${rt.department})",
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

}



