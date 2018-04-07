package com.codelife.test.fragment

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codelife.test.BaseFactory
import com.codelife.test.adapter.QuestionListAdapter
import com.codelife.test.common.ItemClickSupport
import com.codelife.test.databinding.LayoutQuestionListBinding
import com.codelife.test.models.QuestionInfo
import com.codelife.test.mvp.Contract.QuestionListContract
import com.codelife.test.mvp.presenter.QuestionListPresenter
import com.codelife.test.utils.SpacesItemDecoration
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layout_question_list.*

class QuestionListFragment : BaseFragment(), QuestionListContract.View {

    lateinit var binding: LayoutQuestionListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_question_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvQuestionList.itemAnimator = DefaultItemAnimator()
        binding.rvQuestionList.addItemDecoration(SpacesItemDecoration(1, 10, true))
        binding.rvQuestionList.layoutManager = GridLayoutManager(activity, 1)
        ItemClickSupport.addTo(rv_question_list).setOnItemClickListener({ recyclerView, position, v ->
            val selectedItem = (recyclerView.adapter as QuestionListAdapter).questionList[position]
            if (selectedItem.selectedOption == null) {
                openDetailFragment(selectedItem, position)
            }

        })


        presenter.getQuestionList()

        binding.fabQuestionListSave.setOnClickListener {
            calculateScore()
        }
    }

    private fun openDetailFragment(item: QuestionInfo, position: Int) {
        val fragment = QuestionDetailFragment()
        val bundle = Bundle()
        bundle.putString(QuestionDetailFragment.DATA_KEY, Gson().toJson(item))
        bundle.putInt(QuestionDetailFragment.POSITION_KEY, position)
        fragment.arguments = bundle
        fragment.setTargetFragment(this, 1)
        fragmentManager?.beginTransaction()?.add(R.id.container_view, fragment)?.hide(this)?.addToBackStack(null)?.commit()
    }

    override fun getPresenter(): QuestionListPresenter {
        return QuestionListPresenter(this, BaseFactory.getInstance().eventBus, BaseFactory.getInstance().feature)
    }

    override fun getPresenterView(): QuestionListContract.View {
        return this
    }

    override fun onQuestionListSucess(data: ArrayList<QuestionInfo>) {
        binding.rvQuestionList.adapter = QuestionListAdapter(data)
    }

    override fun showProgressDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgressDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayMessage(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1 && data?.hasExtra(QuestionDetailFragment.DATA_KEY)!!) {
            val position = data.getIntExtra(QuestionDetailFragment.POSITION_KEY, -1)
            val updatedItem = Gson().fromJson(data.getStringExtra(QuestionDetailFragment.DATA_KEY), QuestionInfo::class.java)
            if (position != -1) {
                val adapter = rv_question_list.adapter as QuestionListAdapter
                adapter.questionList.set(position, updatedItem)
                rv_question_list.adapter.notifyItemChanged(position)
            }
        }
    }

    private fun calculateScore() {
        var rightAnswer: Int = 0
        val list = (binding.rvQuestionList.adapter as QuestionListAdapter).questionList
        for (i in list) {
            val selectionAnswer = i.selectedOption
            if (selectionAnswer != null && selectionAnswer.id.equals(i.solution)) {
                rightAnswer += 1
            }
        }


        val message = getString(R.string.final_score,rightAnswer)
        val snackbar = Snackbar.make(activity!!.findViewById(android.R.id.content),message , Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retry", {
            view -> snackbar.dismiss()
            presenter.getQuestionList()
        }).show()


    }
}
