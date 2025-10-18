package com.example.vknews.presentation.news

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vknews.R
import com.example.vknews.domain.StatisticsItem
import com.example.vknews.domain.StatisticsType
import com.example.vknews.domain.news.NewsItem

@Composable
fun VkPost(
    modifier: Modifier = Modifier,
    newsItem: NewsItem,
    onLikeClickListener: (StatisticsItem) -> Unit,
    onCommentClickListener: (StatisticsItem) -> Unit,
    onRepostClickListener: (StatisticsItem) -> Unit,
    onViewClickListener: (StatisticsItem) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary)
    ) {

        PostHead(newsItem)
        PostText(newsItem)
        Spacer(modifier = Modifier.height(12.dp))
        PostPicture(newsItem)
        PostFooter(
            onLikeClickListener = onLikeClickListener,
            onCommentClickListener = onCommentClickListener,
            onRepostClickListener = onRepostClickListener,
            onViewClickListener = onViewClickListener
        )

    }
}


@Composable
fun PostHead(
    newsItem: NewsItem
) {
    Log.d("recomposition", "PostHead")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .heightIn(64.dp, 64.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        AsyncImage(
            model = newsItem.sourceIcon.takeIf { it.isNotBlank() },
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = newsItem.sourceName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = newsItem.pubDate,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }

        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun PostText(
    newsItem: NewsItem
) {
    Log.d("recomposition", "PostText")
    Text(
        text = newsItem.title,
        modifier = Modifier
            .padding(horizontal = 12.dp)
    )
}

@Composable
fun PostPicture(
    newsItem: NewsItem
) {
    Log.d("recomposition", "PostPicture")
    AsyncImage(
        model = newsItem.imageUrl.takeIf { it.isNotBlank() },
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}

@Composable
fun PostFooter(
    onLikeClickListener: (StatisticsItem) -> Unit,
    onCommentClickListener: (StatisticsItem) -> Unit,
    onRepostClickListener: (StatisticsItem) -> Unit,
    onViewClickListener: (StatisticsItem) -> Unit
) {
    Log.d("recomposition", "PostFooter")
    val statistics = listOf(
        StatisticsItem(StatisticsType.LIKES, 12),
        StatisticsItem(StatisticsType.VIEWS, 432),
        StatisticsItem(StatisticsType.COMMENTS, 43),
        StatisticsItem(StatisticsType.REPOSTS, 38)
    )
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            val likesItem = statistics.getItemByType(StatisticsType.LIKES)
            IconWithCountRow(
                iconResId = R.drawable.ic_like,
                count = likesItem.count.toString(),
                onItemClickListener = { onLikeClickListener(likesItem) }
            )


            val commentsItem = statistics.getItemByType(StatisticsType.COMMENTS)
            IconWithCountRow(
                iconResId = R.drawable.ic_comment,
                count = commentsItem.count.toString(),
                onItemClickListener = { onCommentClickListener(commentsItem) }
            )

            val repostsItem = statistics.getItemByType(StatisticsType.REPOSTS)
            IconWithCountRow(
                iconResId = R.drawable.ic_share,
                count = repostsItem.count.toString(),
                onItemClickListener = { onRepostClickListener(repostsItem) }
            )
        }

        Row {
            val viewsItem = statistics.getItemByType(StatisticsType.VIEWS)
            IconWithCountRow(
                iconResId = R.drawable.ic_views_count,
                count = viewsItem.count.toString(),
                endPadding = 0.dp,
                onItemClickListener = { onViewClickListener(viewsItem) }
            )
        }
    }
}

private fun List<StatisticsItem>.getItemByType(type: StatisticsType): StatisticsItem {
    return this.find { it.type == type } ?: throw RuntimeException()
}

@Composable
fun IconWithCountRow(
    iconResId: Int,
    count: String,
    endPadding: Dp = 12.dp,
    onItemClickListener: () -> Unit
) {
    Log.d("recomposition", "IconWithCountRow")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onItemClickListener() }
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 4.dp),
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = count,
            modifier = Modifier
                .padding(end = endPadding),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}
