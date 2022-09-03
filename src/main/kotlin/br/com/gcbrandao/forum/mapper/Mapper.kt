package br.com.gcbrandao.forum.mapper

interface Mapper<T, U> {

    fun map(t: T): U

}
