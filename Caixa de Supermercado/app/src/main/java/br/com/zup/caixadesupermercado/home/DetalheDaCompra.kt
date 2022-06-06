package br.com.zup.caixadesupermercado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.zup.caixadesupermercado.databinding.ActivityDetalheDaCompraBinding
import br.com.zup.caixadesupermercado.home.MainActivity
import br.com.zup.caixadesupermercado.model.Produto

class DetalheDaCompraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheDaCompraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheDaCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.detalhe_da_compra)


        recuperarExibirCarrinho()

        binding.buttonRefazerCompra.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }
    private fun recuperarExibirCarrinho(){
        val produto = intent.getParcelableExtra<Produto>(PRODUTO)

        if(produto != null){
            val valorTotalCarrinho = calcularCompras(
                produto.getQuantidadeProduto(),
                produto.getValorProduto()
            )
            exibirValorTotal(valorTotalCarrinho)
        }
    }
    private fun calcularCompras(
        quantidadeProduto: Int,
        valorProduto: Double
    ): Double {
        return (quantidadeProduto * valorProduto)
    }

    private fun exibirValorTotal(valorTotal: Double){
        binding.tvTotalCarrinho.text = "O VALOR TOTAL É DE: R$ $valorTotal"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}