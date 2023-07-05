package br.com.renancsdev.animacao.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.renancsdev.animacao.R
import br.com.renancsdev.animacao.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var context     = this@MainActivity
    var layoutMain  = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setarConfiguracaoInicial()

        eventoBotoes()
    }

    // Configuracao Inicial
    fun setarConfiguracaoInicial(){
        setarLayout()
        setarBinding()
    }
    fun setarLayout(){
        setContentView(R.layout.activity_main)
    }
    fun setarBinding(){
        binding = DataBindingUtil.setContentView(context , layoutMain)
    }

    fun eventoBotoes(){
        binding.btnFadein.setOnClickListener {
            chamarAnimacaoFadeIn(binding.tvAnimacao , R.anim.fade_in)
        }
        binding.btnFadeout.setOnClickListener {
            chamarAnimacaoFadeOut(binding.tvAnimacao , R.anim.fade_out)
        }
        binding.btnZoomin.setOnClickListener {
            chamarAnimacao(binding.tvAnimacao , R.anim.zoom_in)
        }
        binding.btnZoomout.setOnClickListener {
            chamarAnimacao(binding.tvAnimacao , R.anim.zoom_out)
        }
        binding.btnSlidedown.setOnClickListener {
            chamarAnimacao(binding.tvAnimacao , R.anim.slide_down)
        }
        binding.btnSlideup.setOnClickListener {
            chamarAnimacao(binding.tvAnimacao , R.anim.slide_up)
        }
        binding.btnBounce.setOnClickListener {
            chamarAnimacao(binding.tvAnimacao , R.anim.bounce)
        }
        binding.btnRotate.setOnClickListener {
            chamarAnimacao(binding.tvAnimacao , R.anim.rotate)
        }
        binding.btnTransicaoEsquerda.setOnClickListener {
            chamarAnimacaoTransicaoEsquerda()
        }
        binding.btnTransicaoDireita.setOnClickListener {
            chamarAnimacaoTransicaoDireita()
        }
    }

    fun chamarAnimacao(texto: TextView, tipoAnimacao: Int){
        val animation = AnimationUtils.loadAnimation(this, tipoAnimacao)
        texto.startAnimation(animation)
    }

    fun chamarAnimacaoFadeIn(texto: TextView, tipoAnimacao: Int){
        texto.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this, tipoAnimacao)
        texto.startAnimation(animation)
    }

    fun chamarAnimacaoFadeOut(texto: TextView, tipoAnimacao: Int){
        val animation = AnimationUtils.loadAnimation(this, tipoAnimacao)
        texto.startAnimation(animation)
        delayViaHandlerTexto(texto , 1000)
    }

    fun delayViaHandlerTexto(texto: TextView , delay: Long){
        Handler(Looper.getMainLooper()).postDelayed({
            texto.visibility = View.GONE
        }, delay)
    }

    fun delayViaHandler(delay: Long){
        Handler(Looper.getMainLooper()).postDelayed({
        }, delay)
    }


    fun chamarAnimacaoTransicaoEsquerda(){
        this.overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_left)
        delayViaHandler(3000)
        this.startActivity(Intent(this , TransicaoResultado::class.java))
    }

    fun chamarAnimacaoTransicaoDireita(){
        this.overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right)
        delayViaHandler(3000)
        this.startActivity(Intent(this , TransicaoResultado::class.java))
    }

    fun chamarAnimacaoTransicaoDevagar(){
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@MainActivity, TransicaoResultado::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.fade_in_slow, R.anim.fade_out_slow)
        }, 2000)
    }
}