package com.jsps.pooca.form.elementos;

import com.jsps.pooca.form.validador.LargoValidador;
import com.jsps.pooca.form.validador.Validador;
import com.jsps.pooca.form.validador.mensaje.MensajeFormateable;

import java.util.ArrayList;
import java.util.List;

abstract public class ElementoForm { //Una clase abstracta evita que se pueda Instanciar.
    //Y se pueda utilizar solamente para la herencia.

    protected String valor;
    protected String nombre;

    private List<Validador> validadores;
    private List<String> errores;

    public ElementoForm() {
        this.validadores = new ArrayList<>();
        this.errores = new ArrayList<>();
    }

    public ElementoForm(String nombre) {
        this();
        this.nombre = nombre;
    }

    public ElementoForm addValidador(Validador validador) {
        this.validadores.add(validador);
        return this;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esValido() {
        for (Validador v : this.validadores) {
            if (!v.esValido(this.valor)) {
                if (v instanceof MensajeFormateable) {
                    this.errores.add(((MensajeFormateable) v).getMensajeFormateado(this.nombre));
                } else {
                    this.errores.add(String.format(v.getMensaje(), nombre));
                }
            }
        }

        return (this.errores.isEmpty());
    }

    abstract public String dibujarHtml();


}