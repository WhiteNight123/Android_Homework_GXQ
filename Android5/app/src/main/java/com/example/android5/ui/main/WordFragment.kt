package com.example.android5.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android5.databinding.FragmentWordBinding

/**
 * ...
 * @author WhiteNight123(Guo XiaoQiang)
 * @email 1448375249@qq.com
 * @data 2022/3/8
 */
class WordFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(WordViewModel::class.java) }
    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchWordEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchWord(content)
            } else {
                viewModel.wordList.clear()
            }
        }
        viewModel.wordLiveData.observe(viewLifecycleOwner, Observer { result ->
            val word = result.getOrNull()
            if (word != null) {
                viewModel.wordList.clear()
                viewModel.wordList.add(word)
                binding.wordText.text = viewModel.wordList[0].word
                binding.accentText.text = viewModel.wordList[0].accent
                binding.meanCnText.text = viewModel.wordList[0].meanCn
                binding.meanEnText.text = viewModel.wordList[0].meanEn
                binding.sentenceText.text = viewModel.wordList[0].sentence
                binding.sentenceTransText.text = viewModel.wordList[0].sentenceTrans
            } else {
                Toast.makeText(activity, "未能查询到单词", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}