/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.dao;

import com.teamj.distribuidas.model.Itinerario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dennys
 */
@Stateless
public class ItinerarioDAO extends AbstracDAO<Itinerario> {
    
    public ItinerarioDAO() {
        super(Itinerario.class);
    }
    
}
