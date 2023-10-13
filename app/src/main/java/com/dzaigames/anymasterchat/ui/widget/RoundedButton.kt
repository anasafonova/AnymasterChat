package com.dzaigames.anymasterchat.ui.widget

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dzaigames.anymasterchat.ui.theme.AnymasterChatTheme

@Composable
@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
fun RoundedButtonPreview() {
    AnymasterChatTheme {
        RoundedButton(
            text = "Change offer",
            backgroundColor = Color.White,
            textColor = Color(0xFF040C15),
            cornerShapeRadius = 18.dp,
            onClick = { }
        )
    }
}

@Composable
fun RoundedButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    cornerShapeRadius: Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, textColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(size = cornerShapeRadius),
        contentPadding = PaddingValues(
            top = 6.dp,
            bottom = 6.dp,
            start = 16.dp,
            end = 16.dp
        ),
        modifier = modifier.defaultMinSize(
            minWidth = ButtonDefaults.MinWidth,
            minHeight = 10.dp
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
//                fontFamily = FontFamily(Font(R.font.ibm plex sans)),
                fontWeight = FontWeight(600),
                color = textColor,
                textAlign = TextAlign.Center
            )
        )
    }

//    Button(
//        onClick = onClick,
//        modifier = modifier
//            .background(
//                color = backgroundColor,
//                shape = RoundedCornerShape(size = cornerShapeRadius)
//            )
//            .border(
//                width = 1.dp,
//                color = textColor, //Color(0xFF040C15),
////                shape = RoundedCornerShape(size = cornerShapeRadius)
//            )
//    ) {
//        Text(
//            text = text,
//            style = TextStyle(
//                fontSize = 14.sp,
//                lineHeight = 19.6.sp,
////                fontFamily = FontFamily(Font(R.font.ibm plex sans)),
//                fontWeight = FontWeight(600),
//                color = textColor,
//                textAlign = TextAlign.Center
//            )
//        )
//    }
}