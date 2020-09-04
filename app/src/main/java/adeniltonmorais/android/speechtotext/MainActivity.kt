package adeniltonmorais.android.speechtotext

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private val ENTRADA_VOZ=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgVoice.setOnClickListener {

            falar()
        }


           }

    private fun falar() {
        val myIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        myIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM )
        myIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
        myIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Fale algo")

        try {
            startActivityForResult(myIntent,ENTRADA_VOZ)

        }catch (e:Exception){

            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
        }


    }
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){

            ENTRADA_VOZ->{
                if(resultCode==Activity.RESULT_OK && null !=data){
                    val result= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    txtVoice.text=result[0]
                }
            }



        }
    }

}