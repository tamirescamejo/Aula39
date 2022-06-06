package br.com.zup.caixadesupermercado.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Produto(
    private var nomeProduto: String,
    private var quantidadeProduto: Int,
    private var valorProduto: Double
): Parcelable {

    fun getNomeProduto() = this.nomeProduto
    fun getQuantidadeProduto() = this.quantidadeProduto
    fun getValorProduto() = this.valorProduto

}