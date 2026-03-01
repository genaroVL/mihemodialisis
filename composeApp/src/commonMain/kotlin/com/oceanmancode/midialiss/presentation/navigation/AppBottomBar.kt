package com.oceanmancode.midialiss.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import midialisis.composeapp.generated.resources.Res
import midialisis.composeapp.generated.resources.ic_calculator
import midialisis.composeapp.generated.resources.ic_user_account
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun AppBottomBar(
    tabs: List<TopLevelRoute>,
    selected: TopLevelRoute,
    onSelect: (TopLevelRoute) -> Unit,
) {
    val cs = MaterialTheme.colorScheme

    NavigationBar(
        containerColor = cs.surface,
        tonalElevation = 10.dp
    ) {
        tabs.forEach { tab ->
            val isSelected = tab == selected

            NavigationBarItem(
                selected = isSelected,
                onClick = { onSelect(tab) },
                icon = {
                    Icon(
                        painter = painterResource(tab.iconRes()),
                        contentDescription = tab.title,


                    )
                },
                label = { Text(tab.title) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = cs.primary,
                    selectedTextColor = cs.primary,
                    indicatorColor = cs.primary.copy(alpha = 0.16f),
                    unselectedIconColor = cs.onSurfaceVariant,
                    unselectedTextColor = cs.onSurfaceVariant
                )
            )
        }
    }
}


fun TopLevelRoute.iconRes(): DrawableResource = when (this) {
    PatientsRoute -> Res.drawable.ic_user_account
    CalculateRoute -> Res.drawable.ic_calculator
    else -> Res.drawable.ic_user_account
}


