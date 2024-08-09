/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.service

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import com.mymanu.companionapp.repository.service.download.DownloadProgress
import com.mymanu.companionapp.repository.service.feedback.FeedbackParameters
import com.mymanu.companionapp.repository.service.feedback.Issue
import com.mymanu.companionapp.repository.service.getfiles.FileData
import com.mymanu.companionapp.repository.service.getfiles.GetFilesParameters

interface ServiceRepository {
    fun init() {}
    fun reset() {}

    fun sendFeedback(parameters: FeedbackParameters): LiveData<Result<Unit, Issue, ServiceError>>

    fun getFiles(parameters: GetFilesParameters): LiveData<Result<Unit, List<FileData>, ServiceError>>

    fun downloadFile(
        context: Context,
        fileData: FileData
    ): LiveData<Result<DownloadProgress, Uri, ServiceError>>

    fun cancelDownload()
}
