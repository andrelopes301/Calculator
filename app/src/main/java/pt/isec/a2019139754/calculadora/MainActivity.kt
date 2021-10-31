package pt.isec.a2019139754.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import pt.isec.a2019139754.calculadora.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var calculadora : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calculadora = ActivityMainBinding.inflate(layoutInflater)
        setContentView(calculadora.root)

        //Numeros
        calculadora.zero.setOnClickListener{adicionarExpressao("0",true)}
        calculadora.um.setOnClickListener{adicionarExpressao("1",true)}
        calculadora.dois.setOnClickListener{adicionarExpressao("2",true)}
        calculadora.tres.setOnClickListener{adicionarExpressao("3",true)}
        calculadora.quatro.setOnClickListener{adicionarExpressao("4",true)}
        calculadora.cinco.setOnClickListener{adicionarExpressao("5",true)}
        calculadora.seis.setOnClickListener{adicionarExpressao("6",true)}
        calculadora.sete.setOnClickListener{adicionarExpressao("7",true)}
        calculadora.oito.setOnClickListener{adicionarExpressao("8",true)}
        calculadora.nove.setOnClickListener{adicionarExpressao("9",true)}
        calculadora.ponto.setOnClickListener{adicionarExpressao(".",true)}


        //Operadores
        calculadora.divisao.setOnClickListener{

            if( calculadora.expressao.text.last() == '-' ||  calculadora.expressao.text.last() == '/' ||  calculadora.expressao.text.last() == '+' ||  calculadora.expressao.text.last() == '*'){

                val exp = calculadora.expressao.text.dropLast(1)
                calculadora.expressao.text = ""
                calculadora.expressao.append(exp)
                calculadora.expressao.append("/")
            }
            else
                adicionarExpressao("/",false)
        }
        calculadora.multiplicacao.setOnClickListener{
            if( calculadora.expressao.text.last() == '-' ||  calculadora.expressao.text.last() == '/' ||  calculadora.expressao.text.last() == '+' ||  calculadora.expressao.text.last() == '*'){

                val exp = calculadora.expressao.text.dropLast(1)
                calculadora.expressao.text = ""
                calculadora.expressao.append(exp)
                calculadora.expressao.append("*")
            }
            else
                adicionarExpressao("*",false)
        }
        calculadora.soma.setOnClickListener{
            if( calculadora.expressao.text.last() == '-' ||  calculadora.expressao.text.last() == '/' ||  calculadora.expressao.text.last() == '+' ||  calculadora.expressao.text.last() == '*'){

                val exp = calculadora.expressao.text.dropLast(1)
                calculadora.expressao.text = ""
                calculadora.expressao.append(exp)
                calculadora.expressao.append("+")
            }
            else
                adicionarExpressao("+",false)

        }
        calculadora.subtracao.setOnClickListener{
             if( calculadora.expressao.text.last() == '-' ||  calculadora.expressao.text.last() == '/' ||  calculadora.expressao.text.last() == '+' ||  calculadora.expressao.text.last() == '*'){

                val exp = calculadora.expressao.text.dropLast(1)
                calculadora.expressao.text = ""
                calculadora.expressao.append(exp)
                calculadora.expressao.append("-")
            }
            else
                adicionarExpressao("-",false)

        }
        calculadora.percent.setOnClickListener{adicionarExpressao("%",false)}

        calculadora.apagar.setOnClickListener{
            calculadora.expressao.text = ""
            calculadora.resultado.text = ""
        }

        calculadora.igual.setOnClickListener{

            try{
                val expressao = ExpressionBuilder(calculadora.expressao.text.toString()).build()
                val resultado = expressao.evaluate()
                val resultadoGrande = resultado.toLong()
                if(resultado == resultadoGrande.toDouble())
                    calculadora.resultado.text = resultadoGrande.toString()
                else
                    calculadora.resultado.text = resultado.toString()
            }catch (e:Exception){
                Log.d("Exception","Erro: " + e.message)
            }
        }
    }


    fun adicionarExpressao(string : String, apagar : Boolean) {

        if(calculadora.resultado.text.isNotEmpty())
            calculadora.expressao.text = ""


        if(apagar){
            calculadora.resultado.text = ""
            calculadora.expressao.append(string)
        }else
        {
            calculadora.expressao.append(calculadora.resultado.text)
            calculadora.expressao.append(string)
            calculadora.resultado.text = ""
        }
    }


}