/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import java.io.Serializable;

/**
 *
 * @author yangd
 */
public class userBean extends Object implements Serializable {
    
    private String name;

    public userBean() {
        name = new String();
    }

    public String getName() {return name;}
    public void setName(String value) {name = value;}
}
