package de.syntax_institut.bettersushi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.bettersushi.R
import de.syntax_institut.bettersushi.data.model.Category
import de.syntax_institut.bettersushi.data.model.Dish
import de.syntax_institut.bettersushi.ui.theme.BetterSushiTheme
import de.syntax_institut.bettersushi.util.formatPrice

@Composable
fun DishRowItem(
    dish: Dish,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ListItem(
        headlineContent = {
            NormalText(dish.name)
        },
        supportingContent = {
            NormalText(
                formatPrice(dish.price),
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        leadingContent = {
            Image(
                painterResource(id = dish.image),
                contentDescription = "Dish Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
            headlineColor = MaterialTheme.colorScheme.onPrimary,
            supportingColor = MaterialTheme.colorScheme.onSecondary
        ),
        modifier = modifier
            .padding(vertical = 2.dp, horizontal = 16.dp)
            .clip(MaterialTheme.shapes.medium),
        trailingContent = content
    )
}

@Preview
@Composable
fun DishRowItemPreview() {
    BetterSushiTheme {
        DishRowItem(dish = Dish("Sushi", Category.Sushi, 1.49, R.drawable.cat_sushi)) {
            QuantitySelector(count = 0, decreaseCount = { /*TODO*/ }, increaseCount = { /*TODO*/ })
        }
    }
}