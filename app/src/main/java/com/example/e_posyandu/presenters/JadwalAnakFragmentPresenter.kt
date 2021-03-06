package com.example.e_posyandu.presenters

import com.example.e_posyandu.contracts.JadwalAnakFragmentContract
import com.example.e_posyandu.models.Jadwal
import com.example.e_posyandu.models.JadwalAnak
import com.example.e_posyandu.responses.WrappedListResponse
import com.example.e_posyandu.utilities.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JadwalAnakFragmentPresenter(v : JadwalAnakFragmentContract.View?) : JadwalAnakFragmentContract.presenter {

    private var view : JadwalAnakFragmentContract.View? = v
    private var apiServices = APIClient.APIService()

    override fun getJadwalAnak(token: String) {
        val request = apiServices.getJadwalBalita("Bearer "+ token)
        view?.showLoading()
        request.enqueue(object : Callback<WrappedListResponse<Jadwal>> {
            override fun onResponse(
                call: Call<WrappedListResponse<Jadwal>>,
                response: Response<WrappedListResponse<Jadwal>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status.equals(200)){
                        view?.attacthToRecycler(body?.data)

                    }
                }else{
                    view?.showToast("Terjadi kesalahan, silahkan coba lagi lain waktu")
                }
                view?.hideLoading()
            }

            override fun onFailure(call: Call<WrappedListResponse<Jadwal>>, t: Throwable) {
                view?.showToast("Tidak dapat terkoneksi dengan server")
                view?.hideLoading()
            }
        })
    }

    override fun destroy() {
        view = null
    }
}