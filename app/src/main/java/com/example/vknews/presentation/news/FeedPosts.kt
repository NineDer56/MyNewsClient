package com.example.vknews.presentation.news

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.vknews.domain.news.NewsItem

@Composable
fun FeedPosts(
    posts: List<NewsItem>,
    viewModel: NewsFeedViewModel,
    onCommentsClickListener: (feedPostId: Int) -> Unit,
) {
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts, key = { it.articleId }) { newsItem ->
            val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
                confirmValueChange = { value ->
                    val isDismissed = value == SwipeToDismissBoxValue.EndToStart
                    if (isDismissed) {
                        viewModel.deleteNewsItem(newsItem)
                    }
                    return@rememberSwipeToDismissBoxState isDismissed
                }
            )


            SwipeToDismissBox(
                modifier = Modifier.animateItem(),
                state = swipeToDismissBoxState,
                enableDismissFromStartToEnd = false,
                backgroundContent = {}
            ) {

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
        }

    }
}