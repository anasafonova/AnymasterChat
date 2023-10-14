package com.dzaigames.anymasterchat.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dzaigames.anymasterchat.R

@Composable
@Preview(showBackground = true)
fun NavigationBarPreview() {
    NavigationBar(onBackClick = {  }, onMenuClick = {  }) {
        UserLayout(
            userName = "Daniel",
            userSurname = "Moris",
            profileBitmap = null,
            isVerified = true
        )
    }
}

@Composable
fun NavigationBar(
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surface
            )
            .height(60.dp)
            .fillMaxWidth()
    ) {
        val (backButton, contentRow, menuButton) = createRefs()

        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            tint = Color.Black,
            contentDescription = stringResource(id = R.string.back),
            modifier = Modifier
                .constrainAs(backButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(start = 14.dp, top = 16.dp)
                .clickable {
                    onBackClick()
                }
        )

        Row(
            modifier = Modifier
                .constrainAs(contentRow) {
                    top.linkTo(parent.top)
                    start.linkTo(backButton.end)
                }
        ) {
            content()
        }

        BreadcrumbsButton(
            color = MaterialTheme.colorScheme.secondary,
            onClick = onMenuClick,
            modifier = Modifier
                .constrainAs(menuButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 10.dp, end = 18.dp)
        )
    }
}