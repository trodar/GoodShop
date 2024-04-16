package com.trodar.settings.presentation.setting

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.trodar.model.Profile
import com.trodar.theme.colors.cardColors
import com.trodar.utils.Constants.MODIFIER
import com.trodar.utils.Constants.SPACER16
import com.trodar.utils.Constants.SPACER8

@Composable
fun ProfileCard(
    profile: Profile,
    uri: Uri? = null,
    onProfileClick: () -> Unit,
) {
    ElevatedCard(
        colors = cardColors(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Row(
            modifier = MODIFIER.padding(vertical = SPACER16),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                model = uri,
                placeholder = painterResource(com.trodar.utils.R.drawable.user_add),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(SPACER8))
            Column {
                Text(text = profile.user, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = profile.phone, fontSize = 14.sp)
                Text(text = profile.email, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = "profile",
                    modifier = Modifier.size(32.dp),
                )
            }
        }
    }
}