package br.com.gtb.libraries.uicore.extensions

import androidx.lifecycle.MutableLiveData
import br.com.gtb.libraries.uicore.utils.Result

fun <T : Any> MutableLiveData<Result<T>>.resetStatus() =
    this.postValue(Result.None)