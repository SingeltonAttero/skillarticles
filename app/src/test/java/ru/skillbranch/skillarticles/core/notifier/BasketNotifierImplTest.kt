package ru.skillbranch.skillarticles.core.notifier

import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import ru.skillbranch.skillarticles.core.notifier.event.BasketEvent

class BasketNotifierImplTest {

    private lateinit var notifier: BasketNotifier

    @Before
    fun setUp() {
        notifier = BasketNotifierImpl()
    }

    @Test
    fun `when single event should return one event in subscribe`() {
        val event = BasketEvent.AddDish("test", "Test", "test")
        notifier.putDishes(event)

        notifier.eventSubscribe().test()
            .assertNotComplete()
        Assertions.assertThat(notifier.eventSubscribe().test().values().size).isEqualTo(1)
    }

    @Test
    fun `when put many clone data event should be return buffer event`() {
        val event = BasketEvent.AddDish("test", "Test", "test")
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)
        notifier.putDishes(event)

        notifier.eventSubscribe().test()
            .assertNotComplete()

        Assertions.assertThat(notifier.eventSubscribe().test().values().size).isEqualTo(12)
    }

    @Test
    fun `when many other data event should be return actual and order data`() {
        val listTestEvent = listOf(
            BasketEvent.AddDish("test", "Test", "test"),
            BasketEvent.AddDish("nst", "tyas", "sfx"),
            BasketEvent.AddDish("ase", "ew", "sdc"),
            BasketEvent.AddDish("tweq", "ewt", "dee")
        )
        listTestEvent.forEach {
            notifier.putDishes(it)
        }

        notifier.eventSubscribe().test()
            .assertNotComplete()
        Assertions.assertThat(notifier.eventSubscribe().test().values()).isEqualTo(listTestEvent)
        Assertions.assertThat(notifier.eventSubscribe().test().values().size).isEqualTo(listTestEvent.size)
    }
}