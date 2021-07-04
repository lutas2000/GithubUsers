package demo.lutas.gitgubusers.presentation.views.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import demo.lutas.gitgubusers.R
import demo.lutas.gitgubusers.domain.data.comparators.UserComparator
import demo.lutas.gitgubusers.presentation.viewmodels.UserListViewModel
import demo.lutas.gitgubusers.presentation.views.user_detail.UserDetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserListFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_user_list, container, false)

    private val viewModel by viewModel<UserListViewModel>()
    private var adapter: UserListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                adapter?.submitData(pagingData)
            }
        }
    }

    private fun initRecyclerView() {
        val action = object: UserViewHolder.Action {
            override fun onItemClick(login: String) {
                addUserDetailFragment(login)
            }
        }
        adapter = UserListAdapter(UserComparator, action)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
    }

    private fun addUserDetailFragment(login: String) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val fragment = UserDetailFragment.newInstance(login)
        fragmentTransaction?.add(R.id.main_fragment, fragment)
        fragmentTransaction?.addToBackStack(login)
        fragmentTransaction?.commit()
    }
}