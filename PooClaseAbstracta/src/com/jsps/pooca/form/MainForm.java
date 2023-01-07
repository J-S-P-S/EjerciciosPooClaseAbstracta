package com.jsps.pooca.form;

import com.jsps.pooca.form.elementos.*;
import com.jsps.pooca.form.elementos.select.Opcion;
import com.jsps.pooca.form.validador.*;

import java.util.Arrays;
import java.util.List;

public class MainForm {

    public static void main(String[] args) {

        InputForm username = new InputForm("username");
        username.addValidador(new RequeridoValidador());
        InputForm password = new InputForm("clave", "password");
        password.addValidador(new RequeridoValidador())
                .addValidador(new LargoValidador(6,12));
        InputForm email = new InputForm("email", "email");
        email.addValidador(new RequeridoValidador())
                .addValidador(new EmailValidador());
        InputForm edad = new InputForm("edad", "number");
        edad.addValidador(new NumeroValidador());

        TextAreaForm experencia = new TextAreaForm("exp", 5, 9);

        SelectForm lenguaje = new SelectForm("lenguaje");
        lenguaje.addValidador(new NoNuloValidador());

        lenguaje.addOpcion(new Opcion("1", "Java"))
                .addOpcion(new Opcion("2", "Python").setSelected())
                .addOpcion(new Opcion("3", "JavaScript"))
                .addOpcion(new Opcion("4", "TypeScript"))
                .addOpcion(new Opcion("5", "PHP"));

        ElementoForm saludar = new ElementoForm("Saludo") {
            @Override
            public String dibujarHtml() {
                return "<input disabled name='" + this.nombre + "' value=\"" + this.valor + "\">";
            }
        };

        saludar.setValor("Hola que tal, este campo está deshabilitado");
        username.setValor("Sebas");
        password.setValor("a1b2c3");
        email.setValor("sebas.jsps@correo.com");
        edad.setValor("27");
        experencia.setValor(".... más de 10 años de experiencia ....");

        List<ElementoForm> elementos = Arrays.asList(username,
                password,
                email,
                edad,
                experencia,
                lenguaje,
                saludar);

        for (ElementoForm e : elementos) {
            System.out.println(e.dibujarHtml());
            System.out.println("<br>");
        }

        for (ElementoForm e : elementos){
            if(!e.esValido()){
                System.err.println(e.getNombre() + ": " + e.getErrores());
            }
        }


        /*ElementoForm el = new ElementoForm(){ //Implementar una clase anónima, se implementa al vuelo para poder utilizar el objeto.
        //No es reutilizable, se crea una vez y se utiliza en la clase y ya después no se puede reutilizar en otras clases.
            @Override
            public String dibujarHtml() {
                return null;
            }
        };*/

    }
}