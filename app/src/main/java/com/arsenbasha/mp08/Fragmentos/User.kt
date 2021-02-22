package com.arsenbasha.mp08.Fragmentos

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arsenbasha.mp08.R


class User : Fragment() {

    companion object {
        fun newInstance() = User()
    }

    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar
            ?.setTitle(R.string.menu_User)
        setHasOptionsMenu(true);

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.user_fragment, container, false)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        viewModel.inflar(view)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.share, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.share) {

            //El código que se ejecutara al hacer click en esa opción
        }
        return super.onOptionsItemSelected(item)
    }

}