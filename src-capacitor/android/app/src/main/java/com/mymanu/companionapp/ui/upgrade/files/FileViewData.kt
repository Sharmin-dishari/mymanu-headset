/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.upgrade.files

import com.mymanu.companionapp.repository.service.getfiles.FileData
import com.mymanu.companionapp.ui.common.ListAdapterItemData
import com.mymanu.companionapp.ui.upgrade.tags.TagsAdapter
import com.mymanu.companionapp.utils.FILE_DATE_PATTERN
import com.mymanu.companionapp.utils.format
import com.mymanu.companionapp.utils.ifNullOrEmpty
import com.mymanu.companionapp.utils.parseIsoDate

class FileViewData(val file: FileData) : ListAdapterItemData {

    val createdOn: String = file.createdOn.parseIsoDate().format(FILE_DATE_PATTERN).ifNullOrEmpty { file.createdOn }
    val tags: Array<String> = file.tags
    val tagsAdapter = TagsAdapter(file.tags)

    fun title(defaultValue: String): String = file.title.ifNullOrEmpty { defaultValue }
    fun description(defaultValue: String): String = file.description.ifNullOrEmpty { defaultValue }

    override fun isSameContent(other: ListAdapterItemData?): Boolean = equals(other)

    override fun isSameItem(other: ListAdapterItemData?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileViewData
        return file.id != other.file.id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileViewData

        if (file != other.file) return false

        return true
    }

    override fun hashCode(): Int {
        return file.hashCode()
    }
}
