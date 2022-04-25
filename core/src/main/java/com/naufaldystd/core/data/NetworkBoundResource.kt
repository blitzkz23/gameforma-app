package com.naufaldystd.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ResultType, RequestType> {

	private var result: Flow<Resource<ResultType>> = flow {
		emit(Resource.Loading())
	}
}