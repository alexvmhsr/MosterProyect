/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import com.teamj.distribuidas.dao.ArticuloDAO;
import com.teamj.distribuidas.dao.DetalleDAO;
import com.teamj.distribuidas.dao.FacturaDAO;
import com.teamj.distribuidas.dao.UsuarioExcursionDAO;
import com.teamj.distribuidas.exception.ValidationException;
import com.teamj.distribuidas.model.Detalle;
import com.teamj.distribuidas.model.Factura;
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
public class FacturaServicio {

    @EJB
    private FacturaDAO facturaDAO;

    @EJB
    private DetalleDAO detalleDAO;
    @EJB
    private ArticuloDAO articuloDAO;

    @EJB
    private UsuarioExcursionDAO usuarioExcursionDAO;

    public List<Factura> obtenerTodas() {

        return facturaDAO.findAll();
    }

    public void insertar(Factura factura, List<Detalle> detalles) throws ValidationException {

        try {
            this.facturaDAO.insert(factura);

            for (Detalle d : detalles) {
                d.setFactura(factura);
                this.detalleDAO.insert(d);
                d.getArticulo().setStock(d.getArticulo().getStock() - d.getCantidad());
                this.articuloDAO.update(d.getArticulo());

            }

            //this.mochilaDAO.flush();
        } catch (Exception ex) {
            throw new ValidationException(ex, "Error al crear la factura");
        }
    }

    public void actualizar(Factura m) throws ValidationException {
        try {
            this.facturaDAO.update(m);
            //this.mochilaDAO.flush();
        } catch (Exception e) {
            throw new ValidationException(e, "Error al crear la factura");
        }
    }

    public void eliminar(Integer id) {
        Factura temp = this.facturaDAO.findById(id, false);
        if (temp != null) {
            this.facturaDAO.remove(temp);
        }
    }

    public Factura encontrarMochilaFactura(Integer user_id) {
        Factura f = new Factura();

        f.setDocReceptor(user_id.toString());
        List<Factura> temp = this.facturaDAO.find(f);
        if (temp != null && temp.size() == 1) {
            return temp.get(0);
        }
        return null;
    }

    public void insertarDetallesMochila(List<Detalle> detalles) throws ValidationException {

        try {

            for (Detalle d : detalles) {

                this.detalleDAO.insert(d);
                d.getArticulo().setStock(d.getArticulo().getStock() - d.getCantidad());
                this.articuloDAO.update(d.getArticulo());
            }

            //this.mochilaDAO.flush();
        } catch (Exception ex) {
            throw new ValidationException(ex, "Error al crear la factura");
        }
    }
//TODO

    public List<Detalle> obtenerDetallesMochilaCompra(Integer user_id) {
        Factura f = new Factura();
        f.setDocEmisor(user_id.toString());

        Detalle d = new Detalle();
        d.setFactura(f);
        return this.detalleDAO.find(d);
    }

//    public boolean guardarMochilaFactura(Integer user_id) throws ValidationException {
//        Factura f = new Factura();
//        f.setDocEmisor("0604133546001");
//        f.setTipoDocEmisor("04");
//        f.setClaveAcceso("xx");
//        f.setFechaEmision(new Date());
//        f.setDireccionEmisor("Quito, Sangolqui");
//        f.setTipoDocReceptor("04");
//        f.setTotal(BigDecimal.ZERO);
//        f.setSubtotal(BigDecimal.ZERO);
//        f.setDocReceptor(user_id.toString());
//        f.setRazonSocial("Excursiones S.A");
//        f.setSecuencial("000000000000000");
//        try {
//            this.facturaDAO.insert(f);
//            return true;
//        } catch (Exception ex) {
//            throw new ValidationException(ex, "Error al crear la factura mochila");
//
//        }
//    }
}
