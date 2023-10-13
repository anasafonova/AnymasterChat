package com.dzaigames.anymasterchat.ui.widget

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dzaigames.anymasterchat.R

@Composable
@Preview(showBackground = true)
fun BreadcrumbsButtonPreview() {
    BreadcrumbsButton(
        color = MaterialTheme.colorScheme.secondary,
        onClick = { }
    )
}

@Composable
fun BreadcrumbsButton(
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_breadcrumbs),
            tint = color,
            contentDescription = stringResource(id = R.string.open_menu)
        )
    }
}