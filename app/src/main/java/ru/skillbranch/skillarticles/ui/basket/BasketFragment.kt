package ru.skillbranch.skillarticles.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import ru.skillbranch.skillarticles.core.notifier.BasketNotifier
import ru.skillbranch.skillarticles.core.notifier.event.BasketEvent
import ru.skillbranch.skillarticles.databinding.FragmentBasketBinding

class BasketFragment : Fragment() {
    companion object {
        fun newInstance() = BasketFragment()
    }

    private val notifier: BasketNotifier by inject()

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notifier.eventSubscribe()
            .subscribe {
                if (it is BasketEvent.AddDish) {
                    binding.tvDishes.text = "${binding.tvDishes.text}\n\n ${it.title} стоимость ${it.price}"
                }

            }
    }
}