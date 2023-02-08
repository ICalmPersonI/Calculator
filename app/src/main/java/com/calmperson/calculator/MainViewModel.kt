package com.calmperson.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    private val model = Model()

    private var _currExp = MutableLiveData<String>("")
    val currExp: LiveData<String>
        get() = _currExp

    private val _calculationsHistory = MutableLiveData(mutableListOf<String>())
    val calculationsHistory: LiveData<MutableList<String>>
        get() = _calculationsHistory

    private var _expOverflow = MutableLiveData(false)
    val expOverFlow: MutableLiveData<Boolean>
        get() = _expOverflow


    fun calculate() {
        val exp = _currExp.value!!
        _currExp.value = model.calculate(exp)?.let { result ->
            addToHistory { "$exp = $result" }
            result.toString()
        } ?: ""
    }

    fun addCharToExp(chr: Char) {
        if (!_expOverflow.value!!) {
            _currExp.value += chr
        }
    }

    fun removeLast() {
        val exp = _currExp.value!!
        if (exp.isNotEmpty()) {
            _currExp.value = exp.substring(0, exp.lastIndex)
        }
    }

    fun clearExp() {
        _currExp.value = ""
    }

    private fun addToHistory(body: () -> String) {
        with(_calculationsHistory.value!!) {
            if (this.size > 10) this.clear()
            this.add(body.invoke())
        }
    }


}