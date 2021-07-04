package demo.lutas.gitgubusers.presentation.views.user_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import demo.lutas.gitgubusers.R
import demo.lutas.gitgubusers.domain.data.entities.User
import kotlinx.android.synthetic.main.item_user_list.view.*

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    interface Action {
        fun onItemClick(login: String)
    }

    fun bind(item: User?, action: Action) {
        item?.apply {
            itemView.text_login.text = login
            itemView.text_badge.visibility = if (siteAdmin) View.VISIBLE else View.GONE
            Glide.with(itemView.context)
                .load(avatarUrl)
                .circleCrop()
                .into(itemView.image_avatar)
            itemView.setOnClickListener {
                action.onItemClick(login)
            }
        }
    }
}

class UserListAdapter(
    diffCallback: DiffUtil.ItemCallback<User>,
    private val action: UserViewHolder.Action
) : PagingDataAdapter<User, UserViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_user_list, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, action)
    }
}