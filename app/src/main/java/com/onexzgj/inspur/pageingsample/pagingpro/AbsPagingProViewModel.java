package com.onexzgj.inspur.pageingsample.pagingpro;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * des：
 * author：onexzgj
 * time：2020/5/4
 */
public abstract class AbsPagingProViewModel<T> extends ViewModel {

    protected PagedList.Config config;
    private DataSource dataSource;
    private LiveData<PagedList<T>> pageData;

    private MutableLiveData<Boolean> boundaryPageData = new MutableLiveData<>();


    public LiveData<PagedList<T>> getPageData() {
        return pageData;
    }

    public DataSource getDataSource() {
        return dataSource;
    }


    public AbsPagingProViewModel() {
        config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(22)
                // .setMaxSize(100)；
                // .setEnablePlaceholders(false)
                // .setPrefetchDistance()
                .build();

        pageData = new LivePagedListBuilder(factory,config)
                .setInitialLoadKey(0)
                .setBoundaryCallback(callback)
                .build();
    }


    private DataSource.Factory factory =new DataSource.Factory() {
        @NonNull
        @Override
        public DataSource create() {
            if (dataSource==null || dataSource.isInvalid()) {
                dataSource = createDataSource();
            }

            return dataSource;
        }
    };

    protected abstract DataSource createDataSource();

    public LiveData<Boolean> getBoundaryPageData() {
        return boundaryPageData;
    }

    //PagedList数据被加载 情况的边界回调callback
    //但 不是每一次分页 都会回调这里，具体请看 ContiguousPagedList#mReceiver#onPageResult
    //deferBoundaryCallbacks
    PagedList.BoundaryCallback<T> callback = new PagedList.BoundaryCallback<T>() {
        @Override
        public void onZeroItemsLoaded() {
            //新提交的PagedList中没有数据
            boundaryPageData.postValue(false);
        }

        @Override
        public void onItemAtFrontLoaded(@NonNull T itemAtFront) {
            //新提交的PagedList中第一条数据被加载到列表上
            boundaryPageData.postValue(true);
        }

        @Override
        public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
            //新提交的PagedList中最后一条数据被加载到列表上
        }
    };

}
