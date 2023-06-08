package com.example.flexfitness;

public class ClaseEjercicio {
    String titulo, desc, video;

    public ClaseEjercicio(String titulo, String desc, String video) {
        this.titulo = titulo;

        if(desc != null){
            this.desc = desc;
        }else{
            this.desc = "¡Haz 4 sets de 15 repeticiones con el peso que manejes!";
        }

        this.video = video;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getDesc() {
        return desc;
    }

    public String getVideo() {
        return video;
    }
}//claseEjercicio
