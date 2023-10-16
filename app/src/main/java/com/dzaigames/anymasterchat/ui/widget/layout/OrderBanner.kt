package com.dzaigames.anymasterchat.ui.widget.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dzaigames.anymasterchat.R

@Composable
@Preview(showBackground = false)
fun OrderBannerPreview() {
    OrderBanner(
        userName = "Daniel",
        price = 1498.0
    )
}

@Composable
fun OrderBanner(
    userName: String,
    price: Double,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0x1A040C15),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .width(347.dp)
            .wrapContentHeight(Alignment.CenterVertically)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
    ) {
        Column(
            modifier = Modifier
//                .fillMaxWidth()
                .align(Alignment.Center)
                .wrapContentSize(Alignment.Center)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.order_logo),
                contentDescription = stringResource(id = R.string.order_logo),
                modifier = Modifier
                    .size(50.dp)
            )

            Text(
                text = stringResource(id = R.string.customer_gets_your_offer, userName),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 19.6.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF040C15),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .padding(top = 12.dp)
            )

            Text(
                text = String.format("€%,.2f", price),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF040C15),

                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
            )

            Divider()

            Text(
                text = stringResource(id = R.string.to_complete_the_deal, userName),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.8.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF040C15),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 14.dp, start = 4.dp, end = 4.dp)
            )
        }
    }

}