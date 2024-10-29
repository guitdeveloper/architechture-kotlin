package br.com.gtb.libraries.uicore.exceptions

import br.com.gtb.libraries.uicore.R
import com.blankj.utilcode.util.StringUtils

class ListIsEmptyException : Exception() {
    override val message: String?
        get() = StringUtils.getString(R.string.message_list_is_empty)
}