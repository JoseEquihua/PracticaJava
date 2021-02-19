package interfaces;

import dtos.DtoUsuario;
import java.util.List;


/**
 *
 * @author PC
 */
public interface IUsuario {
    
    public List<DtoUsuario> read(String correo) throws Exception;
    public List<DtoUsuario> read(String correo, String password) throws Exception;
    public boolean create(DtoUsuario user) throws Exception;
}
