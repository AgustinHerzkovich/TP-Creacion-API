package com.example.javalin.presentacion;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class LogoutHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        String idSesion = context.header("idSesion"); // Obtengo el idSesion mediante el header de la solicitud

        if (idSesion == null || idSesion.isEmpty()) { // Devuelvo un código de error 400 si no hubo header idSesion
            context.status(400).result("Falta el header idSesion");
            return;
        }

        SesionManager sesionManager = SesionManager.get(); // Obtengo la instancia única de SesionManager

        if (sesionManager.eliminar(idSesion) != null) {
            context.status(200).result("Sesión cerrada exitosamente"); // Elimino la sesión si es que existía y devuelvo código 200
        } else {
            context.status(404).result("Sesión no encontrada"); // En caso de no existir la sesión devuelvo código 400
        }
    }
}
