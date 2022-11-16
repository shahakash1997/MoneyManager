package co.asinfotech.mymoneymanager.ui.fragments.savings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.asinfotech.mymoneymanager.R

class SavingsFragment : Fragment() {

    companion object {
        fun newInstance() = SavingsFragment()
    }

    private lateinit var viewModel: SavingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_savings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SavingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}