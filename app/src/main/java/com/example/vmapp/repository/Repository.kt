package com.example.vmapp.repository


import com.example.vmapp.api.ApiService
import com.example.vmapp.util.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


interface Repository{
    suspend fun fetchPeople(): Flow<UIState>
    suspend fun fetchRooms():Flow<UIState>
}
class RepositoryImp @Inject constructor(private val apiService: ApiService):Repository
{
    override suspend fun fetchPeople(): Flow<UIState> =
       flow {
          emit(UIState.Loading)
          try{
              val response = apiService.getPeople()
              if(response.isSuccessful){
                  response.body()?.let {
                      emit(UIState.Success(it))
                  }?:throw Exception("Response is null")
              }
              else throw Exception("Failed Network Call")
          }catch(e :Exception){
              emit(UIState.Error(e))
          }
       }

    override suspend fun fetchRooms(): Flow<UIState> =
        flow{
            emit(UIState.Loading)
            try{
                val response = apiService.getRoom()
                if(response.isSuccessful){
                    response.body()?.let{
                        emit(UIState.Success(it))
                    }?:throw Exception("Response is null")
                }
                else throw Exception("Network Failed")
            }catch (e :Exception){
                emit(UIState.Error(e))
            }
        }


}