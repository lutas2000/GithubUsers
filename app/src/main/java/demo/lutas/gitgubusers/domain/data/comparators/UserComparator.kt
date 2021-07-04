package demo.lutas.gitgubusers.domain.data.comparators

import androidx.recyclerview.widget.DiffUtil
import demo.lutas.gitgubusers.domain.data.entities.User

object UserComparator : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}