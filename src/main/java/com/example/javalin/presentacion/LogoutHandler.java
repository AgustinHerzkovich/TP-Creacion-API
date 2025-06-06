package com.example.javalin.presentacion;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class LogoutHandler implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        String idSesion = context.header("idSesion");

        if (idSesion == null || idSesion.isEmpty()) {
            context.status(400).result("Falta el header idSesion");
            return;
        }

        SesionManager sesionManager = SesionManager.get();

        if (sesionManager.eliminar(idSesion) != null) {
            context.status(200).result("Sesión cerrada exitosamente");
        } else {
            context.status(404).result("Sesión no encontrada");
        }
    }
}
