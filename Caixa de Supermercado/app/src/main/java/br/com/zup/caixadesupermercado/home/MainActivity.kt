package br.com.zup.caixadesupermercado.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.zup.caixadesupermercado.*
import br.com.zup.caixadesupermercado.databinding.ActivityMainBinding
import br.com.zup.caixadesupermercado.model.Produto

class MainActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding
        private lateinit var nomeProduto: String
        private lateinit var quantidadeProduto: String
        private lateinit var valorUnitarioProduto: String

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.buttonCalcular.setOnClickListener {
                enviarDadosCompra()
            }
        }

        private fun enviarDadosCompra() {
            recuperarDadosCarrinho()
            if (!verificarCamposEdicao()) {
                val produto = Produto(
                    nomeProduto,
                    quantidadeProduto.toInt(),
                    valorUnitarioProduto.toDouble()
                )

                val intent = Intent(this, DetalheDaCompraActivity::class.java).apply {
                    putExtra(PRODUTO, produto)
                }
                startActivity(intent)
                limparCarrinho()
            }
        }
        private fun recuperarDadosCarrinho(){
            this.nomeProduto = binding.etNomeProduto.text.toString()
            this.quantidadeProduto = binding.etQuantidadeProduto.text.toString()
            this.valorUnitarioProduto = binding.etValorProduto.text.toString()
        }

        private fun verificarCamposEdicao(): Boolean {
            return if (nomeProduto.isEmpty() || quantidadeProduto.isEmpty() || valorUnitarioProduto.isEmpty()) {
                Toast.makeText(this, MSG_CAMPO_OBRIGATORIO, Toast.LENGTH_LONG).show()
                true
            } else {
                false
            }
        }

        private fun limparCarrinho() {
            binding.etNomeProduto.text.clear()
            binding.etQuantidadeProduto.text.clear()
            binding.etValorProduto.text.clear()
        }
}