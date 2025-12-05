package com.example.vknews.presentation.news

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknews.di.ViewModelFactory
import com.example.vknews.domain.news.NewsItem

@Composable
fun FeedPosts(
    posts: List<NewsItem>,
    viewModelFactory: ViewModelFactory,
    isLoadingMore : Boolean,
    onCommentsClickListener: (feedPostId: Int) -> Unit,
) {
    val viewModel : NewsFeedViewModel = viewModel(factory = viewModelFactory)
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = {it.articleId}
        ) { newsItem ->
            Log.d("LazyColumn", "postsize: ${posts.size}")

            ArticlePost(
                newsItem = newsItem,
                onLikeClickListener = {
//                        viewModel.updateStatisticsItem(
//                            newsItem,
//                            StatisticsType.LIKES
//                        )
                },
                onCommentClickListener = {
//                        onCommentsClickListener(newsItem.id)
                },
                onRepostClickListener = {
//                        viewModel.updateStatisticsItem(
//                            newsItem,
//                            StatisticsType.REPOSTS
//                        )
                },
                onViewClickListener = {
//                        viewModel.updateStatisticsItem(
//                            newsItem,
//                            StatisticsType.VIEWS
//                        )
                },
                onReadArticleClickListener = {
                    context.startActivity(it)
                }
            )

        }
        item{
            if(isLoadingMore){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            } else {
                SideEffect {
                    viewModel.loadMoreNews()
                }
            }
        }

    }
}






//            val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
//                confirmValueChange = { value ->
//                    val isDismissed = value == SwipeToDismissBoxValue.EndToStart
//                    if (isDismissed) {
//                        viewModel.deleteNewsItem(posts[index])
//                    }
//                    return@rememberSwipeToDismissBoxState isDismissed
//                }
//            )


//            SwipeToDismissBox(
//                modifier = Modifier.animateItem(),
//                state = swipeToDismissBoxState,
//                enableDismissFromStartToEnd = false,
//                backgroundContent = {}
//            ) {
//
//            }