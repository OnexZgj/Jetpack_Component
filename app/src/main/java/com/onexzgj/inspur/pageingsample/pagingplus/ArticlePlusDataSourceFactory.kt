/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onexzgj.inspur.pageingsample.pagingplus

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.onexzgj.inspur.onexkt.model.Article

/**
 * A simple data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
class ArticlePlusDataSourceFactory() : DataSource.Factory<Int, Article>() {
    val sourceLiveData = MutableLiveData<ArticlPlusDataSource>()
    lateinit var source: ArticlPlusDataSource

    override fun create(): DataSource<Int, Article> {
        source = ArticlPlusDataSource()
        sourceLiveData.postValue(source)
        return source
    }
}
