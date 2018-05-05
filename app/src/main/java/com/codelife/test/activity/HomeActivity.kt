package com.codelife.test.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.codelife.test.R
import com.codelife.test.fragment.QuestionListFragment
import com.codelife.test.mvp.IPresenter
import com.codelife.test.mvp.IView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        loadQuestionListFragment()
    }

    private fun loadQuestionListFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container_view,QuestionListFragment(),null)
                .commit()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getPresenter(): IPresenter<*>? {
        return null
    }

    override fun getPresenterView(): IView? {
        return null
    }
}
