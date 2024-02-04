package tasks

import contributors.User

fun List<User>.aggregate(): List<User> =
    this.groupBy ({it.login}, {it})
        .mapValues { it.value.reduce{
            left, right ->
            User(left.login, left.contributions + right.contributions)
        } }
        .values
        .sortedByDescending { it.contributions }
        .toList()