package br.com.gcbrandao.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

        fun notificar(
            autor: String
        ) {
            val message = SimpleMailMessage()
            message.setSubject("[Form] Resposta Recebida")
            message.setText("Seu topico foi respondido, va la conferir!!")
            message.setTo(autor)

            javaMailSender.send(message)
        }

}