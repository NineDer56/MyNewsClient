package com.example.vknews.presentation.news

import android.content.Intent
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.vknews.R
import com.example.vknews.domain.StatisticsItem
import com.example.vknews.domain.StatisticsType
import com.example.vknews.domain.news.NewsItem

@Composable
fun ArticlePost(
    modifier: Modifier = Modifier,
    newsItem: NewsItem,
    onLikeClickListener: (StatisticsItem) -> Unit,
    onCommentClickListener: (StatisticsItem) -> Unit,
    onRepostClickListener: (StatisticsItem) -> Unit,
    onViewClickListener: (StatisticsItem) -> Unit,
    onReadArticleClickListener: (Intent) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary)
    ) {

        PostHead(newsItem)
        PostTitle(newsItem)
        PostKeywords(newsItem)
        PostPicture(newsItem)
        PostDescription(newsItem)
        ReadArticle(
            newsItem = newsItem,
            onReadArticleClickListener = onReadArticleClickListener
        )
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
            .padding(start = 12.dp, end = 12.dp, top = 4.dp)
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

        IconButton(
            onClick = {},
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}

@Composable
fun PostKeywords(newsItem: NewsItem) {
    if (newsItem.keywords.isNotEmpty()) {
        Text(
            text = newsItem.keywords.joinToString(),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .padding(horizontal = 12.dp)
        )
    }
}

@Composable
fun PostTitle(
    newsItem: NewsItem
) {
    Log.d("recomposition", "PostText")
    Text(
        text = newsItem.title,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(horizontal = 12.dp)
    )
}

@Composable
fun PostPicture(
    newsItem: NewsItem
) {
    if(newsItem.imageUrl.isNotBlank()){
        Spacer(modifier = Modifier.height(4.dp))
        AsyncImage(
            model = newsItem.imageUrl.takeIf { it.isNotBlank() },
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }

}

@Composable
fun PostDescription(newsItem: NewsItem) {
    if(newsItem.description.isNotBlank()){
        Text(
            text = newsItem.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}


@Composable
fun ReadArticle(newsItem: NewsItem, onReadArticleClickListener: (Intent) -> Unit) {
    Text(
        text = stringResource(R.string.read_article),
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onTertiary,
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .clickable(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, newsItem.link.toUri())
                    onReadArticleClickListener(intent)
                }
            )
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
            .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 12.dp)
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


@Preview
@Composable
fun PreviewArticlePost() {
    ArticlePost(
        modifier = Modifier.padding(8.dp),
        newsItem = NewsItem(
            articleId = "312321",
            title = "Go Back to the Shadow! UK Professor Claims Tolkien's Lord of the Rings 'Demonizes' People of Color",
            link = "https://twitchy.com/grateful-calvin/2025/10/17/tolkiens-lord-of-the-rings-demonizes-people-of-color-n2420498",
            keywords = listOf("english", "data", "it"),
            creator = listOf("Топор"),
            description = "slfjlsdjfksdjflksdflsdjlkdsj jsdflk jsdlk jsdlk jsdlkj sdlkfjslkdj flsdf lksdnlks nbljrwpijepirqpo lkdsn ",
            pubDate = "2025-10-17 22:00:00",
            imageUrl = "https://media.townhall.com/cdn/hodl/tw/images/up/2024/331/5bb3b0d6-b72e-4248-aa49-78d6ccf50e33.PNG",
            videoUrl = "",
            sourceName = "Twitchy",
            sourceUrl = "https://twitchy.com",
            sourceIcon = "https://n.bytvi.com/twitchy.png"
        ),
        onLikeClickListener = {},
        onRepostClickListener = {},
        onViewClickListener = {},
        onCommentClickListener = {},
        onReadArticleClickListener = {}
    )
}