package com.example.javalin.persistencia;

import com.example.javalin.modelo.Dueño;
import com.example.javalin.modelo.Mascota;
import com.example.javalin.modelo.Raza;

import java.util.List;

import static java.util.Arrays.asList;

public class RepositorioDueños {

    public List<Dueño> obtenerTodos() {
        return asList(obtenerJose(), obtenerLuna());
    }

    public Dueño obtenerJose() {
        Dueño jose = new Dueño();
        jose.setNombre("Jose");
        jose.setId(1);
        jose.setPassword("******");

        Mascota felix = new Mascota();
        felix.setNombre("Felix");
        felix.setRaza(Raza.GATO);
        Mascota bob = new Mascota();
        bob.setNombre("Bob");
        bob.setRaza(Raza.GATO);

        jose.setMascotas(asList(felix, bob));
        return jose;
    }

    public Dueño obtenerLuna() {
        Dueño jose = new Dueño();
        jose.setNombre("Luna");
        jose.setId(2);
        jose.setPassword("&&&&&&&");

        Mascota mimi = new Mascota();
        mimi.setNombre("Mimi");
        mimi.setRaza(Raza.PERRO);

        jose.setMascotas(asList(mimi));
        return jose;
    }

    public Dueño obtenerUsuario(String username, String password) { // Obtengo el usuario en particular en base a su username y password
        return obtenerTodos().stream().filter(usuario -> usuario.getNombre().equals(username) && usuario.getPassword().equals(password)).findFirst().orElse(null);
    }
}
