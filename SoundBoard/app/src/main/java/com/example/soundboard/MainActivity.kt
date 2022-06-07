package com.example.soundboard

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*


class MainActivity : AppCompatActivity() {
    var Z = R.raw.sound1
    var A = R.raw.sounda
    var B = R.raw.soundb
    var C = R.raw.soundc
    var D = R.raw.soundd
    var E = R.raw.sounde

    var btnArray: Array<Button?> = Array(6) { null }
    var saveList: MutableList<Int> = ArrayList()
    var mp: MediaPlayer = MediaPlayer()
    var FileName = "dataFile0.txt"
    var file1 = File(FileName)
    var isRecording: Boolean = false
  //  val recordButton: Button? = null
  //  var playbButton: Button? = null
  //  var sekwencjaButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrayConnect(btnArray)
        val recordButton: Button? = findViewById(R.id.RecordBtn)
        val playButton: Button? = findViewById(R.id.button14)
       // val sekwencjaButton: Button? = findViewById(R.id.history)
        playButton?.setBackgroundColor(Color.BLACK)
        recordButton?.setBackgroundColor(Color.BLACK)
    }

    fun clearFile() // inaczej się nie udawało
    {
        try {

            val FileOut: FileOutputStream = openFileOutput(file1.path, MODE_PRIVATE)
            val outWriter = OutputStreamWriter(FileOut)
            outWriter.write("")
            outWriter.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    fun arrayConnect(tab: Array<Button?>) {
        for (i in 1 until 7) {
            val ID = this.getResources().getIdentifier("button" + i, "id", this.getPackageName())
            tab[i - 1] = findViewById(ID)

        }
    }

    fun playSound(context: Context, SoundID: Int) {
        mp = MediaPlayer.create(context, SoundID)
        mp.start()
    }

    fun showIds(v: View) {
        if(!saveList.isEmpty()) {
            var line = ""
            for (i in 0 until saveList.size) {
                line = line + translateToLetter(saveList[i]) + "-"
            }
            Toast.makeText(this, line, Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this, "BRAK SEKWENCJI", Toast.LENGTH_SHORT).show()

    }

    fun read(v: View) {
        try {
            val FileIN: FileInputStream = openFileInput(file1.path)
            val InputRead = InputStreamReader(FileIN)
            val linie = InputRead.readLines()
            InputRead.close()
            if (!linie.isEmpty()) {
                val splitLine = linie[0].split(';')
                for (i in 0 until splitLine.size) {
                    if (splitLine[i] != "") {
                        playSound(this, splitLine[i].toInt())
                        Thread.sleep(700)
                        //Handler(Looper.getMainLooper()).post(Runnable {
                      //      playSound(this, splitLine[i].toInt())

                      //  }, )
                    }
                }
            }
            else {
                Toast.makeText(this, "BRAK SEKWENCJI", Toast.LENGTH_SHORT).show()
            }

        } catch (e: IOException) {
            throw e
        }

    }

    fun write() {
        var line = ""
        try {

            val FileOut: FileOutputStream = openFileOutput(file1.path, MODE_APPEND)
            val outWriter = OutputStreamWriter(FileOut)
            for (i in 0 until saveList.size)
                line += saveList[i].toString() + ";"
            outWriter.write(line)
            outWriter.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun starRecord(v: View) {
        if (isRecording) {
            clearFile()
            write()
            isRecording = false
            v.setBackgroundColor(Color.BLACK)

        } else if (!isRecording) {
            saveList.clear()
            isRecording = true
            v.setBackgroundColor(Color.RED)

        }

    }

    fun translateToLetter(x: Int): Char {
        val s: Char = when {
            x == A -> 'A'
            x == B -> 'B'
            x == C -> 'C'
            x == D -> 'D'
            x == E -> 'E'
            x == Z -> 'Z'
            else -> '-'
        }
        return s
    }

    fun setREd() {
        for (i in 0 until btnArray.size) {
            btnArray[i]?.setBackgroundColor(Color.RED)
        }
    }

    fun soundButtons(v: View) {
        setREd()
        v.setBackgroundColor(Color.GREEN)
        val s: Int = when (v.id) {
            btnArray[0]?.id -> A
            btnArray[1]?.id -> B
            btnArray[2]?.id -> C
            btnArray[3]?.id -> D
            btnArray[4]?.id -> E
            btnArray[5]?.id -> Z
            else -> 0 // xd
        }
        playSound(this, s)
        if (isRecording) {
            saveList.add(s)
        }

    }
}