package com.dzaigames.anymasterchat.ui.widget

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dzaigames.anymasterchat.R

@Composable
@Preview
fun UserLayoutPreview() {
    UserLayout(
        userName = "Daniel",
        userSurname = "Moris",
        profileBitmap = null,
        isVerified = true
    )
}

@Composable
fun UserLayout(
    userName: String,
    userSurname: String,
    profileBitmap: Bitmap?,
    isVerified: Boolean
) {
    ConstraintLayout(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surface
            )
            .height(60.dp)
            .fillMaxWidth()
    ) {
        val (profileIcon, headerLabel, verificationLabel) = createRefs()

        if (profileBitmap != null) {
            Image(
                modifier = Modifier
                    .padding(top = 12.dp, start = 6.dp)
                    .size(34.dp)
                    .constrainAs(profileIcon) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                bitmap = profileBitmap.asImageBitmap(),
                contentDescription = stringResource(id = R.string.user_profile_logo)
            )
        } else {
            Image(
                modifier = Modifier
                    .padding(top = 12.dp, start = 6.dp)
                    .size(34.dp)
                    .constrainAs(profileIcon) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                painter = painterResource(id = R.drawable.user_picture),
                contentDescription = stringResource(id = R.string.user_profile_logo)
            )
        }

        Text(
            text = "$userName $userSurname",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF040C15),
            ),
            modifier = Modifier
                .constrainAs(headerLabel) {
                    top.linkTo(parent.top)
                    start.linkTo(profileIcon.end)
                }
                .padding(top = 10.dp, start = 12.dp)
        )

        if (isVerified) {
            VerifiedLabel(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .constrainAs(verificationLabel) {
                        top.linkTo(headerLabel.bottom)
                        start.linkTo(profileIcon.end)
                    }
                    .padding(start = 12.dp, top = 3.dp)
            )
        }


    }
}