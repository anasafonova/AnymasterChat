package com.dzaigames.anymasterchat.ui.widget.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dzaigames.anymasterchat.ui.widget.button.RoundedButton

@Composable
@Preview(showBackground = true)
fun TaskLayoutPreview() {
    TaskLayout(
        taskText = "Cleaning of a two-room apartment",
        taskPrice = 1498.0,
        changeOffer = { }
    )
}

@Composable
fun TaskLayout(
    taskText: String,
    taskPrice: Double,
    changeOffer: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.surface
            )
            .height(60.dp)
            .fillMaxWidth()
    ) {
        val (taskLabel, priceText, actionButton) = createRefs()

        Text(
            text = taskText,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.8.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF040C15),
                textDecoration = TextDecoration.Underline,
            ),
            modifier = Modifier
                .constrainAs(taskLabel) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(start = 14.dp, top = 12.dp)
        )

        Text(
            text = String.format("€%,.2f", taskPrice),
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF040C15),
                textAlign = TextAlign.Right,
            ),
            modifier = Modifier
                .constrainAs(priceText) {
                    top.linkTo(taskLabel.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 14.dp) //, top = 12.dp)
        )

        RoundedButton(
            text = "Change offer",
            backgroundColor = Color.White,
            textColor = Color(0xFF040C15),
            cornerShapeRadius = 18.dp,
            modifier = Modifier
                .constrainAs(actionButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = 8.dp,
                    end = 14.dp
                )
        ) {
            changeOffer()
        }

//        Modifier
//            .border(width = 1.dp, color = Color(0xFF040C15), shape = RoundedCornerShape(size = 18.dp)))
//        .width(115.dp)
//        .height(32.dp)
//        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 18.dp))
//        .padding(start = 16.dp, top = 6.dp, end = 16.dp, bottom = 6.dp)
    }
}
//        Box(
//            Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//                .background(color = MaterialTheme.colorScheme.surface)
//        ) {
//            ConstraintLayout(
//                modifier = Modifier
//                    .background(
//                        MaterialTheme.colorScheme.surface
//                    )
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//            ) {
//                val (profileIcon, firstNameText, lastNameText, notificationIcon, notificationPresent) = createRefs()
//                Box(
//                    modifier = Modifier
//                        .constrainAs(profileIcon) {
//                            top.linkTo(parent.top)
//                            start.linkTo(parent.start)
//                        }
//                        .padding(start = 16.dp, top = 8.dp)
//                        .width(40.dp)
//                        .height(40.dp)
//                        .clickable {
////                            scope.launch {
//////                                drawerState.open()
////                            }
//                        },
//                ) {
//                    Image(
//                        modifier = Modifier.align(Alignment.Center),
//                        painter = painterResource(id = R.drawable.ic_launcher_foreground), //ic_topbar_user_icon),
//                        contentDescription = ""
//                    )
//                    Text(
//                        text = "${firstName[0].uppercase()}${lastName[0].uppercase()}",
//                        color = MaterialTheme.colorScheme.primaryContainer,
//                        style = MaterialTheme.typography.titleSmall,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//
//                Text(
//                    text = firstName,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    style = MaterialTheme.typography.labelSmall,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .constrainAs(firstNameText) {
//                            top.linkTo(parent.top)
//                            start.linkTo(profileIcon.end)
//                        }
//                        .padding(start = 10.dp, top = 16.dp)
//                )
//
//                Text(
//                    text = lastName,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    style = MaterialTheme.typography.labelSmall,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .constrainAs(lastNameText) {
//                            bottom.linkTo(parent.bottom)
//                            start.linkTo(profileIcon.end)
//                        }
//                        .padding(start = 10.dp, bottom = 16.dp)
//                )
//
////                Image(
////                    painter = painterResource(id = uiR.drawable.ic_appbar_notification),
////                    contentDescription = null,
////                    modifier = Modifier
////                        .constrainAs(notificationIcon) {
////                            end.linkTo(parent.end)
////                            top.linkTo(parent.top)
////                        }
////                        .padding(top = 8.dp, end = 12.dp)
////                        .clickable { if (isNotificationsConfigured) toNotifications() }
////                )
////                if (isNotificationsPresent && isNotificationsConfigured) {
////                    Image(
////                        painter = painterResource(id = uiR.drawable.ic_notification_dot),
////                        contentDescription = null,
////                        modifier = Modifier
////                            .constrainAs(notificationPresent) {
////                                end.linkTo(notificationIcon.end)
////                                top.linkTo(notificationIcon.top)
////                            }
////                            .padding(top = 8.dp, end = 12.dp)
////                    )
////                }
//            }
//        }