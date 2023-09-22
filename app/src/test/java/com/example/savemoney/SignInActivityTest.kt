package com.example.savemoney

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SignInActivityTest{
    @Test
    fun empty_email(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
        "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun valid_input(){
        val result = RegistrationUtil.validateRegistrationInput(
            "examp@gmail.com",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }
    @Test
    fun email_exists(){
        val result = RegistrationUtil.validateRegistrationInput(
            "peter@gmail.com",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun pass_empty(){
        val result = RegistrationUtil.validateRegistrationInput(
            "ex@gamil.com",
            "",
            ""
        )
        assertThat(result).isFalse()
    }
    @Test
    fun inc_repeated(){
        val result = RegistrationUtil.validateRegistrationInput(
            "ex@gmail.com",
            "123",
            "1234"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun pass_less_digit(){
        val result = RegistrationUtil.validateRegistrationInput(
            "ex@gmail.com",
            "abc1",
            "abc1"
        )
        assertThat(result).isFalse()
    }

}