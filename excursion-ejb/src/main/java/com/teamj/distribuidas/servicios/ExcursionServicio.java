/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.ArticuloDAO;
import com.teamj.distribuidas.dao.ExcursionArticuloDAO;
import com.teamj.distribuidas.dao.ExcursionDAO;
import com.teamj.distribuidas.dao.MochilaDAO;
import com.teamj.distribuidas.dao.UsuarioExcursionDAO;
import com.teamj.distribuidas.exception.ValidationException;
import com.teamj.distribuidas.model.Articulo;
import com.teamj.distribuidas.model.Excursion;
import com.teamj.distribuidas.model.ExcursionArticulo;
import com.teamj.distribuidas.model.ExcursionArticuloPK;
import com.teamj.distribuidas.model.Mochila;
import com.teamj.distribuidas.model.Usuario;
import com.teamj.distribuidas.model.UsuarioExcursion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Gaming
 */
@LocalBean
@Stateless
public class ExcursionServicio {

    @EJB
    private ExcursionDAO excursionDAO;

    @EJB
    private MochilaDAO mochilaDAO;

    @EJB
    private UsuarioExcursionDAO usuarioExcursionDAO;
    @EJB
    private ArticuloDAO articuloDAO;
    @EJB
    private ExcursionArticuloDAO excursionArticuloDAO;

    public List<Excursion> obtenerTodas() {

        return excursionDAO.findAll();
    }

    public void insertar(Excursion e, Usuario u) throws ValidationException {
        Mochila m = new Mochila();
        m.setNombre("Creador");
        try {
            List<Mochila> tempMochilas = this.mochilaDAO.find(m);
            if (tempMochilas != null && tempMochilas.size() == 1) {
                this.excursionDAO.insert(e);
                UsuarioExcursion usuarioExcursion = new UsuarioExcursion();
                usuarioExcursion.setMochila(tempMochilas.get(0));
                usuarioExcursion.setUsuario(u);
                usuarioExcursion.setExcursion(e);
                this.usuarioExcursionDAO.insert(usuarioExcursion);
                Articulo articulo = new Articulo();
                articulo.setNombre("Derecho a excursión " + e.getNombre());
                if (articulo.getNombre().length() >= 32) {
                    articulo.setNombre(articulo.getNombre().substring(0, 31));
                }
                articulo.setPrecio(e.getCosto());
                articulo.setStock(e.getMaxAsistentes());
                articulo.setDescripcion(String.valueOf(e.getId()));
                this.articuloDAO.insert(articulo);
                ExcursionArticulo ea = new ExcursionArticulo();
                ExcursionArticuloPK eapk = new ExcursionArticuloPK();
                eapk.setIdArticulo(articulo.getId());
                eapk.setIdExcursion(e.getId());
                ea.setExcursionArticuloPK(eapk);
                ea.setCantidad(1);
                this.excursionArticuloDAO.insert(ea);

            } else {

                throw new ValidationException("Error al crear excursion, hay mas de un usuario especificado");
            }
            //this.mochilaDAO.flush();
        } catch (Exception ex) {
            throw new ValidationException(ex, "Error al crear Excursión");
        }
    }

    public void actualizar(Excursion e) throws ValidationException {
        try {
            this.excursionDAO.update(e);
            Articulo articulo = new Articulo();
            articulo.setDescripcion(String.valueOf(e.getId()));
            List<Articulo> articulos = this.articuloDAO.find(articulo);
            if (articulos != null && articulos.size() == 1) {
                articulos.get(0).setNombre("Derecho a excursión " + e.getNombre());
                articulos.get(0).setPrecio(e.getCosto());
                articulos.get(0).setStock(e.getMaxAsistentes());
                this.articuloDAO.update(articulos.get(0));
            } else {
                throw new ValidationException("Error al actualizar Excursión, existe mas de un articulo de derecho");
            }
            //this.mochilaDAO.flush();
        } catch (Exception ex) {
            throw new ValidationException(ex, "Error al actualizar Excursión");
        }
    }

    public void eliminar(Integer id) throws ValidationException {
        Excursion temp = this.excursionDAO.findById(id, false);

        UsuarioExcursion usuarioExcursion = new UsuarioExcursion();
        usuarioExcursion.setExcursion(temp);
        List<UsuarioExcursion> usuariosAsociados = this.usuarioExcursionDAO.find(usuarioExcursion);
        if (usuariosAsociados != null && !usuariosAsociados.isEmpty()) {
            for (UsuarioExcursion ue : usuariosAsociados) {
                this.usuarioExcursionDAO.remove(ue);
            }
        }

        ExcursionArticulo excursionArticulo = new ExcursionArticulo();
        excursionArticulo.setExcursion(temp);
        List<ExcursionArticulo> articulosAsociados = this.excursionArticuloDAO.find(excursionArticulo);
        if (articulosAsociados != null && !articulosAsociados.isEmpty()) {
            for (ExcursionArticulo ea : articulosAsociados) {
                this.excursionArticuloDAO.remove(ea);
            }
        }

        if (temp != null) {
            this.excursionDAO.remove(temp);
        } else {
            throw new ValidationException("Error al eliminar Excursión, no se ha encontrado la excursion");
        }
        Articulo articulo = new Articulo();
        articulo.setDescripcion(String.valueOf(id));
        List<Articulo> articulos = this.articuloDAO.find(articulo);
        if (articulos != null && articulos.size() == 1) {
            this.articuloDAO.remove(articulos.get(0));
        } else {
            throw new ValidationException("Error al eliminar Excursión, existe mas de un articulo de derecho o no se encuentra");
        }
    }

    public void participar(Excursion e, Usuario u, Mochila m) throws ValidationException {
        try {
            this.excursionDAO.insert(e);
            //this.mochilaDAO.flush();
        } catch (Exception ex) {
            throw new ValidationException(ex, "Error al crear Excursión");
        }
    }

    public Articulo obtenerArticuloDerecho(Excursion e) throws ValidationException {
        try {

            Articulo articulo = new Articulo();
            articulo.setDescripcion(String.valueOf(e.getId()));
            List<Articulo> articulos = this.articuloDAO.find(articulo);
            if (articulos != null && articulos.size() == 1) {
                return articulos.get(0);
            } else {
                throw new ValidationException("Error al actualizar Excursión, existe mas de un articulo de derecho, o no se ha encontrado");
            }
            //this.mochilaDAO.flush();
        } catch (Exception ex) {
            throw new ValidationException(ex, "Error al actualizar Excursión");
        }
    }

    public Excursion obtenerPorId(Integer id) {
        return this.excursionDAO.findById(id, false);
    }
}
