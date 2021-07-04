package demo.lutas.gitgubusers.presentation.views.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import demo.lutas.gitgubusers.R
import demo.lutas.gitgubusers.presentation.viewmodels.UserDetailState
import demo.lutas.gitgubusers.presentation.viewmodels.UserDetailViewModel
import kotlinx.android.synthetic.main.fragment_user_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment: Fragment() {
    companion object {
        fun newInstance(login: String): UserDetailFragment {
            val fragment = UserDetailFragment()
            val args = Bundle()
            args.putString("login", login)
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel by viewModel<UserDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_user_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image_close.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        viewModel.state.observe(viewLifecycleOwner) {
            if (it is UserDetailState.Success) {
                it.user.apply {
                    text_name.text = name
                    text_bio.text = bio
                    text_login.text = login
                    text_location.text = location
                    text_blog.text = blog
                    text_badge.visibility = if (siteAdmin) View.VISIBLE else View.GONE
                    Glide.with(view)
                        .load(avatarUrl)
                        .circleCrop()
                        .into(image_avatar)
                }
            }
            if (it is UserDetailState.Loading) {
                progress_bar.show()
            } else {
                progress_bar.hide()
            }
            // TODO handle error
        }

        val login = arguments?.getString("login", null)
        if (!login.isNullOrEmpty()) {
            viewModel.getUserDetail(login)
        }
    }
}