package com.example.room

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class homeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }






    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Realm.init(activity) //to strat configration of database realm

        fitshData()

        recycler_home.layoutManager = LinearLayoutManager(this.context,LinearLayout.VERTICAL,false)
        recycler_home.adapter = adapter_home()

        bu_exit.setOnClickListener { activity?.finish() }
        bu_face.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100004191377620")))
        }
        bu_share.setOnClickListener {
            val sent=Intent()
            sent.action=Intent.ACTION_SEND
            sent.putExtra(Intent.EXTRA_TEXT,"download app for learning c++")
            sent.type="text/plain"
            startActivity(Intent.createChooser(sent,"choose your app you want"))
        }

    }

  fun fitshData() {
    val url="https://rabeaadle.000webhostapp.com/public/api/"
   //   val url="https://jsonplaceholder.typicode.com/"
      val retrofit=Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
      val APi:API=retrofit.create(API::class.java)

       val call =APi.getedata()
      var item =call.enqueue(object:Callback<ArrayList<data>>{
          override fun onFailure(call: Call<ArrayList<data>>, t: Throwable) {
              Toast.makeText(activity, "can  not ", Toast.LENGTH_SHORT).show()
          }

          override fun onResponse(call: Call<ArrayList<data>>, response: Response<ArrayList<data>>) {
              val config : RealmConfiguration =RealmConfiguration.Builder().name("cplass.realm").build()
              val realm:Realm=Realm.getInstance(config) //to make configration of database realm

              var alladata :ArrayList<data> ?=response.body() //make all data from api in var all data
              if(realm.where(DataBase::class.java).findAll().isEmpty()){ //to check if database is empty  or not  because  ever time it download from api
                  realm.executeTransaction {
                      for(i in alladata!!)
                      {
                          val lesson =realm.createObject(DataBase::class.java,i.id)
                          lesson.index_name=i.index_name
                          lesson.image_url=i.image_url
                          lesson.lesson=i.lesson

                      }
                  }

              }
              // if thier  new update in database
              if(realm.where(DataBase::class.java).findAll().size!=alladata!!.size) {

                  realm.executeTransaction {
                      realm.deleteAll()
                      for(i in alladata!!)
                      {
                          val lesson =realm.createObject(DataBase::class.java,i.id)
                          lesson.index_name=i.index_name
                          lesson.image_url=i.image_url
                          lesson.lesson=i.lesson

                      }
                  }

              }

              recycler_home.adapter = adapter_home()


          }

      })


  }
}