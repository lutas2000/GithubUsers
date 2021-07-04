package demo.lutas.gitgubusers.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import demo.lutas.gitgubusers.R
import demo.lutas.gitgubusers.presentation.views.user_list.UserListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_fragment, UserListFragment())
        fragmentTransaction.commit()
    }
}