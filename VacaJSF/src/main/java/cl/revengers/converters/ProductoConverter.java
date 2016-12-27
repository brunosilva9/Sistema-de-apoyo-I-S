/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.revengers.converters;

import cl.revengers.backbeans.AplicationBean;
import cl.revengers.entities.Producto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author 
 */
@FacesConverter("productoConverter")
public class ProductoConverter implements Converter {

    private final static Logger logger = Logger.getLogger(ProductoConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                AplicationBean appBean = context.getApplication().evaluateExpressionGet(context, "#{aplicationBean}", AplicationBean.class);
                return appBean.getProductoFacade().find(Integer.parseInt(value.trim()));
            } catch (Exception e) {
                logger.error("Error grave en converter Producto.", e);
                throw new RuntimeException(e);
            } finally {
                //something
            }
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            if (value != null) {
                return String.valueOf(((Producto) value).getIdProducto());
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("Error grave en converter Producto.", e);
            throw new RuntimeException(e);
        } finally {

        }
    }
}
