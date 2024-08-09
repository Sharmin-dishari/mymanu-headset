/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.upgrade.download

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.mymanu.companionapp.repository.service.getfiles.FileData
import com.mymanu.companionapp.ui.upgrade.tags.TagsAdapter
import com.mymanu.companionapp.utils.FILE_DATE_PATTERN
import com.mymanu.companionapp.utils.format
import com.mymanu.companionapp.utils.ifNullOrEmpty
import com.mymanu.companionapp.utils.parseIsoDate

class FileContent(context: Context, val file: FileData) {
    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    val tagsAdapter = TagsAdapter(file.tags)
    val hardwareVersions: String = file.hardwareVersions.joinToString(", ")

    fun title(defaultValue: String): String = file.title.ifNullOrEmpty { defaultValue }
    fun chipFamily(defaultValue: String): String = file.chipFamily.ifNullOrEmpty { defaultValue }
    fun date(defaultValue: String): String =
        file.createdOn.parseIsoDate().format(FILE_DATE_PATTERN).ifNullOrEmpty { defaultValue }

    fun description(defaultValue: String): String = file.description.ifNullOrEmpty { defaultValue }
    fun id(defaultValue: String): String = file.id.ifNullOrEmpty { defaultValue }
}
