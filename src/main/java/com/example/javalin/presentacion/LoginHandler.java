package com.example.javalin.presentacion;

import com.example.javalin.modelo.Dueño;
import com.example.javalin.persistencia.RepositorioDueños;
import com.example.javalin.presentacion.dto.LoginRequest;
import com.example.javalin.presentacion.dto.LoginResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class LoginHandler implements Handler {

    private final RepositorioDueños repoDueños;

    public LoginHandler() {
        this.repoDueños = new RepositorioDueños();
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        //validamos user/pass y buscamos datos de ese usuario para agregar en la sesión

        LoginRequest loginRequest = context.bodyAsClass(LoginRequest.class);

        Dueño dueño = repoDueños.obtenerUsuario(loginRequest.getUsername(), loginRequest.getPassword()); // Obtengo el usuario particular y ya no un hardcode

        if (dueño == null) {
            context.status(400).result("Usuario no encontrado o contraseña incorrecta"); // Si no existe el usuario o la contraseña es incorrecta devuelvo 400
            return;
        }

        System.out.println("Login: " + loginRequest);
        System.out.println("Login: " + dueño);

        SesionManager sesionManager = SesionManager.get();
        String idSesion = sesionManager.crearSesion("dueño", dueño);

//        sesionManager.agregarAtributo(idSesion, "fechaInicio", new Date());
//        sesionManager.agregarAtributo(idSesion, "rol", repoRoles.getByUser(idUser));

        context.status(200).json(new LoginResponse(idSesion)); // Devuelvo 200 en caso de que el login haya sido correcto

    }

}
