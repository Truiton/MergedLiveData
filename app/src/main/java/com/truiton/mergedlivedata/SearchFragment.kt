package com.truiton.mergedlivedata

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.truiton.mergedlivedata.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private var listenerSearch: OnSearchFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSearchFragmentInteractionListener) {
            listenerSearch = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnSearchFragmentInteractionListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = activity?.let { FourSquareAdapter(it) }
        binding.items.adapter = adapter
        binding.items.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?

        var mainActivityViewModel: MainActivityViewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
        mainActivityViewModel.allFavourites.observe(viewLifecycleOwner, Observer { words ->
            words?.let { adapter?.setWords(it) }
        })


        adapter?.setOnItemClickListener(object : FourSquareAdapter.ClickListener {
            override fun onClick(position: Int, view: View) {
                Toast.makeText(activity, "Clicked: $position", Toast.LENGTH_SHORT).show()
                mainActivityViewModel.setPlace(position)
                listenerSearch?.onItemClick(position)
            }
        })

        val handler: Handler = @SuppressLint("HandlerLeak")
        object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                if (msg.what == MainActivity.TRIGGER_SERACH) {
                    if (!TextUtils.isEmpty(binding.searchBox?.text)) {
                        mainActivityViewModel.getPlaces(binding.searchBox.text.toString())
                    }
                }
            }
        }

        binding.searchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                handler.removeMessages(MainActivity.TRIGGER_SERACH)
                handler.sendEmptyMessageDelayed(
                    MainActivity.TRIGGER_SERACH,
                    MainActivity.SEARCH_TRIGGER_DELAY_IN_MS
                )
            }
        })

    }

    override fun onDetach() {
        super.onDetach()
        listenerSearch = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnSearchFragmentInteractionListener {
        fun onItemClick(position: Int)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
