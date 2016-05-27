/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arqui.beans;

import java.io.Serializable;


/**
 *
 * @author PabloA
 */

public class CrudBean implements Serializable{

    /**
     * Creates a new instance of CrudBean
     */
    private boolean creating;
    private boolean modifying;

    public boolean isCreating() {
        return creating;
    }

    public void setCreating(boolean creating) {
        this.creating = creating;
    }

    public boolean isModifying() {
        return modifying;
    }

    public void setModifying(boolean modifying) {
        this.modifying = modifying;
    }

    public void beginCreate() {
        this.creating = true;
        this.modifying=false;
    }

    public void beginModify() {
        this.modifying = true;
        this.creating=false;
    }

    

    public void reset() {
        this.modifying = false;
        this.creating = false;
    }
}
