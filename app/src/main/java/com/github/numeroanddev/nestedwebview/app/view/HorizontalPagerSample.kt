package com.github.numeroanddev.nestedwebview.app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.compose.AndroidFragment
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.github.numeroanddev.nestedwebview.app.R

private const val viewHorizontalPagerSampleRoute = "viewHorizontalPagerSample"

fun NavController.navigateToViewHorizontalPagerSample(navOptions: NavOptions? = null) {
    navigate(viewHorizontalPagerSampleRoute, navOptions)
}

fun NavGraphBuilder.viewHorizontalPagerSampleScreen(
) {
    composable(route = viewHorizontalPagerSampleRoute) {
        HorizontalPagerSample()
    }
}

@Composable
private fun HorizontalPagerSample(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
    ) {
        Box(Modifier.padding(it)) {
            AndroidFragment<HorizontalPagerFragment>()
        }
    }
}

class HorizontalPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_horizontal_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ViewPager2>(R.id.view_pager).apply {
            adapter = WebPageAdapter(this@HorizontalPagerFragment)
        }
    }
}

class WebPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val pageList = listOf(
        "https://www.apple.com/store",
        "https://developer.android.com/",
    )

    override fun getItemCount(): Int = pageList.size

    override fun createFragment(position: Int): Fragment {
        return ItemFragment.createInstance(pageList[position])
    }
}

class ItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_horizontal_pager_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<WebView>(R.id.webview).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(requireNotNull(arguments?.getString(ARG_URL)))
        }
    }

    companion object {
        private const val ARG_URL = "url"
        fun createInstance(url: String): ItemFragment {
            return ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_URL, url)
                }
            }
        }
    }
}