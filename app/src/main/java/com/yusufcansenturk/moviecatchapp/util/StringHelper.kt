package com.yusufcansenturk.moviecatchapp.util

class StringHelper {
    fun getTrName(name: String): String {
        var tr_name = ""

        if (name == "Action"){
            tr_name = "Aksiyon"
        }else if (name == "Adventure"){
            tr_name = "Macera"
        }else if (name == "Animation"){
            tr_name = "Animasyon"
        }else if (name == "Comedy"){
            tr_name = "Komedi"
        }else if (name == "Crime"){
            tr_name = "Süç"
        }else if (name == "Documentary"){
            tr_name = "Belgesel"
        }else if (name == "Drama"){
            tr_name = "Drama"
        }else if (name == "Family"){
            tr_name = "Aile"
        }else if (name == "Fantasy"){
            tr_name = "Fantazi"
        }else if (name == "History"){
            tr_name = "History"
        }else if (name == "Horror"){
            tr_name = "Korku"
        }else if (name == "Music"){
            tr_name = "Müzik"
        }else if (name == "Mystery"){
            tr_name = "Gizem"
        }else if (name == "Romance"){
            tr_name = "Romantik"
        }else if (name == "Science Fiction"){
            tr_name = "Bilim Kurgu"
        }else if (name == "TV Movie"){
            tr_name = "TV Filim"
        }else if (name == "Thriller"){
            tr_name = "Gerilim"
        }else if (name == "War"){
            tr_name = "Savaş"
        }else if (name == "Western"){
            tr_name = "Batı"
        }

        return tr_name

    }
}