package de.syntax_institut.bettersushi.util

fun formatPrice(price: Double): String {
    return "${String.format("%.2f", price)}€"
}