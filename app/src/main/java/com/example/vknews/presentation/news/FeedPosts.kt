package com.example.vknews.presentation.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vknews.domain.FeedPost
import com.example.vknews.domain.StatisticsType

@Composable
fun FeedPosts(
    posts: List<FeedPost>,
    viewModel: NewsFeedViewModel,
    onCommentsClickListener: (feedPostId: Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts, key = { it.id }) { feedPost ->
            val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
                confirmValueChange = { value ->
                    val isDismissed = value == SwipeToDismissBoxValue.EndToStart
                    if (isDismissed) {
                        viewModel.deletePost(feedPost)
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

                VkPost(
                    feedPost = feedPost,
                    onLikeClickListener = {
                        viewModel.updateStatisticsItem(
                            feedPost,
                            StatisticsType.LIKES
                        )
                    },
                    onCommentClickListener = {
                        onCommentsClickListener(feedPost.id)
                    },
                    onRepostClickListener = {
                        viewModel.updateStatisticsItem(
                            feedPost,
                            StatisticsType.REPOSTS
                        )
                    },
                    onViewClickListener = {
                        viewModel.updateStatisticsItem(
                            feedPost,
                            StatisticsType.VIEWS
                        )
                    }
                )
            }
        }

    }
}