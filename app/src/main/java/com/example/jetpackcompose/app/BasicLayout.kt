package com.example.jetpackcompose.app

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFilter
import com.example.jetpackcompose.R
import com.example.jetpackcompose.data.Profile
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme


class BasicLayout : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                WindowSize(windowSizeClass)
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyApp() {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
        Scaffold(
            bottomBar = { Navigation() },
           // topBar = {SmallAppBar(scrollBehavior,"Home")}
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }

    @Composable
    fun WindowSize(windowSize: WindowSizeClass) {
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                MyApp()
            }

            WindowWidthSizeClass.Expanded -> {
                AppLandscape()
            }
        }
    }




    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MediumTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
        MediumTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors( // use correct API
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary),
            title = { Text("Small top App Bar", maxLines = 1, overflow = TextOverflow.Ellipsis) },
            navigationIcon = {
                IconButton(onClick = { /* handle back */ }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = { /* handle menu */ }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            },
            scrollBehavior = scrollBehavior
        )
    }





    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SmallAppBar(scrollBehavior: TopAppBarScrollBehavior,title: String) {
        val context = LocalContext.current
        TopAppBar(
            title = {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {

                IconButton(onClick = {  Toast.makeText(context, "Back clicked", Toast.LENGTH_SHORT).show() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(onClick = { Toast.makeText(context, "Menu clicked", Toast.LENGTH_SHORT).show() }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
                IconButton(onClick = { Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show() }) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Menu")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            scrollBehavior = scrollBehavior
        )
    }


    @Composable
    fun SearchBar(
        modifier: Modifier = Modifier, query: String, onQueryChanged: (String) -> Unit) {
        TextField(
            value = query, onValueChange = onQueryChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            placeholder = {
                Text(stringResource(R.string.placeholder_search))
            },
            modifier = modifier
                .fillMaxWidth(1f)
                .heightIn(min = 56.dp)
        )
    }


    @Composable
    fun AlignYourBodyElement(
        image: Int,
        description: String,
        modifier: Modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(88.dp)
                    .clip(CircleShape)
                    .border(0.2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                text = description
            )
        }
    }


    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {
        var searchText by remember { mutableStateOf("") }
        Column(
            modifier
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            SearchBar(Modifier.padding(horizontal = 16.dp),
                query=searchText,
                onQueryChanged = { searchText = it })
            HomeSection(title = R.string.body) {
                Body(searchText)
            }
            HomeSection(title = R.string.favorite) {
                Favorite(searchText)
            }
            Spacer(Modifier.height(16.dp))
        }
    }

    @Composable
    fun HomeSection(
        @StringRes title: Int,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Column(modifier) {
            Text(
                stringResource(title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            content()
        }
    }


    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
        wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
    )
    @Composable
    fun HomeSectionPreview() {
        AppLandscape()
    }


    @Composable
    fun AppLandscape() {
        JetpackComposeTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                Row {
                    NavigationLandscape()
                    HomeScreen()
                }
            }
        }
    }


    @Composable
    fun Favorite(searchText: String) {
        val data = mutableListOf<Profile>()
        data.add(
            Profile(
                description = "Self Motivation",
                profileUrl = "",
                image = R.drawable.profile
            )
        )
        data.add(Profile(description = "Challenge", profileUrl = "", image = R.drawable.profile))
        data.add(Profile(description = "Massage", profileUrl = "", image = R.drawable.profile))
        data.add(Profile(description = "Nature", profileUrl = "", image = R.drawable.profile))
        data.add(Profile(description = "Food", profileUrl = "", image = R.drawable.profile))
        val fitered=data.fastFilter { it.description.contains(searchText) }
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.height(168.dp)
        ) {
            items(fitered) { it -> FavoriteCollectionCard(it, modifier = Modifier.height(80.dp)) }
        }
    }

    @Composable
    fun Body(searchText: String) {
        val data = mutableListOf<Profile>()
        data.add(Profile(description = "Yoga 1", profileUrl = "", image = R.drawable.stone))
        data.add(Profile(description = "Yoga 2", profileUrl = "", image = R.drawable.stone))
        data.add(Profile(description = "Yoga 3", profileUrl = "", image = R.drawable.stone))
        data.add(Profile(description = "Yoga 4", profileUrl = "", image = R.drawable.stone))
        data.add(Profile(description = "Yoga 5", profileUrl = "", image = R.drawable.stone))
        val fitered=data.fastFilter { it.description.contains(searchText) }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            contentPadding = PaddingValues(horizontal = 4.dp),
        ) {
            items(fitered) { it ->
                AlignYourBodyElement(
                    it.image,
                    it.description,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

    @Composable
    fun FavoriteCollectionCard(
        profile: Profile,
        modifier: Modifier = Modifier
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(255.dp)
            ) {

                Image(
                    painter = painterResource(profile.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = profile.description,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }


    @Composable
    private fun Navigation(modifier: Modifier = Modifier) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.home)
                    )
                },
                selected = true,

                onClick = {}
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.AccountBox,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.profile)
                    )
                },
                selected = false,
                onClick = {}
            )
        }
    }

    @Composable
    private fun NavigationLandscape(modifier: Modifier = Modifier) {
        NavigationRail(
            modifier = modifier.padding(start = 8.dp, end = 8.dp),
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationRailItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.home)
                        )
                    },
                    selected = true,
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(8.dp))
                NavigationRailItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.AccountBox,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.profile)
                        )
                    },
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}
