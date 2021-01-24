package ru.skillbranch.skillarticles.core.notifier

import io.reactivex.rxjava3.core.Observable
import ru.skillbranch.skillarticles.core.notifier.event.BasketEvent

interface BasketNotifier {
    fun eventSubscribe(): Observable<BasketEvent>
    fun putDishes(dish: BasketEvent.AddDish)
}