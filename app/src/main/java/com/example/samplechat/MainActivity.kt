package com.example.samplechat

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.alphamovie.lib.AlphaMovieView
import com.example.samplechat.ui.theme.SampleChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    InitiatePreview()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun InitiatePreview() {
    val images: ArrayList<UserData> = ArrayList()
    images.add(UserData("Jack", R.drawable.person))
    images.add(UserData("Jack", R.drawable.imgs))
    images.add(UserData("Jack", R.drawable.pic))
    images.add(UserData("Jack", R.drawable.imagtest))
    images.add(UserData("Jack", R.drawable.nn))
    images.add(UserData("Jack", R.drawable.person))
    val dialog = remember { mutableStateOf(false) }
    var message by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        Row(
            modifier = Modifier
                .background(color = Color(0xFF4293D3))
                .requiredHeight(65.dp)
                .fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.person),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .fillMaxHeight()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(80.dp)), alignment = Alignment.Center
            )
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Esther Howard",
                    fontStyle = FontStyle.Normal,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Host",
                    fontStyle = FontStyle.Normal,
                    fontSize = 10.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row(
            modifier = Modifier
                .requiredHeight(350.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.person),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(2.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    items(images.size) { photo ->
                        val ph = images[photo]
                        Image(
                            painter = painterResource(ph.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(118.dp)
                                .fillMaxHeight()
                                .padding(2.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        Row(
            modifier = Modifier
                .background(color = Color(0xFF4293D3))
                .requiredHeight(55.dp)
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Comments",
                fontStyle = FontStyle.Normal,
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        BoxWithConstraints(modifier = Modifier.fillMaxWidth(1f)) {
            val maxHeight = this.maxHeight

            val topHeight: Dp = 300.dp
            val bottomHeight: Dp = maxHeight / 3

            val centerHeight = 200.dp

            val centerPaddingBottom = bottomHeight - centerHeight / 2

            Top(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .height(topHeight), images
            )

            Bottom(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .padding(0.dp, 0.dp, 30.dp, 10.dp)
                    .height(bottomHeight), dialog
            )

            Center(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = centerPaddingBottom)
                    .fillMaxWidth()
                    .height(centerHeight)
                    .align(Alignment.Center), dialog
            )
        }


        Row(
            modifier = Modifier
                .background(color = Color.White)
                .requiredHeight(55.dp)
                .weight(1f, false)
        ) {

            TextField(
                value = message,
                onValueChange = {
                    message = it
                }, placeholder = { Text(text = "Send a Message") },
                modifier = Modifier
                    .requiredWidth(400.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .height(55.dp)
                    .padding(5.dp, 5.dp, 5.dp, 1.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFCECECE), placeholderColor = Color(0xD31B1717),
                    textColor = Color(0xD31B1717), disabledPlaceholderColor = Color(0xD31B1717)
                )
            )


            Image(
                painter = painterResource(R.drawable.se),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .fillMaxHeight()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(80.dp))
            )
        }
    }
}

@Composable
private fun Top(modifier: Modifier, images: ArrayList<UserData>) {
    LazyColumn(
        modifier = modifier
    ) {
        items(images.size) {
            val cmtdata = images[it]
            Row(
                modifier = Modifier
                    .background(color = Color.White)
                    .requiredHeight(55.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(cmtdata.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(45.dp)
                        .fillMaxHeight()
                        .padding(5.dp)
                        .clip(RoundedCornerShape(80.dp))
                )
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = cmtdata.name + "Sent a gift to Tom",
                        fontStyle = FontStyle.Normal,
                        fontSize = 10.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun Bottom(modifier: Modifier, dialog: MutableState<Boolean>) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = {
                dialog.value = !dialog.value
            },
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp)), containerColor = Color(0xFF4293D3)
        ) {
            Icon(painterResource(R.drawable.gift), "")
        }

    }
}

@Composable
fun Center(modifier: Modifier, dialog: MutableState<Boolean>) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (dialog.value) {
            val context = LocalContext.current
            AndroidView({
                LayoutInflater.from(context).inflate(R.layout.test, null)
            }) { view ->
                val alphaMovieView = view.findViewById<AlphaMovieView>(R.id.video_playr)
                alphaMovieView.setVideoFromAssets("vide.mp4")
            }
            //Dialog(dialogType = dialog)
        }
    }
}