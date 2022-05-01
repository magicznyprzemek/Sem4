package com.example.soundboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Context
import android.media.MediaPlayer
import android.widget.Button
import android.widget.Toast
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity()
{
    var Z = R.raw.sound1
    var A = R.raw.sounda
    var B = R.raw.soundb
    var C = R.raw.soundc
    var D = R.raw.soundd
    var E = R.raw.sounde

    var btnArray: Array<Button?> = Array(6) { null }
    var saveList: MutableList<Int> = ArrayList()
    var mp : MediaPlayer = MediaPlayer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ArrayConnect(btnArray)
        Write("ala")
        Write("ma")
        Write("kota")


    }

    fun ArrayConnect(tab: Array<Button?>) {
        for (i in 1 until 7) {
            val ID = this.getResources().getIdentifier("button" + i, "id", this.getPackageName())
            tab[i - 1] = findViewById(ID)

        }
    }

    fun PlaySound(context: Context, SoundID: Int) {
       // mp.start()
        mp=MediaPlayer.create(context, SoundID)
        mp.start()
    }

    fun showIds(v: View) {
        for (i in 0 until saveList.size) {
            Toast.makeText(this, saveList[i].toString(), Toast.LENGTH_SHORT).show()
        }
    }
    fun Read(v : View)
    {
        try
        {
            val FileIN : FileInputStream = openFileInput("dataFile.txt")
            val InputRead : InputStreamReader = InputStreamReader(FileIN)
            var text: String = ""
            while(!text.isEmpty())
            {
                text = InputRead.readText()
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }

        }
        catch (e: Exception)
        {
            throw e
        }

    }
    fun Write(line :String)
    {
      try
      {
         var fileOut :FileOutputStream = openFileOutput("dataFile.txt", MODE_PRIVATE)
          val out_writer: OutputStreamWriter = OutputStreamWriter(fileOut)
          out_writer.write(line)
          out_writer.close()

      }
      catch (e: Exception)
      {
          e.printStackTrace()
      }
    }

    fun SoundButtons(v: View) {
        val s: Int = when {
            v.id == btnArray[0]?.id -> A
            v.id == btnArray[1]?.id -> B
            v.id == btnArray[2]?.id -> C
            v.id == btnArray[3]?.id -> D
            v.id == btnArray[4]?.id -> E
            v.id == btnArray[5]?.id -> Z
            else -> 0
        }
        PlaySound(this, s)
        saveList.add(s)
    }
}