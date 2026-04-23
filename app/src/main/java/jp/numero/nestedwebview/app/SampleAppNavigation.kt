package jp.numero.nestedwebview.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import jp.numero.nestedwebview.app.compose.composeHorizontalPagerSampleScreen
import jp.numero.nestedwebview.app.compose.composeVerticalScrollSampleScreen
import jp.numero.nestedwebview.app.compose.navigateToComposeHorizontalPagerSample
import jp.numero.nestedwebview.app.compose.navigateToComposeVerticalScrollSample
import jp.numero.nestedwebview.app.view.navigateToViewVerticalScrollSample
import jp.numero.nestedwebview.app.view.viewVerticalScrollSampleScreen

@Composable
fun SampleAppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = sampleListRoute,
    ) {
        sampleListScreen(
            onNavigateComposeVerticalScrollSample = {
                navController.navigateToComposeVerticalScrollSample()
            },
            onNavigateComposeHorizontalPagerSample = {
                navController.navigateToComposeHorizontalPagerSample()
            },
            onNavigateViewVerticalScrollSample = {
                navController.navigateToViewVerticalScrollSample()
            }
        )
        composeVerticalScrollSampleScreen()
        composeHorizontalPagerSampleScreen()
        viewVerticalScrollSampleScreen()
    }
}